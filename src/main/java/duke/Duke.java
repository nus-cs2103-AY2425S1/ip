package duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * The Duke class represents the main entry point for the Duke application.
 * It initializes the necessary components and handles the main interaction loop.
 */
public class Duke {

    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList taskList;
    private Boolean isOnline;

    /**
     * Constructs a new Duke instance.
     * Initializes the user interface, storage, parser, and task list.
     */
    public Duke() {
        this.ui = new Ui(this);
        this.storage = new Storage("data/", "duke.txt");
        this.parser = new Parser();
        this.taskList = new TaskList();
        this.storage.loadFile(this.taskList, this.parser);
        this.isOnline = true;
    }

    /**
     * Processes a command by parsing it and executing the appropriate actions.
     * Captures and returns the output produced during the command execution.
     *
     * @param command The command to be processed.
     * @return The output produced during the execution of the command.
     */
    public String getResponse(String command) {
        PrintStream originalOut = System.out;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(outputStream);

        System.setOut(ps);

        try {
            this.parser.parseCommand(command, this.taskList, this.storage, this.ui);
        } finally {
            System.setOut(originalOut);
        }

        return outputStream.toString();
    }

    /**
     * Checks if Duke is currently online.
     *
     * @return true if Duke is online; false otherwise.
     */
    public boolean isOnline() {
        return isOnline;
    }

    /**
     * Gets the greeting message from the user interface.
     *
     * @return The greeting message from Duke.
     */
    public String getGreeting() {
        return this.ui.greet();
    }

    /**
     * Sets Duke to offline mode, indicating that it is no longer active.
     */
    public void goOffline() {
        this.isOnline = false;
    }

}