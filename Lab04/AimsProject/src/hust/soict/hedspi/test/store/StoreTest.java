
package hust.soict.hedspi.test.store;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.store.Store;

public class StoreTest {
    public static void main(String[] args) {
        Store store = new Store();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc(
                "Interstellar", "Sci-Fi", "Christopher Nolan", 169, 17.5f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc(
                "Toy Story", "Animation", "John Lasseter", 81, 9.5f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc(
                "Parasite", "Drama", "Bong Joon-ho", 132, 12.0f);

        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(dvd3);

        System.out.println("Store after adding:");
        store.displayStore();

        store.removeMedia(dvd2);
        System.out.println("\nStore after removing Toy Story:");
        store.displayStore();

        System.out.println("\nFind media by title keyword 'paras':");
        System.out.println(store.findMediaByTitle("paras"));
    }
}

