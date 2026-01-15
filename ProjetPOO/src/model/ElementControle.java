/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import util.CategorieControle;
import util.NiveauDefaillance;

/**
 *
 * @author Admin
 */


public class ElementControle {

    private String description;          
    public NiveauDefaillance niveau;
    private ParametreControle parametre;

    public ElementControle(String description, NiveauDefaillance niveau) {
        this.description = description;
        this.niveau = niveau;
    }

    public NiveauDefaillance getNiveau() {
        return niveau;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ParametreControle getParametre() {
        return parametre;
    }

    public void setParametre(ParametreControle parametre) {
        this.parametre = parametre;
    }
    
    
}

