package hust.soict.hedspi.aims.store;

import hust.soict.hedspi.aims.media.Media;
import java.util.ArrayList;
import java.util.List;

public class Store {

    private final ArrayList<Media> itemsInStore = new ArrayList<>();

    public List<Media> getItemsInStore() {
        return new ArrayList<>(itemsInStore);
    }

    public void removeMedia(Media media) {
        if (media == null) {
            return;
        }
        if (!itemsInStore.remove(media)) {
            System.out.println("Cannot find media in store: " + media.getTitle());
            return;
        }
        System.out.println("Removed from store: " + media.getTitle());
    }

    public void addMedia(Media media) {
        if (media == null) {
            return;
        }
        if (containsMediaId(media.getId())) {
            System.out.println("Media already exists in store: " + media.getTitle());
            return;
        }
        itemsInStore.add(media);
        System.out.println("Added to store: " + media.getTitle());
    }

    public void displayStore() {
        System.out.println("================= STORE =================");
        if (itemsInStore.isEmpty()) {
            System.out.println("(store is empty)");
        } else {
            for (int i = 0; i < itemsInStore.size(); i++) {
                System.out.println((i + 1) + ". " + itemsInStore.get(i));
            }
        }
        System.out.println("=========================================");
    }

    public Media findMediaByTitle(String title) {
        if (title == null) {
            return null;
        }
        String keyword = title.trim().toLowerCase();
        for (Media media : itemsInStore) {
            if (media.getTitle().toLowerCase().contains(keyword)) {
                return media;
            }
        }
        return null;
    }

    private boolean containsMediaId(int id) {
        for (Media media : itemsInStore) {
            if (media.getId() == id) {
                return true;
            }
        }
        return false;
    }
}


