package carRegister;

import exception.*;
import exception.InvalidPriceException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import validator.CarValidator;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Model implements Serializable {

    private SimpleStringProperty engineType;
    private SimpleStringProperty brand;
    private SimpleDoubleProperty price;

    /**
     * Updated by Hitomi
     * @param engineType
     * @param brand
     * @param price
     */
    public Model(EngineType engineType, String brand, double price) {
        this.engineType = new SimpleStringProperty(engineType.name());
        this.brand = new SimpleStringProperty(brand);
        this.price = new SimpleDoubleProperty(price);
    }

    public String getEngineType() {
        return engineType.get();
    }

    public SimpleStringProperty engineTypeProperty() {
        if(engineType == null){
            throw new IllegalArgumentException("Cannot be blank. Choose Engine");
        }
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType.set(engineType);
    }

    public String getBrand() {
        return brand.get();
    }

    public SimpleStringProperty brandProperty() {
        return brand;
    }

    public void setBrand(String brand) {
        if(!CarValidator.name(brand)){
            throw new InvalidDataException("Invalid brand: " + brand);
        }
        this.brand.set(brand);
    }

    public double getPrice() {
        return price.get();
    }

    public SimpleDoubleProperty priceProperty() {
        return price;
    }

    public void setPrice(double price) {
        if(!CarValidator.price(price)){
            throw new InvalidDataException("");
        }
        this.price.set(price);
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%s", engineType.getValue(), brand.getValue(),price.getValue());
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        //s.defaultWriteObject();
        s.writeUTF(getEngineType());
        s.writeUTF(getBrand());
        s.writeDouble(getPrice());
    }

    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        this.engineType = new SimpleStringProperty();
        this.brand = new SimpleStringProperty();
        this.price = new SimpleDoubleProperty();

        setEngineType(s.readUTF());
        setBrand(s.readUTF());
        setPrice(s.readDouble());
    }
}
