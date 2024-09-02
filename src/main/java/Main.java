import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import snowy.Snowy;

public class Main extends Application {

    private Snowy snowy = new Snowy("data/snowy.txt");
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image humanImage = new Image(this.getClass().getResourceAsStream("images/bot.jpg"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/human.jpg"));

    @Override
    public void start(Stage stage) throws Exception {
        dialogContainer = new VBox();
        scrollPane = new ScrollPane();
        scrollPane.setContent(dialogContainer);
        dialogContainer.heightProperty().addListener((observable -> scrollPane.setVvalue(1.0)));

        userInput = new TextField();
        sendButton = new Button("Send");

        userInput.setOnAction((event) -> {
            handleUserInput();
        });
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        AnchorPane mainLayout = new AnchorPane();

        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);



        scene = new Scene(mainLayout);
        stage.setScene(scene);
        stage.show();


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

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);
    }

    private void handleUserInput() {
        dialogContainer.getChildren().addAll(new DialogBox(userInput.getText(), botImage));
    }
}
