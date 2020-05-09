package carRegister;

import exception.InvalidNameException;
import exception.InvalidPriceException;
import file.JobjFileOperation;
import forms.ComponentForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import validator.CarValidator;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

public class ComponentsRegister implements Serializable {

    private ObservableList<Component> componentObservableList = FXCollections.observableArrayList();

    //ArrayList<Components> componentsArrayList = new ArrayList<>();
    public List<Component> all_components() {
        return componentObservableList;
    }

    public void addComponent(Component c) {
        componentObservableList.add(c);
    }

    public void removeAll() {
        componentObservableList.clear();
    }

    public String validateAndRegisterComponents(String componentsName, String componentsPrice) {


      //  ComponentsOLD enComponentsOLD = new ComponentsOLD(componentsName, intComponentsPrice);
        //comRegister.add(enComponentsOLD);

        //return showRegister();
        return "";
    }

    public void registrerComponent(ComponentForm componentForm) throws IllegalArgumentException {
        this.validate(componentForm);
        componentObservableList.add( componentForm.asComponent() );
    }

    private void validate(ComponentForm componentForm) throws IllegalArgumentException {
        if (!CarValidator.name(componentForm.getName())) {
            throw new InvalidNameException(componentForm.getName());
        }

        double componentPrice;
        try {
            componentPrice = Double.parseDouble(componentForm.getPrice());
        } catch (NumberFormatException e) {
            throw new InvalidPriceException(componentForm.getPrice());
        }

        if (!CarValidator.price(componentPrice)) {
            throw new InvalidPriceException(componentForm.getPrice());
        }

    }

    public ObservableList<Component> getComponentObservableList() {
        return componentObservableList;
    }

    public void setComponentObservableList(ObservableList<Component> componentObservableList) {
        this.componentObservableList = componentObservableList;
    }

    public String showRegister() {
        StringBuilder componentsList = new StringBuilder();
        for (Component c : componentObservableList) {
            componentsList.append(c).append("\n");
        }
        return componentsList.toString();
    }

    public String toCvs() {
        StringBuilder sb = new StringBuilder();
        for (Component c : componentObservableList) {
            sb.append(c.toCvs()).append("\n");
        }
        return sb.toString();
    }

    public void deleteComponent(Component componentToRemove) {
        componentObservableList.remove(componentToRemove);
    }

    public void flush() {
        componentObservableList.clear();
    }

    public void save() {
        JobjFileOperation fileOps = new JobjFileOperation();
        try {
            fileOps.save(componentObservableList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
