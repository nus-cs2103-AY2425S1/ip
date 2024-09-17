package bibi.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * Represents a horizontal bibi.gui.ErrorBox with a Label for error messages
 */
public class ErrorMessage extends HBox {
    private Label errorLabel;

    /**
     * Constructs a new ErrorBox with the specified error message when command is invalid.
     *
     * @param errorLabel The error message to be displayed in red.
     */
    public ErrorMessage(Label errorLabel) {
        this.errorLabel = errorLabel;
        clearMessage();
    }

    public void clearMessage() {
        errorLabel.setText("");
    }

    public void setMessage(String msg) {
        errorLabel.setText(msg);
    }
}
