package blob;

import javafx.scene.image.Image;
import javafx.scene.layout.VBox;

import java.io.IOException;

/**
 * Represents chatbot Blob. Blob is able to help the user form a "to-do" list
 * in the form of a csv file.
 * Blob requires the user to enter the file path to which they desire the csv file to be saved
 * (preferably "./database.csv")
 */
public class Blob {
    private blob.Storage storage;
    private TaskList tasklist;
    private Ui ui;

    public Blob(String filePath, Image userImg, Image blobImg) {
        this.storage = new Storage(filePath);
        this.tasklist = new TaskList(this.storage);
        this.ui = new Ui(this.tasklist, userImg, blobImg);
    }

    /**
     * Kicks start the chatbot's chatting functionality
     */
    public void run(VBox dialogContainer) {
        this.ui.initialise(dialogContainer);
    }

    public String reply(VBox dialogContainer, String humanInput) {
        return this.ui.converse(dialogContainer, humanInput);
    }



//    public static void main(String[] args) {
//        new Blob("./src/main/java/database.csv").run();
//    }
}
