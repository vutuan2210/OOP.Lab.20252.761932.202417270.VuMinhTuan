package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;

public class AddDigitalVideoDiscToStoreScreen extends AddItemToStoreScreen {
    public AddDigitalVideoDiscToStoreScreen(Store store, Cart cart) {
        super(store, cart, "Add DVD");
    }

    @Override
    protected void addSpecificFields() {
        addField("director", "Director");
        addField("length", "Length");
        addField("warranty", "Warranty");
    }

    @Override
    protected Media createMedia() {
        DigitalVideoDisc dvd = new DigitalVideoDisc(
                getField("title"),
                getField("category"),
                getField("director"),
                getFloatField("cost"),
                getIntField("length"),
                getField("warranty"));
        dvd.setId(store.getItemCount() + 1);
        return dvd;
    }
}
