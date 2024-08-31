package zaibot.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import zaibot.Zaibot;
import zaibot.utils.Ui;

/**
 * This class is the controller for the MainWindow, which is the
 * main display for everything in the GUI.
 */
public class MainWindow extends AnchorPane {

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/smoothbrain.jpg"));
    private final Image responseImage = new Image(this.getClass().getResourceAsStream("/images/zaibot.jpg"));
    private final Image whiteImage = new WritableImage(1, 1);
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
        dialogContainer.getChildren().addAll(
                DialogBox.getResponseDialog(Ui.printGreeting(), responseImage)
        );
        // Automatically scroll to the bottom initially
        scrollPane.setVvalue(1.0);

        dialogContainer.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollPane.setVvalue(1.0);
        });

    }

    /**
     * This method sends the command in the TextField message
     * if the key pressed is the Enter key.
     * @param event The KeyEvent sent
     */
    public void sendCommandFromKeyboard(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            sendCommand();
        }
    }

    /**
     * This method sends the command in the TextField message
     * when a mouse is clicked.
     * @param event The MouseEvent sent when clicked.
     */
    public void sendCommandFromMouse(MouseEvent event) {
        sendCommand();
    }

    private void sendCommand() {
        String input = message.getText().trim();
        String output = zaibot.runCommand(input);
        String[] outputs = output.split("\\r?\\n");

        ArrayList<DialogBox> dialogs = Arrays.stream(outputs)
                .skip(1)
                .map(outputLine -> DialogBox.getResponseDialog(outputLine, whiteImage))
                .collect(Collectors.toCollection(ArrayList::new));

        System.out.println(input);
        System.out.println(output);

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getResponseDialog(outputs[0], responseImage)
        );

        dialogContainer.getChildren().addAll(dialogs);

        message.clear();
    }
}
