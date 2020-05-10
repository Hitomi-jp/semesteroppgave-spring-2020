package org.example;

import carRegister.CarDatabase;
import carRegister.Model;
import carRegister.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SecondaryController implements Initializable {

    @FXML
    private ChoiceBox<Customer> cbxCustomer;

    @FXML
    private ChoiceBox<Model> cbxModel;


    @FXML
    private TableColumn<Model, String> typeColumn;

    @FXML
    private TableColumn<Customer, String> brandColumn;

    @FXML
    private TableColumn<Customer, Double> priceColoumn;

    @FXML
    private ListView<String> listviewComponents;

    @FXML
    private ListView<String> listviewSelectedComponents;

    private CarDatabase carDatabase = App.getCarDatabase();

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cbxCustomer.getItems().addAll(carDatabase.getCustomerList());
//        cbxModel
    }
}
