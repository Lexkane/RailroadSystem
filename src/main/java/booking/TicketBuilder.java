package booking;

import booking.train.types.TypeOfCarriage;
import booking.train.types.TypeOfEngine;

import java.util.Date;

public class TicketBuilder {
    private Ticket ticket;

    TicketBuilder() {
        ticket = new Ticket();
    }

    public TicketBuilder setName(String name) {
        ticket.setName(name);
        return this;
    }

    public TicketBuilder setJourney(String journeyName) {
        ticket.setJourneyName(journeyName);
        return this;
    }

    public TicketBuilder setCarriage(String carriage) {
        ticket.setCarriage(carriage);
        return this;
    }

    public TicketBuilder setPlace(Integer place) {
        ticket.setPlace(place);
        return this;
    }

    public TicketBuilder setDate(Date date) {
        ticket.setDate(date);
        return this;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public TicketBuilder generatePrice(TypeOfCarriage carriageType, TypeOfEngine engineType, Integer routeDistance) {
        double totalPrice;
        if (engineType == TypeOfEngine.LIGHT) {
            if (carriageType == TypeOfCarriage.RESERVED_SEAT) {
                totalPrice = routeDistance / 10.0;
            } else {
                totalPrice = routeDistance / 5.0;
            }
        } else {
            if (carriageType == TypeOfCarriage.RESERVED_SEAT) {
                totalPrice = routeDistance / 2.0;
            } else {
                totalPrice = routeDistance;
            }
        }
        ticket.setPrice(totalPrice);
        return this;
    }
}
