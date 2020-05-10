package carRegister;

import exception.InvalidNameException;
import exception.InvalidPriceException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import validator.CarValidator;

import java.util.List;

public class Car {

    private transient SimpleObjectProperty<CarType> carType;
    private transient SimpleStringProperty name;
    private transient SimpleDoubleProperty totalPrice;
    private transient SimpleListProperty<Component> componentList;

    public Car(CarType carType, String name) {
        this.carType = new SimpleObjectProperty<CarType>(carType);
        this.name = new SimpleStringProperty(name);
        this.totalPrice = new SimpleDoubleProperty(carType.getPrice());
        this.componentList = new SimpleListProperty<>();

    }

    public CarType getCarType() {
        return carType.get();
    }

    public SimpleObjectProperty<CarType> carTypeProperty() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType.set(carType);
    }

    public double getTotalPrice() {
        return totalPrice.get();
    }

    public SimpleDoubleProperty totalPriceProperty() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        if (!CarValidator.price(totalPrice)) {
            throw new InvalidPriceException();
        }
        this.totalPrice.set(totalPrice);
    }

    public String getName() {
        return name.getValue();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        if (!CarValidator.name(name)) {
            throw new InvalidNameException();
        }
        this.name.set(name);
    }

}