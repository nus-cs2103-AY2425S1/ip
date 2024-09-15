package gray.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

/**
 * Represents a dialog message that has image.
 */
public class DialogBox extends GridPane {
    @FXML
    private ImageView imageView;
    @FXML
    private Label nameLabel;
    @FXML
    private Label messageLabel;

    /**
     * Constructs a dialog box for the GUI.
     *
     * @param image
     * @param message
     * @throws IOException
     */
    public DialogBox(Image image, String name, String message) {
        FXMLLoader fxmlLoader = new FXMLLoader((MainWindow.class.getResource("/view/DialogBox.fxml")));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        imageView.setImage(image);
        nameLabel.setText(name);
        messageLabel.setText(message);
    }

    /**
     * Constructs a dialog for the GUI.
     *
     * @param image
     * @param name
     * @param message
     * @param rightToLeft
     */
    public DialogBox(Image image, String name, String message, boolean rightToLeft) {
        this(image, name, message);
        if (rightToLeft) {
            setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        }
    }
}
