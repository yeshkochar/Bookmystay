import java.util.*;

abstract class Rm {
    String t; int b; double p;
    Rm(String t, int b, double p) { this.t = t; this.b = b; this.p = p; }
    void disp() { System.out.println(t + " | " + b + " Bed | $" + p); }
}
class Sgl extends Rm { Sgl() { super("Single", 1, 100); } }
class Dbl extends Rm { Dbl() { super("Double", 2, 180); } }
class Ste extends Rm { Ste() { super("Suite", 3, 350); } }
class Inv {
    Map<String, Integer> m = new HashMap<>();
    Inv() { m.put("Single", 5); m.put("Double", 0); m.put("Suite", 2); }
}
class Srch {
    void run(Inv i, List<Rm> list) {
        System.out.println("Available Rooms");
        list.stream().filter(r -> i.m.getOrDefault(r.t, 0) > 0).forEach(r -> {
            r.disp();
            System.out.println("Stock: " + i.m.get(r.t) + "\n");
        });
    }
}
public class Main {
    public static void main(String[] args) {
        Inv i = new Inv();
        List<Rm> catalog = Arrays.asList(new Sgl(), new Dbl(), new Ste());
        new Srch().run(i, catalog);
    }
}