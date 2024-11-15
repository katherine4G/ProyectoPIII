package utils.Thread;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 *
 * @author kathe
 */

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ClearFieldsThread extends Thread {
    private Pane rootPane;

    public ClearFieldsThread(Pane rootPane) {
        this.rootPane = rootPane;
    }

    @Override
    public void run() {
        clearFieldsRecursive(rootPane);
    }

    private void clearFieldsRecursive(Pane parent) {
        for (Node node : parent.getChildren()) {
            if (node instanceof TextField textField) {
                Platform.runLater(() -> textField.clear());
            } else if (node instanceof ComboBox<?> comboBox) {
                Platform.runLater(() -> comboBox.getSelectionModel().clearSelection());
            } else if (node instanceof Pane pane) {
                clearFieldsRecursive(pane);
            }
        }
    }
}
