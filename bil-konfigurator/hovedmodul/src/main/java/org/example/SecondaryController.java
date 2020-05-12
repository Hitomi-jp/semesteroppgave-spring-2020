package org.example;

import carRegister.*;
import exception.InvalidDataException;
import exception.Popups;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SecondaryController implements Initializable {

    @FXML
    private ChoiceBox<Customer> cbxCustomer;

    @FXML
    private ChoiceBox<Model> cbxModel;

    @FXML
    public TableView<Car> tableViewCar;

    @FXML
    private TableColumn<Car, String> engineTypeColumn;

    @FXML
    private TableColumn<Car, String> brandColumn;

    @FXML
    private TableColumn<Car, Number> priceColoumn;

    @FXML
    private ListView<Component> listviewComponents;

    @FXML
    private ListView<Component> listviewSelectedComponents;

    private CarDatabase carDatabase = App.getCarDatabase();

    private Customer currentCustomer;

    @FXML
    void customerSelect(ActionEvent event) {
        System.out.println("customerSelect");
        if ( !cbxCustomer.getSelectionModel().isEmpty()) {
            currentCustomer = cbxCustomer.getSelectionModel().getSelectedItem();
            tableViewCar.setItems(currentCustomer.getCarList());
            listviewSelectedComponents.getItems().clear();
            listviewComponents.getItems().clear();
            refreshTableViewComponents();
        }
    }

    @FXML
    void carAdd(ActionEvent event) {
        Model model = cbxModel.getSelectionModel().getSelectedItem();
        currentCustomer.getCarList().add(
                new Car(model)
        );

        refreshTableViewComponents();
    }

    @FXML
    void carDelete(ActionEvent event) {
        Car selectedCar = tableViewCar.getSelectionModel().getSelectedItem();
        if (selectedCar == null)
            Popups.showErrorDialog("no car selected.");
        else {
            currentCustomer.getCarList().remove(selectedCar);
            listviewSelectedComponents.getItems().clear();
            listviewComponents.getItems().clear();
        }
        refreshTableViewComponents();
    }


    @FXML
    void load(ActionEvent event) {
        try {
            carDatabase.loadCustomerData("UserData.csv");
        } catch (InvalidDataException e) {
            Popups.showErrorDialog(e.getMessage());
        }
        cbxCustomer.getSelectionModel().clearAndSelect(0);
        refreshTableViewComponents();
    }

    @FXML
    void save(ActionEvent event) {
        carDatabase.saveCustomerData("UserData.csv");
    }

    @FXML
    void goToAdminView() throws IOException {
        App.setRoot("primary");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        cbxCustomer.getItems().addAll(carDatabase.getCustomerList());
//        cbxModel.setItems( carDatabase.getModelObservableList() );

        engineTypeColumn.setCellValueFactory(engineTypeCell -> engineTypeCell.getValue().getModel().engineTypeProperty());
        brandColumn.setCellValueFactory(brandCell -> brandCell.getValue().getModel().brandProperty());
        priceColoumn.setCellValueFactory(priceCell -> priceCell.getValue().totalPriceProperty());
//        cbxCustomer.getItems().addAll(carDatabase.getCustomerList());
        cbxCustomer.setItems(carDatabase.getCustomerObservableList());
        cbxModel.setItems( carDatabase.getModelObservableList() );
        refreshTableViewComponents();

    }

    private void refreshTableViewComponents() {
        if (currentCustomer != null ) {
            System.out.println("refresh currentCust: " + currentCustomer + "\thash: " + currentCustomer.hashCode() + "\tcars.size: " + currentCustomer.getCarList().size());
            tableViewCar.setItems(currentCustomer.getCarList());
        }
        tableViewCar.refresh();
    }

    public void selectCar(MouseEvent mouseEvent) {
        Car car = tableViewCar.getSelectionModel().getSelectedItem();
        if (car == null)
            return;

        listviewComponents.getItems().clear();
        listviewSelectedComponents.getItems().clear();

        for ( Component c : carDatabase.getComponentObservableList()) {
            if (car.getComponentList().contains(c)) {
                listviewSelectedComponents.getItems().add( c );
            } else {
                listviewComponents.getItems().add( c );
            }
        }
//        listviewComponents.getItems().add()
        System.out.println("selected;: "+ car.toString());

    }

    @FXML
    void chooseComponent(ActionEvent event) {
        Car selectedCar = tableViewCar.getSelectionModel().getSelectedItem();
        Component selectedComponent = listviewComponents.getSelectionModel().getSelectedItem();
        if (selectedCar == null) {
            Popups.showErrorDialog("Select car first");
            return;
        }

        if (selectedComponent == null) {
            Popups.showErrorDialog("Select a component to add to selectedCar");
            return;
        }

        listviewComponents.getItems().remove(selectedComponent);
        selectedCar.addComponent( selectedComponent );
        listviewSelectedComponents.getItems().clear();
        listviewSelectedComponents.getItems().addAll( selectedCar.getComponentList() );

        refreshTableViewComponents();

    }
}
