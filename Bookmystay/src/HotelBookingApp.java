import java.util.*;

// Reservation class
class Reservation {

    String reservationId;
    String customerName;
    String roomType;

    Reservation(String reservationId,
                String customerName,
                String roomType) {

        this.reservationId = reservationId;
        this.customerName = customerName;
        this.roomType = roomType;
    }

    public String toString() {
        return reservationId + " | "
                + customerName + " | "
                + roomType;
    }
}



// Booking History
class BookingHistory {

    // maintains insertion order
    LinkedList<Reservation> history =
            new LinkedList<>();


    // add confirmed booking
    public void addReservation(
            Reservation r) {

        history.add(r);

        System.out.println(
                "Added to history → "
                        + r.reservationId
        );
    }


    // get all reservations
    public List<Reservation> getAll() {
        return history;
    }
}



// Report Service
class BookingReportService {

    BookingHistory history;

    BookingReportService(
            BookingHistory history) {

        this.history = history;
    }


    // show all bookings
    public void showAllBookings() {

        System.out.println(
                "\nBooking History Report"
        );

        for (Reservation r :
                history.getAll()) {

            System.out.println(r);
        }
    }


    // count by room type
    public void countByRoomType() {

        HashMap<String, Integer> map =
                new HashMap<>();

        for (Reservation r :
                history.getAll()) {

            map.put(
                    r.roomType,
                    map.getOrDefault(
                            r.roomType, 0
                    ) + 1
            );
        }

        System.out.println(
                "\nRoom Type Report"
        );

        for (String type :
                map.keySet()) {

            System.out.println(
                    type + " → "
                            + map.get(type)
            );
        }
    }
}



// Demo / Admin
public class BookingHistoryDemo {

    public static void main(String[] args) {

        BookingHistory history =
                new BookingHistory();

        BookingReportService report =
                new BookingReportService(
                        history
                );


        // confirmed bookings
        history.addReservation(
                new Reservation(
                        "R101",
                        "Aman",
                        "Single"
                )
        );

        history.addReservation(
                new Reservation(
                        "R102",
                        "Rahul",
                        "Double"
                )
        );

        history.addReservation(
                new Reservation(
                        "R103",
                        "Neha",
                        "Single"
                )
        );


        // admin requests report
        report.showAllBookings();

        report.countByRoomType();
    }
}