package org.example;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.io.IOException;

public class SecondaryController {

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtUserPhone;

    @FXML
    private TableView<?> tableViewUser;

    @FXML
    void btnGoToAdminView() throws IOException {
        App.setRoot("primary");

    }

}
