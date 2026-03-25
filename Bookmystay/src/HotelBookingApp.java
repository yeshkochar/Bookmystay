import java.util.HashMap;
import java.util.Map;
class RoomInv {
    private Map<String, Integer> inv = new HashMap<>();
    public RoomInv() {
        inv.put("Single", 5);
        inv.put("Double", 3);
        inv.put("Suite", 2);
    }
    public int get(String t) {
        return inv.getOrDefault(t, 0);
    }
    public void set(String t, int q) {
        if (inv.containsKey(t)) inv.put(t, q);
    }
    public void disp() {
        System.out.println("--- Inv Status ---");
        inv.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println("------------------");
    }
}
public class Main {
    public static void main(String[] args) {
        System.out.println("Book My Stay v3.0\n");
        RoomInv ri = new RoomInv();
        ri.disp();
        System.out.println("\nBooking 1 Suite...");
        ri.set("Suite", ri.get("Suite") - 1);
        ri.disp();
    }
}