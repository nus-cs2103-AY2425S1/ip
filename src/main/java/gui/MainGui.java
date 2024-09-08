package gui;

import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import skibidi.Skibidi;

public class MainGui extends Application {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private final Skibidi bot = new Skibidi("data/tasks.txt");
    private final Image userImage = new Image(getClass().getResourceAsStream("/images/user.png"));
    private final Image botImage = new Image(getClass().getResourceAsStream("/images/bot.png"));

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(scrollPane, userInput, sendButton);

        Scene scene = new Scene(anchorPane);
        stage.setScene(scene);
        stage.show();

        stage.setTitle("Skibidi Chatbot");
        stage.setMinHeight(800.0);
        stage.setMinWidth(800.0);

        anchorPane.setPrefSize(800.0, 800.0);
        anchorPane.setStyle("-fx-background-color: #1e1e1e");

        scrollPane.setPrefSize(785, 735);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background-color: #1e1e1e");

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.setStyle("-fx-background-color: #1e1e1e");

        userInput.setPrefWidth(735.0);

        sendButton.setPrefWidth(55.0);

        // anchor ui components to corners
        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setRightAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked(event -> handleUserInput());
        userInput.setOnAction(event -> handleUserInput());

        Parameters params = getParameters();
        List<String> args = params.getRaw();
        if (!args.isEmpty()) {
            dialogContainer.getChildren()
                    .add(DialogBox.getBotDialog(new Label(args.get(0)), new ImageView(botImage)));
        }
    }

    private void handleUserInput() {
        String input = userInput.getText();
        String response = bot.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(new Label(input), new ImageView(userImage)),
                DialogBox.getBotDialog(new Label(response), new ImageView(botImage)));
        userInput.clear();
        if (input.equals("bye") || input.equals("exit")) {
            Platform.exit();
        }
    }
}
