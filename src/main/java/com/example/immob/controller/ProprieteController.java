package com.example.immob.controller;

import com.example.immob.model.Propriete;
import com.example.immob.service.ProprieteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/proprietes")
public class ProprieteController {

    @Autowired
    private ProprieteService proprieteService;

    @PostMapping
    public ResponseEntity<Propriete> creerPropriete(@RequestBody Propriete propriete) {
        Propriete nouvellePropriete = proprieteService.creerPropriete(propriete);
        return ResponseEntity.ok(nouvellePropriete);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Propriete> obtenirPropriete(@PathVariable Long id) {
        return proprieteService.obtenirPropriete(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Propriete> mettreAJourPropriete(@PathVariable Long id,
            @RequestBody Propriete proprieteDetails) {
        Propriete propriete = proprieteService.mettreAJourPropriete(id, proprieteDetails);
        if (propriete != null) {
            return ResponseEntity.ok(propriete);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerPropriete(@PathVariable Long id) {
        proprieteService.supprimerPropriete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/recherche")
    public ResponseEntity<List<Propriete>> rechercherProprietes(
            @RequestParam String adresse,
            @RequestParam Double minPrix,
            @RequestParam Double maxPrix) {
        List<Propriete> proprietes = proprieteService.rechercherProprietes(adresse, minPrix, maxPrix);
        return ResponseEntity.ok(proprietes);
    }
}
