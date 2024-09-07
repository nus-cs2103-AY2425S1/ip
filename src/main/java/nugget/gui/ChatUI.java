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

public class ChatUI {

    private Nugget nugget;
    private VBox messageArea;
    private TextField inputField;
    private ScrollPane scrollPane;

    private Image userImage;
    private Image botImage;

    public ChatUI(Nugget nugget) {
        this.nugget = nugget;
        initializeComponents();
    }

    private void initializeComponents() {
        userImage = new Image(this.getClass().getResourceAsStream("/images/nugget.jpeg"));
        botImage = new Image(this.getClass().getResourceAsStream("/images/bot.jpeg"));

        // Message area (Scrollable)
        messageArea = new VBox(10);
        scrollPane = new ScrollPane(messageArea);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);

        // Automatically scroll to the bottom when new messages are added
        messageArea.heightProperty().addListener((observable, oldValue, newValue) -> scrollPane.setVvalue(1.0));

        inputField = new TextField();
        inputField.setPromptText("Enter command...");
        inputField.setOnAction(e -> handleInput());
        HBox.setHgrow(inputField, Priority.ALWAYS);

        // Submit button
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(e -> handleInput());

        // Input field and button side by side
        HBox inputLayout = new HBox(10, inputField, submitButton);
        inputLayout.setAlignment(Pos.BOTTOM_CENTER);

        VBox layout = new VBox(10, scrollPane, inputLayout);
        layout.setPadding(new Insets(10));
        VBox.setVgrow(scrollPane, Priority.ALWAYS);
    }

    public VBox getLayout() {
        return new VBox(scrollPane, new HBox(inputField, new Button("Submit")));
    }

    private void handleInput() {
        String input = inputField.getText();
        if (!input.trim().isEmpty()) {
            addMessage(input, true);
            nugget.handleInput(input);
            inputField.clear();
        }
    }

    public void addMessage(String message, boolean isUser) {
        HBox messageBox = MessageHandler.createMessageBox(message, isUser, userImage, botImage);
        messageArea.getChildren().add(messageBox);
    }
}
