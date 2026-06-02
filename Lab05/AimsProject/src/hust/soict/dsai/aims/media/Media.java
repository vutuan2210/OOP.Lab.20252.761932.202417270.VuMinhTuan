package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.exception.InvalidMediaException;
import java.util.Comparator;
import java.util.Objects;

public abstract class Media {

    private int id;
    private String title;
    private String category;
    private float cost;

    public static final Comparator<Media> COMPARE_BY_TITLE_COST =
            new MediaComparatorByTitleCost();
    public static final Comparator<Media> COMPARE_BY_COST_TITLE =
            new MediaComparatorByCostTitle();


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Media)) return false;

        Media other = (Media) obj;
        if (this.title == null) return other.title == null;
        return this.title.equals(other.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title);
    }

    public Media() {
    }

    public Media(int id, String title, String category, float cost) {
        setId(id);
        setTitle(title);
        setCategory(category);
        setCost(cost);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id < 0) {
            throw new InvalidMediaException("Media id must be non-negative.");
        }
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidMediaException("Media title must not be empty.");
        }
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            throw new InvalidMediaException("Media category must not be empty.");
        }
        this.category = category;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        if (cost < 0) {
            throw new InvalidMediaException("Media cost must be non-negative.");
        }
        this.cost = cost;
    }
}
