package hust.soict.hedspi.test.cart;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.disc.DigitalVideoDisc;

public class CartTest {
    public static void main(String[] args) {
        Cart cart = new Cart();

        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", 18.99f);
        DigitalVideoDisc dvd4 = new DigitalVideoDisc("The Godfather", "Crime", "Francis Ford Coppola", 175, 21.99f);
        DigitalVideoDisc dvd5 = new DigitalVideoDisc("Lion", "Documentary", 15.99f);

        cart.addDigitalVideoDisc(dvd1, dvd2);
        cart.addDigitalVideoDisc(dvd3, dvd4, dvd5);

        System.out.println("All DVDs in cart:");
        cart.printCart();

        System.out.println("\nSearching by ID 2:");
        cart.searchByID(2);

        System.out.println("\nSearching by ID 10 (not found):");
        cart.searchByID(10);

        System.out.println("\nSearching by title 'Lion':");
        cart.searchByTitle("Lion");

        System.out.println("\nSearching by title 'tion' (partial match):");
        cart.searchByTitle("tion");

        System.out.println("\nSearching by title 'xyz' (not found):");
        cart.searchByTitle("xyz");
    }
}

