package hust.soict.hedspi.aims;

import hust.soict.hedspi.aims.cart.Cart;
import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Playable;
import hust.soict.hedspi.aims.store.Store;
import java.util.Scanner;

public class Aims {
    public static void main(String[] args) {
        Store store = new Store();
        Cart cart = new Cart();
        seedSampleData(store);

        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            
            while (running) {
                showMainMenu();
                int choice = readInt(scanner);
                
                switch (choice) {
                    case 1 -> handleStore(store, cart, scanner);
                    case 2 -> handleUpdateStore(store, scanner);
                    case 3 -> handleCart(cart, scanner);
                    case 0 -> running = false;
                    default -> System.out.println("Please choose 0, 1, 2 or 3.");
                }
            }
        }
        System.out.println("Goodbye.");
    }

    private static void seedSampleData(Store store) {
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("Interstellar", "Science Fiction", "Christopher Nolan", 169,
                17.5f);
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Whiplash", "Drama", "Damien Chazelle", 106, 12.0f);

        Book book1 = new Book("Clean Code", "Programming", 20.0f);
        book1.addAuthor("Robert C. Martin");
        Book book2 = new Book("Dune", "Science Fiction", 15.0f);
        book2.addAuthor("Frank Herbert");

        CompactDisc cd1 = new CompactDisc("Road Trip", "Music", "Various", 0, 10.0f, "Various Artists");

        store.addMedia(dvd1);
        store.addMedia(dvd2);
        store.addMedia(book1);
        store.addMedia(book2);
        store.addMedia(cd1);
    }

    private static void handleStore(Store store, Cart cart, Scanner scanner) {
        boolean back = false;
        while (!back) {
            store.displayStore();
            showStoreMenu();
            int choice = readInt(scanner);

            switch (choice) {
                case 1 -> showMediaDetails(store, cart, scanner);
                case 2 -> addStoreMediaToCart(store, cart, scanner);
                case 3 -> playStoreMedia(store, scanner);
                case 4 -> handleCart(cart, scanner);
                case 0 -> back = true;
                default -> System.out.println("Please choose a valid option.");
            }
        }
    }

    private static void showMediaDetails(Store store, Cart cart, Scanner scanner) {
        System.out.print("Enter title keyword: ");
        String title = scanner.nextLine();

        Media media = store.findMediaByTitle(title);
        if (media == null) {
            System.out.println("No media matched your input.");
            return;
        }

        media.displayDetails();

        System.out.println("1. Add to cart");
        System.out.println("2. Play this media");
        System.out.println("0. Back");
        System.out.print("Your choice: ");
        int choice = readInt(scanner);

        if (choice == 1) {
            cart.addMedia(media);
        } else if (choice == 2) {
            playMedia(media);
        }
    }

    private static void addStoreMediaToCart(Store store, Cart cart, Scanner scanner) {
        System.out.print("Enter title keyword: ");
        String title = scanner.nextLine();
        Media media = store.findMediaByTitle(title);

        if (media == null) {
            System.out.println("Media was not found in store.");
            return;
        }

        cart.addMedia(media);
    }

    private static void playStoreMedia(Store store, Scanner scanner) {
        System.out.print("Enter title keyword: ");
        String title = scanner.nextLine();
        Media media = store.findMediaByTitle(title);

        if (media == null) {
            System.out.println("Media was not found in store.");
            return;
        }

        playMedia(media);
    }

    private static void handleUpdateStore(Store store, Scanner scanner) {
        boolean back = false;
        while (!back) {
            showUpdateMenu();
            int choice = readInt(scanner);

            switch (choice) {
                case 1 -> addMediaToStore(store, scanner);
                case 2 -> removeMediaFromStore(store, scanner);
                case 0 -> back = true;
                default -> System.out.println("Please choose 0, 1 or 2.");
            }
        }
    }

    private static void addMediaToStore(Store store, Scanner scanner) {
        System.out.print("Type (dvd/cd/book): ");
        String type = scanner.nextLine().trim().toLowerCase();

        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Category: ");
        String category = scanner.nextLine();
        System.out.print("Cost: ");
        float cost = readFloat(scanner);

        switch (type) {
            case "dvd" -> {
                System.out.print("Director: ");
                String director = scanner.nextLine();
                System.out.print("Length (minutes): ");
                int length = readInt(scanner);
                store.addMedia(new DigitalVideoDisc(title, category, director, length, cost));
            }
            case "cd" -> {
                System.out.print("Artist: ");
                String artist = scanner.nextLine();
                store.addMedia(new CompactDisc(title, category, "Unknown", 0, cost, artist));
            }
            case "book" -> {
                Book book = new Book(title, category, cost);
                System.out.print("Author (optional, Enter to skip): ");
                String author = scanner.nextLine();
                if (!author.isBlank()) {
                    book.addAuthor(author);
                }
                store.addMedia(book);
            }
            default -> System.out.println("Unsupported media type.");
        }
    }

    private static void removeMediaFromStore(Store store, Scanner scanner) {
        System.out.print("Enter title keyword to remove: ");
        String title = scanner.nextLine();
        Media media = store.findMediaByTitle(title);

        if (media == null) {
            System.out.println("Media not found.");
            return;
        }

        store.removeMedia(media);
    }

    private static void handleCart(Cart cart, Scanner scanner) {
        boolean back = false;
        while (!back) {
            cart.printCart();
            showCartMenu();
            int choice = readInt(scanner);

            switch (choice) {
                case 1 -> filterCart(cart, scanner);
                case 2 -> sortCart(cart, scanner);
                case 3 -> removeFromCart(cart, scanner);
                case 4 -> playFromCart(cart, scanner);
                case 5 -> placeOrder(cart);
                case 0 -> back = true;
                default -> System.out.println("Please choose a valid option.");
            }
        }
    }

    private static void filterCart(Cart cart, Scanner scanner) {
        System.out.println("1. Search by title");
        System.out.println("2. Search by ID");
        System.out.println("0. Back");
        System.out.print("Your choice: ");
        int choice = readInt(scanner);

        if (choice == 1) {
            System.out.print("Title keyword: ");
            cart.searchByTitle(scanner.nextLine());
        } else if (choice == 2) {
            System.out.print("ID: ");
            cart.searchById(readInt(scanner));
        }
    }

    private static void sortCart(Cart cart, Scanner scanner) {
        System.out.println("1. Sort by title then cost");
        System.out.println("2. Sort by cost then title");
        System.out.println("0. Back");
        System.out.print("Your choice: ");
        int choice = readInt(scanner);

        if (choice == 1) {
            cart.sortByTitleCost();
        } else if (choice == 2) {
            cart.sortByCostTitle();
        }
    }

    private static void removeFromCart(Cart cart, Scanner scanner) {
        System.out.print("Enter title keyword to remove: ");
        String title = scanner.nextLine();
        Media media = cart.findMediaByTitle(title);
        if (media == null) {
            System.out.println("Media not found in cart.");
            return;
        }
        cart.removeMedia(media);
    }

    private static void playFromCart(Cart cart, Scanner scanner) {
        System.out.print("Enter title keyword: ");
        Media media = cart.findMediaByTitle(scanner.nextLine());
        if (media == null) {
            System.out.println("Media not found in cart.");
            return;
        }
        playMedia(media);
    }

    private static void placeOrder(Cart cart) {
        if (cart.getItemsOrdered().isEmpty()) {
            System.out.println("Cart is empty. Nothing to place.");
            return;
        }

        System.out.printf("Order placed for %d item(s), total %.2f$%n",
                cart.getItemsOrdered().size(), cart.totalCost());
        cart.emptyCart();
    }

    private static void playMedia(Media media) {
        if (!(media instanceof Playable)) {
            System.out.println("This item cannot be played.");
            return;
        }
        ((Playable) media).play();
    }

    private static void showMainMenu() {
        System.out.println("\nAIMS");
        System.out.println("1. View store");
        System.out.println("2. Update store");
        System.out.println("3. View cart");
        System.out.println("0. Exit");
        System.out.print("Choose: ");
    }

    private static void showStoreMenu() {
        System.out.println("\nStore Options");
        System.out.println("1. See media details");
        System.out.println("2. Add media to cart");
        System.out.println("3. Play media");
        System.out.println("4. Open cart");
        System.out.println("0. Back");
        System.out.print("Choose: ");
    }

    private static void showUpdateMenu() {
        System.out.println("\nUpdate Store");
        System.out.println("1. Add media");
        System.out.println("2. Remove media");
        System.out.println("0. Back");
        System.out.print("Choose: ");
    }

    private static void showCartMenu() {
        System.out.println("\nCart Options");
        System.out.println("1. Filter media");
        System.out.println("2. Sort media");
        System.out.println("3. Remove media");
        System.out.println("4. Play media");
        System.out.println("5. Place order");
        System.out.println("0. Back");
        System.out.print("Choose: ");
    }

    private static int readInt(Scanner scanner) {
        while (true) {
            String text = scanner.nextLine();
            try {
                return Integer.parseInt(text.trim());
            } catch (NumberFormatException ex) {
                System.out.print("Please enter an integer: ");
            }
        }
    }

    private static float readFloat(Scanner scanner) {
        while (true) {
            String text = scanner.nextLine();
            try {
                return Float.parseFloat(text.trim());
            } catch (NumberFormatException ex) {
                System.out.print("Please enter a number: ");
            }
        }
    }
}
