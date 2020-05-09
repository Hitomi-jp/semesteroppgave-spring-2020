package carRegister;

import exception.InvalidNameException;
import exception.InvalidPriceException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import validator.CarValidator;

import java.io.*;

public class Component implements Serializable {

    private transient SimpleStringProperty componentName;
    private transient SimpleDoubleProperty componentPrice;

    public Component(String componentName, double componentPrice) {
        if (!CarValidator.price(componentPrice)) {
            throw new InvalidPriceException();
        }
        this.componentName = new SimpleStringProperty(componentName);
        this.componentPrice = new SimpleDoubleProperty(componentPrice);
    }

    public SimpleStringProperty componentNameProperty() {
        return componentName;
    }

    public SimpleDoubleProperty componentPriceProperty() {
        return componentPrice;
    }

    public String getComponentName() {
        return componentName.getValue();
    }

    public void setComponentName(String componentName) {
        if (!CarValidator.name(componentName)) {
            throw new InvalidNameException();
        }
        this.componentName.set(componentName);
    }

    public double getComponentPrice() {
        return componentPrice.getValue();
    }


    public void setComponentPrice(double componentPrice) {
        if (!CarValidator.price(componentPrice)) {
            throw new InvalidPriceException();
        }
        this.componentPrice.set(componentPrice);
    }

    @Override
    public String toString() {
        return String.format("%s,%s", componentName.getValue(), componentPrice.getValue());
    }

    public String toCvs() {
        StringBuilder sb = new StringBuilder();
        sb.append(componentName.getValue()).append(",");
        sb.append(componentPrice.getValue()).append(",");
        //sb.append("\n")
        return sb.toString();
    }

    //Serialization class needs to be implements Serializable(Write to binary)

    public static void writeToFile(Component com) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Component.bin"));
        objectOutputStream.writeObject(com);
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
        s.writeUTF(getComponentName());
        s.writeDouble(getComponentPrice());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        this.componentName = new SimpleStringProperty();
        this.componentPrice = new SimpleDoubleProperty();

        setComponentName(s.readUTF());
        setComponentPrice(s.readDouble());
    }
}
