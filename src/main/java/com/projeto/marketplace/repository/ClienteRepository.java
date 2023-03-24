package com.projeto.marketplace.repository;

import com.projeto.marketplace.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("" +
            "SELECT CASE WHEN COUNT(c) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Cliente c " +
            "WHERE c.email = ?1"
    )
    Boolean selectExistsEmail(String email);
}
