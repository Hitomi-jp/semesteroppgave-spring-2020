package carRegister;

import exception.InvalidPriceException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import validator.CarValidator;

public class Car {

    private transient SimpleObjectProperty<Model> model;
    private transient SimpleDoubleProperty totalPrice;
    private transient SimpleListProperty<Component> componentList;

    public Car(Model model) {
        this.model = new SimpleObjectProperty<Model>(model);
        this.totalPrice = new SimpleDoubleProperty(model.getPrice());
        this.componentList = new SimpleListProperty<>(FXCollections.observableArrayList());
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
        double sum = this.model.get().getPrice();
        for(Component comp : this.getComponentList()) {
            sum += comp.getComponentPrice();
        }
        this.totalPrice.set(sum);
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        if (!CarValidator.price(totalPrice)) {
            throw new InvalidPriceException();
        }
        this.totalPrice.set(totalPrice);
    }

    public ObservableList<Component> getComponentList() {
        return componentList.get();
    }

    public SimpleListProperty<Component> componentListProperty() {
        return componentList;
    }

    public void setComponentList(ObservableList<Component> componentList) {
        this.componentList.set(componentList);
    }

    public void addComponent(Component selectedComponent) {
        this.componentList.get().add( selectedComponent );
    }

    public String toCsv() {
        StringBuilder sb = new StringBuilder();
        sb.append( getModel().toCsv() );
        sb.append( getTotalPrice()).append(";");
        for ( Component c : getComponentList()) {
            sb.append( c.toCsv() );
        }
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append( getModel().toString() );
        sb.append( getTotalPrice() );
        for ( Component c : getComponentList()) {
            sb.append( c.toString() );
        }
        return sb.toString();
    }
}