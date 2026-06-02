package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.exception.InvalidMediaException;
import hust.soict.dsai.aims.exception.PlayerException;
import java.util.Objects;

public class Track implements Playable {
    private String title;
    private int length;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Track other = (Track) obj;

        if (this.title == null) {
            if (other.title != null) return false;
        } else if (!this.title.equals(other.title)) {
            return false;
        }
        return this.length == other.length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, length);
    }

    public Track() {
    }

    public Track(String title, int length) {
        if (title == null || title.trim().isEmpty()) {
            throw new InvalidMediaException("Track title must not be empty.");
        }
        if (length <= 0) {
            throw new InvalidMediaException("Track length must be positive.");
        }
        this.title = title;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    // Kept for compatibility with CD.play() in this project
    public void play() throws PlayerException {
        if (length <= 0) {
            System.err.println("ERROR: Track length is non-positive!");
            throw new PlayerException("ERROR: Track length is non-positive!");
        }
        System.out.println("Playing track: " + title);
        System.out.println("Track length: " + length);
    }
}
