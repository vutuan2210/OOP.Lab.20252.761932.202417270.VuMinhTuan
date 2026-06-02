package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.InvalidMediaException;
import hust.soict.dsai.aims.store.Store;
import java.io.IOException;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class CartScreen extends JFrame {
    private Cart cart;
    private Store store;

    public CartScreen(Cart cart) {
        this(null, cart);
    }

    public CartScreen(Store store, Cart cart) {
        super();
        if (cart == null) {
            throw new InvalidMediaException("Cart must not be null.");
        }
        this.store = store;
        this.cart = cart;

        JFXPanel fxPanel = new JFXPanel();
        this.add(fxPanel);

        this.setTitle("Cart");
        this.setSize(1024, 768);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    URL fxmlUrl = getClass().getResource("/hust/soict/dsai/aims/screen/cart.fxml");
                    if (fxmlUrl == null) {
                        throw new IOException("Cannot find cart.fxml resource.");
                    }
                    FXMLLoader loader = new FXMLLoader(fxmlUrl);
                    CartScreenController controller = new CartScreenController(store, cart);
                    loader.setController(controller);
                    Parent root = loader.load();
                    fxPanel.setScene(new Scene(root));
                } catch (IOException | RuntimeException e) {
                    JOptionPane.showMessageDialog(CartScreen.this, e.getMessage(),
                            "Cannot load cart screen", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
