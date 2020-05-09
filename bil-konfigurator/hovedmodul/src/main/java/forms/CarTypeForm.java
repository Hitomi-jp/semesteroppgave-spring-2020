package forms;

import carRegister.CarType;
import carRegister.EngineType;

public class CarTypeForm {
    private EngineType engineType;
    private String model;
    private String price;

    public CarTypeForm(EngineType engineType, String name, String price) {
        this.engineType = engineType;
        this.model = name;
        this.price = price;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public String getModel() {
        return model;
    }

    public String getPrice() {
        return price;
    }

    public CarType asCarType() {
        return new CarType(engineType, this.model, Double.parseDouble(this.price));
    }
}
