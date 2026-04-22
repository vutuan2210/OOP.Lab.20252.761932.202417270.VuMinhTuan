package hust.soict.hedspi.aims.media;

import java.util.Comparator;

public class MediaComparatorByTitleCost implements Comparator<Media> {
    @Override
    public int compare(Media left, Media right) {
        String leftTitle = left.getTitle() == null ? "" : left.getTitle();
        String rightTitle = right.getTitle() == null ? "" : right.getTitle();

        int byTitle = leftTitle.compareToIgnoreCase(rightTitle);
        if (byTitle != 0) {
            return byTitle;
        }

        return Float.compare(left.getCost(), right.getCost());
    }
}
