package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.disc.DigitalVideoDisc;

public class Aims {
    public static void main(String[] args) {

        Cart anOrder = new Cart();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc(
                "The Lion King", "Animation", "Roger Allers", 87, 19.95f);

        DigitalVideoDisc dvd2 = new DigitalVideoDisc(
                "Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);

        DigitalVideoDisc dvd3 = new DigitalVideoDisc(
                "Aladin", "Animation", 18.99f);

        DigitalVideoDisc dvd4 = new DigitalVideoDisc(
                "The Godfather", "Crime", "Francis Ford Coppola", 175, 21.99f);

        // Single-parameter overload
        anOrder.addDigitalVideoDisc(dvd1);

        // Two-parameter overload
        anOrder.addDigitalVideoDisc(dvd2, dvd3);

        // Varargs overload
        anOrder.addDigitalVideoDisc(dvd4);

        // Print cart
        anOrder.printCart();

        System.out.println("\nRemoving dvd2...\n");
        anOrder.removeDigitalVideoDisc(dvd2);

        // Print cart after removal
        anOrder.printCart();
    }
}

