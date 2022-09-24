package Model;

import Controller.Register;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;


public class Store {

    protected String name;
    // 
    private Item [] items;
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private ProductCatalog prodCat;
    /**
     * Μια δομή δεδομένων για την αποθήκευση των συναλλαγών.
     * Κλειδί σε κάθε εγγραφή αποτελεί ο κωδικός συναλλαγής
     */
    private HashMap<Integer,Transaction> transactions;
    /**
     * Τα διαθέσιμα ταμεία του καταστήματος
     */
    private Register register;
    /**
     * Μια κοινή μεταβλητή για όλες τις συναλλαγές του καταστήματος
     */
    private static int transactionId=1;
   
    public Store() {
        name = "MyShop";
        address= "My address";
        prodCat = new ProductCatalog();
        transactions = new HashMap<Integer, Transaction>();

        // Δημιουργεί ένα αρχικό κατάστημα
        makeInitialStore();
    }

    public static int getTransactionId() {
        return transactionId;
    }

    /**
     * Δημιουργεί ένα αρχικό κατάστημα και πραγματοποιεί
     * κάποιες αρχικές συναλλαγές.
     */
    private void makeInitialStore(){
        // Δημιουργούμε και καταχωρεί ένα ταμείο στο κατάστημα
        register = new Register(this);

        // Δημιουργεί και καταχωρεί στο κατάστημα 5 προϊόντα
        Item p1 = new Item("Κ1", "Σαμπουάν", 4.35,11.0);
        Item p2 = new Item("K2", "Σαπούνι", 1.28,8.0);
        Item p3 = new Item("A1", "Μαλακτικό Ρούχων", 8.72,12.0);
        Item p4 = new Item("A2", "Απορρυπαντικό Πλυντηρίου", 12.49,22.0);
        Item p5 = new Item("Τ1", "Στιγμιαίος Καφές", 2.92,17.0);
        prodCat.addProduct(p1);prodCat.addProduct(p2);prodCat.addProduct(p3);
        prodCat.addProduct(p4);prodCat.addProduct(p5);

        //Εμφάνισε τα προϊόντα
        System.out.println("Διαθέσιμα προϊόντα στο κατάστημα");
        prodCat.displayProducts();

        // Ανακτά ένα ταμείο
        Register r = getRegister();

        // Πραγματοποιεί μία πώληση

        // Αρχικοποιεί την πώληση
        r.makeNewSale();
        //Προσθέτει 2 Σαμπουάν και ένα Μαλακτικό Ρούχων
        r.enterItem(p1, 2);
        r.enterItem(p3, 1);
        //Πληρώνει
        r.makePayment(30.0);
        // Ολοκληρώνει την πώληση και παίρνει την απόδειξη
        Receipt r1 = r.endSale();
        // Πέρνει τον κωδικό συναλλαγής για μελλοντική χρήση
        int tr1_id = r1.getTransaction().getId();
        //Εκτυπώνει την απόδειξη
        System.out.println(r1);

        //Πραγματοποιούναι με παρόμοιο τρόπο άλλες δύο πωλήσεις

        r.makeNewSale();
        r.enterItem(p2, 3);
        r.enterItem(p4, 1);
        //Πληρώνει
        r.makePayment(50.0);
        Receipt r2 = r.endSale();
        System.out.println(r2);

        r.makeNewSale();
        r.enterItem(p5, 1);
        r.enterItem(p2, 2);
        //Πληρώνει
        r.makePayment(20.0);
        Receipt r3 = r.endSale();
        int tr3_id = r3.getTransaction().getId();
        System.out.println(r3);

        // Πραγματοποιεί μία επιστροφή

        // Ανακτά την σχετική εγγραφή από την πώληση δίνοντας τον κωδικό της πώλησης
        // και την περιγραφή του προϊόντος
        SalesLineItem l1 = (SalesLineItem) r.retreiveSale(tr1_id).getEntry(p1);
        // Αρχικοποιεί την επιστροφή
        r.makeNewReturn();
        // Προσθέτει μια εγγραφή επιστροφής ενός Σαμπουάν
        // με αιτιολογία ότι ήταν ανοιγμένο
        r.enterItem(new ReturnLineItem(l1,new FaultSpecification(5, "Ανοιγμένη συσκευασία"),1));
        // Ολοκληρώνει την πώληση και παίρνει την απόδειξη
        Receipt r4 = r.endSale();
        //Εκτυπώνει την απόδειξη
        System.out.println(r4);

         //Πραγματοποιούναι με παρόμοιο τρόπο άλλη μία επιστροφή
        SalesLineItem l2 = (SalesLineItem) r.retreiveSale(tr3_id).getEntry(p5);
        r.makeNewReturn();
        r.enterItem(new ReturnLineItem(l2,new FaultSpecification(1, "Ληγμένο προϊόν"),1));
        Receipt r5 = r.endSale();
        System.out.println(r5);

        //Απελευθερώνει το ταμείο
        r.release();
    }

    public ProductCatalog getProductCatalog(){
        return prodCat;
    }

