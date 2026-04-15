package SourceCode;

public class StoreTest {
    public static void main(String[] args) {
        Store store = new Store();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", 18.99f);
        DigitalVideoDisc dvd4 = new DigitalVideoDisc("The Godfather", "Crime", "Francis Ford Coppola", 175, 21.99f);

        System.out.println("Adding DVDs to store...\n");
        store.addDVD(dvd1);
        store.addDVD(dvd2);
        store.addDVD(dvd3);
        store.addDVD(dvd4);

        System.out.println("\nStore inventory:");
        store.printStore();

        System.out.println("\nRemoving dvd2 from store...\n");
        store.removeDVD(dvd2);

        System.out.println("\nStore inventory after removal:");
        store.printStore();

        System.out.println("\nTrying to remove dvd2 again (should not be found):\n");
        store.removeDVD(dvd2);

        System.out.println("\nTotal DVDs in store: " + store.getQtyInStore());
    }
}
