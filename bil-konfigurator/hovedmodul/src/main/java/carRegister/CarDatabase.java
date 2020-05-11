package carRegister;

import exception.InvalidNameException;
import exception.InvalidPriceException;
import file.JobjFileOperation;
import forms.CarTypeForm;
import forms.ComponentForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import validator.CarValidator;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class CarDatabase implements Serializable {

    private ObservableList<Model> modelObservableList = FXCollections.observableArrayList();
    private ObservableList<Component> componentObservableList = FXCollections.observableArrayList();
    private ArrayList<Customer> customerList = new ArrayList<>();
    private final String FILENAME_DEFAULT = "CarDataComponentsDefault.dat";

    public CarDatabase() {
        customerList.add(new Customer("Per", Collections.emptyList()) );
        customerList.add(new Customer("Hitomi", Collections.emptyList()) );
        customerList.add(new Customer("Maja", Collections.emptyList()) );
        customerList.add(new Customer("Hamzeh", Collections.emptyList()) );
        loadDefaults();
    }

    public void register(CarTypeForm carTypeForm) throws IllegalArgumentException {
        this.validate(carTypeForm);
        modelObservableList.add(carTypeForm.asCarType());
    }

    public void register(ComponentForm componentForm) throws IllegalArgumentException {
        this.validate(componentForm);
        componentObservableList.add(componentForm.asComponent());
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

    // TODO make sure carForm has valid data
    private void validate(CarTypeForm carTypeForm) throws IllegalArgumentException {

    }

    public ObservableList<Component> getComponentObservableList() {
        return componentObservableList;
    }

    public ObservableList<Model> getModelObservableList() {
        return modelObservableList;
    }


//    public void setComponentObservableList(ObservableList<Component> componentObservableList) {
//        this.componentObservableList = componentObservableList;
//    }

    public String showData() {
        StringBuilder componentsList = new StringBuilder();
        for (Component c : componentObservableList) {
            componentsList.append(c).append("\n");
        }
        return componentsList.toString();

        }

    /**
     * added by Hitomi
     * @return
     */
    public String showModelData() {
        StringBuilder modelList = new StringBuilder();
        for (Model m : modelObservableList) {
            modelList.append(m).append("\n");
        }
        return modelList.toString();
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

    /**
     * Loads data from src/main/resources/CarDataComponentsDefault.dat.
     * PS.. We never save data to this file!
     *
     * @link https://stackoverflow.com/questions/15749192/how-do-i-load-a-file-from-resource-folder
     */
    public void loadDefaults() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        String filename = Objects.requireNonNull(classloader.getResource(FILENAME_DEFAULT)).getFile();
        load(filename);
    }

    public void load(String filename) {
        JobjFileOperation fileOps = new JobjFileOperation();
        try {
            flush();
            componentObservableList.addAll(fileOps.load(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(String filename) {
        JobjFileOperation fileOps = new JobjFileOperation();
        try {
            fileOps.save(componentObservableList, filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Customer> getCustomerList() {
        return customerList;
    }
}
