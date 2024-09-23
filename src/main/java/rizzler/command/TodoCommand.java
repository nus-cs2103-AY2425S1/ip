package rizzler.command;

import rizzler.RizzlerException;
import rizzler.Storage;
import rizzler.task.TaskLog;
import rizzler.task.ToDo;

/**
 * Represents the user's instruction to create a new todo.
 */
public class TodoCommand extends Command {
    public static final String COMMAND_FORMAT = """
            Correct Usage:
            todo {task description}
            Examples:
            todo Feed Max tonight!
            todo Submit CS2103 individual proj""";
    private final ToDo newTodo;

    /**
     * Constructs a TodoCommand. Also initialises a ToDo.
     *
     * @param todoDesc Text description of the task to be done.
     */
    public TodoCommand(String todoDesc) throws RizzlerException {
        super();
        checkInputValidity(todoDesc);
        this.newTodo = new ToDo(todoDesc);
    }

    /**
     * Adds the created ToDo item to the taskLog, and also updates the storage file.
     *
     * @param storage <code>Storage</code> object instantiated in main <code>Rizzler</code> class.
     * @param taskLog <code>TaskLog</code> object instantiated in main <code>Rizzler</code> class.
     * @return Lines representing the created ToDo for user verification.
     */
    @Override
    public String[] execute(Storage storage, TaskLog taskLog) {
        taskLog.addTask(newTodo);
        storage.storeTasks(taskLog);
        return createConfirmationMessage(taskLog.getNumTasks());
    }

    private String[] createConfirmationMessage(int newNumTasks) {
        return new String[] {"certainly, i'll keep track of this todo for you ;)",
                "\t" + newTodo,
                "now we have " + newNumTasks + " tasks to work on."};
    }

    private void checkInputValidity(String todoDesc) throws RizzlerException {
        if (todoDesc.isEmpty()) {
            throw new RizzlerException("missing argument");
        }
    }
}
