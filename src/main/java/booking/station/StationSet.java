package booking.station;

import booking.Main;

import java.util.HashSet;
import java.util.LinkedList;

public class StationSet<E extends Station> extends HashSet implements Iterable {
    @Override
    public boolean add(Object obj) {
        if (!(obj instanceof Station)) {
            return false;
        }

        if (!this.contains(obj)) {
            super.add(obj);
        } else {
            ((Station) getElement(obj)).getConnection().addAll(((Station) obj).getConnection());
        }
        return true;
    }

    public Object getElement(Object station) {
        for (Object obj : this) {
            if (((Station) obj).equals(station))
                return obj;
        }
        return null;
    }

    public Station getElement(String name) {
        for (Object obj : this) {
            if (((Station) obj).getName().equals(name))
                return (Station) obj;
        }
        return null;
    }

    public static void main(String[] args) {
        StationMap stationMap = new StationMap(Main.initRout(), Main.initStation());

        for(Object station: stationMap.getStation()){
            System.out.println(((Station)station).getName());
            System.out.println("has connecion wtih");

            for(Station stop: ((Station)station).getConnection()){
                System.out.println(stop);
            }
        }

        LinkedList<Station> visited = new LinkedList<>();
        visited.add(stationMap.getStation().getElement("Lviv"));
        stationMap.getAllPaths(stationMap.getStation().getElement("Lviv"),
                stationMap.getStation().getElement("Odessa"), visited,
                stationMap.getStation().getElement("Odessa").getLatitude() - stationMap.getStation().getElement("Lviv").getLatitude() > 0);

    }
}
