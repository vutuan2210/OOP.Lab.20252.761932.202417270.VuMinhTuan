package hust.soict.hedspi.aims.media;

import java.util.ArrayList;
import java.util.List;

public class Polymorphism {
    public static void main(String[] args) {
        List<Media> mediaList = new ArrayList<>();

        Book cleanCode = new Book("Clean Code", "Programming", 20.5f);
        cleanCode.addAuthor("Robert C. Martin");

        DigitalVideoDisc interstellar = new DigitalVideoDisc(
                "Interstellar", "Sci-Fi", "Christopher Nolan", 169, 15.0f);

        CompactDisc classics = new CompactDisc("Road Trip Songs", "Music", "Various", 12.0f);
        classics.addTrack(new Track("Highway Star", 390));
        classics.addTrack(new Track("Take It Easy", 221));

        mediaList.add(cleanCode);
        mediaList.add(interstellar);
        mediaList.add(classics);

        System.out.println("Media list:");
        for (Media media : mediaList) {
            System.out.println(media);
        }

        System.out.println("\nPlay all playable media:");
        for (Media media : mediaList) {
            if (media instanceof Playable playable) {
                playable.play();
            }
        }
    }
}
