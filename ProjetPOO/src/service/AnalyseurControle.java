/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

/**
 *
 * @author Admin
 */

import model.*;
import util.NiveauDefaillance;
import java.util.List;

public class AnalyseurControle implements IAnalyseControle {
     @Override
    public NiveauDefaillance genererVerdict(List<ElementControle> elements) {
        if (elements.stream().anyMatch(e -> e.niveau == NiveauDefaillance.CRITIQUE))
            return NiveauDefaillance.CRITIQUE;

        if (elements.stream().anyMatch(e -> e.niveau == NiveauDefaillance.MAJEUR))
            return NiveauDefaillance.MAJEUR;

        return NiveauDefaillance.MINEUR;
    }
}
