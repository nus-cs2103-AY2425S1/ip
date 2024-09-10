package duck.commands;

import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.data.task.ToDo;
import duck.storage.Storage;
import duck.ui.Ui;

/**
 * Represents a command to add a ToDo task to the task list.
 * When executed, it parses the input message and adds the corresponding ToDo task to the list.
 */
public class ToDoCommand extends Command {

    private static final String TODO_COMMAND_ERROR_MESSAGE = """
            Quack, what are you trying "to do"? Give Duck a valid description instead of an empty one.
            todo {description}
            """;

    /**
     * Constructs a ToDoCommand with the specified message.
     *
     * @param message The message associated with the command, which should contain the description of the ToDo task.
     */
    public ToDoCommand(String message) {
        super(message);
    }

    /**
     * Executes the command by parsing the message and adding a ToDo task to the task list.
     * The task is then saved to the storage system.
     *
     * @param tasks The list of tasks to which the ToDo task will be added.
     * @param storage The storage system for saving the updated list of tasks.
     * @param ui The user interface for displaying messages to the user (not used in this command).
     * @throws DuckException If the description of the ToDo task is invalid.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DuckException {
        super.execute(tasks, storage, ui);

        ToDo todo = parseToDo(message);
        tasks.addTask(todo, storage);
    }

    /**
     * Determines whether the command signifies an exit operation.
     *
     * @return false, as the ToDoCommand does not signify an exit operation.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Parses the input message to extract the description of the ToDo task.
     *
     * @param input The message containing the description of the ToDo task.
     * @return A ToDo task with the specified description.
     * @throws DuckException If the description is empty.
     */
    private ToDo parseToDo(String input) throws DuckException {
        String description = getDescription(input);
        return new ToDo(description);
    }

    private String getDescription(String input) throws DuckException {
        String description = input.replaceFirst("(?i)^todo\\s*", "");
        if (description.isEmpty()) {
            throw new DuckException(TODO_COMMAND_ERROR_MESSAGE);
        }
        return description;
    }
}
