package hust.soict.hedspi.aims.test;

import hust.soict.hedspi.aims.media.Book;
import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.DigitalVideoDisc;
import hust.soict.hedspi.aims.media.Media;
import hust.soict.hedspi.aims.media.Track;
import java.util.ArrayList;
import java.util.List;

public class PolymorphismDemo {
    public static void main(String[] args) {
        List<Media> mediae = new ArrayList<>();

        Book book = new Book("The Hobbit", "Fantasy", 15.0f);
        DigitalVideoDisc dvd = new DigitalVideoDisc("Avengers", "Action", "Joss Whedon", 143, 20.0f);
        CompactDisc cd = new CompactDisc("Greatest Hits", "Music", 10.0f);
        cd.addTrack(new Track("Intro", 2));
        cd.addTrack(new Track("Main Theme", 5));

        mediae.add(cd);
        mediae.add(dvd);
        mediae.add(book);

        // Iterate and print toString() for each media
        for (Media m : mediae) {
            System.out.println(m.toString());
        }
    }
}
