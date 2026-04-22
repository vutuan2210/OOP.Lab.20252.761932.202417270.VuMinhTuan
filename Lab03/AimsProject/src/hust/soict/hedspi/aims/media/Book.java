package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Book extends Media {
    private final List<String> authors = new ArrayList<>();

    public Book(String title) {
        super(title);
    }

    public Book(String title, String category, float cost) {
        super(title, category, cost);
    }


    public List<String> getAuthors() {
        return new ArrayList<>(authors);
    }

    public void setAuthors(List<String> authors) {
        this.authors.clear();
        if (authors != null) {
            for (String author : authors) {
                addAuthor(author);
            }
        }
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