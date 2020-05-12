package carRegister;

import exception.InvalidDataException;
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

    public Customer(String name, ObservableList<Car> carList) {
        this.name = new SimpleStringProperty();
        setName(name);
        this.carList = new SimpleListProperty<Car>(carList);

//        this.carList.addAll(carList);
    }

    public static Customer fromCvs(String[] data) {
//        Customer c = new Customer(data[0]);
//        Array
        return null;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        if (!CarValidator.name(name)) {
            throw new InvalidDataException("Invalid customer name: " + name);
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
        StringBuilder sb = new StringBuilder();
        sb.append(getName());
//        for ( Car car : this.getCarList()) {
//            sb.append( car.toString() );
//        }
        return sb.toString();
    }

    public String toCsv() {
        StringBuilder sb = new StringBuilder();
        sb.append(getName()).append(";");
        for ( Car car : this.getCarList()) {
            sb.append( car.toCsv() );
        }
        return sb.toString();
    }
}
