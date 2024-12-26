package oose2324.group5.dispensex.platform.validation;

import javafx.scene.control.TextField;

public class Validate {

    public static void showCorrectLabel(TextField textField) {
        CreateMenuItemWarning.displayMenuItemWarning(textField, "✔");
        textField.setStyle("-fx-border-color: green;");
    }

    public static boolean checkIfEmpty(TextField textField) {
        if (textField.getText().matches("")) {
            CreateMenuItemWarning.displayMenuItemWarning(textField, "Veld mag niet leeg zijn!");
            return true;
        }
        return false;
    }

    public static boolean checkIfEmptyAndCorrectTimeNotation(TextField textField) {
        if (checkIfEmpty(textField)) {
            return false;
        } else if (!textField.getText().matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$")) {
            CreateMenuItemWarning.displayMenuItemWarning(textField, "Voer het juiste format in!");
            return false;
        }
        showCorrectLabel(textField);
        return true;
    }

    public static boolean checkIfEmptyAndOnlyNumbers(TextField textField) {
        if (checkIfEmpty(textField)) {
            return false;
        } else if (!textField.getText().matches("\\d")) {
            CreateMenuItemWarning.displayMenuItemWarning(textField, "Alleen nummers tussen 0 en 10!");
            return false;
        }
        showCorrectLabel(textField);
        return true;
    }

    public static boolean checkIfTextFieldIsEmpty(TextField textField) {
        if (checkIfEmpty(textField)) {
            return false;
        } else {
            showCorrectLabel(textField);
        }
        return true;
    }
}
