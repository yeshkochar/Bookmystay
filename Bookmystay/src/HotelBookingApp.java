import java.util.*;

// Custom Exception
class InvalidBookingException extends Exception {

    InvalidBookingException(String message) {
        super(message);
    }
}


// Validator class
class InvalidBookingValidator {

    public static void validate(
            String customerName,
            String roomType,
            Map<String, Integer> inventory
    ) throws InvalidBookingException {

        // check name
        if (customerName == null
                || customerName.isEmpty()) {

            throw new InvalidBookingException(
                    "Customer name cannot be empty"
            );
        }

        // check room type exists
        if (!inventory.containsKey(roomType)) {

            throw new InvalidBookingException(
                    "Invalid room type"
            );
        }

        // check availability
        if (inventory.get(roomType) <= 0) {

            throw new InvalidBookingException(
                    "Room not available"
            );
        }
    }
}



// Booking Service
class SafeBookingService {

    Map<String, Integer> inventory =
            new HashMap<>();


    SafeBookingService() {

        inventory.put("Single", 2);
        inventory.put("Double", 1);
    }


    public void bookRoom(
            String name,
            String roomType) {

        try {

            // validation
            InvalidBookingValidator.validate(
                    name,
                    roomType,
                    inventory
            );

            // allocate
            inventory.put(
                    roomType,
                    inventory.get(roomType) - 1
            );

            System.out.println(
                    "Booking confirmed for "
                            + name
                            + " | "
                            + roomType
            );

        } catch (InvalidBookingException e) {

            System.out.println(
                    "Booking Failed → "
                            + e.getMessage()
            );
        }
    }
}



// Demo
public class ValidationDemo {

    public static void main(String[] args) {

        SafeBookingService service =
                new SafeBookingService();

        service.bookRoom(
                "Aman",
                "Single"
        );

        service.bookRoom(
                "",
                "Single"
        );

        service.bookRoom(
                "Rahul",
                "Suite"
        );

        service.bookRoom(
                "Neha",
                "Double"
        );

        service.bookRoom(
                "Priya",
                "Double"
        );
    }
}