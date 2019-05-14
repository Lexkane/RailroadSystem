package booking.view;

import booking.Order;
import booking.Ticket;
import booking.TicketBuilder;
import booking.exception.IllegalDirectionException;
import booking.exception.NoSuchStationException;
import booking.journey.Journey;
import booking.journey.Route;
import booking.train.Carriage;
import booking.train.types.TypeOfCarriage;
import booking.train.types.TypeOfEngine;

import java.util.*;

public class Menu {

  private Map<String, String> menu;
  private Map<String, Command> methods;
  private Scanner input;

  private List<String> stations;
  private List<Journey> journeys;


  public Menu(List<String> stations, List<Journey> journeys) {
    this.stations = stations;
    this.journeys = journeys;
    input = new Scanner(System.in);
    menu = new LinkedHashMap<>();
    methods = new HashMap<>();
    initMenu();
  }

  public void show() {
    String userInput;
    do {
      System.out.println("\n\t\t~~MENU~~");
      for (String key : menu.keySet()) {
        System.out.println(key + " - " + menu.get(key));
      }
      System.out.println("Q - Quit");

      userInput = getStringUserInput();
      try {
        methods.get(userInput).execute();
      } catch (NullPointerException e) {
        //    ignore for cleaner console  view
      }
    } while (!userInput.equalsIgnoreCase("Q"));
  }


  private void initMenu() {
    menu.put("1", "Make an Order");
    methods.put("1", this::makeOrder);
  }

  private void makeOrder() {
    try {
      String startCity;
      String endCity;
      while (true) {
        startCity = chooseStartCity();
        endCity = chooseEndCity();
        if (!startCity.equalsIgnoreCase(endCity)) {
          break;
        }
        System.out.println("ERROR! Start City  and End City are Equal!");
      }
      List<Journey> validJourneys = findValidJourneys(startCity, endCity);
      boolean hasJourneys = showJourneys(validJourneys);
      if (!hasJourneys) {
        return;
      }
      System.out.println("Enter Journey:");
      Integer journeyId = getIntegerUserInput();
      Journey journey = journeys.get(journeyId - 1);
//        Order.makeOrder(startCity, endCity, journey);
      Map<String, List<Integer>> carriagesMap = getAvailablePlaces(startCity,
          endCity, journey);
      showAvailablePlaces(journey, carriagesMap);
      System.out.println("Enter Name of Carriage:");
      String carriageName = getStringUserInput();
      System.out.println("Enter Number of Place in Carriage:");
      Integer place = getPlace(carriageName, getIntegerUserInput(),
          carriagesMap);
      Integer routeDistance = journey.getRoute()
          .getDistance(startCity, endCity);
      TypeOfEngine engineType = journey.getTrain().getLocomotive()
          .getTypeOfEngine();
      TypeOfCarriage carriageType = getCarriage(journey, carriageName)
          .getType();
      TicketBuilder builder = Order.builder();// todo: replace Order to Ticket

      builder.setJourney(journey.getName())
          .setName(startCity + " - " + endCity)
          .setCarriage(carriageName)
          .setPlace(place)
          .setDate(journey.getDate())
          .generatePrice(carriageType, engineType, routeDistance);
      Ticket ticket = builder.getTicket();
      System.out.println(ticket);
    } catch (NoSuchStationException | IllegalDirectionException e) {
      e.printStackTrace();
    }
  }

  private TypeOfCarriage getCarriageType() {
    return null;
  }

  private Carriage getCarriage(Journey journey, String carriageName) {
    List<Carriage> carriages = journey.getTrain().getListOfCarriages();

    return carriages.stream().filter(x -> x.getName().equals(carriageName))
        .findFirst().get();
  }

  private Integer getPlace(String carriageName, Integer userInput,
      Map<String, List<Integer>> carriagesMap) {
    return carriagesMap.get(carriageName).get(userInput);
  }

  private void showAvailablePlaces(Journey journey,
      Map<String, List<Integer>> availablePlaces) {
    for (String carriage : availablePlaces.keySet()) {
      List<Integer> places = availablePlaces.get(carriage);
      String carriageType = journey.getTrain().getFullNameOfCarriage(carriage);

      System.out.print(carriageType + ": [");
//            System.out.print(carriage + " - " + "carriage" + ": [");
      for (int i = 0; i < places.size(); i++) {
        System.out
            .print(places.get(i) + 1 + ((i == places.size() - 1) ? "]" : ", "));
      }
      System.out.println();
    }
  }

  private Map<String, List<Integer>> getAvailablePlaces(String startCity,
      String endCity, Journey journey) throws NoSuchStationException {
    return journey.getAvailablePlaces(startCity, endCity);
  }

  private List<Journey> findValidJourneys(String startCity, String endCity) {
    List<Journey> validJourneys = new ArrayList<>();
    for (Journey j : journeys) {
      Route route = j.getRoute();
      if (route.getBegin().equalsIgnoreCase(startCity)) {
        Map<String, Integer> stops = route.getStops();
        if (stops.containsKey(endCity)) {
          validJourneys.add(j);
        }
      }
    }
    return validJourneys;
  }

  private boolean showJourneys(List<Journey> validJourneys) {
    System.out.println("\t\t~~Journey List~~");
    if (validJourneys.size() == 0) {
      System.out.println("Nothing was Found");
      return false;

    } else {
      for (int i = 0; i < validJourneys.size(); i++) {
        System.out.println((i + 1) + " - " + validJourneys.get(i).getRoute());
      }
    }
    return true;
  }

  private String chooseEndCity() {
    System.out.println("Enter the City to Which You are Going:");
    showStations();
    return stations.get(getIntegerUserInput() - 1);
  }

  private String chooseStartCity() {
    System.out.println("Enter the City from Which You are Going:");
    showStations();
    return stations.get(getIntegerUserInput() - 1);
  }

  private Integer getIntegerUserInput() {
    int userInput = 0;
    try {
      do {

        userInput = input.nextInt();

      } while (userInput == 0);
    } catch (Exception e) {
      System.out.println("Incorrect Input!");
      System.out.println("Please, Try Again:");
      getIntegerUserInput();
    }
    return userInput;
  }

  private String getStringUserInput() {
    return input.next();
  }

  private void showStations() {
    for (int i = 0; i < stations.size(); i++) {
      System.out.println((i + 1) + " - " + stations.get(i));
    }
  }
}