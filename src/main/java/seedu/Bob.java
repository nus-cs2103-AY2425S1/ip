package seedu;

import seedu.parser.BobException;
import seedu.parser.Parser;
import seedu.storage.Storage;
import seedu.task.TaskList;
import seedu.ui.Formatter;

/**
 * The {@code Bob} program keeps track of the user's tasks, including ToDos, Events, and Deadlines.
 * It remembers past tasks and allows users to manage their tasks by adding, listing, marking, unmarking,
 * deleting, and finding them. Tasks are saved to and loaded from a file to maintain persistence across sessions.
 */
public class Bob {
    private Storage storage;
    private TaskList tasks;
    private Formatter formatter;
    private Parser parser;

    /**
     * Constructs a new instance of the {@code Bob} program. Initializes the {@code Formatter}, {@code TaskList},
     * {@code Storage}, and {@code Parser} objects, and loads tasks from the storage.
     */
    public Bob() {
        this.formatter = new Formatter();
        this.tasks = new TaskList();
        this.storage = new Storage();
        this.parser = new Parser();
        this.storage.loadTasks(this.tasks);

        assert this.formatter != null;
        assert this.tasks != null;
        assert this.storage != null;
        assert this.parser != null;
    }

    /**
     * Starts the Bob program by introducing Bob to the user.
     *
     * @return A string that introduces Bob.
     */
    public String start() {
        return formatter.introduceBobUi();
    }

    /**
     * Exits the program. Saves the current tasks to a text file before exiting.
     *
     * @return A string indicating that Bob is exiting.
     */
    public String exit() {
        this.tasks.saveTasks(this.storage);
        return formatter.exitBobUi();
    }

    /**
     * Receives user input and sends it to the {@code Parser} for further processing.
     * Returns Bob's response based on the user's command.
     *
     * @param message The user input message.
     * @return Bob's response to the user input.
     */
    public String getResponse(String message) {
        assert message != null : "User input message should not be null";
        String command = message.split(" ")[0];
        try {
            return handleCommand(message, command);
        } catch (BobException e) {
            return e.getMessage();
        }
    }

    /**
     * Handles the user's command by parsing the input and executing the corresponding action.
     *
     * @param message The user input message.
     * @param command The first word in the user input, indicating the command.
     * @return A string response from Bob based on the executed command.
     * @throws BobException If an unknown command is encountered or a parsing error occurs.
     */
    private String handleCommand(String message, String command) throws BobException {
        switch (command) {
        case "bye":
            return exit();
        case "list":
            return this.tasks.listTasks();
        case "mark":
            return this.parser.markTaskAsDoneParser(message, this.tasks);
        case "unmark":
            return this.parser.unmarkTaskAsDoneParser(message, this.tasks);
        case "delete":
            return this.parser.deleteTaskParser(message, this.tasks);
        case "todo":
            return this.parser.addToDoParser(message, this.tasks);
        case "deadline":
            return this.parser.addDeadlineParser(message, this.tasks);
        case "event":
            return this.parser.addEventParser(message, this.tasks);
        case "find":
            return this.parser.findParser(message, this.tasks);
        default:
            throw new BobException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }
}
