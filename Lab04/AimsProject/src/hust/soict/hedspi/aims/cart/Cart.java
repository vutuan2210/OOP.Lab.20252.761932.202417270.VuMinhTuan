package hust.soict.hedspi.aims.cart;

import hust.soict.hedspi.aims.media.Media;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    public static final int MAX_NUMBERS_ORDERED = 20;

    private final ArrayList<Media> itemsOrdered = new ArrayList<>();

    public void addMedia(Media media) {
        if (media == null) {
            return;
        }
        if (itemsOrdered.size() >= MAX_NUMBERS_ORDERED) {
            System.out.println("Cart is full. Cannot add more items.");
            return;
        }
        itemsOrdered.add(media);
        System.out.println("Added to cart: " + media.getTitle());
    }

    public void removeMedia(Media media) {
        if (media == null) {
            return;
        }
        if (!itemsOrdered.remove(media)) {
            System.out.println("Item was not found in cart: " + media.getTitle());
            return;
        }
        System.out.println("Removed from cart: " + media.getTitle());
    }

    public float totalCost() {
        float total = 0.0f;
        for (Media media : itemsOrdered) {
            total += media.getCost();
        }
        return total;
    }

    public void printCart() {
        System.out.println("================= CART =================");
        if (itemsOrdered.isEmpty()) {
            System.out.println("(empty)");
        }
        for (int i = 0; i < itemsOrdered.size(); i++) {
            System.out.println((i + 1) + ". " + itemsOrdered.get(i));
        }
        System.out.printf("Total cost: %.2f$%n", totalCost());
        System.out.println("========================================");
    }

    public void searchById(int id) {
        for (Media media : itemsOrdered) {
            if (media.getId() == id) {
                System.out.println("Found: " + media);
                return;
            }
        }
        System.out.println("No media found with ID " + id);
    }

    public void searchByTitle(String title) {
        if (title == null) {
            return;
        }
        String keyword = title.trim().toLowerCase();
        boolean any = false;
        for (Media media : itemsOrdered) {
            if (media.getTitle().toLowerCase().contains(keyword)) {
                System.out.println("Matched: " + media);
                any = true;
            }
        }
        if (!any) {
            System.out.println("No media matched title: " + title);
        }
    }

    public List<Media> getItemsOrdered() {
        return new ArrayList<>(itemsOrdered);
    }

    public void emptyCart() {
        itemsOrdered.clear();
        System.out.println("Cart is now empty.");
    }

    public Media findMediaByTitle(String title) {
        if (title == null) {
            return null;
        }
        String keyword = title.trim().toLowerCase();
        for (Media media : itemsOrdered) {
            if (media.getTitle().toLowerCase().contains(keyword)) {
                return media;
            }
        }
        return null;
    }

    public void sortByTitleCost() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_TITLE_COST);
        System.out.println("Cart sorted by title then cost.");
    }

    public void sortByCostTitle() {
        Collections.sort(itemsOrdered, Media.COMPARE_BY_COST_TITLE);
        System.out.println("Cart sorted by cost then title.");
    }
}
