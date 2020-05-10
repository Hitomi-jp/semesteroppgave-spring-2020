package org.example;

import carRegister.Model;
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
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class PrimaryController implements Initializable {
    /**
     * Whole scene in AnchorPane
     */
    @FXML
    private AnchorPane acpScene;
    /**
     * TextFields for CarType input.
     */
    @FXML
    private ChoiceBox<EngineType> cbxEngineType;
    @FXML
    private TextField txtCarBrand;
    @FXML
    private TextField txtCarPrice;

    /**
     * Table for showing CarType.
     */
    @FXML
    private TableView<Model> tableViewModel;
    @FXML
    public TableColumn<Model, String> engineTypeColumn;
    @FXML
    public TableColumn<Model, String> carBrandColumn;
    @FXML
    public TableColumn<Model, Number> carPriceColumn;

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

    private StringConverter<Number> nStrConverter = new Converter.Price();
    private CarDatabase carDatabase = App.getCarDatabase();
    private final String FILENAME = "CarDataComponents.dat";

    // TODO Add filtering to tableViewComponents: new TextField where you type something and the table filters out anything not matching.
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCols();
        refreshTableViewComponents();

        cbxEngineType.getItems().setAll(EngineType.values());

        //This will allow the table to select multiple rows at once.
        tableViewModel.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableViewComponents.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        //Tableview components column eidtable
        tableViewModel.setEditable(true);
        tableViewComponents.setEditable(true);

        //tableViewComponents.setItems(carDatabase.getComponentObservableList());
        //tableViewComponents.getColumns().get(0).setCellValueFactory();
        componentNameColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        componentPriceColumn.setCellFactory(TextFieldTableCell.forTableColumn(nStrConverter));
    }

    @FXML
    void carTypeAdd(ActionEvent event) {
        CarTypeForm carForm = new CarTypeForm(
                cbxEngineType.getValue(),
                txtCarBrand.getText(),
                txtCarPrice.getText()
        );
        carDatabase.register(carForm);
        refreshTableViewComponents();
    }

    @FXML
    void carTypeDelete(ActionEvent event) {
        tableViewModel.getItems().removeAll(tableViewModel.getSelectionModel().getSelectedItems());
    }

    /**
     * This method will create a new component object and add it to the table
     *
     * @param event
     */
    @FXML
    void componentAdd(ActionEvent event) {
        ComponentForm compForm = new ComponentForm(
                txtComponentName.getText(),
                txtComponentPrice.getText()
        );
        carDatabase.register(compForm);
        refreshTableViewComponents();
    }

    @FXML
    void componentDelete(ActionEvent event) {
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
        engineTypeColumn.setCellValueFactory(engineTypeCell -> engineTypeCell.getValue().engineTypeProperty());
        carBrandColumn.setCellValueFactory(modelCell -> modelCell.getValue().brandProperty());
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
        tableViewModel.setItems(carDatabase.getModelObservableList());
        tableViewComponents.setItems(carDatabase.getComponentObservableList());

        // Debugging:
        txtUt.setText(carDatabase.showData());
    }

    @FXML
    void goToCustomer() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    public void save(ActionEvent actionEvent) {
        acpScene.setDisable(true);
        carDatabase.save(FILENAME);
        acpScene.setDisable(false);
    }

    @FXML
    public void load(ActionEvent actionEvent) {
        carDatabase.load(FILENAME);
    }

}


