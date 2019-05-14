package booking;

import booking.journey.Route;
import booking.station.Station;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Initialization {
    private List<Route> routes = new ArrayList<>();
    private List<Station> station = new ArrayList<>();

    public Initialization(String routeFile, String stationFile) {
        readRoutes(routeFile);
        readStations(stationFile);
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public List<Station> getStations() {
        return station;
    }

    private void readStations(String file){
        try (InputStreamReader inputStream = new InputStreamReader(new FileInputStream(file));
             BufferedReader bufferedReader = new BufferedReader(inputStream)) {
            while(bufferedReader.ready()){
                station.add(parseStation(bufferedReader.readLine()));
                bufferedReader.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void readRoutes(String file) {
        try (InputStreamReader inputStream = new InputStreamReader(new FileInputStream(file));
            BufferedReader bufferedReader = new BufferedReader(inputStream)) {
            while(bufferedReader.ready()){
                routes.add(new Route(parseStations(bufferedReader.readLine()),parseDistance(bufferedReader.readLine())));
                bufferedReader.readLine();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private List<String> parseStations(String route){
        List<String> result = Arrays.asList(route.split(" / "));
        return result;
    }

    private Station parseStation(String station){
        return new Station(station.split(" ")[0], Integer.parseInt(station.split(" ")[1]));
    }

    private List<Integer> parseDistance(String distance){
        String[] distances = distance.split(" , ");
        Integer[] integerDistances = new Integer[distances.length];
        for(int i = 0; i < distances.length; i++){
            integerDistances[i] = Integer.parseInt(distances[i]);
        }
        return Arrays.asList(integerDistances);
    }
}
