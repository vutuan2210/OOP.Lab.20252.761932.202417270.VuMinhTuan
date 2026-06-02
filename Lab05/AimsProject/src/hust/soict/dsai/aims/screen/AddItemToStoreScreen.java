package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.InvalidMediaException;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public abstract class AddItemToStoreScreen extends JFrame {
    protected Store store;
    protected Cart cart;
    private Map<String, JTextField> fields = new HashMap<>();
    private JPanel formPanel;

    public AddItemToStoreScreen(Store store, Cart cart, String title) {
        super(title);
        if (store == null) {
            throw new InvalidMediaException("Store must not be null.");
        }
        if (cart == null) {
            throw new InvalidMediaException("Cart must not be null.");
        }
        this.store = store;
        this.cart = cart;

        setLayout(new BorderLayout());
        setJMenuBar(createMenuBar());

        JLabel header = new JLabel(title);
        header.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(header, BorderLayout.NORTH);

        formPanel = new JPanel(new GridLayout(0, 2, 8, 8));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addCommonFields();
        addSpecificFields();
        add(formPanel, BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");
        addButton.addActionListener(e -> addItem());
        cancelButton.addActionListener(e -> {
            new StoreScreen(store, cart);
            dispose();
        });
        actions.add(addButton);
        actions.add(cancelButton);
        add(actions, BorderLayout.SOUTH);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(420, 320);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    protected void addField(String key, String label) {
        formPanel.add(new JLabel(label));
        JTextField field = new JTextField();
        fields.put(key, field);
        formPanel.add(field);
    }

    protected String getField(String key) {
        JTextField field = fields.get(key);
        if (field == null) {
            throw new InvalidMediaException("Unknown input field: " + key);
        }
        String value = field.getText();
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidMediaException("Field must not be empty: " + key);
        }
        return value.trim();
    }

    protected String getOptionalField(String key) {
        JTextField field = fields.get(key);
        if (field == null) {
            throw new InvalidMediaException("Unknown input field: " + key);
        }
        String value = field.getText();
        return value == null ? "" : value.trim();
    }

    protected float getFloatField(String key) {
        try {
            return Float.parseFloat(getField(key));
        } catch (NumberFormatException ex) {
            throw new InvalidMediaException("Field must be a valid number: " + key);
        }
    }

    protected int getIntField(String key) {
        try {
            return Integer.parseInt(getField(key));
        } catch (NumberFormatException ex) {
            throw new InvalidMediaException("Field must be a valid integer: " + key);
        }
    }

    protected void addCommonFields() {
        addField("title", "Title");
        addField("category", "Category");
        addField("cost", "Cost");
    }

    protected abstract void addSpecificFields();

    protected abstract Media createMedia();

    private void addItem() {
        try {
            Media media = createMedia();
            store.addMedia(media);
            JOptionPane.showMessageDialog(this, "Added to store: " + media.getTitle());
            new StoreScreen(store, cart);
            dispose();
        } catch (RuntimeException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Cannot add item",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private JMenuBar createMenuBar() {
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
        menuBar.add(menu);
        return menuBar;
    }
}
