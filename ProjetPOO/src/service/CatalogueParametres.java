/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;
import java.util.*;
import model.*;
import util.*;

/**
 *
 * @author Admin
 */
public class CatalogueParametres {

    public static List<ParametreControle> creerParametres() {

        List<ParametreControle> parametres = new ArrayList<>();

        // DOCUMENTS ADMINISTRATIFS
        ParametreControle plaque = new ParametreControle(
            "Plaque d'immatriculation",
            CategorieControle.DOCUMENTS_ADMINISTRATIFS
        );

        plaque.ajouterDefaut(new ElementControle(
            "Manquante",
            NiveauDefaillance.CRITIQUE
        ));

        plaque.ajouterDefaut(new ElementControle(
            "Illisible",
            NiveauDefaillance.MAJEUR
        ));

        parametres.add(plaque);

        // ECLAIRAGE
        ParametreControle feux = new ParametreControle(
            "Feux de croisement",
            CategorieControle.ECLAIRAGE
        );

        feux.ajouterDefaut(new ElementControle(
            "Non fonctionnel",
            NiveauDefaillance.MAJEUR
        ));

        parametres.add(feux);

        return parametres;
    }
}

