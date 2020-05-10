package carRegister;

import exception.InvalidNameException;
import exception.InvalidPriceException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import validator.CarValidator;

public class Car {

    private transient SimpleObjectProperty<Model> carType;
    private transient SimpleStringProperty name;
    private transient SimpleDoubleProperty totalPrice;
    private transient SimpleListProperty<Component> componentList;

    public Car(Model model, String name) {
        this.carType = new SimpleObjectProperty<Model>(model);
        this.name = new SimpleStringProperty(name);
        this.totalPrice = new SimpleDoubleProperty(model.getPrice());
        this.componentList = new SimpleListProperty<>();

    }

    public Model getCarType() {
        return carType.get();
    }

    public SimpleObjectProperty<Model> carTypeProperty() {
        return carType;
    }

    public void setCarType(Model model) {
        this.carType.set(model);
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