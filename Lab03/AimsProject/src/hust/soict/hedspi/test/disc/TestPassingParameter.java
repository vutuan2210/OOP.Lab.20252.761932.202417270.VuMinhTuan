package hust.soict.hedspi.test.disc;

import hust.soict.hedspi.aims.media.DigitalVideoDisc;

public class TestPassingParameter {

    public static void main(String[] args) {
        DigitalVideoDisc first = new DigitalVideoDisc("Jungle");
        DigitalVideoDisc second = new DigitalVideoDisc("Cinderella");

        swap(first, second);
        System.out.println("After swap (FAIL - pass by value):");
        System.out.println("first title  = " + first.getTitle());
        System.out.println("second title = " + second.getTitle());

        DigitalVideoDisc[] firstRef = { new DigitalVideoDisc("Jungle") };
        DigitalVideoDisc[] secondRef = { new DigitalVideoDisc("Cinderella") };

        trySwap(firstRef, secondRef);
        System.out.println("After trySwap (SUCCESS - using array wrapper):");
        System.out.println("first title  = " + firstRef[0].getTitle());
        System.out.println("second title = " + secondRef[0].getTitle());
    }

    public static void trySwap(DigitalVideoDisc[] left, DigitalVideoDisc[] right) {
        DigitalVideoDisc temp = left[0];
        left[0] = right[0];
        right[0] = temp;
    }

    public static void swap(DigitalVideoDisc first, DigitalVideoDisc second) {
        DigitalVideoDisc temp = first;
        first = second;
        second = temp;
        System.out.println("Inside swap (local only): first=" + first.getTitle() + ", second=" + second.getTitle());
    }

    public static void rename(DigitalVideoDisc dvd, String title) {
        String previous = dvd.getTitle();
        dvd.setTitle(title);
        DigitalVideoDisc replacedLocally = new DigitalVideoDisc(previous);
        System.out.println("Inside rename (local replacement): " + replacedLocally.getTitle());
    }
}