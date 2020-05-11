package forms;

import carRegister.Model;
import carRegister.EngineType;

public class ModelForm {
    private EngineType engineType;
    private String brand;
    private String price;

    public ModelForm(EngineType engineType, String brand, String price) {
        this.engineType = engineType;
        this.brand = brand;
        this.price = price;
    }

    public EngineType getEngineType() {
        return engineType;
    }

    public String getBrand() {
        return brand;
    }

    public String getPrice() {
        return price;
    }

    public Model asModel() {
        return new Model(engineType, this.brand, Double.parseDouble(this.price));
    }
}
