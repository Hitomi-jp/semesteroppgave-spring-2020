package carRegister;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import java.io.Serializable;

public class Model implements Serializable {

    private SimpleStringProperty type;
    private SimpleStringProperty brand;
    private SimpleDoubleProperty price;

    public Model(EngineType engineType, String brand, double price) {
        this.type = new SimpleStringProperty(engineType.name());
        this.brand = new SimpleStringProperty(brand);
        this.price = new SimpleDoubleProperty(price);
    }

    public SimpleStringProperty typeProperty() {
        return type;
    }

    public String getBrand() {
        return brand.get();
    }

    public SimpleStringProperty brandProperty() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand.set(brand);
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