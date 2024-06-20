package com.example.immob.repository;

import com.example.immob.model.Propriete;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProprieteRepository extends JpaRepository<Propriete, Long> {
    List<Propriete> findByAdresseContainingAndPrixBetween(String adresse, Double minPrix, Double maxPrix);
}
