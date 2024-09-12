package blob;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.util.Scanner;

/**
 * Responsible for initalising all other related objects.
 * Responsible for hosting conversation.
 * Responsible for certain repeated chat aesthetics used throughout the chat.
 * Constructor requires a TaskList object as an argument.
 */
public class Ui {

    public final String UNKNOWN_COMMAND_MSG = "ERROR! Unknown command!";
    private Parser parser;
    private TaskList tasklist;
    private Image userImg;
    private Image blobImg;

    public Ui(TaskList tasklist, Image userImg, Image blobImg) {
        this.tasklist = tasklist;
        this.parser = new Parser();
        this.userImg = userImg;
        this.blobImg = blobImg;
    }

    /**
     * Begins conversation with user
     */
    public void initialise(VBox dialogContainer) {
        String startMsg = "Hello! I'm Blob! What can I do for you?";
        addBlobMsg(dialogContainer,startMsg);
    }

    /**
     *
     * @param dialogContainer container containing all dialogue between Blob and user
     * @param msg String message to be displayed
     */
    public void addBlobMsg(VBox dialogContainer, String msg) {
        dialogContainer.getChildren().addAll(
                DialogBox.getBlobDialog(msg, blobImg)
        );
    }

    /**
     * Maintains constant conversation with user.
     */
    public String converse(VBox dialogContainer, String humanInput) {
        return parser.evaluateAction(this, tasklist, humanInput);
    }
}
