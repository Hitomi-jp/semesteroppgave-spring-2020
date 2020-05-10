package forms;

import carRegister.Model;
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

    public Model asCarType() {
        return new Model(engineType, this.model, Double.parseDouble(this.price));
    }
}
