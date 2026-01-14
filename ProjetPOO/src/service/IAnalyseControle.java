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

public interface IAnalyseControle {
   boolean genererVerdict();
   float genererPourcentage(List<ElementControle> elements, NiveauDefaillance niveau);
   int CompterElements(List<ElementControle> elements);
    
}
