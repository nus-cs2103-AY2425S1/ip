package stobberi.command;

import stobberi.components.TaskList;
import stobberi.stobberiexception.EmptyStobberiException;
import stobberi.stobberiexception.SameTaskStobberiException;
import stobberi.stobberiexception.StobberiException;
import stobberi.task.Todo;

/**
 * Represents a command to add a new to-do task to a {@link TaskList}.
 */
public class TodoCommand extends Command {
    /**
     * The list of tasks to which the to-do task will be added.
     */
    private TaskList taskList;

    /**
     * The description of the to-do task.
     */
    private String descriptions;

    /**
     * Constructs a new {@code TodoCommand} with the specified {@link TaskList} and task description.
     *
     * @param taskList    The list of tasks to which the new to-do task will be added.
     * @param descriptions The description of the to-do task.
     */
    public TodoCommand(TaskList taskList, String descriptions) {
        this.taskList = taskList;
        this.descriptions = descriptions;
    }

    /**
     * Executes the command by adding a new to-do task to the {@link TaskList}.
     * If the description is empty, an {@link EmptyStobberiException} is thrown.
     *
     * @throws StobberiException if the task description is empty.
     */
    @Override
    public String execute() throws StobberiException {
        if (descriptions.isEmpty()) {
            throw new EmptyStobberiException("Where is the task?");
        }
        if (taskList.hasTask(descriptions)) {
            throw new SameTaskStobberiException("I'm sorri! This task has already been added!");
        }
        return taskList.addTask(new Todo(descriptions));
    }
}
