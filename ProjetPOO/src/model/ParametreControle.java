/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;
import java.util.*;
import util.*;
/**
 *
 * @author Admin
 */
public class ParametreControle {

    private String nom;                  
    private CategorieControle categorie; 
    private List<ElementControle> defauts;

    public ParametreControle(String nom, CategorieControle categorie) {
        this.nom = nom;
        this.categorie = categorie;
        this.defauts = new ArrayList<>();
    }

    public void ajouterDefaut(ElementControle defaut) {
        defauts.add(defaut);
    }

    public List<ElementControle> getDefauts() {
        return defauts;
    }
}

