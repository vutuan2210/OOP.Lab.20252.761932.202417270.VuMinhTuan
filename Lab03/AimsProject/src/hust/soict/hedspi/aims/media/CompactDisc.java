package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class CompactDisc extends Disc implements Playable {
    private String artist;
    private final List<Track> tracks = new ArrayList<>();

    public CompactDisc(String title) {
        super(title);
        this.artist = "Unknown";
    }

    public CompactDisc(String title, String category, String director, int length, float cost, String artist) {
        super(title, category, director, length, cost);
        this.artist = artist == null ? "Unknown" : artist.trim();
    }

    public CompactDisc(String title, String category, float cost) {
        super(title, category, cost);
        this.artist = "Unknown";
    }

    public CompactDisc(String title, String category, String director, float cost) {
        super(title, category, director, cost);
        this.artist = "Unknown";
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        if (artist != null && !artist.isBlank()) {
            this.artist = artist.trim();
        }
    }

    public void addTrack(Track track) {
        if (track == null) {
            return;
        }
        if (!tracks.contains(track)) {
            tracks.add(track);
            System.out.println("Added track: " + track.getTitle());
            return;
        }
        System.out.println("Track already exists in this CD.");
    }

    public void removeTrack(Track track) {
        if (track == null) {
            return;
        }
        if (tracks.remove(track)) {
            System.out.println("Removed track: " + track.getTitle());
            return;
        }
        System.out.println("Track does not exist.");
    }

    @Override
    public int getLength() {
        int totalLength = 0;
        for (Track track : tracks) {
            totalLength += track.getLength();
        }
        return totalLength;
    }

    @Override
    public String toString() {
        return String.format("CD | %s | %s | artist=%s | %d min | %.2f$",
                getTitle(), getCategory(), artist, getLength(), getCost());
    }

    @Override
    public void play() {
        if (tracks.isEmpty()) {
            System.out.println("CD has no tracks to play.");
            return;
        }
        System.out.println("Playing CD: " + getTitle() + " - " + artist);
        for (Track track : tracks) {
            track.play();
        }
    }
}
