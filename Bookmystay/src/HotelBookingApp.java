import java.util.*;

// Booking Request class
class BookingRequest {
    String customerName;
    String roomType;

    BookingRequest(String customerName, String roomType) {
        this.customerName = customerName;
        this.roomType = roomType;
    }
}

// Inventory Service
class InventoryService {

    // roomType -> available count
    HashMap<String, Integer> inventory = new HashMap<>();

    // roomType -> set of allocated room IDs
    HashMap<String, Set<String>> allocatedRooms = new HashMap<>();

    public InventoryService() {
        inventory.put("Single", 2);
        inventory.put("Double", 2);
        inventory.put("Suite", 1);

        allocatedRooms.put("Single", new HashSet<>());
        allocatedRooms.put("Double", new HashSet<>());
        allocatedRooms.put("Suite", new HashSet<>());
    }

    // check availability
    public boolean isAvailable(String roomType) {
        return inventory.get(roomType) > 0;
    }

    // allocate room
    public String allocateRoom(String roomType) {

        if (!isAvailable(roomType)) {
            return null;
        }

        String roomId;

        // generate unique room ID
        do {
            roomId = roomType.substring(0,1) + (int)(Math.random() * 1000);
        }
        while (allocatedRooms.get(roomType).contains(roomId));

        // store in set (no duplicate)
        allocatedRooms.get(roomType).add(roomId);

        // decrement inventory
        inventory.put(roomType, inventory.get(roomType) - 1);

        return roomId;
    }
}


// Booking Service
class BookingService {

    Queue<BookingRequest> requestQueue = new LinkedList<>();
    InventoryService inventoryService = new InventoryService();

    // add booking request (FIFO)
    public void addRequest(String name, String roomType) {
        requestQueue.add(new BookingRequest(name, roomType));
    }

    // process booking
    public void processBookings() {

        while (!requestQueue.isEmpty()) {

            BookingRequest req = requestQueue.poll();

            System.out.println("Processing: " + req.customerName);

            if (inventoryService.isAvailable(req.roomType)) {

                String roomId =
                        inventoryService.allocateRoom(req.roomType);

                System.out.println(
                        "Reservation Confirmed → "
                                + req.customerName +
                                " | Room Type: " + req.roomType +
                                " | Room ID: " + roomId
                );

            } else {

                System.out.println(
                        "Reservation Failed → No "
                                + req.roomType + " rooms available"
                );
            }
        }
    }
}


// Main class
public class HotelReservationSystem {

    public static void main(String[] args) {

        BookingService service = new BookingService();

        // FIFO queue
        service.addRequest("Aman", "Single");
        service.addRequest("Rahul", "Single");
        service.addRequest("Priya", "Single"); // should fail
        service.addRequest("Neha", "Suite");

        service.processBookings();
    }
}