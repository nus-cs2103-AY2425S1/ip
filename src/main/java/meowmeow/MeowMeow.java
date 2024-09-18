package meowmeow;

import java.io.IOException;

/**
 * Represents the main class of the MeowMeow application
 */
public class MeowMeow {
    private Storage saver;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public MeowMeow(String filePath) {
        this.saver = new Storage(filePath);
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
        this.tasks = saver.getTaskList();
        //Ui.start();
        this.parser = new Parser(this.tasks, this.saver, this.ui);
        //this.parser.parse();
    }

    public Parser getParser() {
        return this.parser;
    }

    /**
     * Creates a new instance of MeowMeow with the specified save file path.
     * Runs the MeowMeow application.
     *
     * @throws IOException If an I/O error occurs when finding the save file.
     */
    //public static void main(String[] args) throws IOException {
//        new MeowMeow("./data/meowmeow.txt").run();
//    }
}