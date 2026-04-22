package hust.soict.hedspi.aims.media;

public class DigitalVideoDisc extends Disc implements Playable {

    public DigitalVideoDisc(String title) {
        super(title);
    }

    public DigitalVideoDisc(String title, String category, float cost) {
        super(title, category, cost);
    }

    public DigitalVideoDisc(String title, String category, String director, float cost) {
        super(title, category, director, cost);
    }

    public DigitalVideoDisc(String title, String category, String director, int length, float cost) {
        super(title, category, director, length, cost);
    }

    @Override
    public String toString() {
        return String.format("DVD | %s | %s | dir=%s | %d min | %.2f$",
                getTitle(), getCategory(), getDirector(), getLength(), getCost());
    }

    public boolean isMatch(String title) {
        if (title == null) {
            return false;
        }
        return getTitle().toLowerCase().contains(title.toLowerCase());
    }

    @Override
    public void play() {
        if (getLength() <= 0) {
            System.out.println("Cannot play this DVD because its length is invalid.");
            return;
        }
        System.out.println("Playing DVD: " + getTitle());
        System.out.println("Duration: " + getLength() + " minutes");
    }
}
