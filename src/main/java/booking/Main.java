package booking;

import booking.exception.NoSuchStationException;
import booking.journey.Journey;
import booking.journey.Route;
import booking.station.Station;
import booking.train.Carriage;
import booking.train.Locomotive;
import booking.train.Train;
import booking.train.types.TypeOfCarriage;
import booking.view.Menu;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Main {

  public static void main(String[] args) throws NoSuchStationException {
    List<Station> station = initStation();
    List<Route> routes = initRout();
    List<Journey> journeys = initJourneys(routes);

//    for (Route r : routes) {
//      System.out.println(r.hasStation(station.get(3)));
//    }

    //Menu menu = new Menu(station, journeys);
    //menu.show();

  }

  private static List<Journey> initJourneys(List<Route> routes)
      throws NoSuchStationException {
    List<Journey> journeys = new ArrayList<>();
    List<Carriage> carriages = new ArrayList<>();
    for (int i = 0; i < 5; i++) {
      carriages.add(new Carriage("" + i, 24, TypeOfCarriage.COMPARTMENT));
    }
    for (Route r : routes) {
      journeys.add(new Journey(r, new Date(300000000),
          new Train(new Locomotive(), carriages)));
    }
    return journeys;
  }

  public static List<Station> initStation() {
    return new Initialization("src\\main\\resources\\routes.txt", "src\\main\\resources\\stations.txt").getStations();
  }

  public static List<Route> initRout() {
    return new Initialization("src\\main\\resources\\routes.txt", "src\\main\\resources\\stations.txt").getRoutes();
  }
}
