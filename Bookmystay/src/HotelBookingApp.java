import java.util.*;


// Reservation class
class Reservation {

    String reservationId;
    String roomType;
    String roomId;

    Reservation(String reservationId,
                String roomType,
                String roomId) {

        this.reservationId = reservationId;
        this.roomType = roomType;
        this.roomId = roomId;
    }
}



// Booking History
class BookingHistory {

    Map<String, Reservation> history =
            new HashMap<>();

    public void add(Reservation r) {
        history.put(r.reservationId, r);
    }

    public Reservation get(String id) {
        return history.get(id);
    }

    public void remove(String id) {
        history.remove(id);
    }
}



// Inventory Service
class InventoryService {

    Map<String, Integer> inventory =
            new HashMap<>();

    InventoryService() {
        inventory.put("Single", 1);
        inventory.put("Double", 1);
    }

    public void increase(String type) {
        inventory.put(
                type,
                inventory.get(type) + 1
        );
    }

    public void decrease(String type) {
        inventory.put(
                type,
                inventory.get(type) - 1
        );
    }
}



// Cancellation Service
class CancellationService {

    BookingHistory history;
    InventoryService inventory;

    // rollback stack (LIFO)
    Stack<String> rollbackStack =
            new Stack<>();


    CancellationService(
            BookingHistory h,
            InventoryService i) {

        history = h;
        inventory = i;
    }


    public void cancel(String reservationId) {

        // validate
        Reservation r =
                history.get(reservationId);

        if (r == null) {

            System.out.println(
                    "Cancellation Failed → Not found"
            );
            return;
        }


        // push to stack (rollback record)
        rollbackStack.push(r.roomId);


        // restore inventory
        inventory.increase(r.roomType);


        // remove from history
        history.remove(reservationId);


        System.out.println(
                "Cancelled → "
                        + reservationId
        );
    }


    // show rollback stack
    public void showRollback() {

        System.out.println(
                "Rollback Stack: "
                        + rollbackStack
        );
    }
}



// Demo
public class CancellationDemo {

    public static void main(String[] args) {

        BookingHistory history =
                new BookingHistory();

        InventoryService inventory =
                new InventoryService();

        CancellationService cancel =
                new CancellationService(
                        history,
                        inventory
                );


        // confirmed bookings
        Reservation r1 =
                new Reservation(
                        "R101",
                        "Single",
                        "S1"
                );

        history.add(r1);
        inventory.decrease("Single");


        // cancel
        cancel.cancel("R101");

        cancel.cancel("R200"); // invalid

        cancel.showRollback();
    }
}