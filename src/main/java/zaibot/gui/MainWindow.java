package zaibot.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Dialog;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
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
