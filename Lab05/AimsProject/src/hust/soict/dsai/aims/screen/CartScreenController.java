package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.exception.InvalidMediaException;
import hust.soict.dsai.aims.exception.PlayerException;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Disc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.Playable;
import hust.soict.dsai.aims.store.Store;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.SwingUtilities;

public class CartScreenController {
    private Cart cart;
    private Store store;
    private FilteredList<Media> filteredMedia;

    @FXML
    private TableView<Media> tblMedia;

    @FXML
    private TableColumn<Media, String> colMediaTitle;

    @FXML
    private TableColumn<Media, String> colMediacategory;

    @FXML
    private TableColumn<Media, Float> colMediaCost;

    @FXML
    private Button btnPlay;

    @FXML
    private Button btnRemove;

    @FXML
    private Button btnPlaceOrder;

    @FXML
    private Label lblTotalCost;

    @FXML
    private TextField tfFilter;

    @FXML
    private RadioButton radioBtnFilterId;

    @FXML
    private RadioButton radioBtnFilterTitle;

    public CartScreenController(Cart cart) {
        this(null, cart);
    }

    public CartScreenController(Store store, Cart cart) {
        super();
        if (cart == null) {
            throw new InvalidMediaException("Cart must not be null.");
        }
        this.store = store;
        this.cart = cart;
    }

    @FXML
    private void initialize() {
        colMediaTitle.setCellValueFactory(
                new PropertyValueFactory<Media, String>("title"));
        colMediacategory.setCellValueFactory(
                new PropertyValueFactory<Media, String>("category"));
        colMediaCost.setCellValueFactory(
                new PropertyValueFactory<Media, Float>("cost"));
        filteredMedia = new FilteredList<>(this.cart.getItemsOrdered(), media -> true);
        tblMedia.setItems(filteredMedia);

        btnPlay.setVisible(false);
        btnRemove.setVisible(false);
        updateTotalCost();

        tblMedia.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Media>() {
                    @Override
                    public void changed(ObservableValue<? extends Media> observable, Media oldValue,
                            Media newValue) {
                        if (newValue != null) {
                            updateButtonBar(newValue);
                        } else {
                            btnPlay.setVisible(false);
                            btnRemove.setVisible(false);
                        }
                    }
                });

        tfFilter.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                showFilteredMedia(newValue);
            }
        });

        radioBtnFilterId.selectedProperty().addListener((observable, oldValue, newValue) ->
                showFilteredMedia(tfFilter.getText()));
        radioBtnFilterTitle.selectedProperty().addListener((observable, oldValue, newValue) ->
                showFilteredMedia(tfFilter.getText()));

        cart.getItemsOrdered().addListener((ListChangeListener<Media>) change -> updateTotalCost());
    }

    void updateButtonBar(Media media) {
        btnRemove.setVisible(true);
        if (media instanceof Playable) {
            btnPlay.setVisible(true);
        } else {
            btnPlay.setVisible(false);
        }
    }

    @FXML
    void btnRemovePressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (media == null) {
            showInfo("Remove media", "Please select a media item first.");
            return;
        }
        try {
            cart.removeMedia(media);
        } catch (InvalidMediaException ex) {
            showError("Cannot remove media", ex.getMessage());
        }
    }

    @FXML
    void btnPlayPressed(ActionEvent event) {
        Media media = tblMedia.getSelectionModel().getSelectedItem();
        if (!(media instanceof Playable)) {
            showInfo("Play media", "Please select a playable media item first.");
            return;
        }

        try {
            ((Playable) media).play();
            showInfo("Play", getPlayMessage(media));
        } catch (PlayerException | InvalidMediaException ex) {
            System.err.println(ex.getMessage());
            System.err.println(ex.toString());
            ex.printStackTrace();
            showError("Cannot play media", ex.getMessage());
        }
    }

    @FXML
    void btnPlaceOrderPressed(ActionEvent event) {
        if (cart.getItemsOrdered().isEmpty()) {
            showInfo("Order", "The cart is empty.");
            return;
        }

        showInfo("Order", "Order created.\nTotal cost: " + cart.totalCost() + " $");

        cart.clear();
        tblMedia.getSelectionModel().clearSelection();
        btnPlay.setVisible(false);
        btnRemove.setVisible(false);
        updateTotalCost();
    }

    @FXML
    void miViewStorePressed(ActionEvent event) {
        if (store != null) {
            SwingUtilities.invokeLater(() -> new StoreScreen(store, cart));
        } else {
            System.out.println("Store screen is not available from this cart instance.");
        }
    }

    @FXML
    void miAddBookPressed(ActionEvent event) {
        if (store != null) {
            SwingUtilities.invokeLater(() -> new AddBookToStoreScreen(store, cart));
        }
    }

    @FXML
    void miAddCDPressed(ActionEvent event) {
        if (store != null) {
            SwingUtilities.invokeLater(() -> new AddCompactDiscToStoreScreen(store, cart));
        }
    }

    @FXML
    void miAddDVDPressed(ActionEvent event) {
        if (store != null) {
            SwingUtilities.invokeLater(() -> new AddDigitalVideoDiscToStoreScreen(store, cart));
        }
    }

    void showFilteredMedia(String filter) {
        String normalizedFilter = filter == null ? "" : filter.trim().toLowerCase();

        filteredMedia.setPredicate(media -> {
            if (normalizedFilter.isEmpty()) {
                return true;
            }

            if (radioBtnFilterId.isSelected()) {
                return String.valueOf(media.getId()).contains(normalizedFilter);
            }

            String title = media.getTitle();
            return title != null && title.toLowerCase().contains(normalizedFilter);
        });
    }

    private void updateTotalCost() {
        lblTotalCost.setText(cart.totalCost() + " $");
    }

    private String getPlayMessage(Media media) {
        StringBuilder message = new StringBuilder();
        message.append("Playing: ").append(media.getTitle());

        if (media instanceof DigitalVideoDisc) {
            DigitalVideoDisc dvd = (DigitalVideoDisc) media;
            message.append("\nDVD length: ").append(dvd.getLength());
        } else if (media instanceof CompactDisc) {
            CompactDisc cd = (CompactDisc) media;
            message.append("\nCD length: ").append(cd.getLength());
        } else if (media instanceof Disc) {
            Disc disc = (Disc) media;
            message.append("\nDisc length: ").append(disc.getLength());
        }

        return message.toString();
    }

    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
