package hust.soict.hedspi.aims.media;

import java.util.Comparator;

public class MediaComparatorByCostTitle implements Comparator<Media> {
    @Override
    public int compare(Media left, Media right) {
        int byCost = Float.compare(right.getCost(), left.getCost());
        if (byCost != 0) {
            return byCost;
        }

        String leftTitle = left.getTitle() == null ? "" : left.getTitle();
        String rightTitle = right.getTitle() == null ? "" : right.getTitle();
        return leftTitle.compareToIgnoreCase(rightTitle);
    }
}
