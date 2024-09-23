import carine.exceptions.InvalidDateException;
import carine.exceptions.InvalidInputException;
import carine.exceptions.InvalidTaskInDatabaseException;
import carine.exceptions.MissingDateException;
import carine.exceptions.MissingTaskNameException;
import carine.exceptions.TaskNotFoundException;
import carine.parsers.Parser;
import carine.tasks.TaskList;

/**
 * The Duke class represents the main entry point for the Duke application.
 * It handles the initialization of the user interface, task list, and parser,
 * and processes user commands in a loop until the program is terminated.
 */
public class Carine {
    private TaskList taskList;

    private Parser parser;

    /**
     * Constructs a new Duke instance. Initializes the task list, user interface, and parser.
     */
    public Carine() throws InvalidTaskInDatabaseException {
        taskList = TaskList.init();
        parser = new Parser(taskList);
    }


    /**
     * Returns response based on user input.
     *
     * @return The response to user if action is successful or exception is found.
     */
    public String getResponse(String input) {
        try {
            return parser.parse(input);
        } catch (InvalidInputException | MissingTaskNameException | MissingDateException
                 | TaskNotFoundException | InvalidDateException e) {
            return "ERROR: " + e;
        }
    }

    public TaskList getTaskList() {
        return taskList;
    }
}

