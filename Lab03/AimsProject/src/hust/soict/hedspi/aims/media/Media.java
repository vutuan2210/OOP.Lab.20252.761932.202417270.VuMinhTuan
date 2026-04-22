package hust.soict.hedspi.aims.media;

import java.util.Comparator;
import java.util.Objects;

public abstract class Media {
    private static int nextId = 1;

    private final int id;
    private String title;
    private String category;
    private float cost;

    public static final Comparator<Media> COMPARE_BY_COST_TITLE = new MediaComparatorByCostTitle();
    public static final Comparator<Media> COMPARE_BY_TITLE_COST = new MediaComparatorByTitleCost();

    public Media(String title) {
        this(title, "Unknown", 0.0f);
    }

    public Media(String title, String category, float cost) {
        this.id = nextId++;
        this.title = title == null ? "Untitled" : title.trim();
        this.category = category == null ? "Unknown" : category.trim();
        this.cost = Math.max(0.0f, cost);
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public float getCost() {
        return cost;
    }


    public void setTitle(String title) {
        if (title != null && !title.isBlank()) {
            this.title = title.trim();
        }
    }

    public void setCategory(String category) {
        if (category != null && !category.isBlank()) {
            this.category = category.trim();
        }
    }

    public void setCost(float cost) {
        this.cost = Math.max(0.0f, cost);
    }

    public void displayDetails() {
        System.out.println("--- MEDIA DETAILS ---");
        System.out.println("ID      : " + id);
        System.out.println("Title   : " + title);
        System.out.println("Category: " + category);
        System.out.printf("Cost    : %.2f$%n", cost);
        System.out.println("---------------------");
    }

    @Override
    public String toString() {
        return String.format("[%d] %s | %s | %.2f$", id, title, category, cost);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Media)) {
            return false;
        }
        Media other = (Media) obj;
        return Objects.equals(title == null ? null : title.toLowerCase(),
                other.title == null ? null : other.title.toLowerCase())
                && Objects.equals(category == null ? null : category.toLowerCase(),
                        other.category == null ? null : other.category.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                title == null ? null : title.toLowerCase(),
                category == null ? null : category.toLowerCase());
    }
}
