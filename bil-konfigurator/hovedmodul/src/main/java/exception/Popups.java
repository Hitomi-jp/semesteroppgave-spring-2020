package exception;

import javafx.scene.control.Alert;

public class Popups {
    public static void showErrorDialog(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Feil!");
        alert.setHeaderText("Ugyldig data!");
        alert.setContentText(msg);

        alert.showAndWait();
    }

    public static void showSuccessDialog(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Personregister");
        alert.setHeaderText("Operasjon vellykket");
        alert.setContentText(msg);

        alert.showAndWait();
    }

}
