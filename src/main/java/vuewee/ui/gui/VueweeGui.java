package vuewee.ui.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import vuewee.ui.TaskListGui;

public class VueweeGui extends Application {
    private static TaskListGui taskListGui;

    private static ScrollPane scrollPane;
    private static VBox dialogContainer;
    private static TextField userInput;
    private static Button sendButton;
    private static Scene scene;

    private static Image userImage = new Image(VueweeGui.class.getResourceAsStream("/images/DaUser.png"));
    private static Image botImage = new Image(VueweeGui.class.getResourceAsStream("/images/DaDuke.png"));

    public VueweeGui() {
    }

    public static void setTaskListGui(TaskListGui taskListGui) {
        VueweeGui.taskListGui = taskListGui;
    }

    public static void sendMessage(String message) {
        VueweeGui.dialogContainer.getChildren().addAll(DialogBox.getBotDialog(message, VueweeGui.botImage));
    }

    @Override
    public void start(Stage stage) {
        // Setting up required components

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
        stage.setTitle("Vuewee");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        // Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Creates a dialog box containing user input, and appends it to the dialog
     * container. Clears the user input after processing.
     */
    private void handleUserInput() {
        String userText = userInput.getText();
        VueweeGui.dialogContainer.getChildren().addAll(DialogBox.getUserDialog(userText, userImage));
        VueweeGui.taskListGui.displayTasks();
        userInput.clear();
    }

}