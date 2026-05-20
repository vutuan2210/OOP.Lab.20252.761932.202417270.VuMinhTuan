package hust.soict.hedspi.test.cart;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;

public class CartTest {
    public static void main(String[] args) {
        Cart cart = new Cart();

        DigitalVideoDisc dune = new DigitalVideoDisc(
                "Dune", "Sci-Fi", "Denis Villeneuve", 155, 19.5f);
        DigitalVideoDisc matrix = new DigitalVideoDisc(
                "The Matrix", "Sci-Fi", "Wachowski", 136, 14.0f);
        DigitalVideoDisc soul = new DigitalVideoDisc(
                "Soul", "Animation", "Pete Docter", 100, 10.0f);

        cart.addMedia(dune);
        cart.addMedia(matrix);
        cart.addMedia(soul);
        cart.printCart();

        System.out.println("\nSearch title contains 'matrix':");
        cart.searchByTitle("matrix");

        System.out.println("\nSort by cost then title:");
        cart.sortByCostTitle();
        cart.printCart();
    }
}
