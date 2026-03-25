abstract class Room {
    String type;
    int beds;
    double price;

    Room(String t, int b, double p) {
        this.type = t;
        this.beds = b;
        this.price = p;
    }
    void show() {
        System.out.println(type + " Room | " + beds + " Bed(s) | $" + price);
    }
}
class Sgl extends Room { Sgl() { super("Single", 1, 100.0); } }
class Dbl extends Room { Dbl() { super("Double", 2, 180.0); } }
class Ste extends Room { Ste() { super("Suite", 3, 350.0); } }
public class UseCase2RoomInitialization {
    public static void main(String[] args) {
        System.out.println("--- Book My Stay v2.0 ---");
        Room r1 = new Sgl();
        Room r2 = new Dbl();
        Room r3 = new Ste();
        int q1 = 5, q2 = 3, q3 = 2;
        r1.show(); System.out.println("Left: " + q1);
        System.out.println("---");
        r2.show(); System.out.println("Left: " + q2);
        System.out.println("---");
        r3.show(); System.out.println("Left: " + q3);
    }
}