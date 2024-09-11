package arts.command;

import arts.ArtsException;
import arts.task.TaskList;
import arts.task.Todo;
import arts.util.Storage;
import arts.util.Ui;

/**
 * Represents a command to add a todo task to the task list.
 */
public class AddTodoCommand implements Command {
    private static final String EMPTY_DESCRIPTION_ERROR_MESSAGE = "The description of a todo cannot be empty.";
    private static final String DUPLICATE_TODO_MESSAGE = "A todo with the same description already exists.";

    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;
    private final String description;

    /**
     * Constructs an AddTodoCommand with the specified task list, storage, UI, and task description.
     *
     * @param tasks The list of tasks.
     * @param storage The storage used to save tasks.
     * @param ui The user interface for displaying messages.
     * @param description The description of the todo task to be added.
     */
    public AddTodoCommand(TaskList tasks, Storage storage, Ui ui, String description) {
        assert tasks != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";
        assert ui != null : "Ui cannot be null";
        assert description != null && !description.trim().isEmpty() : "Description cannot be null or empty";

        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
        this.description = normalizeSpaces(description);
    }

    /**
     * Executes the command to add a todo task. Checks the task description for validity,
     * adds the task to the task list, saves the updated task list to storage, and returns
     * a confirmation message.
     *
     * @throws ArtsException If the task description is empty or invalid.
     */
    @Override
    public String execute() throws ArtsException {
        if (description == null || description.trim().isEmpty()) {
            throw new ArtsException(EMPTY_DESCRIPTION_ERROR_MESSAGE);
        }

        Todo newTodo = new Todo(description);

        if (tasks.contains(newTodo)) {
            throw new ArtsException(DUPLICATE_TODO_MESSAGE);
        }

        tasks.addTask(newTodo);

        try {
            storage.save(tasks.getTasks());
        } catch (Exception e) {
            throw new ArtsException("Failed to save tasks: " + e.getMessage());
        }

        return String.format("Hooray! 🎊 A new adventure awaits with this task:\n✨ %s ✨\n"
                        + "Your quest now has %d %s to conquer! Keep shining, champion! 🌟",
                tasks.getTask(tasks.size() - 1),
                tasks.size(),
                tasks.size() == 1 ? "task" : "tasks");
    }

    /**
     * Normalize spaces in a string by replacing multiple spaces with a single space.
     *
     * @param input The input string.
     * @return The normalized string.
     */
    private String normalizeSpaces(String input) {
        return input.replaceAll("\\s+", " ").trim();
    }
}
