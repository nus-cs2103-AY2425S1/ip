package asura.ui;

import java.util.Scanner;

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

/**
 * Represents the UI of the program.
 */
public class Ui extends Application {

    private Scanner scanner;
    private String introduction = """
            Hello! I'm Asura!
            What can I do for you?""";
    private String goodbye = """
            Bye. Hope to see you again soon!""";
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/avatar1.png"));
    private Image asuraImage = new Image(this.getClass().getResourceAsStream("/images/avatar2.png"));

    /**
     * Creates a UI.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Starts the GUI.
     * @param stage primary stage of JavaFX.
     */
    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");
        DialogBox dialogBox = new DialogBox("Heaalo!", userImage);
        dialogContainer.getChildren().addAll(dialogBox);

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);
        Scene scene = new Scene(mainLayout);

        //Formatting the window to look as expected

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

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Outputs the introduction.
     */
    public void showIntroduction() {
        System.out.println(formatResponse(introduction));
    }

    /**
     * Outputs the goodbye.
     */
    public void showGoodbye() {
        System.out.println(formatResponse(goodbye));
    }

    /**
     * Outputs the error that is specified.
     * @param error The error specified.
     */
    public void showError(String error) {
        System.out.println(formatResponse(error));
    }

    /**
     * Reads the next input of the user.
     * @return The input of the user.
     */
    public String readCommand() {
        return scanner.nextLine();
    }

    /**
     * Outputs and formats the string specified.
     * @param s The string specified.
     */
    public void printString(String s) {
        System.out.println(formatResponse(s));
    }

    /**
     * Formats the string to include heading and footing lines.
     * @param msg The string to be formatted.
     * @return The formatted string.
     */
    private String formatResponse(String msg) {
        String startBorder = "---------------------------------------\n";
        String endBorder = "\n---------------------------------------";
        String formattedMsg = startBorder + msg + endBorder;
        return formattedMsg.indent(3);
    }
}
