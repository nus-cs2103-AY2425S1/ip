package barcus;

import barcus.command.Command;
import barcus.exception.BarcusException;
import barcus.parser.Parser;
import barcus.storage.Storage;
import barcus.tasklist.TaskList;

/**
 * Main class for Barcus chatbot
 */
public class Barcus {
    private Storage storage;
    private TaskList tasks;
    private String commandType;

    /**
     * Constructor for Barcus, creates a new instance of Barcus with path to saved task data
     * @param filePath
     */
    public Barcus(String filePath) {
        this.storage = new Storage(filePath);
        try {
            tasks = storage.load();
        } catch (BarcusException e) {
            tasks = new TaskList();
        }
        this.commandType = "UnknownCommand";
    }

    /**
     * Executes command and gets output
     * @param input full command from user
     * @return barcus's string output
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            c.execute(tasks, storage);
            commandType = c.getClass().getSimpleName();
            //System.out.println("Command type: " + commandType);
            return c.getString();
        } catch (BarcusException e) {
            commandType = "UnknownCommand";
            return "Uh oh, " + e.getMessage();
        }
    }

    /**
     * Returns the welcome message of barcus
     * @return String welcome message
     */
    public String getWelcome() {
        return "Beep bop! Hello I am Barcus, ready to be of assistance!\n"
                + "Write 'bye' to leave!\n";
    }

    /**
     * Returns last command type used
     * @return string of command type name
     */
    public String getCommandType() {
        return commandType;
    }
}
