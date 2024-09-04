package weeny.ui;

import weeny.Weeny;

import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class WeenyGui {

    // Other class instances
    private final Ui ui = new Ui();

    // GUI instances
    private final Weeny weenyApp;
    private final Image userImage;
    private final Image weenyImage;
    private VBox dialogContainer;
    private TextField userInput;

    public WeenyGui(Weeny weenyApp) {
        this.weenyApp = weenyApp;
        userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
        weenyImage = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    }

    public void start(Stage stage) {
        ScrollPane scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        Button sendButton = new Button("Send");


        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        stage.setTitle("Duke");
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
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        dialogContainer.getChildren().addAll(
                DialogBox.getWeenyDialog(ui.showWelcomeMessage(), userImage)
        );

        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });
        userInput.setOnAction((event) -> {
            handleUserInput();
        });

        Scene scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();

    }

    private void handleUserInput() {
        String userText = userInput.getText();
        String weenyText = weenyApp.executeWeeny(userText);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(userText, userImage),
                DialogBox.getWeenyDialog(weenyText, weenyImage)
        );
        userInput.clear();
    }
}
