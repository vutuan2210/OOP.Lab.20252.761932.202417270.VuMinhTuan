package hust.soict.dsai.test.media;

import hust.soict.dsai.aims.media.Book;
import hust.soict.dsai.aims.media.CD;
import hust.soict.dsai.aims.media.Media;
import hust.soict.dsai.aims.media.DigitalVideoDisc;
import hust.soict.dsai.aims.media.Track;

import java.util.ArrayList;
import java.util.List;

public class PolymorphismToStringTest {
    public static void main(String[] args) {
        List<Media> mediae = new ArrayList<>();

        CD cd = new CD(1, "CD Title", "Music", 9.99f, "Artist A", "Director A");
        cd.addTrack(new Track("Track 1", 180));
        cd.addTrack(new Track("Track 2", 200));

        DigitalVideoDisc dvd = new DigitalVideoDisc("DVD Title", "Movies", "Director B", 14.5f, 120, "1 year");

        Book book = new Book();
        book.setTitle("Book Title");
        book.setCategory("Literature");
        book.setCost(7.25f);
        book.addAuthor("Author 1");

        mediae.add(cd);
        mediae.add(dvd);
        mediae.add(book);

        for (Media m : mediae) {
            System.out.println(m.toString());
        }
    }
}

