package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.exception.InvalidMediaException;

public class DVD extends Media {

    private String director;
    private int length;
    private String warranty;

    public DVD() {
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        if (director == null || director.trim().isEmpty()) {
            throw new InvalidMediaException("DVD director must not be empty.");
        }
        this.director = director;
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
        this.warranty = warranty;
    }
}
