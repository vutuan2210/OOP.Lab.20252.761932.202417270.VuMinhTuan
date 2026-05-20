package hust.soict.hedspi.aims.media;

public abstract class Disc extends Media {
    private String director;
    private int length;

    public Disc(String title) {
        super(title);
        this.director = "Unknown";
        this.length = 0;
    }

    public Disc(String title, String category, String director, int length, float cost) {
        super(title, category, cost);
        this.director = director == null ? "Unknown" : director.trim();
        this.length = Math.max(0, length);
    }

    public Disc(String title, String category, String director, float cost) {
        this(title, category, director, 0, cost);
    }

    public Disc(String title, String category, float cost) {
        this(title, category, "Unknown", 0, cost);
    }

    public String getDirector() {
        return director;
    }

    public int getLength() {
        return length;
    }
}
