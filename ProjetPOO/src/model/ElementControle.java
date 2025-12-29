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


public abstract class ElementControle {
    protected String nom;
    public NiveauDefaillance niveau;

    public abstract CategorieControle getCategorie();
}
