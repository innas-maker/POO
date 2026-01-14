/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.*;
import service.*;
/**
 *
 * @author Admin
 */
public class ControleTechnique {
     public Vehicule vehicule;
     public List<ParametreControle> parametres;

    public ControleTechnique() {
        this.parametres = CatalogueParametres.creerParametres();
    }

    public List<ParametreControle> getParametres() {
        return parametres;
    }

    public Vehicule getVehicule() {
        return vehicule;
    }

    public void setVehicule(Vehicule vehicule) {
        this.vehicule = vehicule;
    }
    
    
}

