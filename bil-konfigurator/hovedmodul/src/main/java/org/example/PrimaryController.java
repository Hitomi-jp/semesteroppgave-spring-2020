package org.example;

import carRegister.Component;
import carRegister.ComponentsRegister;
import exception.InvalidNameException;
import exception.PriceStringConverter;
import file.FileOpen;
import file.FileSave;
import forms.ComponentForm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class PrimaryController implements Initializable {
    private boolean useTexFile = true;

    FileOpen fileOpen;
    FileSave fileSave;

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
    private TableView<Component> tableViewComponents;

    @FXML
    private TableColumn<Component, String> componentsNameColum;

    @FXML
    private TableColumn<Component, Double> componentsPriceColum;

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

    private PriceStringConverter.DoubleStringConverter dStrConverter = new PriceStringConverter.DoubleStringConverter();
    private ComponentsRegister comRegister = new ComponentsRegister();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCols();
        refreshTableViewComponents();

        //This will allow the table to select multiple rows at once.
        tableViewComponents.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //Tableview components column eidtable
        tableViewComponents.setEditable(true);

        tableViewComponents.setItems(comRegister.getComponentObservableList());
        componentsNameColum.setCellValueFactory(nameCell -> nameCell.getValue().componentsNameProperty());
        //tableViewComponents.getColumns().get(0).setCellValueFactory();
        componentsNameColum.setCellFactory(TextFieldTableCell.forTableColumn());
        componentsPriceColum.setCellFactory(TextFieldTableCell.forTableColumn(dStrConverter));
    }

    @FXML
    void btnCarTypeAdd(ActionEvent event) {

    }

    @FXML
    void btnCarTypeDelete(ActionEvent event) {

    }

    /**
     * This method will create a new component object and add it to the table
     *
     * @param event
     */
    @FXML
    void btnComponentsAdd(ActionEvent event) {
        txtUt.setText(comRegister.validateAndRegisterComponents(txtComponentsProductName.getText(), txtComponentsPrice.getText()));
        refreshTableViewComponents();
    }

    @FXML
    void btnComponentsDelete(ActionEvent event) {
        ObservableList<Component> allComponents, selectedComponents;
        allComponents = tableViewComponents.getItems();
        selectedComponents = tableViewComponents.getSelectionModel().getSelectedItems();
        allComponents.removeAll(selectedComponents);

        for(Component c : selectedComponents) {
            comRegister.deleteComponent(c);
        }

        txtUt.setText(comRegister.showRegister());
    }


    public void componentNameEdit(TableColumn.CellEditEvent<Component, String> componentsStringCellEditEvent) {
        try {
            Component component = tableViewComponents.getSelectionModel().getSelectedItem();
            component.setComponentsName(componentsStringCellEditEvent.getNewValue());

        } catch (InvalidNameException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Feil!");
            alert.setHeaderText("Invalid data!");
            alert.setContentText("Type correct date!");
            alert.showAndWait();
            e.printStackTrace();
            throw new InvalidNameException();
        }
    }

    public void componentsPriceEdit(TableColumn.CellEditEvent<Component, Double> componentsDoubleCellEditEvent) {
        try {
            Component component = tableViewComponents.getSelectionModel().getSelectedItem();
            component.setComponentsPrice(componentsDoubleCellEditEvent.getNewValue());
        } catch (NumberFormatException e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Feil!");
            alert.setHeaderText("Invalid data!");
            alert.setContentText("Type correct date!");
            alert.showAndWait();
            e.printStackTrace();
            throw new NumberFormatException();
        }

    }

    public void btnComponentsFileSave(ActionEvent actionEvent) {
        comRegister.save();
    }

    public void btnComponentsFileOpen(ActionEvent actionEvent) {

    }

    public ObservableList<Component> getComponents() {
        ObservableList<Component> components = FXCollections.observableArrayList();
        components.add(new Component("Motor", 500000));
        components.add(new Component("Rim", 20000));

        return components;
    }


    //clear all the columns(go to initialize)
    private void initCols() {
        componentsNameColum.setCellValueFactory(new PropertyValueFactory<Component, String>("componentsName"));
        componentsPriceColum.setCellValueFactory(new PropertyValueFactory<Component, Double>("componentsPrice"));
    }

    private void refreshTableViewComponents() {
        ObservableList<Component> allComponentData = FXCollections.observableArrayList();
        allComponentData.addAll(comRegister.all_components());
        tableViewComponents.setItems(allComponentData);
    }

    public void btnOptionsAdd(ActionEvent actionEvent) {
        ComponentForm cForm = new ComponentForm(
                txtComponentsProductName.getText(),
                txtComponentsPrice.getText()
        );

        comRegister.registrerComponent( cForm );

        txtUt.setText( comRegister.showRegister() );

        //tableViewComponents.refresh();
        refreshTableViewComponents();
    }

    public void btnOptionsDelete(ActionEvent actionEvent) {
    }

    @FXML
    void GoToCustomer() throws IOException {
        App.setRoot("secondary");

    }

}


