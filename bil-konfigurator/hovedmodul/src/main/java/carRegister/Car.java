package carRegister;

import exception.InvalidPriceException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import validator.CarValidator;

public class Car {

    private transient SimpleObjectProperty<Model> model;
    private transient SimpleDoubleProperty totalPrice;
    private transient SimpleListProperty<Component> componentList;

    public Car(Model model) {
        this.model = new SimpleObjectProperty<Model>(model);
        this.totalPrice = new SimpleDoubleProperty(model.getPrice());
        this.componentList = new SimpleListProperty<>();
    }

    public Model getModel() {
        return model.get();
    }

    public SimpleObjectProperty<Model> modelProperty() {
        return model;
    }

    public void setModel(Model model) {
        this.model.set(model);
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
}