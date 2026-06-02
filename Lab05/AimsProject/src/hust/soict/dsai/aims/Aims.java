package hust.soict.dsai.aims;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.InvalidMediaException;
import hust.soict.dsai.aims.exception.LimitExceededException;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.store.Store;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Aims {

    private static Store store;
    private static Cart cart;

    public static void main(String[] args) {
        store = new Store();
        cart = new Cart();

        seedDemoData();

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                showMenu();
                int choice = readInt(sc);
                switch (choice) {
                    case 1:
                        store.displayStore();
                        break;
                    case 2:
                        store.displayStoreDetails();
                        break;
                    case 3:
                        cart.printCart();
                        while (true) {
                            cartMenu();
                            Integer cartChoiceObj = tryReadInt(sc);
                            if (cartChoiceObj == null) return;
                            int cartChoice = cartChoiceObj;
                            switch (cartChoice) {
                                case 1:
                                    System.out.println("Filter by: ");
                                    System.out.println("1. id");
                                    System.out.println("2. title");
                                    int filterChoice = readInt(sc);
                                    if (filterChoice == 1) {
                                        System.out.print("Enter id: ");
                                        int id = readInt(sc);
                                        cart.searchCartById(id);
                                    } else if (filterChoice == 2) {
                                        System.out.print("Enter title: ");
                                        String title = sc.nextLine();
                                        cart.searchCartByTitle(title);
                                    } else {
                                        System.out.println("Invalid filter choice.");
                                    }
                                    break;
                                case 2:
                                    System.out.println("Sort by: ");
                                    System.out.println("1. title");
                                    System.out.println("2. cost");
                                    int sortChoice = readInt(sc);
                                    if (sortChoice == 1) {
                                        cart.sortByTitle();
                                        cart.printCart();
                                    } else if (sortChoice == 2) {
                                        cart.sortByCost();
                                        cart.printCart();
                                    } else {
                                        System.out.println("Invalid sort choice.");
                                    }
                                    break;
                                case 3:
                                    removeMediaFromCart(sc);
                                    break;
                                case 4:
                                    playMediaFromCart(sc);
                                    break;
                                case 5:
                                    // Simplified lab behavior: create order, then empty cart
                                    System.out.println("Order created! Total cost: $" + cart.totalCost());
                                    cart.clear();
                                    System.out.println("Current cart is now empty.");
                                    cart.printCart();
                                    break;


                                case 0:
                                    // Back to main menu
                                    cart.printCart();
                                    cartChoice = -1; // no-op
                                    break;
                                default:
                                    System.out.println("Invalid choice. Please choose 0-1-2-3-4-5.");
                            }
                            if (cartChoice == 0) {
                                break;
                            }
                        }
                        break;
                    case 0:
                        System.out.println("Exit...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please choose 0-1-2-3.");
                }
            }
        }
    }

    public static void showMenu() {
        System.out.println("AIMS: ");
        System.out.println("--------------------------------");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. See current cart");
        System.out.println("0. Exit");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3");
    }

    public static void cartMenu() {
        System.out.println("Options: ");
        System.out.println("--------------------------------");
        System.out.println("1. Filter medias in cart");
        System.out.println("2. Sort medias in cart");
        System.out.println("3. Remove media from cart");
        System.out.println("4. Play a media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4-5");
    }

    public static void storeMenu() {
        System.out.println("Options: ");

        System.out.println("--------------------------------");
        System.out.println("1. See a media’s details");
        System.out.println("2. Add a media to cart");
        System.out.println("3. Play a media");
        System.out.println("4. See current cart");
        System.out.println("0. Back");
        System.out.println("--------------------------------");
        System.out.println("Please choose a number: 0-1-2-3-4");
    }


    private static Integer tryReadInt(Scanner sc) {
        String line;
        if (!sc.hasNextLine()) return null;
        line = sc.nextLine();
        try {
            return Integer.parseInt(line.trim());
        } catch (Exception e) {
            System.out.print("Please enter a valid number: ");
            return tryReadInt(sc);
        }
    }

    private static int readInt(Scanner sc) {
        String line;
        while (true) {
            line = sc.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (Exception e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    private static void playMediaFromCart(Scanner sc) {
        if (cart.getItemsOrdered().isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        cart.printCart();
        System.out.print("Enter media number to play: ");
        int mediaNumber = readInt(sc);
        int index = mediaNumber - 1;

        if (index < 0 || index >= cart.getItemsOrdered().size()) {
            System.out.println("Invalid media number.");
            return;
        }

        Media media = cart.getItemsOrdered().get(index);
        if (!(media instanceof Playable)) {
            System.out.println("This media cannot be played: " + media.getTitle());
            return;
        }

        try {
            ((Playable) media).play();
        } catch (PlayerException e) {
            System.err.println(e.getMessage());
            System.err.println(e.toString());
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, e.getMessage(), "Illegal Media Length",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void removeMediaFromCart(Scanner sc) {
        if (cart.getItemsOrdered().isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        cart.printCart();
        System.out.print("Enter media number to remove: ");
        int mediaNumber = readInt(sc);
        int index = mediaNumber - 1;

        if (index < 0 || index >= cart.getItemsOrdered().size()) {
            System.out.println("Invalid media number.");
            return;
        }

        Media media = cart.getItemsOrdered().get(index);
        try {
            cart.removeMedia(media);
            System.out.println("Removed from cart: " + media.getTitle());
        } catch (InvalidMediaException ex) {
            System.out.println("Cannot remove media: " + ex.getMessage());
        }
    }

            
    private static void seedDemoData() {
        // Seed store with some demo items so “Add a media to cart” can work.
        // Using the legacy aims.disc.DVD types because the provided Store has addDVD/removeDVD for compatibility.
        hust.soict.dsai.aims.disc.DVD dvd1 = new hust.soict.dsai.aims.disc.DVD(
                "The Lion King", "Animation", "Roger Allers", 19.95f, 87, "1 year warranty");
        hust.soict.dsai.aims.disc.DVD dvd2 = new hust.soict.dsai.aims.disc.DVD(
                "Star Wars", "Science Fiction", "George Lucas", 24.95f, 87, "1 year warranty");
        hust.soict.dsai.aims.disc.DVD dvd3 = new hust.soict.dsai.aims.disc.DVD(
                "Aladin", "Animation", "Ron Clements", 18.99f, 90, "1 year warranty");

        try {
            store.addDVD(dvd1);
            store.addDVD(dvd2);
            store.addDVD(dvd3);
        } catch (LimitExceededException | InvalidMediaException ex) {
            System.out.println("Could not seed demo store data: " + ex.getMessage());
        }
    }
}
