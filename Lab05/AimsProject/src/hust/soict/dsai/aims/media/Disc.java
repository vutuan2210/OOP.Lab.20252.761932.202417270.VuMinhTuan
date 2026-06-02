package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.exception.InvalidMediaException;

public abstract class Disc extends Media {

    private int length;
    private String director;

    public Disc() {
    }

    public Disc(int id, String title, String category, float cost, int length, String director) {
        super(id, title, category, cost);
        setLength(length);
        setDirector(director);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        if (length < 0) {
            throw new InvalidMediaException("Disc length must be non-negative.");
        }
        this.length = length;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        if (director == null || director.trim().isEmpty()) {
            throw new InvalidMediaException("Disc director must not be empty.");
        }
        this.director = director;
    }
}
