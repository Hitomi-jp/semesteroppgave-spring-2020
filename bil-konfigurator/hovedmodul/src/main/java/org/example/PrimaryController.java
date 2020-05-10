package org.example;

import carRegister.CarType;
import carRegister.Component;
import carRegister.CarDatabase;
import carRegister.EngineType;
import converter.Converter;
import forms.CarTypeForm;
import forms.ComponentForm;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class PrimaryController implements Initializable {
    /**
     * TextFields for CarType input.
     */
    @FXML
    private ChoiceBox<EngineType> cbxCarType;
    @FXML
    private TextField txtCarModel;
    @FXML
    private TextField txtCarPrice;

    /**
     * Table for showing CarType.
     */
    @FXML
    private TableView<CarType> tableViewCarType;
    @FXML
    public TableColumn<CarType, String> carTypeColumn;
    @FXML
    public TableColumn<CarType, String> carModelColumn;
    @FXML
    public TableColumn<CarType, Number> carPriceColumn;

    /**
     * TextFields for CarType input.
     */
    @FXML
    private TextField txtComponentName;
    @FXML
    private TextField txtComponentPrice;

    /**
     * Table for showing Components.
     */
    @FXML
    private TableView<Component> tableViewComponents;
    @FXML
    private TableColumn<Component, String> componentNameColumn;
    @FXML
    private TableColumn<Component, Number> componentPriceColumn;

    /**
     * Textarea for debugging.
     */
    @FXML
    private TextArea txtUt;

    /**
     * Buttons
     */
    public Button btnGoToCustomer;

    private StringConverter<Number> nStrConverter = new Converter.Price();
    private CarDatabase carDatabase = new CarDatabase();
    private final String FILENAME = "CarDataComponents.dat";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCols();
        refreshTableViewComponents();

        cbxCarType.getItems().setAll(EngineType.values());
        //This will allow the table to select multiple rows at once.
        tableViewCarType.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableViewComponents.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //Tableview components column eidtable
        tableViewCarType.setEditable(true);
        tableViewComponents.setEditable(true);

        //tableViewComponents.setItems(carDatabase.getComponentObservableList());
        //tableViewComponents.getColumns().get(0).setCellValueFactory();
        componentNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        componentPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(nStrConverter));
    }

    @FXML
    void btnCarTypeAdd(ActionEvent event) {
        CarTypeForm carForm = new CarTypeForm(
                cbxCarType.getValue(),
                txtCarModel.getText(),
                txtCarPrice.getText()
        );
        carDatabase.register(carForm);
        refreshTableViewComponents();
    }

    @FXML
    void btnCarTypeDelete(ActionEvent event) {
        tableViewCarType.getItems().removeAll(tableViewCarType.getSelectionModel().getSelectedItems());
    }

    /**
     * This method will create a new component object and add it to the table
     *
     * @param event
     */
    @FXML
    void btnComponentsAdd(ActionEvent event) {
        ComponentForm compForm = new ComponentForm(
                txtComponentName.getText(),
                txtComponentPrice.getText()
        );
        carDatabase.register(compForm);
        refreshTableViewComponents();
    }

    @FXML
    void btnComponentsDelete(ActionEvent event) {
        ObservableList<Component> allComponents, selectedComponents;
        allComponents = tableViewComponents.getItems();
        selectedComponents = tableViewComponents.getSelectionModel().getSelectedItems();
        allComponents.removeAll(selectedComponents);
    }

    // TODO This should use same validation logic as CarDatabase.validate(...).
    public void componentNameEdit(TableColumn.CellEditEvent<Component, String> componentsStringCellEditEvent) {
//        try {
            Component component = tableViewComponents.getSelectionModel().getSelectedItem();
            component.setComponentName(componentsStringCellEditEvent.getNewValue());
            refreshTableViewComponents();
//        } catch (InvalidNameException e) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Feil!");
//            alert.setHeaderText("Invalid data!");
//            alert.setContentText("Type correct date!");
//            alert.showAndWait();
//            e.printStackTrace();
//            throw new InvalidNameException();
//        }
    }

    // TODO This should use same validation logic as CarDatabase.validate(...).
    public void componentPriceEdit(TableColumn.CellEditEvent<Component, Number> componentNumberCellEditEvent) {
//        try {
            Component component = tableViewComponents.getSelectionModel().getSelectedItem();
            component.setComponentPrice(componentNumberCellEditEvent.getNewValue().doubleValue());
            refreshTableViewComponents();
//        } catch (NumberFormatException e) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Feil!");
//            alert.setHeaderText("Invalid data!");
//            alert.setContentText("Type correct date!");
//            alert.showAndWait();
//            e.printStackTrace();
//            throw new NumberFormatException();
//        }
    }

    //clear all the columns(go to initialize)
    // Manually mapping all the columns (should autowire but didnt).
    private void initCols() {
        carTypeColumn.setCellValueFactory(typeCell -> typeCell.getValue().typeProperty());
        carModelColumn.setCellValueFactory(modelCell -> modelCell.getValue().modelProperty());
        carPriceColumn.setCellValueFactory(priceCell -> priceCell.getValue().priceProperty());

        componentNameColumn.setCellValueFactory(nameCell -> nameCell.getValue().componentNameProperty());
        componentPriceColumn.setCellValueFactory(priceCell -> priceCell.getValue().componentPriceProperty());
//        componentNameColumn.setCellValueFactory(new PropertyValueFactory<Component, String>("componentName"));
//        componentPriceColumn.setCellValueFactory(new PropertyValueFactory<Component, Double>("componentPrice"));
    }

    /**
     * Workaround for observable list not working currently
     * https://stackoverflow.com/questions/37753266/javafx-tableview-and-observablelist-how-to-auto-update-the-table
     */
    private void refreshTableViewComponents() {
        // Original:
        //ObservableList<Component> allComponentData = FXCollections.observableArrayList();
        //allComponentData.addAll(carDatabase.all_components());
        //tableViewComponents.setItems(allComponentData);

        // Simpler:
        tableViewCarType.setItems(carDatabase.getCarTypeObservableList());
        tableViewComponents.setItems(carDatabase.getComponentObservableList());

        // Debugging:
        txtUt.setText(carDatabase.showData());
    }

    @FXML
    void GoToCustomer() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    public void btnSave(ActionEvent actionEvent) {
        carDatabase.save(FILENAME);
    }

    @FXML
    public void btnLoad(ActionEvent actionEvent) {
        carDatabase.load(FILENAME);
    }
}


