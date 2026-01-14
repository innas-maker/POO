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

        
          plaque.ajouterDefaut(new ElementControle(
            "Conforme",
            NiveauDefaillance.MAJEUR
        ));

        parametres.add(plaque);
        
         ParametreControle certif = new ParametreControle(
            "Certificat d'immatriculation",
            CategorieControle.DOCUMENTS_ADMINISTRATIFS
        );

        certif.ajouterDefaut(new ElementControle(
            "Manquante",
            NiveauDefaillance.CRITIQUE
        ));

        certif.ajouterDefaut(new ElementControle(
            "falsifié",
            NiveauDefaillance.CRITIQUE
        ));

        
        parametres.add(certif);
        
        
          ParametreControle assu = new ParametreControle(
            "Assurance",
            CategorieControle.DOCUMENTS_ADMINISTRATIFS
        );

        assu.ajouterDefaut(new ElementControle(
            "Manquante",
            NiveauDefaillance.MINEUR
        ));
        parametres.add(assu);

        
        
        
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
        
        ParametreControle feuxR = new ParametreControle(
            "Feux de route",
            CategorieControle.ECLAIRAGE
        );

        feuxR.ajouterDefaut(new ElementControle(
            "Non fonctionnel",
            NiveauDefaillance.MAJEUR
        ));
        feuxR.ajouterDefaut(new ElementControle(
            "Non conforme",
            NiveauDefaillance.MINEUR
        ));
        feuxR.ajouterDefaut(new ElementControle(
            "intense",
            NiveauDefaillance.MINEUR
        ));

        parametres.add(feuxR);
        
        ParametreControle cligno = new ParametreControle(
            "Feux de route",
            CategorieControle.ECLAIRAGE
        );

        cligno.ajouterDefaut(new ElementControle(
            "Non fonctionnel",
            NiveauDefaillance.MAJEUR
        ));
        cligno.ajouterDefaut(new ElementControle(
            "couleur",
            NiveauDefaillance.MINEUR
        ));
        cligno.ajouterDefaut(new ElementControle(
            "fréquence",
            NiveauDefaillance.MINEUR
        ));

        parametres.add(cligno);
        
        
        
        //MOTEUR
        ParametreControle supp = new ParametreControle(
            "Support moteur",
            CategorieControle.MOTEUR
        );

        supp.ajouterDefaut(new ElementControle(
            "Fixation",
            NiveauDefaillance.CRITIQUE
        ));
        supp.ajouterDefaut(new ElementControle(
            "Etat stucturel",
            NiveauDefaillance.MINEUR
        ));

        supp.ajouterDefaut(new ElementControle(
            "jeu",
            NiveauDefaillance.MAJEUR
        ));


        parametres.add(supp);
        
        ParametreControle fuite = new ParametreControle(
            "Fuite",
            CategorieControle.MOTEUR
        );

        fuite.ajouterDefaut(new ElementControle(
            "Huile",
            NiveauDefaillance.MINEUR
        ));
        fuite.ajouterDefaut(new ElementControle(
            "Carburant",
            NiveauDefaillance.MINEUR
        ));
        fuite.ajouterDefaut(new ElementControle(
            "Liquide dangereux",
            NiveauDefaillance.MINEUR
        ));

        parametres.add(fuite);
        
        ParametreControle cour = new ParametreControle(
            "Courroies",
            CategorieControle.MOTEUR
        );

        cour.ajouterDefaut(new ElementControle(
            "usure",
            NiveauDefaillance.MINEUR
        ));
        cour.ajouterDefaut(new ElementControle(
            "alignement",
            NiveauDefaillance.MAJEUR
        ));
        cour.ajouterDefaut(new ElementControle(
            "risque rupture",
            NiveauDefaillance.CRITIQUE
        ));

        parametres.add(cour); 
       
        
        // FREINAGE
        ParametreControle plaqt = new ParametreControle(
            "Plaquettes",
            CategorieControle.FREINAGE
        );

        plaqt.ajouterDefaut(new ElementControle(
            "épaisseur",
            NiveauDefaillance.MINEUR
        ));
         plaqt.ajouterDefaut(new ElementControle(
            "usure",
            NiveauDefaillance.MAJEUR
        ));
          plaqt.ajouterDefaut(new ElementControle(
            "bruit",
            NiveauDefaillance.MINEUR
        ));

        parametres.add(plaqt);
        
        
        ParametreControle disc = new ParametreControle(
            "Disque",
            CategorieControle.FREINAGE
        );

        disc.ajouterDefaut(new ElementControle(
            "épaisseur",
            NiveauDefaillance.MINEUR
        ));
        disc.ajouterDefaut(new ElementControle(
            "voilage",
            NiveauDefaillance.MAJEUR
        ));
        disc.ajouterDefaut(new ElementControle(
            "fissure",
            NiveauDefaillance.CRITIQUE
        ));
               
        parametres.add(disc);
        
        
         ParametreControle liq = new ParametreControle(
            "Liquide de frein",
            CategorieControle.FREINAGE
        );

        liq.ajouterDefaut(new ElementControle(
            "niveau",
            NiveauDefaillance.CRITIQUE
        ));
        liq.ajouterDefaut(new ElementControle(
            "fuite",
            NiveauDefaillance.MINEUR
        ));
        liq.ajouterDefaut(new ElementControle(
            "qualité",
            NiveauDefaillance.MAJEUR
        ));
        
        parametres.add(liq);

        return parametres;
        
    }
}