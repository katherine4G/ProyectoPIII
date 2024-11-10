package animations;
import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class AnimatiosUtils {

    public static void applyHoverAnimations(Pane container) {
        for (Node node : container.getChildren()) {
            if (node instanceof Button) {
                applyButtonHoverAnimation((Button) node);
            } else if (node instanceof TextField) {
                applyTextFieldHoverAnimation((TextField) node);
            } else if (node instanceof ComboBox<?>) {
                applyComboBoxHoverAnimation((ComboBox<?>) node);
            }
        }
    }

    private static void applyTextFieldHoverAnimation(TextField textField) {
        DropShadow shadow = new DropShadow(10, Color.rgb(0, 0, 0, 0.2));
        
        textField.setOnMouseEntered(e -> {
            animateBounce(textField);
            textField.setEffect(shadow);
        });
        
        textField.setOnMouseExited(e -> textField.setEffect(null));
    }

    private static void applyButtonHoverAnimation(Button button) {
        DropShadow shadow = new DropShadow(15, Color.rgb(0, 0, 255, 0.3));
        
        button.setOnMouseEntered(e -> {
            animateBounce(button);
            animateOpacity(button, 0.8);
            button.setEffect(shadow);
        });
        
        button.setOnMouseExited(e -> {
            button.setEffect(null);
            animateOpacity(button, 1.0);
        });
    }

    private static void applyComboBoxHoverAnimation(ComboBox<?> comboBox) {
        DropShadow shadow = new DropShadow(12, Color.rgb(255, 165, 0, 0.4));
        
        comboBox.setOnMouseEntered(e -> {
            animateBounce(comboBox);
            comboBox.setEffect(shadow);
        });
        
        comboBox.setOnMouseExited(e -> comboBox.setEffect(null));
    }

    // Método para animación de rebote
    private static void animateBounce(Node node) {
        ScaleTransition scaleUp = new ScaleTransition(Duration.millis(200), node);
        scaleUp.setToX(1.1);
        scaleUp.setToY(1.1);

        ScaleTransition scaleDown = new ScaleTransition(Duration.millis(200), node);
        scaleDown.setToX(1.0);
        scaleDown.setToY(1.0);

        SequentialTransition bounce = new SequentialTransition(scaleUp, scaleDown);
        bounce.play();
    }

    // Método para animación de opacidad
    private static void animateOpacity(Node node, double toOpacity) {
        FadeTransition fade = new FadeTransition(Duration.millis(200), node);
        fade.setToValue(toOpacity);
        fade.play();
    }
}
