package Controller;

import View.MainStoreForm;
import Model.Store;

public class Main {

     public static void main(String[] args) {
         // Δημιουργία Καταστήματος
         Store store = new Store();
         // Ανακτηση ενός ταμείου
         Register reg = store.getRegister();
         // Δημιουργία της κεντρικής φόρμας
         MainStoreForm f = new MainStoreForm(reg);
         // Εμφάνιση της κεντρικής φόρμας
         f.setVisible(true);
    }

}
