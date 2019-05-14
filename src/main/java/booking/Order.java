package booking;

import booking.journey.Journey;

public class Order {


    public static void makeOrder(String startCity, String endCity, Journey journey) {

    }

    public static TicketBuilder builder() {
        return new TicketBuilder();
    }
}
