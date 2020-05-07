package carRegister;

import exception.InvalidDataTypeException;
import exception.InvalidNameException;
import exception.InvalidPriceException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import validator.CarValidator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ComponentsRegister implements Serializable {

    ArrayList<Components> componentsArrayList = new ArrayList<>();

    public String validateAndRegisterComponents(String componentsName, String componentsPrice) {

        try {
            if (!CarValidator.name(componentsName)) {
                return "Invalid component name. Start with Capital letter.";
            }

        } catch (InvalidNameException e) {
            return e.getMessage();
        }

        double intComponentsPrice;
        try {
            intComponentsPrice = Double.parseDouble(componentsPrice);
        } catch (NumberFormatException e) {
            return "Invalid data. Price must be numbers.";
        }
        try{
            if(!CarValidator.price(intComponentsPrice)){
                return "Invalid component price. Price must be 0 or over";
            }
        } catch (InvalidPriceException e) {
            return e.getMessage();
        }

        Components enComponents = new Components(componentsName, intComponentsPrice);
        componentsArrayList.add(enComponents);

        return showRegister();

    }

    public List<Components> all_components() {
        return componentsArrayList;
    }

    public String showRegister() {
        StringBuilder componentsList = new StringBuilder();
        for (Components c : componentsArrayList) {
            componentsList.append(c).append("\n");
        }
        return componentsList.toString();

    }

    public String toCvs() {
        StringBuilder sb = new StringBuilder();
        for (Components c : componentsArrayList) {
            sb.append(c.toCvs()).append("\n");
        }
        return sb.toString();
    }

    public void flush() {
        componentsArrayList.clear();
    }
}
