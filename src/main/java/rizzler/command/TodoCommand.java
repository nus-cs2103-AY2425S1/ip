package rizzler.command;

import rizzler.Storage;
import rizzler.task.TaskLog;
import rizzler.task.ToDo;

/**
 * Represents the user's command to create a new ToDo.
 */
public class TodoCommand extends Command {
    private final ToDo newTodo;

    /**
     * Constructor for a TodoCommand. Also initialises a ToDo.
     * @param todoDesc Text description of the task to be done.
     */
    public TodoCommand(String todoDesc) {
        super();
        this.newTodo = new ToDo(todoDesc);
    }

    /**
     * Adds the created ToDo item to the taskLog, and also updates the storage file.
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

    private String[] createConfirmationMessage(int numTasks) {
        return new String[] {"certainly, i'll keep track of this todo for you ;)",
                "\t" + newTodo,
                "now we have " + numTasks + " tasks to work on."};
    }
}