    public void setProductCatalog(ProductCatalog prodCat){
        this.prodCat=prodCat;
    }

    /**
     * Προσθέτει ένα νέο προϊόν στο κατάστημα
     * @param prodSpec Το προϊόν που προστήθεται
     */
    public void addProduct(Item prodSpec) {
        prodCat.addProduct(prodSpec);
    }

     /**
     * Αναζητά ένα προϊόν με κωδικό itemId στο κατάστημα
     * και εκτυπώνει την περιγραφή του.
     *
     * @param itemId Ο κωδικός του προϊόντος
     */
    public void printProduct(String itemId) {
         Item ret =  prodCat.findProduct(itemId);
         if (ret!=null)
             System.out.println(ret);
         else
             System.out.println("Προϊόν με κωδικό "+ itemId +" δεν βρέθηκε");
    }

    /**
     * Προσθέτει μία συναλλαγή value στην δομή δεδομένων που τις αποθηκεύουμε
     * και αυξάνει τον κωδικό συναλλαγών κατά ένα.
     *
     * @param value Η συναλλαγή που επιθυμούμε να αποθηκεύσουμε
     * @return Τον κωδικό της συναλλαγής
     */
    public int addTransaction(Transaction value) {
        value.setId(transactionId);
        transactions.put(transactionId, value);
        transactionId++;
        updateProductQuantities(value);
        return transactionId-1;
    }

    /**
     * Αναζητά μία συναλλαγή με κωδικό code στο κατάστημα
     * και εκτυπώνει την περιγραφή του.
     * Αν δεν βρεθεί εκτυπώνει σχετικό μήνυμα
     *
     * @param code Ο κωδικός της συναλλαγής
     */
    public void printTransaction(int code) {
         Transaction ret =  transactions.get(new Integer(code));
         if (ret!=null)
             System.out.println(ret);
         else
             System.out.println("Συναλλαγή με κωδικό "+ code +" δεν βρέθηκε");
    }

     /**
     * Αναζητά μία συναλλαγή με κωδικό code στο κατάστημα
     * και την επιστρέφει.
     * Αν δεν βρεθεί επιστρέφει null
     *
     * @param code Ο κωδικός της συναλλαγής
     */
     public Transaction retreiveTransaction(int code) {
         return  transactions.get(new Integer(code));
    }

     /**
     * Αναζητά μία συναλλαγή με ημερομηνια date στο κατάστημα
     * και την επιστρέφει.
     * Αν δεν βρεθεί επιστρέφει null.
     *
     * @param date Η ημερομηνία της συναλλαγής
     */
    public Transaction retreiveTransaction(GregorianCalendar date) {
        Iterator<Entry<Integer, Transaction>> iter = transactions.entrySet().iterator();

        while(iter.hasNext()){
         Transaction ret =  iter.next().getValue();
         if (ret!=null && ret.getDate().equals(date))
             return ret;
        }
        return null;
    }

    /**
     * Αναζητά μία πώληση με κωδικό code στο κατάστημα
     * και την επιστρέφει.
     * Αν δεν βρεθεί ή βρεθεί συναλλαγή επιστροφής με αυτό τον
     * κωδικό επιστρέφει null
     *
     * @param code Ο κωδικός της συναλλαγής
     */
    public Sale retreiveSale(int code){
        Transaction ret =  transactions.get(new Integer(code));
        //Μπορεί ο κωδικός που δόθηκε να μην αντιστοιχεί σε πώληση
        if (ret!=null && !ret.getClass().equals(new Sale().getClass()))
            return null;
        else
            return (Sale)ret;
    }

    /**
     * Αναζητά μία επιστροφή με κωδικό code στο κατάστημα
     * και την επιστρέφει.
     * Αν δεν βρεθεί ή βρεθεί συναλλαγή πώλησης με αυτό τον
     * κωδικό επιστρέφει null
     *
     * @param code Ο κωδικός της συναλλαγής
     */
    public Return retreiveReturn(int code){
        Transaction ret =  transactions.get(new Integer(code));
        //Μπορεί ο κωδικός που δώθηκε να μην αντιστοιχεί σε επιστροφή
        if (ret!=null && !ret.getClass().equals(new Return().getClass()))
            return null;
        else
            return (Return) ret;
    }

    /**
     * Ενημερώνει τα διαθέσιμα προϊόντα στο κατάστημα
     * βάση των εγγραφών που έχουν πραγματοποιηθεί σε μία συναλλαγή
     *
     * @param t Η συναλλαγή
     */
    private void updateProductQuantities(Transaction t) {
        ArrayList<LineItem> lst = t.getEntries();
        for (int i = 0; i < lst.size(); i++) {
            Item prod = lst.get(i).getProduct();
            prod.setQuantity(prod.getQuantity()-lst.get(i).getQuantity());
        }
    }

    /**
     *
     * @return Επιστρέφει το πρώτο διαθέσιμο ταμείο
     */
    public Register getRegister(){
        if (!register.isOccupied()){
                register.setOccupied();
                return register;
        }
        return null;
    }
    
   /* public Item retrieveItem(String itemID) {
        return new
        
    }
*/

}

