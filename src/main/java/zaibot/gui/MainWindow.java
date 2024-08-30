package zaibot.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import zaibot.Zaibot;

/**
 * This class is the controller for the MainWindow, which is the
 * main display for everything in the GUI.
 */
public class MainWindow extends AnchorPane {

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/smoothbrain.jpg"));
    private final Image responseImage = new Image(this.getClass().getResourceAsStream("/images/zaibot.jpg"));
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField message;
    private Zaibot zaibot;

    public void setZaibot(Zaibot zaibot) {
        this.zaibot = zaibot;
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * This method sends the command in the TextField message
     * when a mouse is clicked.
     * @param event The MouseEvent sent when clicked.
     */
    public void sendCommand(MouseEvent event) {
        String input = message.getText().trim();
        String output = zaibot.runCommand(input);

        System.out.println(input);
        System.out.println(output);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getResponseDialog(output, responseImage)
        );

        message.clear();
    }
}
