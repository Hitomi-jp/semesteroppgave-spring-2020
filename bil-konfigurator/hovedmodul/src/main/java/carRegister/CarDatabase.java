package carRegister;

import exception.InvalidDataException;
import file.CsvFileOperation;
import file.JobjFileOperation;
import forms.ModelForm;
import forms.ComponentForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import validator.CarValidator;

import java.io.IOException;
import java.io.Serializable;
import java.util.Objects;

public class CarDatabase implements Serializable {

    private ObservableList<Model> modelObservableList = FXCollections.observableArrayList();
    private ObservableList<Component> componentObservableList = FXCollections.observableArrayList();
    private ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();
    private final String DEFAULT_ADMINDATA_FILENAME = "CarDataComponentsDefault.dat";
    private final String DEFAULT_CUSTOMERDATA_FILENAME = "CustomerDataDefault.csv";

    public CarDatabase() {
        ObservableList<Car> cars1 = FXCollections.observableArrayList();
        Car tesla = new Car(new Model(EngineType.ELECTRIC, "Tesla X", 80000.0));
//        tesla.addComponent( new Component("Rims", 2000) );
//        tesla.addComponent( new Component("GPS", 9000) );
//        tesla.addComponent( new Component("Red paint", 2000) );
        cars1.add(tesla);
        customerObservableList.add(new Customer("Per", cars1) );

        ObservableList<Car> cars2 = FXCollections.observableArrayList();
        Car bmw = new Car(new Model(EngineType.ELECTRIC, "BMW i3", 60000.0));
//        bmw.addComponent( new Component("Antenna", 10) );
//        bmw.addComponent( new Component("Performance", 900) );
//        bmw.addComponent( new Component("Insurance", 599) );
        cars2.add(bmw);
        customerObservableList.add(new Customer("Hitomi", cars2) );

        ObservableList<Car> cars3 = FXCollections.observableArrayList();
        Car merc = new Car(new Model(EngineType.GASOLINE, "Mercedes SLK", 260000.0));
//        merc.addComponent( new Component("Supersport", 10000) );
//        merc.addComponent( new Component("Leather", 8000) );
        cars3.add(merc);
        customerObservableList.add(new Customer("Maja", cars3) );

        ObservableList<Car> cars4 = FXCollections.observableArrayList();
        Car honda = new Car(new Model(EngineType.HYBRID, "Honda", 60000.0));
//        honda.addComponent( new Component("Long range", 7200) );
        cars4.add(honda);
        customerObservableList.add(new Customer("Aina", cars4) );


        loadAdminDefaults();
    }

    public void register(ModelForm modelForm) throws InvalidDataException {
        CarValidator.validate(modelForm);
        modelObservableList.add(modelForm.asModel());
    }

    public void register(ComponentForm componentForm) throws IllegalArgumentException {
        CarValidator.validate(componentForm);
        componentObservableList.add(componentForm.asComponent());
    }



    public ObservableList<Component> getComponentObservableList() {
        return componentObservableList;
    }

    public ObservableList<Model> getModelObservableList() {
        return modelObservableList;
    }

    public ObservableList<Customer> getCustomerObservableList() {
        return customerObservableList;
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


    public void deleteComponent(Component componentToRemove) {
        componentObservableList.remove(componentToRemove);
    }

    public void flush() {
        componentObservableList.clear();
        modelObservableList.clear();
    }


    public void loadCustomerDefaults() {

    }

    public void loadCustomerData(String filename) {
        CsvFileOperation csvFileOperation = new CsvFileOperation();
        try {
            customerObservableList.clear();
            customerObservableList.addAll(csvFileOperation.load(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveCustomerData(String filename) {
        CsvFileOperation csvFileOperation = new CsvFileOperation();
        try {
            csvFileOperation.save(customerObservableList, filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Loads data from src/main/resources/CarDataComponentsDefault.dat.
     * PS.. We never save data to this file!
     *
     * @link https://stackoverflow.com/questions/15749192/how-do-i-load-a-file-from-resource-folder
     */
    public void loadAdminDefaults() {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        String filename = Objects.requireNonNull(classloader.getResource(DEFAULT_ADMINDATA_FILENAME)).getFile();
        loadAdminData(filename);
    }

    public void loadAdminData(String filename) {
        JobjFileOperation fileOps = new JobjFileOperation();
        try {
            flush();
            JobjFileOperation.LoadedResources resources = fileOps.load(filename);
            for (Component c : resources.compList ) {
                CarValidator.validate(c);
                componentObservableList.add(c);
            }

            for (Model m : resources.modelList ) {
                CarValidator.validate(m);
                modelObservableList.add(m);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveAdminData(String filename) {
        JobjFileOperation fileOps = new JobjFileOperation();
        try {
            fileOps.save(componentObservableList, modelObservableList, filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
