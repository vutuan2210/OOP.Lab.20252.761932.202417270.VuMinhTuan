package hust.soict.hedspi.aims.media;

import java.util.ArrayList;

public class Book extends Media {
    private final ArrayList<String> authors = new ArrayList<>();

    public Book(String title) {
        super(title);
    }

    public Book(String title, String category, float cost) {
        super(title, category, cost);
    }

    @Override
    public void displayDetails() {
        System.out.println("--- BOOK DETAILS ---");
        System.out.println("ID       : " + getId());
        System.out.println("Title    : " + getTitle());
        System.out.println("Category : " + getCategory());
        System.out.println("Authors  : " + (authors.isEmpty() ? "(none)" : String.join(", ", authors)));
        System.out.printf("Cost     : %.2f$%n", getCost());
        System.out.println("--------------------");
    }

    @Override
    public String toString() {
        return String.format("Book | %s | %s | %.2f$ | authors=%s",
                getTitle(), getCategory(), getCost(), authors);
    }

    public boolean addAuthor(String authorName) {
        if (authorName == null || authorName.isBlank()) {
            return false;
        }
        String normalized = authorName.trim();
        if (authors.contains(normalized)) {
            return false;
        }
        authors.add(normalized);
        return true;
    }

    public boolean removeAuthor(String authorName) {
        if (authorName == null) {
            return false;
        }
        return authors.remove(authorName.trim());
    }
}