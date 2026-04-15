package hust.soict.dsai.aims.cart;

import hust.soict.dsai.aims.disc.DigitalVideoDisc;

public class Cart {

    public static final int MAX_NUMBERS_ORDERED = 20;

    private DigitalVideoDisc[] itemsOrdered = new DigitalVideoDisc[MAX_NUMBERS_ORDERED];
    private int qtyOrdered = 0;

    // Add one DVD
    public void addDigitalVideoDisc(DigitalVideoDisc disc) {
        if (qtyOrdered >= MAX_NUMBERS_ORDERED) {
            System.out.println("The cart is almost full");
        } else {
            itemsOrdered[qtyOrdered] = disc;
            qtyOrdered++;
            System.out.println("The disc has been added");
        }
    }

    // Overload: add two DVDs in one call
    public void addDigitalVideoDisc(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
        addDigitalVideoDisc(dvd1);
        addDigitalVideoDisc(dvd2);
    }

    // Overload: add an arbitrary number of DVDs (accepts array as well)
    public void addDigitalVideoDisc(DigitalVideoDisc... dvdList) {
        for (DigitalVideoDisc dvd : dvdList) {
            if (dvd != null) {
                addDigitalVideoDisc(dvd);
            }
        }
    }

    // Remove DVD
    public void removeDigitalVideoDisc(DigitalVideoDisc disc) {
        boolean found = false;

        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i] == disc) {
                found = true;

                for (int j = i; j < qtyOrdered - 1; j++) {
                    itemsOrdered[j] = itemsOrdered[j + 1];
                }

                itemsOrdered[qtyOrdered - 1] = null;
                qtyOrdered--;

                System.out.println("The disc has been removed");
                break;
            }
        }

        if (!found) {
            System.out.println("The disc was not found in the cart");
        }
    }

    // Calculate total cost
    public float totalCost() {
        float sum = 0;

        for (int i = 0; i < qtyOrdered; i++) {
            sum += itemsOrdered[i].getCost();
        }

        return sum;
    }

    // Print items in cart
    public void printCart() {
        System.out.println("***********************CART***********************");
        System.out.println("Ordered Items:");
        for (int i = 0; i < qtyOrdered; i++) {
            System.out.println((i + 1) + ". " + itemsOrdered[i].toString());
        }
        System.out.println("Total cost: " + totalCost());
        System.out.println("***************************************************");
    }

    // Search DVD by ID
    public void searchByID(int id) {
        boolean found = false;
        System.out.println("Search results for ID " + id + ":");
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i].getId() == id) {
                System.out.println((i + 1) + ". " + itemsOrdered[i].toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No DVD found with ID " + id);
        }
    }

    // Search DVD by title
    public void searchByTitle(String title) {
        boolean found = false;
        System.out.println("Search results for title \"" + title + "\":");
        for (int i = 0; i < qtyOrdered; i++) {
            if (itemsOrdered[i].isMatch(title)) {
                System.out.println((i + 1) + ". " + itemsOrdered[i].toString());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No DVD found matching title \"" + title + "\"");
        }
    }
}
