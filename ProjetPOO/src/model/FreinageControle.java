/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import service.*;
import java.util.List;


import util.CategorieControle;
import util.NiveauDefaillance;

/**
 *
 * @author Admin
 */
public abstract class FreinageControle implements IAnalyseControle{
    
     
    // Attributs de classe pour stocker les compteurs
// Attributs de classe pour stocker les compteurs
private int compteurCritique = 0;
private int compteurMajeur = 0;
private int compteurMineur = 0;

@Override
public int CompterElements(List<ElementControle> elements){
    // Réinitialiser les compteurs
    compteurCritique = 0;
    compteurMajeur = 0;
    compteurMineur = 0;
    
    if (elements == null || elements.isEmpty()) {
        return 0;
    }
    
    // Récupérer tous les paramètres du catalogue
    List<ParametreControle> tousParametres = CatalogueParametres.creerParametres();
    
    // Parcourir les éléments reçus
    for (ElementControle element : elements) {
        if (element != null) {
            // Vérifier si cet élément appartient à la catégorie MOTEUR
            boolean estMoteur = false;
            for (ParametreControle param : tousParametres) {
                if (CatalogueParametres.CategorieControle.FREINAGE == param.getCategorie()) {
                    for (ElementControle defaut : param.getDefauts()) {
                        if (defaut.getDescription().equals(element.getDescription())) {
                            estMoteur = true;
                            break;
                        }
                    }
                }
                if (estMoteur) break;
            }
            
            // Si l'élément est de catégorie MOTEUR, le compter
            if (estMoteur) {
                switch (element.getNiveau()) {
                    case CRITIQUE -> compteurCritique++;
                    case MAJEUR -> compteurMajeur++;
                    case MINEUR -> compteurMineur++;
                }
            }
        }
    }
    
    // Retourner le total
    return compteurCritique + compteurMajeur + compteurMineur;
}

public float genererPourcentage(List<ElementControle> elements){
    if (elements == null || elements.isEmpty()) {
        return 100.0f; // Pas de défaillance = 100%
    }
    
    // Utiliser la première méthode pour faire le comptage
    int total = CompterElements(elements);
    
    if (total == 0) {
        return 100.0f; // Aucune défaillance MOTEUR détectée
    }
    
    // Appliquer la formule : (3 - (0.2*mineur + 0.4*majeur + 0.6*critique)) * 100 / 3
    float score = 3 - (0.2f * compteurMineur + 0.4f * compteurMajeur + 0.6f * compteurCritique);
    float pourcentage = (score * 100) / 3;
    
    // S'assurer que le pourcentage reste entre 0 et 100
    if (pourcentage < 0) {
        pourcentage = 0;
    }
    if (pourcentage > 100) {
        pourcentage = 100;
    }
    
    return pourcentage;
}

@Override
public int genererVerdict(float pourcentage) {
    if (pourcentage == 100) {
        return 1;
    } else if (pourcentage >= 80 && pourcentage < 100) {
        return 2;
    } else {
        return 3;
    }
}

// Méthodes getter pour accéder aux compteurs
public int getCompteurCritique() {
    return compteurCritique;
}

public int getCompteurMajeur() {
    return compteurMajeur;
}

public int getCompteurMineur() {
    return compteurMineur;
}
  }
