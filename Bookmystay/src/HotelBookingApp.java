import java.io.*;
import java.util.*;


// Reservation class
class Reservation implements Serializable {

    String reservationId;
    String roomType;

    Reservation(String id, String type) {
        reservationId = id;
        roomType = type;
    }
}



// System State (inventory + bookings)
class SystemState implements Serializable {

    Map<String, Integer> inventory =
            new HashMap<>();

    Map<String, Reservation> bookings =
            new HashMap<>();
}



// Persistence Service
class PersistenceService {

    String fileName = "state.dat";


    // save to file
    public void save(SystemState state) {

        try {

            ObjectOutputStream out =
                    new ObjectOutputStream(
                            new FileOutputStream(
                                    fileName
                            )
                    );

            out.writeObject(state);

            out.close();

            System.out.println(
                    "State saved to file"
            );

        } catch (Exception e) {

            System.out.println(
                    "Save error"
            );
        }
    }



    // load from file
    public SystemState load() {

        try {

            ObjectInputStream in =
                    new ObjectInputStream(
                            new FileInputStream(
                                    fileName
                            )
                    );

            SystemState state =
                    (SystemState)
                            in.readObject();

            in.close();

            System.out.println(
                    "State loaded"
            );

            return state;

        } catch (Exception e) {

            System.out.println(
                    "No saved state found"
            );

            return new SystemState();
        }
    }
}



// Demo
public class PersistenceDemo {

    public static void main(String[] args) {

        PersistenceService ps =
                new PersistenceService();

        SystemState state;


        // try loading old state
        state = ps.load();


        // if empty, create new
        if (state.inventory.isEmpty()) {

            state.inventory.put(
                    "Single", 2
            );

            state.inventory.put(
                    "Double", 1
            );
        }


        // add booking
        Reservation r =
                new Reservation(
                        "R101",
                        "Single"
                );

        state.bookings.put(
                r.reservationId,
                r
        );

        state.inventory.put(
                "Single",
                state.inventory.get(
                        "Single"
                ) - 1
        );


        // save before shutdown
        ps.save(state);


        System.out.println(
                "System ready"
        );
    }
}