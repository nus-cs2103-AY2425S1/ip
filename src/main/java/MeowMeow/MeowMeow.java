package MeowMeow;

import java.io.IOException;

/**
 * Represents the main class of the MeowMeow application
 */
public class MeowMeow {
    private Saving saver;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    public MeowMeow(String filePath) {
        this.saver = new Saving(filePath);
        this.ui = new Ui();
    }

    /**
     * Loads saved data and creates a TaskList with any saved Tasks.
     * Asks for user input and parses it using a new Parser object.
     *
     * @throws IOException If an I/O error occurs during loading the save file.
     */
    public void run() throws IOException {
        this.saver.getData();
        this.taskList = saver.getTaskList();
        String input = Ui.start();
        this.parser = new Parser(this.taskList, this.saver, this.ui, input);
        this.parser.parse();
    }

    /**
     * Creates a new instance of MeowMeow with the specified save file path.
     * Runs the MeowMeow application.
     *
     * @throws IOException If an I/O error occurs when finding the save file.
     */
    public static void main(String[] args) throws IOException {
        new MeowMeow("./data/duke.txt").run();
    }
}