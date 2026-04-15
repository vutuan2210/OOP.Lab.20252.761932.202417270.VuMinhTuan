package SourceCode;

public class Store {
    public static final int MAX_ITEMS_IN_STORE = 100;

    private DigitalVideoDisc[] itemsInStore = new DigitalVideoDisc[MAX_ITEMS_IN_STORE];
    private int qtyInStore = 0;

    // Add DVD to store
    public void addDVD(DigitalVideoDisc disc) {
        if (qtyInStore >= MAX_ITEMS_IN_STORE) {
            System.out.println("The store is full");
        } else {
            itemsInStore[qtyInStore] = disc;
            qtyInStore++;
            System.out.println("The disc has been added to store");
        }
    }

    // Remove DVD from store
    public void removeDVD(DigitalVideoDisc disc) {
        boolean found = false;

        for (int i = 0; i < qtyInStore; i++) {
            if (itemsInStore[i] == disc) {
                found = true;

                for (int j = i; j < qtyInStore - 1; j++) {
                    itemsInStore[j] = itemsInStore[j + 1];
                }

                itemsInStore[qtyInStore - 1] = null;
                qtyInStore--;

                System.out.println("The disc has been removed from store");
                break;
            }
        }

        if (!found) {
            System.out.println("The disc was not found in the store");
        }
    }

    // Print all DVDs in store
    public void printStore() {
        System.out.println("*********************STORE INVENTORY*********************");
        for (int i = 0; i < qtyInStore; i++) {
            System.out.println((i + 1) + ". " + itemsInStore[i].toString());
        }
        System.out.println("*********************************************************");
    }

    public int getQtyInStore() {
        return qtyInStore;
    }

    public DigitalVideoDisc getDVD(int index) {
        if (index >= 0 && index < qtyInStore) {
            return itemsInStore[index];
        }
        return null;
    }
}
