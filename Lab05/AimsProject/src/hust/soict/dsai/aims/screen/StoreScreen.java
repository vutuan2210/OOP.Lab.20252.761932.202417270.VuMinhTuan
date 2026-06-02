package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.InvalidMediaException;
import hust.soict.dsai.aims.exception.LimitExceededException;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class StoreScreen extends JFrame {
    private Store store;
    private Cart cart;

    public StoreScreen(Store store) {
        this(store, new Cart());
    }

    public StoreScreen(Store store, Cart cart) {
        if (store == null) {
            throw new InvalidMediaException("Store must not be null.");
        }
        if (cart == null) {
            throw new InvalidMediaException("Cart must not be null.");
        }
        this.store = store;
        this.cart = cart;

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        cp.add(createNorth(), BorderLayout.NORTH);
        cp.add(createCenter(), BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Store");
        setSize(1024, 768);
        setLocationRelativeTo(null);
    }

    JPanel createNorth() {
        JPanel north = new JPanel();
        north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
        north.add(createMenuBar());
        north.add(createHeader());
        return north;
    }

    JMenuBar createMenuBar() {
        JMenu menu = new JMenu("Options");
        JMenu smUpdateStore = new JMenu("Update Store");
        JMenuItem addBook = new JMenuItem("Add Book");
        JMenuItem addCD = new JMenuItem("Add CD");
        JMenuItem addDVD = new JMenuItem("Add DVD");
        addBook.addActionListener(e -> {
            new AddBookToStoreScreen(store, cart);
            dispose();
        });
        addCD.addActionListener(e -> {
            new AddCompactDiscToStoreScreen(store, cart);
            dispose();
        });
        addDVD.addActionListener(e -> {
            new AddDigitalVideoDiscToStoreScreen(store, cart);
            dispose();
        });
        smUpdateStore.add(addBook);
        smUpdateStore.add(addCD);
        smUpdateStore.add(addDVD);
        menu.add(smUpdateStore);
        JMenuItem viewStore = new JMenuItem("View store");
        JMenuItem viewCart = new JMenuItem("View cart");
        viewStore.addActionListener(e -> {
            new StoreScreen(store, cart);
            dispose();
        });
        viewCart.addActionListener(e -> new CartScreen(store, cart));
        menu.add(viewStore);
        menu.add(viewCart);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        menuBar.add(menu);

        return menuBar;
    }

    JPanel createHeader() {
        JPanel header = new JPanel();
        header.setLayout(new BoxLayout(header, BoxLayout.X_AXIS));

        JLabel title = new JLabel("AIMS");
        title.setFont(new Font(title.getFont().getName(), Font.PLAIN, 50));
        title.setForeground(Color.CYAN);

        JButton cart = new JButton("View cart");
        cart.setPreferredSize(new Dimension(100, 50));
        cart.setMaximumSize(new Dimension(100, 50));
        cart.addActionListener(e -> new CartScreen(store, this.cart));

        header.add(Box.createRigidArea(new Dimension(10, 10)));
        header.add(title);
        header.add(Box.createHorizontalGlue());
        header.add(cart);
        header.add(Box.createRigidArea(new Dimension(10, 10)));

        return header;
    }

    JPanel createCenter() {
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(3, 3, 2, 2));
        ArrayList<Media> mediaInStore = new ArrayList<>(store.getItemsInStore());
        for (int i = 0; i < 9; i++) {
            if (i < mediaInStore.size()) {
                MediaStore cell = new MediaStore(mediaInStore.get(i), cart);
                center.add(cell);
            } else {
                center.add(new JPanel());
            }
        }
        return center;
    }

    public static void main(String[] args) {
        Store store = new Store();

        for (int i = 1; i <= 9; i++) {
            try {
                if (i % 3 == 1) {
                    store.addMedia(new DigitalVideoDisc(
                            "DVD" + i + "'s Title", "Animation", "Director", 40.0f + i, 80 + i,
                            "1 year warranty"));
                } else if (i % 3 == 2) {
                    Book book = new Book();
                    book.setTitle("Book" + i + "'s Title");
                    book.setCategory("Book");
                    book.setCost(20.0f + i);
                    store.addMedia(book);
                } else {
                    store.addMedia(new CompactDisc(
                            i, "CD" + i + "'s Title", "Music", 50.0f + i, 60 + i, "Director", "Artist"));
                }
            } catch (LimitExceededException | InvalidMediaException ex) {
                System.out.println("Could not add demo media: " + ex.getMessage());
            }
        }

        new StoreScreen(store);
    }
}
