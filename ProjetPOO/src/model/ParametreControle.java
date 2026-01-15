/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.*;
import service.CatalogueParametres;
import util.*;
/**
 *
 * @author Admin
 */
public class ParametreControle {

    public String nom;                  
    public CategorieControle categorie; 
    public List<ElementControle> defauts;

    public ParametreControle(String nom, CategorieControle categorie) {
        this.nom = nom;
        this.categorie = categorie;
        this.defauts = new ArrayList<>();
    }

  

    public void ajouterDefaut(ElementControle defaut) {
        defaut.setParametre(this);
        defauts.add(defaut);
    }

    public List<ElementControle> getDefauts() {
        return defauts;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public CategorieControle getCategorie() {
        return categorie;
    }

    public void setCategorie(CategorieControle categorie) {
        this.categorie = categorie;
    }
    
    
}

