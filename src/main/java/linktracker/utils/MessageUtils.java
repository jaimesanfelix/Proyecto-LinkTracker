package linktracker.utils;

import javafx.scene.control.Alert;

public class MessageUtils {

    static void showError(String message, String title){
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setContentText(message);
        error.setTitle(title);
        error.show();
    }

    static void showMessage(String message, String title){
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setContentText(message);
        info.setTitle(title);
        info.show();
    }

}
