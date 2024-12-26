package oose2324.group5.dispensex.platform.alert;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Optional;

public class CloseDispenseXWindow {

    public static void closeConfirmation() {
        ButtonType ok = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Annuleren", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert a = new Alert(Alert.AlertType.CONFIRMATION, "message", ok, cancel);

        a.setAlertType(Alert.AlertType.CONFIRMATION);

        a.setTitle("DispenseX");
        a.setHeaderText("Weet u zeker dat u het scherm wilt afsluiten?");
        a.setContentText("Druk op OK om te sluiten of op Annuleren om verder te gaan");

        Stage stage = (Stage) a.getDialogPane().getScene().getWindow();
        Image icon = new Image("file:src/DispenseXLogo.png");
        stage.getIcons().add(icon);

        Optional<ButtonType> result = a.showAndWait();

        if (result.get() == ok) {
            javafx.application.Platform.exit();
        }
    }
}
