package booking.journey;

import booking.Ticket;
import booking.exception.NoSuchStationException;
import booking.train.Train;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Journey {
    private String name;
    private Route route;
    private Date date;
    private Train train;
    private Map<String, int[][]> place;

    public Journey(Route route, Date date, Train train) throws NoSuchStationException {
        this.name = generateName(route.getBegin(), route.getEnd());
        this.date = date;
        this.train = train;
        this.route = route;

        this.place = train.getPlaces(route.getStop().size() - 1);
    }

    public Route getRoute() {
        return route;
    }

    public Train getTrain() {
        return train;
    }

    public Map<String, List<Integer>> getAvailablePlaces(String begin, String end) throws NoSuchStationException {
        int beginId = route.getStationId(begin);
        int endId = route.getStationId(end);

        if (beginId == -1 || endId == -1) {
            throw new NoSuchStationException();
        }

        Map<String, List<Integer>> availablePlace = new HashMap<>();
        for (String carriage : place.keySet()) {
            availablePlace.put(carriage, new ArrayList<Integer>());
            for (int i = 0; i < place.get(carriage).length; i++) {
                for (int j = beginId; j < endId; j++) {
                    if (this.place.get(carriage)[i][j] != 0) {
                        break;
                    }
                    if (j == endId - 1) {
                        availablePlace.get(carriage).add(i);
                    }
                }
            }
        }

        return availablePlace;
    }

    public boolean bookPlace(Ticket ticket, String begin, String end) throws NoSuchStationException {
        int beginId = route.getStationId(begin);
        int endId = route.getStationId(end);

        if (beginId == -1 || endId == -1) {
            throw new NoSuchStationException();
        }

        String carriage = ticket.getCarriage();
        int place = ticket.getPlace() - 1;
        for (int i = beginId; i < endId; i++) {
            if (this.place.get(carriage)[place][i] == 0) {
                this.place.get(carriage)[place][i] = 1;
                break;
            } else {
                return false;
            }
        }

        return true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public Map<String, int[][]> getPlace() {
        return place;
    }

    public void setPlace(Map<String, int[][]> place) {
        this.place = place;
    }

    private String generateName(String beginStation, String endStation) throws NoSuchStationException {
        if (beginStation == null || endStation == null) {
            throw new NoSuchStationException();
        }
        return beginStation + "/" + endStation;

    }

}
