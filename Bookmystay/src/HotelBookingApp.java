import java.util.*;

// Add-On Service class
class AddOnService {

    String serviceName;
    int price;

    AddOnService(String serviceName, int price) {
        this.serviceName = serviceName;
        this.price = price;
    }
}


// Manager for Add-On Services
class AddOnServiceManager {

    // reservationId -> list of services
    HashMap<String, List<AddOnService>> serviceMap = new HashMap<>();


    // add service to reservation
    public void addService(String reservationId, AddOnService service) {

        serviceMap.putIfAbsent(reservationId, new ArrayList<>());

        serviceMap.get(reservationId).add(service);

        System.out.println(
                service.serviceName +
                        " added to reservation " +
                        reservationId
        );
    }


    // calculate total add-on cost
    public int getTotalCost(String reservationId) {

        int total = 0;

        List<AddOnService> list =
                serviceMap.getOrDefault(
                        reservationId,
                        new ArrayList<>()
                );

        for (AddOnService s : list) {
            total += s.price;
        }

        return total;
    }


    // display services
    public void showServices(String reservationId) {

        List<AddOnService> list =
                serviceMap.getOrDefault(
                        reservationId,
                        new ArrayList<>()
                );

        System.out.println(
                "Services for Reservation "
                        + reservationId
        );

        for (AddOnService s : list) {
            System.out.println(
                    s.serviceName + " - " + s.price
            );
        }
    }
}



// Demo class
public class AddOnDemo {

    public static void main(String[] args) {

        AddOnServiceManager manager =
                new AddOnServiceManager();

        String reservationId = "R101";


        // guest selects add-ons
        manager.addService(
                reservationId,
                new AddOnService("Breakfast", 500)
        );

        manager.addService(
                reservationId,
                new AddOnService("Airport Pickup", 800)
        );

        manager.addService(
                reservationId,
                new AddOnService("Extra Bed", 700)
        );


        // show services
        manager.showServices(reservationId);


        // total cost
        int total =
                manager.getTotalCost(reservationId);

        System.out.println(
                "Total Add-On Cost = " + total
        );
    }
}