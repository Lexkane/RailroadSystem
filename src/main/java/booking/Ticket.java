package booking;

import java.util.Date;

public class Ticket {

    private String name;
    private String journeyName;
    private Date date;
    private String carriageNumber;
    private int place;
    private double price;

    public Ticket(String name, Date date, String carriageNumber, int place,
                  double price) {
        this.name = name;
        this.date = date;
        this.carriageNumber = carriageNumber;
        this.place = place;
        this.price = price;
    }

    public Ticket() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getCarriage() {
        return carriageNumber;
    }

    public void setCarriage(String carriageNumber) {
        this.carriageNumber = carriageNumber;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPlace() {
        return place;
    }

    @Override
    public String toString() {
        return "Journey " + name + " date " + date + " carriage number " + carriageNumber
                + " place " + place + " price $" + price;
    }

    public void setJourneyName(String journeyName) {
        this.journeyName = journeyName;
    }
}
