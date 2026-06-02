package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.exception.InvalidMediaException;
import hust.soict.dsai.aims.exception.PlayerException;

public class DigitalVideoDisc extends Disc implements Playable {

    @Override
    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing DVD: " + this.getTitle());
            System.out.println("DVD length: " + this.getLength());
        } else {
            System.err.println("ERROR: DVD length is non-positive!");
            throw new PlayerException("ERROR: DVD length is non-positive!");
        }
    }

    private static int nbDigitalVideoDiscs = 0;
    private String warranty;

    public DigitalVideoDisc(String title) {
        super(++nbDigitalVideoDiscs, title, "Unknown", 0.0f, 0, "Unknown");
        setWarranty("No warranty");
    }

    public DigitalVideoDisc(String title, String category, String director,
                           float cost, int length, String warranty) {
        super(++nbDigitalVideoDiscs, title, category, cost, length, director);
        setWarranty(warranty);
    }

    public DigitalVideoDisc(String title, String category, String director,
                           int length, float cost) {
        super(++nbDigitalVideoDiscs, title, category, cost, length, director);
        setWarranty("No warranty");
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        super(++nbDigitalVideoDiscs, title, category, cost, 0, "Unknown");
        setWarranty("No warranty");
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        if (warranty == null || warranty.trim().isEmpty()) {
            throw new InvalidMediaException("DVD warranty must not be empty.");
        }
        this.warranty = warranty;
    }

    public void displayInfo() {
        System.out.println("Title: " + this.getTitle() + " | Director: " + this.getDirector() + 
                         " | Category: " + this.getCategory() + " | Price: $" + this.getCost());
    }

    @Override
    public String toString() {
        return "DVD - " + this.getTitle() + " - " + this.getCategory() + " - " + this.getDirector() + 
               " - " + this.getLength() + ": " + this.getCost() + " $";
    }

    public boolean isMatch(String searchTitle) {
        if (searchTitle == null) {
            throw new InvalidMediaException("Search title must not be null.");
        }
        return this.getTitle().toLowerCase().contains(searchTitle.toLowerCase());
    }
}
