package kobe;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.ImageView;
import javafx.geometry.Insets;
import javafx.scene.layout.Region;

/**
 * Main class for the Kobe chatbot with a graphical user interface (GUI).
 */
public class Main extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Kobe kobe = new Kobe("data/kobee.txt");

    @Override
    public void start(Stage stage) {
        // Set up GUI components
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        dialogContainer.setPadding(new Insets(10));
        dialogContainer.setSpacing(10);
        scrollPane.setContent(dialogContainer);
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        userInput = new TextField();
        userInput.setPromptText("Enter your message...");

        sendButton = new Button("Send");

        // AnchorPane for overall layout
        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        // Set the scene
        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.setTitle("Kobe Chatbot");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);
        mainLayout.setPrefSize(400.0, 600.0);

        setUpLayout();
        setUpEventHandlers();
        stage.show();

        // Add CSS for styling (if applicable)
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        // Show welcome message on startup
        showWelcomeMessage();
    }

    /**
     * Sets up the layout constraints for UI elements.
     */
    private void setUpLayout() {
        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setFitToWidth(true);
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(scrollPane, 50.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
    }

    /**
     * Sets up event handlers for button clicks and input actions.
     */
    private void setUpEventHandlers() {
        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    /**
     * Handles user input, processes it, and displays responses.
     */
    private void handleUserInput() {
        String input = userInput.getText();
        if (!input.isEmpty()) {
            String response = kobe.getResponse(input);

            // Create new DialogBox instances for both user and Kobe dialogs
            DialogBox userDialog = DialogBox.getUserDialog(input, new ImageView(new Image(this.getClass().getResourceAsStream("/images/patrick.png"))));
            DialogBox kobeDialog = DialogBox.getKobeDialog(response, new ImageView(new Image(this.getClass().getResourceAsStream("/images/kobe.png"))));

            // Add the new dialog boxes to the container
            dialogContainer.getChildren().addAll(userDialog, kobeDialog);

            // Scroll to the bottom after new content is added
            scrollPane.setVvalue(1.0);
            userInput.clear();
        }
    }

    /**
     * Displays the welcome message at the start of the application.
     */
    private void showWelcomeMessage() {
        // Fetch the welcome message from Kobe
        String welcomeMessage = kobe.getResponse("welcome");

        // Create a new DialogBox instance to avoid duplicate children errors
        DialogBox welcomeDialog = DialogBox.getKobeDialog(
                welcomeMessage,
                new ImageView(new Image(this.getClass().getResourceAsStream("/images/kobe.png")))
        );

        // Add the newly created dialog to the container
        dialogContainer.getChildren().add(welcomeDialog);
    }
}
