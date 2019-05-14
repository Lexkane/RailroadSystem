package booking.train;

import booking.train.types.TypeOfEngine;

public class Locomotive {
    private String name;
    private TypeOfEngine typeOfEngine;

    public Locomotive() {
        this.name = "default_Train";
        this.typeOfEngine = TypeOfEngine.LIGHT;
    }

    public Locomotive(String name, TypeOfEngine typeOfEngine) {
        this.name = name;
        this.typeOfEngine = typeOfEngine;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TypeOfEngine getTypeOfEngine() {
        return typeOfEngine;
    }

    public void setTypeOfEngine(TypeOfEngine typeOfEngine) {
        this.typeOfEngine = typeOfEngine;
    }

    @Override
    public String toString() {
        return "booking.train.Locomotive :" +
                "Name = '" + name + '\'' +
                ", Type Of Engine = " + typeOfEngine;
    }
}
