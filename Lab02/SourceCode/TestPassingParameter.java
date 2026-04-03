package SourceCode;

public class TestPassingParameter {

    public static void main(String[] args) {

        DigitalVideoDisc jungleDVD = new DigitalVideoDisc(
                "Jungle", "Adventure", "Someone", 120, 10.0f);

        DigitalVideoDisc cinderellaDVD = new DigitalVideoDisc(
                "Cinderella", "Animation", "Disney", 90, 12.0f);

        // Test swap
        swap(jungleDVD, cinderellaDVD);

        // Sau swap
        System.out.println("Jungle DVD title: " + jungleDVD.getTitle());
        System.out.println("Cinderella DVD title: " + cinderellaDVD.getTitle());

        // Test change title
        changeTitle(jungleDVD, cinderellaDVD.getTitle());

        System.out.println("After changing title:");
        System.out.println("Jungle DVD title: " + jungleDVD.getTitle());
    }

    // Hàm swap (KHÔNG hoạt động như mong đợi)
    public static void swap(DigitalVideoDisc dvd1, DigitalVideoDisc dvd2) {
        DigitalVideoDisc temp = dvd1;
        dvd1 = dvd2;
        dvd2 = temp;
    }

    // Hàm thay đổi title
    public static void changeTitle(DigitalVideoDisc dvd, String title) {
        dvd.setTitle(title);
    }
}