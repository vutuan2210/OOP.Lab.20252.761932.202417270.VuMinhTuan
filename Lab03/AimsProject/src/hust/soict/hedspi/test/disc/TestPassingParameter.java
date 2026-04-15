package hust.soict.hedspi.test.disc;

import hust.soict.hedspi.aims.disc.DigitalVideoDisc;

public class TestPassingParameter {

    public static void main(String[] args) {

        DigitalVideoDisc jungleDVD = new DigitalVideoDisc(
                "Jungle", "Adventure", "Someone", 120, 10.0f);

        DigitalVideoDisc cinderellaDVD = new DigitalVideoDisc(
                "Cinderella", "Animation", "Disney", 90, 12.0f);

        System.out.println("Before swap:");
        System.out.println("jungleDVD title: " + jungleDVD.getTitle());
        System.out.println("cinderellaDVD title: " + cinderellaDVD.getTitle());

        swap(jungleDVD, cinderellaDVD);

        System.out.println("After swap(jungleDVD, cinderellaDVD):");
        System.out.println("jungleDVD title: " + jungleDVD.getTitle());
        System.out.println("cinderellaDVD title: " + cinderellaDVD.getTitle());

        DigitalVideoDisc[] swapped = swapCorrectly(jungleDVD, cinderellaDVD);
        jungleDVD = swapped[0];
        cinderellaDVD = swapped[1];

        System.out.println("After swapCorrectly and re-assignment:");
        System.out.println("jungleDVD title: " + jungleDVD.getTitle());
        System.out.println("cinderellaDVD title: " + cinderellaDVD.getTitle());

        changeTitle(jungleDVD, cinderellaDVD.getTitle());

        System.out.println("After changing title:");
        System.out.println("jungleDVD title: " + jungleDVD.getTitle());
        System.out.println("cinderellaDVD title: " + cinderellaDVD.getTitle());
    }

    // This does not swap caller references because Java is pass-by-value.
    public static void swap(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
        DigitalVideoDisc temp = dvd1;
        dvd1 = dvd2;
        dvd2 = temp;
    }

    // Correct approach: return swapped references and assign them in the caller.
    public static DigitalVideoDisc[] swapCorrectly(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
        return new DigitalVideoDisc[] { dvd2, dvd1 };
    }

    public static void changeTitle(DigitalVideoDisc dvd, String title) {
        dvd.setTitle(title);
    }
}

