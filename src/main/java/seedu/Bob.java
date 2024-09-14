package seedu;

import seedu.parser.BobException;
import seedu.parser.Parser;
import seedu.storage.Storage;
import seedu.task.TaskList;
import seedu.ui.Formatter;

/**
 * Bob program keeps track of the users ToDos, Events, Deadlines.
 * Remembers past tasks.
 */
public class Bob {
    private Storage storage;
    private TaskList tasks;
    private Formatter formatter;
    private Parser parser;

    /**
     * Constructor for Bob
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

    public String start() {
        return formatter.introduceBobUi();
    }

    /**
     * Exits the program. Saves the task into a text file.
     */
    public String exit() {
        this.tasks.saveTasks(this.storage);
        return formatter.exitBobUi();
    }

    /**
     * Receives input and sends to the parser for further processing.
     * @param message Message of user input
     * @return Bob's response
     */
    public String getResponse(String message) {
        assert message != null;
        String command = message.split(" ")[0];
        try {
            return handleCommand(message, command);
        } catch (BobException e) {
            return e.getMessage();
        }
    }

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
