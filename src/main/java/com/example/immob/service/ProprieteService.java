package com.example.immob.service;

import com.example.immob.model.Propriete;
import com.example.immob.repository.ProprieteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProprieteService {

    @Autowired
    private ProprieteRepository proprieteRepository;

    public Propriete creerPropriete(Propriete propriete) {
        return proprieteRepository.save(propriete);
    }

    public Optional<Propriete> obtenirPropriete(Long id) {
        return proprieteRepository.findById(id);
    }

    public Propriete mettreAJourPropriete(Long id, Propriete proprieteDetails) {
        Optional<Propriete> prop = proprieteRepository.findById(id);
        if (prop.isPresent()) {
            Propriete propriete = prop.get();
            propriete.setAdresse(proprieteDetails.getAdresse());
            propriete.setPrix(proprieteDetails.getPrix());
            propriete.setDescription(proprieteDetails.getDescription());
            return proprieteRepository.save(propriete);
        }
        return null;
    }

    public void supprimerPropriete(Long id) {
        proprieteRepository.deleteById(id);
    }

    public List<Propriete> rechercherProprietes(String adresse, Double minPrix, Double maxPrix) {
        return proprieteRepository.findByAdresseContainingAndPrixBetween(adresse, minPrix, maxPrix);
    }
}
