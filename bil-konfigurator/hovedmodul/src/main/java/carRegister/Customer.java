package carRegister;

import exception.InvalidNameException;
import exception.InvalidPriceException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import validator.CarValidator;

import java.util.List;

public class Customer {

    private transient SimpleStringProperty name;
    private transient SimpleListProperty<Car> carList;

    public Customer(String name, List<Car> carList) {
        this.name = new SimpleStringProperty(name);
        this.carList = new SimpleListProperty<Car>();
        this.carList.addAll(carList);
    }

    public String getName() {
        return name.get();
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

    public ObservableList<Car> getCarList() {
        return carList.get();
    }

    public SimpleListProperty<Car> carListProperty() {
        return carList;
    }

    public void setCarList(ObservableList<Car> carList) {
        this.carList.set(carList);
    }

    public String toString() {
        return this.getName();
    }

}
