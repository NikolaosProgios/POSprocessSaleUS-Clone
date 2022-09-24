
package Controller;
import Model.*;

import java.util.GregorianCalendar;

// Είναι ο Ελεγκτήε της US
public class Register {
    private Store store;
    private Sale currentSale;
    private boolean occupied = false;

    public Register(Store store) {
        this.store = store;
    }

    public void makeNewSale(Cashier cashier){
        currentSale = new Sale(cashier);
        currentSale.setDate(GregorianCalendar.getInstance());
    }

    public void enterItem(String itemID, int quantity){
        Item p1 = store.getProductCatalog().findProduct(itemID);
        SalesLineItem s1 = new SalesLineItem(p1,quantity);
        currentSale.add(s1);
    }

    public void makePayment(double amount){
        //((Sale)t).makePayment(amount);
        currentSale.makePayment(amount);
    }

     /**
     * Ολοκληρώνει την τρέχουσα συναλλαγή
     */
    public Receipt endSale(){
        currentSale.becomeComplete();
        if(currentSale.isComplete()){
            store.addTransaction(currentSale);
            return new Receipt(currentSale);
        }
        else
            return null;
    }

     /**
     * Ξεκινά την δημιουργία μιας νέας επιστροφής
     */
    public void makeNewReturn(){
        //currentSale = new Return();
        //currentSale.setDate(GregorianCalendar.getInstance());
    }

     /**
     * Επιστρέφει τον κατάλογο με τα διαθέσιμα προϊόντα
     */
    public ProductCatalog getProductCatalog() {
        return store.getProductCatalog();
    }

     /**
     * Επιστρέφει την πώληση με κωδικό id
     */
    public Sale retreiveSale(int id) {
        return store.retreiveSale(id);
    }

    /**
     * Μέθοδος για να απελευθερώνεται το ταμείο.
     */
    public void release(){
        this.occupied = false;
    }
    
    public void setOccupied() {
        this.occupied = true;
    }
    
    public boolean isOccupied() {
        return occupied;
    }
    
    

     /**
     * Προσθέτει μια γραμμή πώλησης στην τρέχουσα πώληση
     */
    public void enterItem(ReturnLineItem returnLineItem) {
        currentSale.add(returnLineItem);
    }

     /**
     * Δημιουργεί μια γραμμή πώλησης και την προσθέτει στην τρέχουσα πώληση
     */
    public boolean enterItem(Item p1,int quantity) {
        SalesLineItem s1 = createSalesLine(p1,quantity);
        if (s1!=null){
          currentSale.add(s1);
          return true;
        }
        else
            return false;
    }

    /**
     * Βοηθητική μέθοδος για την δημιουργία μια γραμμή πώλησης
     * Αν δεν ικανοποιείται το κριτήριο της διαθέσιμης ποσότητας επιστρέφεται null
     */
    private SalesLineItem createSalesLine(Item p,int quantity){
        if(quantity<=0 || quantity>p.getQuantity())
                return null;
        else
            return new SalesLineItem(p,quantity);
    }

    /**
     *
     * @return Το κατάστημα που ανήκει το ταμείο.
     */
    public Store getBelongingStore() {
        return store;
    }

}
