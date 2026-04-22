package hust.soict.hedspi.aims.media;

public class Disc extends Media {
    private String director;
    private int length;

    public Disc(String title) {
        this(title, "Unknown", "Unknown", 0, 0.0f);
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

    public void setDirector(String director) {
        if (director != null && !director.isBlank()) {
            this.director = director.trim();
        }
    }

    public void setLength(int length) {
        this.length = Math.max(0, length);
    }

    public int getLength() {
        return length;
    }
}
