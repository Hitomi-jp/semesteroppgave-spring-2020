package carRegister;

import exception.InvalidNameException;
import exception.InvalidPriceException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import validator.CarValidator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Component implements Serializable {

    private SimpleStringProperty componentsName;
    private SimpleDoubleProperty componentsPrice;


    public Component(String componentsName, double componentsPrice) {
        if (!CarValidator.price(componentsPrice)) {
            throw new InvalidPriceException();
        }
        this.componentsName = new SimpleStringProperty(componentsName);
        this.componentsPrice = new SimpleDoubleProperty(componentsPrice);

    }

    public SimpleStringProperty componentsNameProperty() {
        return componentsName;
    }

    public String getComponentsName() {
        return componentsName.getValue();
    }

    public void setComponentsName(String componentsName) {
        if (!CarValidator.name(componentsName)) {
            throw new InvalidNameException();
        }

        this.componentsName.set(componentsName);
    }

    public double getComponentsPrice() {
        return componentsPrice.getValue();
    }


    public void setComponentsPrice(double componentsPrice) {
        if (!CarValidator.price(componentsPrice)) {
            throw new InvalidPriceException();
        }
        this.componentsPrice.set(componentsPrice);
    }


    @Override
    public String toString() {
        return String.format("%s,%s", componentsName.getValue(), componentsPrice.getValue());
    }

    public String toCvs() {
        StringBuilder sb = new StringBuilder();
        sb.append(componentsName.getValue()).append(",");
        sb.append(componentsPrice.getValue()).append(",");
        //sb.append("\n")
        return sb.toString();
    }

    //Serialization class needs to be implements Serializable(Write to binary)

    public static void writeToFile(Component com) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Component.bin"));

        objectOutputStream.writeObject(com);
    }

}
