package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.CompactDisc;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;

public class AddCompactDiscToStoreScreen extends AddItemToStoreScreen {
    public AddCompactDiscToStoreScreen(Store store, Cart cart) {
        super(store, cart, "Add CD");
    }

    @Override
    protected void addSpecificFields() {
        addField("director", "Director");
        addField("artist", "Artist");
        addField("length", "Length");
    }

    @Override
    protected Media createMedia() {
        return new CompactDisc(
                store.getItemCount() + 1,
                getField("title"),
                getField("category"),
                getFloatField("cost"),
                getIntField("length"),
                getField("director"),
                getField("artist"));
    }
}
