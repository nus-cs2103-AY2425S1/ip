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
     * Constructs a new {@code TodoCommand} with the specified {@link TaskList} and task description.
     *
     * @param taskList      The list of tasks to which the new to-do task will be added.
     * @param restOfCommand The rest of the command given, which should include the task description.
     */
    public TodoCommand(TaskList taskList, String restOfCommand) {
        super(taskList, restOfCommand);
    }

    /**
     * Executes the command by adding a new to-do task to the {@link TaskList}.
     * If the task description is empty, an {@link EmptyStobberiException} is thrown.
     * If the task already exists in the list, a {@link SameTaskStobberiException} is thrown.
     *
     * @return A string indicating that the to-do task has been added successfully.
     * @throws StobberiException If the task description is empty or if the task already exists in the list.
     */
    @Override
    public String execute() throws StobberiException {
        String descriptions = getRestOfCommand();

        if (descriptions.isEmpty()) {
            throw new EmptyStobberiException("Where is the task?");
        }
        if (getTaskList().hasTask(descriptions)) {
            throw new SameTaskStobberiException("This task has already been added!\n"
                    + "Pwease change your description!!");
        }
        return getTaskList().addTask(new Todo(descriptions));
    }
}
