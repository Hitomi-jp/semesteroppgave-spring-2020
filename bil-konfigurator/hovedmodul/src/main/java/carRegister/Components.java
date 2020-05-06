package carRegister;

import exception.InvalidNameException;
import exception.InvalidPriceException;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import validator.CarValidator;

public class Components {

    private SimpleStringProperty componentsName;
    private SimpleIntegerProperty componentsPrice;


    public Components(String componentsName, int componentsPrice) {

        this.componentsName = new SimpleStringProperty(componentsName);
        this.componentsPrice = new SimpleIntegerProperty(componentsPrice);

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

    public int getComponentsPrice() {
        return componentsPrice.getValue();
    }


    public void setComponentsPrice(int componentsPrice) {
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

}
