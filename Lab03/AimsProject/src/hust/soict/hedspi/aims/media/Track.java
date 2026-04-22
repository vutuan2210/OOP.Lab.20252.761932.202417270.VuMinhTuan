package hust.soict.hedspi.aims.media;

import java.util.Objects;

public class Track implements Playable {
    private final String title;
    private final int length;

    public Track(String title, int length) {
        this.title = title == null ? "Untitled track" : title.trim();
        this.length = Math.max(0, length);
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    @Override
    public void play() {
        if (length <= 0) {
            System.out.println("Skipping track '" + title + "' due to invalid length.");
            return;
        }
        System.out.println("Track: " + title + " (" + length + "s)");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Track)) {
            return false;
        }
        Track other = (Track) obj;
        return length == other.length && Objects.equals(title, other.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, length);
    }
}
