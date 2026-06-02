package hust.soict.dsai.aims.screen;

import hust.soict.dsai.aims.cart.Cart;
import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.store.Store;

public class AddBookToStoreScreen extends AddItemToStoreScreen {
    public AddBookToStoreScreen(Store store, Cart cart) {
        super(store, cart, "Add Book");
    }

    @Override
    protected void addSpecificFields() {
        addField("authors", "Authors (comma separated)");
    }

    @Override
    protected Media createMedia() {
        Book book = new Book();
        book.setId(store.getItemCount() + 1);
        book.setTitle(getField("title"));
        book.setCategory(getField("category"));
        book.setCost(getFloatField("cost"));

        String authors = getOptionalField("authors");
        if (authors != null && !authors.trim().isEmpty()) {
            for (String author : authors.split(",")) {
                book.addAuthor(author.trim());
            }
        }
        return book;
    }
}
