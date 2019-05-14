package booking.train;

import booking.train.types.TypeOfCarriage;

public class Carriage {
    private String name;
    private int amountOfPlaces;
    private TypeOfCarriage type;


    public Carriage(String name, int amountOfPlaces, TypeOfCarriage type) {
        this.name = name;
        this.amountOfPlaces = amountOfPlaces;
        this.type = type;

    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amountOfPlaces;
    }

    public TypeOfCarriage getType() {
        return type;
    }

    public String getFullName() {
        return type + " " + name + 1;
    }
}
