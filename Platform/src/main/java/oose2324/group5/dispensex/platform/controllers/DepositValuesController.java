package oose2324.group5.dispensex.platform.controllers;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import oose2324.group5.dispensex.platform.alert.CloseDispenseXWindow;
import oose2324.group5.dispensex.platform.classes.MedicineTakeoutStatus;
import oose2324.group5.dispensex.platform.httpRequest.DispenseXClient;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class DepositValuesController implements Initializable {
    @FXML
    private TableView<MedicineTakeoutStatus> tableView;
    @FXML
    private TableColumn<MedicineTakeoutStatus, String> dayColumn;
    @FXML
    private TableColumn<MedicineTakeoutStatus, Integer> amountColumn;
    @FXML
    private TableColumn<MedicineTakeoutStatus, String> idColumn;
    @FXML
    private TableColumn<MedicineTakeoutStatus, String> medicineNameColumn;
    @FXML
    private TableColumn<MedicineTakeoutStatus, String> timeOfTakeoutColumn;
    @FXML
    private TableColumn<MedicineTakeoutStatus, String> timeOfDisposalColumn;


    public void goToIntervalScreen(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        try {
            URL url = getClass().getResource("/oose2324/group5/dispensex/platform/ChangeIntervalScreen.fxml");
            if (url == null) {
                System.out.println("Cannot find file!");
                return;
            }
            Parent newRoot = FXMLLoader.load(Objects.requireNonNull(url));
            stage.setScene(new Scene(newRoot));
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        refreshListView();
    }

    public void closeScreen() {
        CloseDispenseXWindow.closeConfirmation();
    }

    public void refreshListView() {
        List<MedicineTakeoutStatus> medicineTakeoutStatuses = DispenseXClient.buildGetAllMedicineTakeoutStatusesRequest();
        ObservableList<MedicineTakeoutStatus> list = FXCollections.observableArrayList(medicineTakeoutStatuses);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        dayColumn.setCellValueFactory(new PropertyValueFactory<>("dayOfWeek"));
        medicineNameColumn.setCellValueFactory(new PropertyValueFactory<>("medicineName"));
        amountColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfMedicine"));
        timeOfDisposalColumn.setCellValueFactory(new PropertyValueFactory<>("timeOfDisposal"));
        timeOfTakeoutColumn.setCellValueFactory(new PropertyValueFactory<>("timeOfTakeout"));

        tableView.setItems(list);
        tableView.setPlaceholder(new Label("De lijst is leeg"));
    }
}
