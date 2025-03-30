package com.trujillo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trujillo.demo.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
}