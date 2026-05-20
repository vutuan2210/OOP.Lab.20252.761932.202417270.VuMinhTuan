package hust.soict.hedspi.lab02;

public class Aims {
        public static void main(String[] args) {

                // Create a new cart
                Cart anOrder = new Cart();

                // Create DVDs
                DigitalVideoDisc dvd1 = new DigitalVideoDisc(
                                "The Lion King", "Animation", "Roger Allers", 87, 19.95f);

                DigitalVideoDisc dvd2 = new DigitalVideoDisc(
                                "Star Wars", "Science Fiction", "George Lucas", 87, 24.95f);

                DigitalVideoDisc dvd3 = new DigitalVideoDisc(
                                "Aladin", "Animation", 18.99f);

                // Add DVDs
                anOrder.addDigitalVideoDisc(dvd1);
                anOrder.addDigitalVideoDisc(dvd2);
                anOrder.addDigitalVideoDisc(dvd3);

                // Print total cost before removal
                System.out.println("Total cost before removal:");
                System.out.println(anOrder.totalCost());

                // Remove one DVD (dvd2)
                anOrder.removeDigitalVideoDisc(dvd2);

                // Print total cost after removal
                System.out.println("Total cost after removal:");
                System.out.println(anOrder.totalCost());
        }
}
