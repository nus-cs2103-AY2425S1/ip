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
        dialogContainer.setPadding(new Insets(10));  // Add padding to dialogContainer
        dialogContainer.setSpacing(10);  // Add spacing between dialog boxes
        scrollPane.setContent(dialogContainer);
        scrollPane.setFitToWidth(true);  // Ensure ScrollPane adjusts to the width of the content
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        userInput = new TextField();
        userInput.setPromptText("Enter your message...");  // Set placeholder text for better UX

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

        setUpLayout(); // Helper method to set layout constraints
        setUpEventHandlers(); // Set up event handlers for button clicks and input actions

        // Show stage
        stage.show();

        // Add CSS for styling (if applicable)
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        // Ensure welcome message is shown when the chatbot starts
        showWelcomeMessage();
    }



    private void setUpLayout() {
        scrollPane.setPrefSize(385, 535); // Set size for scroll pane
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER); // No horizontal scroll
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS); // Always vertical scroll
        scrollPane.setFitToWidth(true); // Make scroll pane fit the content width

        // Ensure the VBox can grow
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);
        dialogContainer.setSpacing(10); // Add spacing between tasks/messages

        userInput.setPrefWidth(325.0); // Set input field width
        sendButton.setPrefWidth(55.0); // Set button width

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(scrollPane, 50.0); // Leave space for input
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
    }


    private void setUpEventHandlers() {
        sendButton.setOnMouseClicked((event) -> handleUserInput());
        userInput.setOnAction((event) -> handleUserInput());

        // Ensure the scroll pane scrolls to the bottom when content is added
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void handleUserInput() {
        String input = userInput.getText();
        if (!input.isEmpty()) {
            String response = kobe.getResponse(input);

            // Add both the user input and chatbot response to the dialog container
            dialogContainer.getChildren().addAll(
                    DialogBox.getUserDialog(input, new ImageView(new Image(this.getClass().getResourceAsStream("/images/patrick.png")))),
                    DialogBox.getKobeDialog(response, new ImageView(new Image(this.getClass().getResourceAsStream("/images/kobe.png"))))
            );

            // Ensure the scroll pane scrolls to the bottom after adding new content
            scrollPane.setVvalue(1.0);
            userInput.clear();
        }
    }

    private void showWelcomeMessage() {
        // Fetch the welcome message from Kobe
        String welcomeMessage = kobe.getResponse("welcome");

        dialogContainer.getChildren().add(
                DialogBox.getKobeDialog(welcomeMessage, new ImageView(new Image(this.getClass().getResourceAsStream("/images/kobe.png"))))
        );
    }

    public static void main(String[] args) {
        launch(args);
    }
}
