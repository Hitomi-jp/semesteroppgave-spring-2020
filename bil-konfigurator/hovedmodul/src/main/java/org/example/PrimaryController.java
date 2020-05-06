package org.example;

import carRegister.Components;
import carRegister.ComponentsRegister;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class PrimaryController implements Initializable {
    ComponentsRegister comRegister = new ComponentsRegister();

    @FXML
    private TextField txtCarTypeProductName;

    @FXML
    private TextField txtCarTypePrice;

    @FXML
    private TextField txtComponentsProductName;

    @FXML
    private TextField txtComponentsPrice;

    @FXML
    private TextField txtExtraOptionsName;

    @FXML
    private TextField txtExtraOptionsPrice;

    @FXML
    private TableView<?> tableViewCarType;

    @FXML
    private TableColumn<?, ?> carTypeNameColum;

    @FXML
    private TableColumn<?, ?> carTypePriceColum;

    @FXML
    private TableView<Components> tableViewComponents;

    @FXML
    private TableColumn<Components, String> componentsNameColum;

    @FXML
    private TableColumn<Components, Double> componentsPriceColum;

    @FXML
    private TableView<?> tableViewExtraOptions;

    @FXML
    private TableColumn<?, ?> extraOptionsNameColum;

    @FXML
    private TableColumn<?, ?> extraOptionsPriceColum;

    @FXML
    private TextArea txtUt;

    @FXML
    private Button ButtonAdmin;



    @FXML
    void btnCarTypeAdd(ActionEvent event) {

    }

    @FXML
    void btnCarTypeDelete(ActionEvent event) {

    }

    /**
     * This method will create a new component object and add it to the table
     * @param event
     */
    @FXML
    void btnComponentsAdd(ActionEvent event) {
        txtUt.clear();
        txtUt.setText(comRegister.validateAndRegisterComponents(txtComponentsProductName.getText(),txtComponentsPrice.getText()));
        loadData();
    }

    @FXML
    void btnComponentsDelete(ActionEvent event) {

    }

    @FXML
    void btnOptionsAdd(ActionEvent event) {

    }

    @FXML
    void btnOptionsDelete(ActionEvent event) {

    }

    @FXML
    void componentNameEdit(ActionEvent event) {

    }

    @FXML
    void componentsPriceEdit(ActionEvent event) {

    }

    @FXML
    void GoToCustomer() throws IOException {
        App.setRoot("secondary");

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initTable();
        loadData();

        
    }


    public ObservableList<Components> getComponents() {
        ObservableList<Components> components = FXCollections.observableArrayList();
        components.add(new Components("Motor", 500000));
        components.add(new Components("Rim", 20000));

        return components;
    }

    public void componentNameEdit(TableColumn.CellEditEvent<Components, String> componentsStringCellEditEvent) {
    }

    public void componentsPriceEdit(TableColumn.CellEditEvent<Components, Double> componentsDoubleCellEditEvent) {
    }

    private void initTable(){initCols();}

    //clear all the columns(go to initialize)
    private void initCols(){
        componentsNameColum.setCellValueFactory(new PropertyValueFactory<Components, String>("componentsName"));
        componentsPriceColum.setCellValueFactory(new PropertyValueFactory<Components, Double>("componentsPrice"));
    }

    private void loadData(){
        ObservableList<Components> componentsData = FXCollections.observableArrayList();
        componentsData.addAll(comRegister.all_components());
        tableViewComponents.setItems(componentsData);
    }
}


