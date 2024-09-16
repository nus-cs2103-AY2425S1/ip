import duke.exceptions.InvalidDateException;
import duke.exceptions.InvalidInputException;
import duke.exceptions.InvalidTaskInDatabaseException;
import duke.exceptions.MissingDateException;
import duke.exceptions.MissingTaskNameException;
import duke.exceptions.TaskNotFoundException;
import duke.parsers.Parser;
import duke.tasks.TaskList;

/**
 * The Duke class represents the main entry point for the Duke application.
 * It handles the initialization of the user interface, task list, and parser,
 * and processes user commands in a loop until the program is terminated.
 */
public class Duke {
    private TaskList taskList;

    private Parser parser;

    /**
     * Constructs a new Duke instance. Initializes the task list, user interface, and parser.
     */
    public Duke() throws InvalidTaskInDatabaseException {
        taskList = TaskList.init();
        parser = new Parser(taskList);
    }

    public String getResponse(String input) {
        try {
            return parser.parse(input);
        } catch (InvalidInputException | MissingTaskNameException | MissingDateException
                 | TaskNotFoundException | InvalidDateException e) {
            return e.toString();
        }
    }

    public TaskList getTaskList() {
        return taskList;
    }
}

