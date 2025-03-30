package com.trujillo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trujillo.demo.model.Persona;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, String> {
}
