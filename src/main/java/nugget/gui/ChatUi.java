package nugget.gui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import nugget.Nugget;

public class ChatUi {

    private static final String USER_IMAGE_PATH = "/images/nugget.jpeg";
    private static final String BOT_IMAGE_PATH = "/images/bot.jpeg";

    private Nugget nugget;
    private VBox messageArea;
    private TextField inputField;
    private ScrollPane scrollPane;
    private Button submitButton;

    private Image userImage;
    private Image botImage;

    public ChatUi(Nugget nugget) {
        this.nugget = nugget;
        initializeComponents();
    }

    private void initializeComponents() {
        userImage = loadImage(USER_IMAGE_PATH);
        botImage = loadImage(BOT_IMAGE_PATH);

        messageArea = new VBox(10);
        scrollPane = new ScrollPane(messageArea);
        configureScrollPane();

        inputField = new TextField();
        configureInputField();

        submitButton = new Button("Submit");
        submitButton.setOnAction(e -> handleInput());

        HBox inputLayout = new HBox(10, inputField, submitButton);
        inputLayout.setAlignment(Pos.BOTTOM_CENTER);

        VBox layout = new VBox(10, scrollPane, inputLayout);
        layout.setPadding(new Insets(10));
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
    }

    private Image loadImage(String path) {
        return new Image(getClass().getResourceAsStream(path));
    }

    private void configureScrollPane() {
        scrollPane.setFitToWidth(true);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        messageArea.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }

    private void configureInputField() {
        inputField.setPromptText("Enter command...");
        inputField.setOnAction(e -> handleInput());
        HBox.setHgrow(inputField, Priority.ALWAYS);
    }

    private void handleInput() {
        String input = inputField.getText();
        if (!input.trim().isEmpty()) {
            nugget.handleInput(input);
            inputField.clear();
            inputField.requestFocus();
        }
    }
}
