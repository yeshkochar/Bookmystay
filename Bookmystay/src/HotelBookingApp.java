import java.util.*;
class Res {
    String n, t;
    Res(String n, String t) { this.n = n; this.t = t; }
    void p() { System.out.println(n + " -> " + t); }
}
class BookQ {
    Queue<Res> q = new LinkedList<>();
    void add(Res r) {
        q.add(r);
        System.out.println("Queued: " + r.n);
    }
    void list() {
        System.out.println("\n--- FIFO Queue ---");
        q.forEach(Res::p);
        System.out.println("------------------\n");
    }
}
public class UseCase5BookingRequestQueue {
    public static void main(String[] args) {
        BookQ bq = new BookQ();
        bq.add(new Res("Alice", "Suite"));
        bq.add(new Res("Bob", "Single"));
        bq.add(new Res("Charlie", "Double"));
        bq.list();
    }
}