package SourceCode;

public class TestClassMembers {
    public static void main(String[] args) {
        System.out.println("Initial count: " + DigitalVideoDisc.getNbDigitalVideoDiscs());
        
        DigitalVideoDisc dvd1 = new DigitalVideoDisc("The Lion King", "Animation", "Roger Allers", 87, 19.95f);
        System.out.println("DVD1 ID: " + dvd1.getId() + ", Count: " + DigitalVideoDisc.getNbDigitalVideoDiscs());
        
        DigitalVideoDisc dvd2 = new DigitalVideoDisc("Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);
        System.out.println("DVD2 ID: " + dvd2.getId() + ", Count: " + DigitalVideoDisc.getNbDigitalVideoDiscs());
        
        DigitalVideoDisc dvd3 = new DigitalVideoDisc("Aladin", "Animation", 18.99f);
        System.out.println("DVD3 ID: " + dvd3.getId() + ", Count: " + DigitalVideoDisc.getNbDigitalVideoDiscs());
    }
}
