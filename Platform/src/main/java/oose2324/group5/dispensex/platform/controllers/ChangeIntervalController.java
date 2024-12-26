package oose2324.group5.dispensex.platform.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import oose2324.group5.dispensex.platform.alert.CloseDispenseXWindow;
import oose2324.group5.dispensex.platform.classes.Interval;
import oose2324.group5.dispensex.platform.httpRequest.DispenseXClient;
import oose2324.group5.dispensex.platform.validation.Validate;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.Objects;

public class ChangeIntervalController {

    @FXML
    private Text confirmationText;
    @FXML
    private TextField timeOfDisposalTextFieldBin1;
    @FXML
    private TextField timeOfDisposalTextFieldBin2;
    @FXML
    private TextField timeOfDisposalTextFieldBin3;
    @FXML
    private TextField timeOfDisposalTextFieldBin4;
    @FXML
    private TextField nameMedicineTextFieldBin1;
    @FXML
    private TextField nameMedicineTextFieldBin2;
    @FXML
    private TextField nameMedicineTextFieldBin3;
    @FXML
    private TextField nameMedicineTextFieldBin4;
    @FXML
    private TextField amountTextField1;
    @FXML
    private TextField amountTextField2;
    @FXML
    private TextField amountTextField3;
    @FXML
    private TextField amountTextField4;

    @FXML
    private Button saveIntervalButton;
    @FXML
    private Button goToDepositScreenButton;
    @FXML
    private Button closeButton;

    public void saveIntervalBin1() {
        saveIntervalBin(timeOfDisposalTextFieldBin1, nameMedicineTextFieldBin1, amountTextField1, confirmationText, "Bakje 1 succesvol opgeslagen!", 1);
    }

    public void saveIntervalBin2() {
        saveIntervalBin(timeOfDisposalTextFieldBin2, nameMedicineTextFieldBin2, amountTextField2, confirmationText, "Bakje 2 succesvol opgeslagen!", 2);
    }

    public void saveIntervalBin3() {
        saveIntervalBin(timeOfDisposalTextFieldBin3, nameMedicineTextFieldBin3, amountTextField3, confirmationText, "Bakje 3 succesvol opgeslagen!", 3);
    }

    public void saveIntervalBin4() {
        saveIntervalBin(timeOfDisposalTextFieldBin4, nameMedicineTextFieldBin4, amountTextField4, confirmationText, "Bakje 4 succesvol opgeslagen!", 4);
    }

    public void saveIntervalBin(TextField timeOfDisposalTFBin, TextField nameMedicineTFBin, TextField amountTFBin, Text confirmationText, String confirmationTextMessage, int binId) {
        boolean timeOfDisposalBin = Validate.checkIfEmptyAndCorrectTimeNotation(timeOfDisposalTFBin);
        boolean medicineNameBin = Validate.checkIfTextFieldIsEmpty(nameMedicineTFBin);
        boolean amountBin = Validate.checkIfEmptyAndOnlyNumbers(amountTFBin);

        if (timeOfDisposalBin && medicineNameBin && amountBin) {
            String originalText = confirmationText.getText();
            confirmationText.setText(confirmationTextMessage);

            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(4), event -> {
                confirmationText.setText(originalText);
            }));
            timeline.play();

            timeline.playFromStart();

            LocalTime timeOfDisposalBin1HttpRequest = LocalTime.parse(timeOfDisposalTFBin.getText());
            int amountBin1HttpRequest = Integer.parseInt(amountTFBin.getText());
            DispenseXClient.buildCreateIntervalRequest(new Interval(binId, timeOfDisposalBin1HttpRequest, nameMedicineTFBin.getText(), amountBin1HttpRequest));
        }
    }

    public void closeScreen() {
        CloseDispenseXWindow.closeConfirmation();
    }

    public void goToDepositScreen(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        try {
            URL url = getClass().getResource("/oose2324/group5/dispensex/platform/DepositValuesScreen.fxml");
            if (url == null) {
                System.out.println("Het bestand kon niet worden gevonden!");
                return;
            }
            Parent newRoot = FXMLLoader.load(Objects.requireNonNull(url));
            stage.setScene(new Scene(newRoot));
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
