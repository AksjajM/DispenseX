package oose2324.group5.dispensex.platform.validation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.util.Duration;

public class CreateMenuItemWarning {

    public static void displayMenuItemWarning(TextField textField, String warningText) {
        final ContextMenu textFieldValidator = new ContextMenu();
        textFieldValidator.setAutoHide(false);
        MenuItem menuItem = new MenuItem(warningText);

        textField.setStyle("-fx-border-color: red;");

        textFieldValidator.getItems().

                clear();
        textFieldValidator.getItems().

                add(
                        menuItem);
        textFieldValidator.show(textField, Side.BOTTOM, 10, 0);

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
            textFieldValidator.hide();
        }));
        timeline.play();

        menuItem.setOnAction(event ->
                timeline.playFromStart());
    }
}
