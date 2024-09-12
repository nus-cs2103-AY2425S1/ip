package kobe;

import javafx.animation.PauseTransition;
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
import javafx.util.Duration;

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
    private final Kobe kobe = new Kobe("data/kobee.txt");

    @Override
    public void start(Stage stage) {
        initializeUI(stage);
        setUpEventHandlers();
        stage.show();
        applyStyles();
        showWelcomeMessage();
    }

    /**
     * Initializes the user interface components.
     */
    private void initializeUI(Stage stage) {
        dialogContainer = createDialogContainer();
        scrollPane = createScrollPane(dialogContainer);
        userInput = createTextField("Enter your message...");
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane(scrollPane, userInput, sendButton);
        configureLayout(mainLayout);

        scene = new Scene(mainLayout, WINDOW_WIDTH, WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Kobe Chatbot");
        stage.setResizable(false);
    }

    /**
     * Creates and configures the dialog container (VBox).
     */
    private VBox createDialogContainer() {
        VBox vbox = new VBox(10);
        vbox.setPadding(new Insets(10));
        vbox.setPrefHeight(Region.USE_COMPUTED_SIZE);
        return vbox;
    }

    /**
     * Creates and configures the ScrollPane for displaying dialogs.
     */
    private ScrollPane createScrollPane(VBox container) {
        ScrollPane sp = new ScrollPane(container);
        sp.setFitToWidth(true);
        sp.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        sp.setPrefSize(SCROLLPANE_WIDTH, SCROLLPANE_HEIGHT);
        sp.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        return sp;
    }

    /**
     * Creates a TextField with a prompt text.
     */
    private TextField createTextField(String prompt) {
        TextField textField = new TextField();
        textField.setPromptText(prompt);
        textField.setPrefWidth(325.0);
        return textField;
    }

    /**
     * Configures the layout of the UI elements.
     */
    private void configureLayout(AnchorPane layout) {
        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);
        AnchorPane.setBottomAnchor(scrollPane, 50.0);
        AnchorPane.setLeftAnchor(userInput, 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);
        AnchorPane.setBottomAnchor(sendButton, 1.0);
    }

    /**
     * Applies the CSS styles to the scene.
     */
    private void applyStyles() {
        scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
    }

    /**
     * Sets up the event handlers for user input and button clicks.
     */
    private void setUpEventHandlers() {
        sendButton.setOnMouseClicked(event -> handleUserInput());
        userInput.setOnAction(event -> handleUserInput());
        dialogContainer.heightProperty().addListener(observable -> scrollPane.setVvalue(1.0));
    }

    /**
     * Handles user input, processes it, and adds dialogs to the container.
     */
    private void handleUserInput() {
        String input = userInput.getText();
        assert input != null && !input.isEmpty() : "User input cannot be null or empty.";

        String response = kobe.getResponse(input);
        addDialog(input, response);
        userInput.clear();

        if ("bye".equalsIgnoreCase(input)) {
            closeApplication();
        }
    }

    /**
     * Adds the user and chatbot dialogs to the container.
     */
    private void addDialog(String userInput, String kobeResponse) {
        if (userInput != null) {
            dialogContainer.getChildren().add(
                    DialogBox.getUserDialog(userInput, createImageView("/images/patrick.png"))
            );
        }
        dialogContainer.getChildren().add(
                DialogBox.getKobeDialog(kobeResponse, createImageView("/images/kobe.png"))
        );
        scrollPane.setVvalue(1.0);
    }

    /**
     * Creates an ImageView from the resource path.
     */
    private ImageView createImageView(String path) {
        return new ImageView(new Image(getClass().getResourceAsStream(path)));
    }

    /**
     * Closes the application after a short delay.
     */
    private void closeApplication() {
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished(event -> ((Stage) sendButton.getScene().getWindow()).close());
        delay.play();
    }

    /**
     * Displays the welcome message when the application starts.
     */
    private void showWelcomeMessage() {
        String welcomeMessage = kobe.getResponse("welcome");
        addDialog(null, welcomeMessage);
    }
}
