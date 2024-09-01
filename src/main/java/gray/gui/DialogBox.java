package gray.gui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
    private Label label;

    /**
     * Constructs a dialog box for the GUI.
     * @param image
     * @param text
     * @throws IOException
     */
    public DialogBox(Image image, String text) {
        FXMLLoader fxmlLoader = new FXMLLoader((MainWindow.class.getResource("/view/DialogBox.fxml")));
        fxmlLoader.setController(this);
        fxmlLoader.setRoot(this);
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        imageView.setImage(image);
        label.setText(text);
    }
}
