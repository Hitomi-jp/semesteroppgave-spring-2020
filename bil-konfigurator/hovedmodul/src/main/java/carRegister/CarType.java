package carRegister;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import java.io.Serializable;

public class CarType implements Serializable {

    private SimpleStringProperty type;
    private SimpleStringProperty name;
    private SimpleDoubleProperty price;

    public CarType(Type type, String name, double price) {
        this.type = new SimpleStringProperty(type.name());
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleDoubleProperty(price);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
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
