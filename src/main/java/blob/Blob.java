package blob;

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

    public Blob(String filePath) {
        this.storage = new Storage(filePath);
        this.tasklist = new TaskList(this.storage);
        this.ui = new Ui(this.tasklist);
    }

    /**
     * Kicks start the chatbot's chatting functionality
     */
    public void run() {
        this.ui.initialise();
        this.ui.converse();
    }

    public static void main(String[] args) throws IOException {
        new Blob("./src/main/java/database.csv").run();
    }
}
