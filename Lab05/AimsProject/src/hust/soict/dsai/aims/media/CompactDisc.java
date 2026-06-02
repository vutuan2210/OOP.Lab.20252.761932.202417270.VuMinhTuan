package hust.soict.dsai.aims.media;

import hust.soict.dsai.aims.exception.InvalidMediaException;
import hust.soict.dsai.aims.exception.PlayerException;
import java.util.ArrayList;
import java.util.List;

public class CompactDisc extends Disc implements Playable {

    @Override
    public void play() throws PlayerException {
        if (this.getLength() > 0) {
            System.out.println("Playing Compact Disc: " + this.getTitle());
            System.out.println("CD length: " + getLength());

            java.util.Iterator<Track> iter = tracks.iterator();
            Track nextTrack;
            while (iter.hasNext()) {
                nextTrack = iter.next();
                try {
                    nextTrack.play();
                } catch (PlayerException e) {
                    throw e;
                }
            }
        } else {
            System.err.println("ERROR: CD length is non-positive!");
            throw new PlayerException("ERROR: CD length is non-positive!");
        }
    }


    private String artist;
    private ArrayList<Track> tracks;

    public CompactDisc() {
        super();
        this.tracks = new ArrayList<>();
    }


    public CompactDisc(int id, String title, String category, float cost,
                         int length, String director,
                         String artist) {
        super(id, title, category, cost, length, director);
        setArtist(artist);
        this.tracks = new ArrayList<>();
    }


    public CompactDisc(int id, String title, String category, float cost,
                         int length, String director,
                         String artist,
                         List<Track> tracks) {
        super(id, title, category, cost, length, director);
        setArtist(artist);
        this.tracks = new ArrayList<>();
        if (tracks != null) {
            for (Track track : tracks) {
                addTrack(track);
            }
        }
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        if (artist == null || artist.trim().isEmpty()) {
            throw new InvalidMediaException("CD artist must not be empty.");
        }
        this.artist = artist;
    }

    public List<Track> getTracks() {
        return new ArrayList<>(tracks);
    }

    public void addTrack(Track track) {
        if (track == null) {
            throw new InvalidMediaException("Track must not be null.");
        }
        if (tracks.contains(track)) {
            throw new InvalidMediaException("Track already exists: " + track.getTitle());
        }
        tracks.add(track);
    }

    public void removeTrack(Track track) {
        if (track == null) {
            throw new InvalidMediaException("Track must not be null.");
        }
        if (!tracks.remove(track)) {
            throw new InvalidMediaException("Track is not listed: " + track.getTitle());
        }
    }
}
