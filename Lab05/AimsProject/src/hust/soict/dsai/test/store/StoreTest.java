package hust.soict.dsai.test.store;

import hust.soict.dsai.aims.store.Store;
import hust.soict.dsai.aims.disc.DVD;

public class StoreTest {
    public static void main(String[] args) {
        Store store = new Store(10);

        DVD dvd1 = new DVD("The Lion King", "Animation", "Roger Allers", 19.95f, 87, "1 year warranty");
        DVD dvd2 = new DVD("Star Wars", "Science Fiction", "George Lucas", 24.95f, 87, "1 year warranty");
        DVD dvd3 = new DVD("Aladin", "Animation", "Ron Clements", 18.99f, 90, "1 year warranty");
        DVD dvd4 = new DVD("Inception", "Science Fiction", "Christopher Nolan", 14.95f, 148, "1 year warranty");
        DVD dvd5 = new DVD("Avatar", "Science Fiction", "James Cameron", 22.95f, 162, "1 year warranty");

        store.addDVD(dvd1);
        store.addDVD(dvd2);
        store.addDVD(dvd3);
        store.addDVD(dvd4);
        store.addDVD(dvd5);

        System.out.println("=== Store Before Removal ===");
        store.displayStore();

        store.removeDVD(dvd2);
        store.removeDVD(dvd4);

        System.out.println("\n=== Store After Removal ===");
        store.displayStore();

        System.out.println("\nTotal items: " + store.getItemCount());
    }
}
