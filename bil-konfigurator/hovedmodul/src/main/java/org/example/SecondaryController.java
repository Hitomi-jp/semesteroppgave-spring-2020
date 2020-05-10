package org.example;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SecondaryController {

    @FXML
    private ChoiceBox<?> cbxCustomer;

    @FXML
    private ChoiceBox<?> cbxEngineType;

    @FXML
    private TextField txtCarName;

    @FXML
    private TableColumn<?, ?> typeColumn;

    @FXML
    private TableColumn<?, ?> nameColumn;

    @FXML
    private TableColumn<?, ?> priceColoumn;

    @FXML
    private ListView<?> listviewComponents;

    @FXML
    private ListView<?> listviewSelectedComponents;

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
    void goToAdminView() throws IOException {
        App.setRoot("primary");

    }

    @FXML
    void load(ActionEvent event) {

    }

    @FXML
    void save(ActionEvent event) {

    }

}
