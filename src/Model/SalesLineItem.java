/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 *
 * @author alefrag
 * 
 * Αναπαριστά μία εγγραφή πώλησης ενός προϊόντος σε μία συναλλαγή πώλησης
 *
 */
public class SalesLineItem extends LineItem{
    public SalesLineItem(Item product, int quantity) {
        super(product, quantity);
    }
}
