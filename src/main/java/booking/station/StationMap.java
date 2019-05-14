package booking.station;

import booking.exception.IllegalDirectionException;
import booking.exception.NoSuchStationException;
import booking.journey.Journey;
import booking.journey.Route;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class StationMap {
    private StationSet<Station> station;

    public StationSet<Station> getStation() {
        return station;
    }

    public void setStation(StationSet<Station> station) {
        this.station = station;
    }

    public StationMap(List<Route> map, List<Station> stationList) {
        this.station = new StationSet<>();

        for (Station stationName : stationList) {
            this.station.add(stationName);
        }

        for (Object station : this.station) {
            for (Object nextStation : this.station) {
                for (Route route : map) {
                    try {
                        if (route.getDistance(((Station) station).getName(), ((Station) nextStation).getName()) != 0) {
                            ((Station) station).addConnection((Station) nextStation);
                        }
                    } catch (NoSuchStationException | IllegalDirectionException e) {
                        continue;
                    }
                }
            }
        }
    }

    public static List<List<Journey>> getAllJourneys(Station stationFrom, Station stationTo, List<Journey> journeys) {
        List<List<Journey>> result = new ArrayList<>();
//        List<List<String>> path = getAllPaths(stationFrom, stationTo);
        //for all pairs find validJourneys
        return result;
    }

    public void getAllPaths(Station stationFrom, Station stationTo, LinkedList<Station> visited, boolean destination) {
        boolean currentDestination;

        for (Station station : stationFrom.getConnection()) {
            if (visited.contains(station)) {
                continue;
            }
            //change to contains stationTo
            if (station.equals(stationTo) && (station.getLatitude() - visited.getLast().getLatitude() > 0) == destination) {
                visited.add(station);
                printPath(visited);
                visited.removeLast();
                break;
            }
        }
        for (Station station : stationFrom.getConnection()) {
            currentDestination = station.getLatitude() - visited.getLast().getLatitude() > 0;
            if (visited.contains(station) || station.equals(stationTo) || currentDestination != destination) {
                continue;
            }
            visited.addLast(station);
            getAllPaths(station, stationTo, visited, destination);
            visited.removeLast();
        }
    }

    private void printPath(LinkedList<Station> visited) {
        for (Station station : visited) {
            System.out.print(station.toString());
            System.out.print(" ");
        }
        System.out.println();
    }

}
