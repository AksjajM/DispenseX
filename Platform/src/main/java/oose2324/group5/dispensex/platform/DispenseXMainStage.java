package oose2324.group5.dispensex.platform;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import oose2324.group5.dispensex.platform.alert.CloseDispenseXWindow;

import java.io.IOException;

public class DispenseXMainStage extends Application {
    @Override
    public void start(Stage stage) {
        FXMLLoader fxmlLoader = new FXMLLoader(DispenseXMainStage.class.getResource("ChangeIntervalScreen.fxml"));
        Scene scene;

        try {
            scene = new Scene(fxmlLoader.load());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Image icon = new Image("file:Platform/src/DispenseXLogo.png");
        stage.getIcons().add(icon);

        stage.setTitle("DispenseX");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                windowEvent.consume();
                CloseDispenseXWindow.closeConfirmation();
            }
        });
    }

    public static void main(String[] args) {
        launch();
    }
}
