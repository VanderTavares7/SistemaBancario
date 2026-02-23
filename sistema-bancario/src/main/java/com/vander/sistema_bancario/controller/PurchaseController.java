package com.vander.sistema_bancario.controller;

import com.vander.sistema_bancario.dto.InstallingProductDTO;
import com.vander.sistema_bancario.dto.PurchaseRequestDTO;
import com.vander.sistema_bancario.service.PurchaseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class PurchaseController {
    private final PurchaseService purchaseService;


    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }


    @PostMapping("/purchases")
    public ResponseEntity<?> buyProduct(@RequestBody PurchaseRequestDTO request) {
        return purchaseService.buyProduct(request);
    }

    @PostMapping("/installingProduct")
    public ResponseEntity<String> installingProduct(
            @RequestBody InstallingProductDTO dto) {

        String result = purchaseService.installingProduct(dto);

        return ResponseEntity.ok(result);
    }
}
