package hust.soict.hedspi.test.disc;

import hust.soict.hedspi.aims.media.CompactDisc;
import hust.soict.hedspi.aims.media.Track;

public class CompactDiscTest {
    public static void main(String[] args) {
        CompactDisc cd = new CompactDisc("Greatest Hits", "Music", "Various Artists", 14.99f);

        Track track1 = new Track("Track One", 180);
        Track track2 = new Track("Track Two", 210);
        Track track3 = new Track("Track Three", 0);

        cd.addTrack(track1);
        cd.addTrack(track2);
        cd.addTrack(track1);

        System.out.println(cd.toString());
        cd.play();

        System.out.println("\nRemove track2 and add an invalid-length track:");
        cd.removeTrack(track2);
        cd.addTrack(track3);
        cd.play();
    }
}
