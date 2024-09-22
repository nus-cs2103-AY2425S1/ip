package meowmeow;

import java.io.IOException;

/**
 * Represents the main class of the MeowMeow application
 */
public class MeowMeow {
    private Storage saver;
    private TaskList tasks;
    private Parser parser;

    public MeowMeow(String filePath) {
        this.saver = new Storage(filePath);
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
        this.parser = new Parser(this.tasks, this.saver);
    }

    /**
     * Returns the parser of the current MeowMeow instance.
     */
    public Parser getParser() {
        return this.parser;
    }
}