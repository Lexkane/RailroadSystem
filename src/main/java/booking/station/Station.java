package booking.station;

import java.util.*;

public class Station {
    private String name;
    private Set<Station> connection;
    private int latitude;

    public Station() {
    }

    public Station(String name, int latitude) {
        this.name = name;
        this.connection = new HashSet<>();

        this.latitude = latitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public Set<Station> getConnection() {
        return connection;
    }

    public void setConnection(Set<Station> connection) {
        this.connection = connection;
    }

    public boolean isConnected(Station stationTo) {
        return connection.contains(stationTo);
    }

    public boolean hasRouteTo(Station stationTo) {
        if (isConnected(stationTo)) return true;

        boolean hasConnection = false;
        List<Station> visited = new ArrayList<>();
        visited.add(this);
        for (Station station : connection) {
            if(!hasConnection) {
                visited.add(station);
                hasConnection = station.hasRouteTo(stationTo, visited);
            } else {
                break;
            }
        }
        return hasConnection;
    }

    private boolean hasRouteTo(Station stationTo, List<Station> visited) {
        if (isConnected(stationTo)) return true;

        boolean hasConnection = false;
        for (Station station : connection) {
           if(hasConnection){
               break;
           }

            if (!visited.contains(station)) {
                visited.add(station);
                hasConnection =  station.hasRouteTo(stationTo, visited);
            }
        }
        return hasConnection;
    }

    public void addConnection(Station station) {
        connection.add(station);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Station)) {
            return false;
        } else if (((Station) obj).name.equals(this.name)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
