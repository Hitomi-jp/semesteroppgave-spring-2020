package carRegister;

import exception.InvalidNameException;
import exception.InvalidPriceException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import validator.CarValidator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Components implements Serializable {

    private SimpleStringProperty componentsName;
    private SimpleDoubleProperty componentsPrice;


    public Components(String componentsName, double componentsPrice) {

        this.componentsName = new SimpleStringProperty(componentsName);
        this.componentsPrice = new SimpleDoubleProperty(componentsPrice);

    }


    public String getComponentsName() {
        return componentsName.getValue();
    }

    public void setComponentsName(String componentsName) {

        this.componentsName.set(componentsName);
    }

    public double getComponentsPrice() {
        return componentsPrice.getValue();
    }


    public void setComponentsPrice(double componentsPrice) {
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

    public static void writeToFile(Components com) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("Component.bin"));

        objectOutputStream.writeObject(com);
    }

}
