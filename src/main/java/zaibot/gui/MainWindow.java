package zaibot.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.VBox;
import zaibot.Zaibot;

public class MainWindow extends AnchorPane {

    @FXML
    private ScrollPane scrollPane;

    @FXML
    private VBox dialogContainer;

    @FXML
    private TextField message;

    private Zaibot zaibot;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/smoothbrain.jpg"));
    private final Image responseImage = new Image(this.getClass().getResourceAsStream("/images/zaibot.jpg"));

    public void setZaibot(Zaibot zaibot) {
        this.zaibot = zaibot;
    }

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

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
