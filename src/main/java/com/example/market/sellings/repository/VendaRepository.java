package com.example.market.sellings.repository;

import com.example.market.sellings.model.Sale;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Sale, Long> {
}
