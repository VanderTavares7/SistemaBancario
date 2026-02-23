package com.vander.sistema_bancario.service;

import com.vander.sistema_bancario.domain.users.User;
import com.vander.sistema_bancario.dto.PurchaseRequestDTO;
import com.vander.sistema_bancario.entity.ProductEntity;
import com.vander.sistema_bancario.entity.PurchaseEntity;
import com.vander.sistema_bancario.repository.ProductRepository;
import com.vander.sistema_bancario.repository.PurchaseRepository;
import com.vander.sistema_bancario.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
@Transactional
public class PurchaseService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PurchaseRepository purchaseRepository;

    public ResponseEntity<?> buyProduct(PurchaseRequestDTO request) {

        //Search user
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found."));

        //Search product
        ProductEntity product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found."));

        //Check stock
        if (product.getProductQuantity() < request.getQuantity()) {
            return ResponseEntity.badRequest().body("Insufficient stock.");
        }

        //Calculate total
        BigDecimal total = product.getProductPrice()
                .multiply(BigDecimal.valueOf(request.getQuantity()));

        BigDecimal saldo = user.getBalance();
        BigDecimal credito = user.getCreditLimit();

        BigDecimal totalDisponivel = saldo.add(credito);

        //Check if you have enough money.
        if (totalDisponivel.compareTo(total) < 0) {
            return ResponseEntity.badRequest().body("Insufficient balance + credit");
        }

        //Discount balance first
        if (saldo.compareTo(total) >= 0) {
            user.setBalance(saldo.subtract(total));
        } else {
            BigDecimal restante = total.subtract(saldo);
            user.setBalance(BigDecimal.ZERO);
            user.setCreditLimit(credito.subtract(restante));
        }

        //Update stock
        product.setProductQuantity(product.getProductQuantity() - request.getQuantity());

        //Create purchase record
        PurchaseEntity purchase = new PurchaseEntity();
        purchase.setUser(user);
        purchase.setProduct(product);
        purchase.setQuantity(request.getQuantity());
        purchase.setTotalPrice(total);

        //Save all
        purchaseRepository.save(purchase);
        userRepository.save(user);
        productRepository.save(product);

        return ResponseEntity.ok("Purchase made successfully!");
    }
}