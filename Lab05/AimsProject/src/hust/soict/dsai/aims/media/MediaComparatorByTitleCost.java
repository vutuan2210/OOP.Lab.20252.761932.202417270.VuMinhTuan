package hust.soict.dsai.aims.media;

import java.util.Comparator;

public class MediaComparatorByTitleCost implements Comparator<Media> {
    @Override
    public int compare(Media a, Media b) {
        if (a == b) return 0;
        if (a == null) return 1;
        if (b == null) return -1;

        String titleA = a.getTitle();
        String titleB = b.getTitle();

        int cmpTitle;
        if (titleA == null && titleB == null) {
            cmpTitle = 0;
        } else if (titleA == null) {
            cmpTitle = -1;
        } else if (titleB == null) {
            cmpTitle = 1;
        } else {
            cmpTitle = titleA.compareToIgnoreCase(titleB);
        }

        if (cmpTitle != 0) return cmpTitle;

        // Same title: higher cost first
        return Float.compare(b.getCost(), a.getCost());
    }
}

