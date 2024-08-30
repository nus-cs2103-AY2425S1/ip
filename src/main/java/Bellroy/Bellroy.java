package Bellroy;

import java.io.IOException;

/**
 * Represents an instance of the Bellroy Chatbot.
 */

public class Bellroy {

    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList taskList;

    /**
     * Initializes the Bellroy Chatbot. If the input file is not found, it will create a new file with that name.
     *
     * @param filePath path to the file that is to be read from and written at the end of the program
     */
    public Bellroy(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (IOException e) {
            taskList = new TaskList();
        }
    }

    /**
     * Starts the Chatbot and asks the user for an input.
     */
    public void run() {
        ui.printWelcomeMessage();
        boolean isRunning = true;
        while(isRunning) {
            String userInput = ui.getInput();
            parser.parse(userInput,taskList,ui,storage);
        }
    }
    public static void main(String[] args) {
        new Bellroy("Bellroy.txt").run();
    }
}
