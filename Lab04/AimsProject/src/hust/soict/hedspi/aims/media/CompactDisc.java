package hust.soict.hedspi.aims.media;

import java.util.ArrayList;

public class CompactDisc extends Disc implements Playable {
    private final String artist;
    private final ArrayList<Track> tracks = new ArrayList<>();

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
    public void displayDetails() {
        System.out.println("--- CD DETAILS ---");
        System.out.println("ID       : " + getId());
        System.out.println("Title    : " + getTitle());
        System.out.println("Category : " + getCategory());
        System.out.println("Artist   : " + artist);
        System.out.println("Director : " + getDirector());
        System.out.println("Length   : " + getLength() + " minutes");
        System.out.printf("Cost     : %.2f$%n", getCost());
        if (tracks.isEmpty()) {
            System.out.println("Tracks   : (none)");
        } else {
            System.out.println("Tracks   :");
            for (int i = 0; i < tracks.size(); i++) {
                Track track = tracks.get(i);
                System.out.println("  " + (i + 1) + ". " + track.getTitle() + " - " + track.getLength());
            }
        }
        System.out.println("------------------");
    }

    @Override
    public String toString() {
        return String.format("CD | %s | %s | artist=%s | %d min | %.2f$",
                getTitle(), getCategory(), artist, getLength(), getCost());
    }

    @Override
    public void play() {
        if (getLength() <= 0) {
            System.out.println("This CD cannot be played because its length is invalid.");
            return;
        }
        System.out.println("Playing CD: " + getTitle() + " (" + getLength() + " minutes)");
        for (Track track : tracks) {
            track.play();
        }
    }
}
