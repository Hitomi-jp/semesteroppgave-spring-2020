package carRegister;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import java.io.Serializable;

public class CarType implements Serializable {

    private SimpleStringProperty type;
    private SimpleStringProperty model;
    private SimpleDoubleProperty price;

    public CarType(EngineType engineType, String model, double price) {
        this.type = new SimpleStringProperty(engineType.name());
        this.model = new SimpleStringProperty(model);
        this.price = new SimpleDoubleProperty(price);
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public String getModel() {
        return model.get();
    }

    public SimpleStringProperty modelProperty() {
        return model;
    }

    public void setModel(String model) {
        this.model.set(model);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        this.price.set(price);
    }
}
