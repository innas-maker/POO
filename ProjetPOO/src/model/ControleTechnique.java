/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.*;
/**
 *
 * @author Admin
 */
public class ControleTechnique {
     private Vehicule vehicule;
    private List<ElementControle> elements = new ArrayList<>();

    public void ajouterElement(ElementControle e) {
        elements.add(e);
    }

    public List<ElementControle> getElements() {
        return elements;
    }
}
