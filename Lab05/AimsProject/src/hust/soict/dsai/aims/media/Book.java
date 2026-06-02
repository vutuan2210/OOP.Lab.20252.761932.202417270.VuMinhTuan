package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.exception.InvalidMediaException;
import java.util.ArrayList;
import java.util.List;

public class Book extends Media {

    private List<String> authors = new ArrayList<>();
    private String content = "";

    public Book() {
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        if (authors == null) {
            throw new InvalidMediaException("Author list must not be null.");
        }
        this.authors = new ArrayList<>();
        for (String author : authors) {
            addAuthor(author);
        }
    }

    public void addAuthor(String authorName) {
        validateAuthor(authorName);
        String normalizedAuthor = authorName.trim();
        if (authors.contains(normalizedAuthor)) {
            throw new InvalidMediaException("Author already exists: " + normalizedAuthor);
        }
        authors.add(normalizedAuthor);
    }

    public void removeAuthor(String authorName) {
        validateAuthor(authorName);
        String normalizedAuthor = authorName.trim();
        if (!authors.remove(normalizedAuthor)) {
            throw new InvalidMediaException("Author is not listed: " + normalizedAuthor);
        }
    }

    public void setContent(String content) {
        if (content == null) {
            throw new InvalidMediaException("Book content must not be null.");
        }
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public int getContentLength() {
        return content.length();
    }

    @Override
    public String toString() {
        return "Book - " + this.getTitle() + " - " + this.getCategory()
                + " - " + authors + ": " + this.getCost() + " $";
    }

    private void validateAuthor(String authorName) {
        if (authorName == null || authorName.trim().isEmpty()) {
            throw new InvalidMediaException("Author name must not be empty.");
        }
    }
}
