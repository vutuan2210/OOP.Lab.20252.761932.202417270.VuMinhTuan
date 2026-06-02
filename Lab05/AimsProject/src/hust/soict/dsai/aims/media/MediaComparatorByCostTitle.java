package hust.soict.dsai.aims.media;

import java.util.Comparator;

public class MediaComparatorByCostTitle implements Comparator<Media> {
    @Override
    public int compare(Media a, Media b) {
        if (a == b) return 0;
        if (a == null) return 1;
        if (b == null) return -1;

        // Decreasing cost
        int cmpCost = Float.compare(b.getCost(), a.getCost());
        if (cmpCost != 0) return cmpCost;

        // Same cost: alphabetical title
        String titleA = a.getTitle();
        String titleB = b.getTitle();

        if (titleA == null && titleB == null) return 0;
        if (titleA == null) return -1;
        if (titleB == null) return 1;
        return titleA.compareToIgnoreCase(titleB);
    }
}

