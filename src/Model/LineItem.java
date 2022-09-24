/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

/**
 *
 * @author alefrag
 *
 * Αφηρημένη κλάση που αναπαριστά μια εγγραφή σε μία συναλλαγή
 *
 */
public abstract class LineItem {

    /**
     * Το προϊόν που συμμετείχε στην συναλλαγή
     */
    protected Item product;

    /**
     * Η ποσότητα του προϊόντος
     */
    protected int quantity;

    public LineItem(Item product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    /**
     *
     * @return Επιστρέφει το κόστος της εγγραφής
     */
    public double getSubtotal(){
        return product.getPrice()*quantity;
    }

    /**
     * Get the value of product
     *
     * @return the value of product
     */
    public Item getProduct() {
        return product;
    }

    /**
     * Set the value of product
     *
     * @param product new value of product
     */
    public void setProduct(Item product) {
        this.product = product;
    }

    /**
     * Get the value of quantity
     *
     * @return the value of quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Set the value of quantity
     *
     * @param quantity new value of quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "\t"+quantity +"x "+ product.toShortString() + "\t" +
                getSubtotal() + " Euro\n";
    }

}
