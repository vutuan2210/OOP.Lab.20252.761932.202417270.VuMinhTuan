package hust.soict.dsai.test.cart;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.Media;



public class CartSortTest {
    private static Media book(String title, float cost) {
        Book b = new Book();
        b.setTitle(title);
        b.setCategory("Book");
        b.setCost(cost);
        // id is not important for sorting tests
        return b;
    }

    public static void main(String[] args) {
        Cart cart = new Cart();

        Media m1 = book("Alpha", 10.0f);
        Media m2 = book("Alpha", 20.0f); // same title, higher cost
        Media m3 = book("beta", 15.0f);
        Media m4 = book("Beta", 12.0f);  // same title (case-insensitive), different cost
        Media m5 = book("gamma", 20.0f); // same cost as m2/m5

        cart.addMedia(new Media[]{m1, m2, m3, m4, m5});

        System.out.println("=== Original order ===");
        cart.printCart();

        System.out.println("=== Sort by title (ASC), then cost (DESC for same title) ===");
        cart.sortByTitle();
        cart.printCart();

        System.out.println("=== Sort by cost (DESC), then title (ASC) ===");
        cart.sortByCost();
        cart.printCart();

        System.out.println("=== Edge case: empty cart ===");
        Cart empty = new Cart();
        empty.sortByTitle();
        empty.sortByCost();
        empty.printCart();

        System.out.println("=== Edge case: single element ===");
        Cart single = new Cart();
        single.addMedia(m1);
        single.sortByTitle();
        single.sortByCost();
        single.printCart();

        // Sanity check: sorting should not remove elements
        int count = cart.getQuantityOrdered();
        cart.sortByTitle();
        if (count != cart.getQuantityOrdered()) {
            throw new RuntimeException("Sort changed cart size unexpectedly");
        }
        System.out.println("All assertions passed.");
    }
}

