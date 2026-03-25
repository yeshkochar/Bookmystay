import java.util.*;


// Booking Request
class BookingRequest {

    String name;
    String roomType;

    BookingRequest(String name, String roomType) {
        this.name = name;
        this.roomType = roomType;
    }
}



// Shared Inventory
class InventoryService {

    Map<String, Integer> inventory =
            new HashMap<>();

    InventoryService() {
        inventory.put("Single", 2);
        inventory.put("Double", 1);
    }


    // critical section
    public synchronized boolean allocate(
            String roomType) {

        if (inventory.get(roomType) > 0) {

            inventory.put(
                    roomType,
                    inventory.get(roomType) - 1
            );

            return true;
        }

        return false;
    }
}



// Shared Queue
class BookingQueue {

    Queue<BookingRequest> queue =
            new LinkedList<>();


    public synchronized void add(
            BookingRequest r) {

        queue.add(r);
    }


    public synchronized BookingRequest get() {

        if (queue.isEmpty())
            return null;

        return queue.poll();
    }
}



// Processor Thread
class BookingProcessor extends Thread {

    BookingQueue queue;
    InventoryService inventory;

    BookingProcessor(
            BookingQueue q,
            InventoryService i) {

        queue = q;
        inventory = i;
    }


    public void run() {

        while (true) {

            BookingRequest r =
                    queue.get();

            if (r == null)
                break;

            boolean ok =
                    inventory.allocate(
                            r.roomType
                    );

            if (ok) {

                System.out.println(
                        Thread.currentThread()
                                .getName()
                                + " booked "
                                + r.roomType
                                + " for "
                                + r.name
                );

            } else {

                System.out.println(
                        Thread.currentThread()
                                .getName()
                                + " failed for "
                                + r.name
                );
            }
        }
    }
}



// Demo
public class ConcurrentBookingDemo {

    public static void main(String[] args)
            throws Exception {

        BookingQueue queue =
                new BookingQueue();

        InventoryService inventory =
                new InventoryService();


        // multiple guests
        queue.add(
                new BookingRequest(
                        "Aman", "Single"
                )
        );

        queue.add(
                new BookingRequest(
                        "Rahul", "Single"
                )
        );

        queue.add(
                new BookingRequest(
                        "Neha", "Single"
                )
        );

        queue.add(
                new BookingRequest(
                        "Priya", "Double"
                )
        );



        // multiple threads
        BookingProcessor t1 =
                new BookingProcessor(
                        queue, inventory
                );

        BookingProcessor t2 =
                new BookingProcessor(
                        queue, inventory
                );

        BookingProcessor t3 =
                new BookingProcessor(
                        queue, inventory
                );


        t1.start();
        t2.start();
        t3.start();


        t1.join();
        t2.join();
        t3.join();


        System.out.println(
                "All bookings processed safely"
        );
    }
}