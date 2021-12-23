package com.example.kbeproject.product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface P_Repository extends JpaRepository<Product, Long> {}
