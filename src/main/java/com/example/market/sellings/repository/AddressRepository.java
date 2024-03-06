package com.example.market.sellings.repository;

import com.example.market.sellings.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("select e from Cliente c join c.endereco e where c.id = :id")
    Address findByClienteId(Long id);
}
