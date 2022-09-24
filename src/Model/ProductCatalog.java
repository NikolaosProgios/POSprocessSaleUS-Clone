package Model;

import java.util.*;

/**
 *
 * @author alefrag
 *
 * Αναπαριστά τα διαθέσιμα προϊόντα ενός καταστήματος
 * παρέχοντας μεθόδους για την αναζήτηση και εκτύπωση τους
 *
 */
public class ProductCatalog {

    /**
     * Η εσωτερική δομή για την αποθήκευση των διαθέσιμων προϊόντων
     */
    private ArrayList<Item> prodCat;

    public ProductCatalog() {
        prodCat = new ArrayList<Item>();
    }

     /**
     * Μέθοδος για να προσθέσουμε ένα προϊόν
     */
    public void addProduct(Item prodSpec) {
        prodCat.add(prodSpec);
    }

    /**
     * Μέθοδος για την εκτύπωση όλων των προϊόντων
     */
    public void displayProducts() {
        Iterator itr = prodCat.iterator();
        while (itr.hasNext()) {
            Item ps = (Item) itr.next();
            System.out.println(ps);
        }
    }

    /**
     * Μέθοδος που επιστρέφει το προϊόν που αντιστοιχεί στον κωδικό itemId
     * Αν δεν βρεθεί προϊόν επιστρέφεται null.
     */
    public Item findProduct(String itemId) {
        Iterator itr = prodCat.iterator();
        while (itr.hasNext()) {
            Item ps = (Item) itr.next();
            if (ps.getltemlD().equals(itemId)) {
                return ps;
            }
        }
        return null;
    }

     /**
     * Μέθοδος που επιστρέφει τα διαθέσιμα προϊόντα
     */
    public ArrayList<Item> getProducts() {
        return prodCat;
    }
}
