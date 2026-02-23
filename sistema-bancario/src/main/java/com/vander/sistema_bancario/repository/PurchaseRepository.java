package com.vander.sistema_bancario.repository;

import com.vander.sistema_bancario.entity.PurchaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<PurchaseEntity, Long> {
}
