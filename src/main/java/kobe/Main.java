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

    private static final double WINDOW_WIDTH = 400.0;
    private static final double WINDOW_HEIGHT = 600.0;
    private static final double SCROLLPANE_HEIGHT = 535;
    private static final double SCROLLPANE_WIDTH = 385;

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    // Injecting the Kobe instance to decouple it from UI
    private final Kobe kobe = new Kobe("data/kobee.txt");

    @Override
    public void start(Stage stage) {
        initializeUI(stage);
        setUpEventHandlers();
        stage.show();

        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());

        // Show welcome message on startup
        showWelcomeMessage();
    }

    /**
     * Initializes the user interface.
     * @param stage The main stage of the application.
     */
    private void initializeUI(Stage stage) {
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

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.setTitle("Kobe Chatbot");
        stage.setResizable(false);
        stage.setMinHeight(WINDOW_HEIGHT);
        stage.setMinWidth(WINDOW_WIDTH);
        mainLayout.setPrefSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        setUpLayout();
    }

    /**
     * Sets up the layout constraints for UI elements.
     */
    private void setUpLayout() {
        scrollPane.setPrefSize(SCROLLPANE_WIDTH, SCROLLPANE_HEIGHT);
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
        assert input != null && !input.isEmpty() : "User input cannot be null or empty.";

        String response = kobe.getResponse(input);
        addDialog(input, response);
        userInput.clear();
    }

    /**
     * Displays the welcome message at the start of the application.
     */
    private void showWelcomeMessage() {
        String welcomeMessage = kobe.getResponse("welcome");
        addDialog(null, welcomeMessage);
    }

    /**
     * Adds the user and chatbot dialogs to the container.
     * @param userInput The user's input message.
     * @param kobeResponse The chatbot's response.
     */
    private void addDialog(String userInput, String kobeResponse) {
        if (userInput != null) {
            DialogBox userDialog = DialogBox.getUserDialog(userInput, new ImageView(new Image(this.getClass().getResourceAsStream("/images/patrick.png"))));
            dialogContainer.getChildren().add(userDialog);
        }
        DialogBox kobeDialog = DialogBox.getKobeDialog(kobeResponse, new ImageView(new Image(this.getClass().getResourceAsStream("/images/kobe.png"))));
        dialogContainer.getChildren().add(kobeDialog);
        scrollPane.setVvalue(1.0);  // Scroll to bottom
    }
}
