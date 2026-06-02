package hust.soict.dsai.test.cart;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.disc.DVD;
import hust.soict.dsai.aims.disc.DigitalVideoDisc;

public class CartTest {
    public static void main(String[] args) {
        Cart cart = new Cart();

        DVD dvd1 = new DVD("The Lion King",
                "Animation", "Roger Allers", 19.95f, 87, "1 year warranty");
        cart.addDigitalVideoDisc(dvd1);

        DVD dvd2 = new DVD("Star Wars",
                "Science Fiction", "George Lucas", 24.95f, 87, "1 year warranty");
        cart.addDigitalVideoDisc(dvd2);

        DVD dvd3 = new DVD("Aladin",
                "Animation", "Ron Clements", 18.99f, 90, "1 year warranty");
        cart.addDigitalVideoDisc(dvd3);

        System.out.println("=== Test printCart() ===");
        cart.printCart();

        System.out.println("\n=== Test searchCartByTitle() ===");
        System.out.println("\nSearching for 'Lion':");
        cart.searchCartByTitle("Lion");

        System.out.println("\nSearching for 'Star':");
        cart.searchCartByTitle("Star");

        System.out.println("\nSearching for 'Aladdin':");
        cart.searchCartByTitle("Aladdin");

        System.out.println("\nSearching for 'Frozen' (not in cart):");
        cart.searchCartByTitle("Frozen");

        System.out.println("\n=== Test searchCartById() ===");
        cart.searchCartById(1);

        System.out.println("\n=== Test DigitalVideoDisc.isMatch() ===");
        DigitalVideoDisc ddvd1 = new DigitalVideoDisc("The Lion King",
                "Animation", "Roger Allers", 87, 19.95f);
        DigitalVideoDisc ddvd2 = new DigitalVideoDisc("Star Wars",
                "Science Fiction", "George Lucas", 87, 24.95f);

        System.out.println("ddvd1.isMatch('Lion'): " + ddvd1.isMatch("Lion"));
        System.out.println("ddvd1.isMatch('King'): " + ddvd1.isMatch("King"));
        System.out.println("ddvd2.isMatch('Wars'): " + ddvd2.isMatch("Wars"));
        System.out.println("ddvd2.isMatch('Trek'): " + ddvd2.isMatch("Trek"));

        System.out.println("\n=== Test DigitalVideoDisc.toString() ===");
        System.out.println(ddvd1.toString());
        System.out.println(ddvd2.toString());
    }
}
