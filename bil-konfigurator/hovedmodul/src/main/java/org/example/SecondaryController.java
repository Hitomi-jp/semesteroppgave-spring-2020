package org.example;

import carRegister.CarType;
import carRegister.Customer;
import carRegister.EngineType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.lang.reflect.Type;

public class SecondaryController {

    @FXML
    private ChoiceBox<String> cbxCustomer;

    @FXML
    private ChoiceBox<String> cbxEngineType;


    @FXML
    private TableColumn<CarType,String> typeColumn;

    @FXML
    private TableColumn<Customer, String> modelColumn;

    @FXML
    private TableColumn<Customer, Double> priceColoumn;

    @FXML
    private ListView<String> listviewComponents;

    @FXML
    private ListView<String> listviewSelectedComponents;

    @FXML
    void carAdd(ActionEvent event) {

    }

    @FXML
    void carDelete(ActionEvent event) {

    }

    @FXML
    void chooseComponent(ActionEvent event) {

    }


    @FXML
    void load(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {

    }

    @FXML
    void goToAdminView() throws IOException {
        App.setRoot("primary");

    }

}
