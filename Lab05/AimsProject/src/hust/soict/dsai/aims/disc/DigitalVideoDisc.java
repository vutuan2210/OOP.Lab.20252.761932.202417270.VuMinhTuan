package hust.soict.dsai.aims.disc;

import hust.soict.dsai.aims.exception.InvalidMediaException;

public class DigitalVideoDisc {
    private static int nbDigitalVideoDiscs = 0;
    private int id;
    private String title;
    private String categories;
    private String director;
    private float cost;
    private int length;
    private String warranty;

    public DigitalVideoDisc(String title) {
        this.id = ++nbDigitalVideoDiscs;
        setTitle(title);
        setCategories("Unknown");
        setDirector("Unknown");
        setCost(0.0f);
        setLength(0);
        setWarranty("No warranty");
    }

    public DigitalVideoDisc(String title, String categories, String director,
                           float cost, int length, String warranty) {
        this.id = ++nbDigitalVideoDiscs;
        setTitle(title);
        setCategories(categories);
        setDirector(director);
        setCost(cost);
        setLength(length);
        setWarranty(warranty);
    }

    public DigitalVideoDisc(String title, String categories, String director,
                           int length, float cost) {
        this.id = ++nbDigitalVideoDiscs;
        setTitle(title);
        setCategories(categories);
        setDirector(director);
        setCost(cost);
        setLength(length);
        setWarranty("No warranty");
    }

    public DigitalVideoDisc(String title, String categories, float cost) {
        this.id = ++nbDigitalVideoDiscs;
        setTitle(title);
        setCategories(categories);
        setDirector("Unknown");
        setCost(cost);
        setLength(0);
        setWarranty("No warranty");
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public void setTitle(String title) {
        validateText(title, "DVD title");
        this.title = title;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        validateText(categories, "DVD category");
        this.categories = categories;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        validateText(director, "DVD director");
        this.director = director;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        if (cost < 0) {
            throw new InvalidMediaException("DVD cost must be non-negative.");
        }
        this.cost = cost;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        if (length < 0) {
            throw new InvalidMediaException("DVD length must be non-negative.");
        }
        this.length = length;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        validateText(warranty, "DVD warranty");
        this.warranty = warranty;
    }

    public void displayInfo() {
        System.out.println("Title: " + title + " | Director: " + director + 
                         " | Category: " + categories + " | Price: $" + cost);
    }

    @Override
    public String toString() {
        return "DVD - " + title + " - " + categories + " - " + director + 
               " - " + length + ": " + cost + " $";
    }

    public boolean isMatch(String searchTitle) {
        validateText(searchTitle, "Search title");
        return title.toLowerCase().contains(searchTitle.toLowerCase());
    }

    private void validateText(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidMediaException(fieldName + " must not be empty.");
        }
    }
}
