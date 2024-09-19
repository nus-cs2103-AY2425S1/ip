package denim.ui;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 * A class representing a Help Box consisting of two labels. The first label contains text for Command Usage,
 * and the other label contains text for Example of the Command being used.
 */
public class HelpBox extends HBox {
    @FXML
    private Label usage;
    @FXML
    private Label example;

    /**
     * Constructs a new HelpBox.
     *
     * @param usage The text to appear in the usage component of the HelpBox.
     * @param example The text that appear in the example component of the HelpBox
     */
    public HelpBox(String usage, String example) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(HelpWindow.class.getResource("/view/HelpBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.usage.setText(usage);
        this.example.setText(example);
    }
}
