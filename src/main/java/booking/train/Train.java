package booking.train;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Train {
    private Locomotive locomotive;
    private List<Carriage> listOfCarriages;

    public Train(Locomotive locomotive, List<Carriage> listOfCarriages) {
        this.locomotive = locomotive;
        this.listOfCarriages = listOfCarriages;
    }

    public Locomotive getLocomotive() {
        return locomotive;
    }

    public void setLocomotive(Locomotive locomotive) {
        this.locomotive = locomotive;
    }

    public List<Carriage> getListOfCarriages() {
        return listOfCarriages;
    }

    public void setListOfCarriages(List<Carriage> listOfCarriages) {
        this.listOfCarriages = listOfCarriages;
    }

    public int getAmountOfPlaces(){
        // to do
        return 0;
    }

    public Map<String, int[][]> getPlaces(int stationCount) {
        Map<String, int[][]> result = new HashMap<>();
        for(Carriage carriage : listOfCarriages){
            result.put(carriage.getName(), new int[carriage.getAmount()][stationCount]);
        }
        return result;
    }

    public String getFullNameOfCarriage(String nameOfCarriage) throws NullPointerException {
        return getCarriage(nameOfCarriage).getFullName();
    }

    public Carriage getCarriage(String nameOfCarriage) throws NullPointerException {
        for (Carriage carriage : listOfCarriages) {
            if (carriage.getName().equals(nameOfCarriage)) {
                return carriage;
            }
        }
        throw new NullPointerException();
    }
}
