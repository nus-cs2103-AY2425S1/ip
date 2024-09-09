package sigma.command;

import sigma.exception.SigmaException;
import sigma.task.TodoTask;
import sigma.utils.Storage;
import sigma.utils.TaskList;
import sigma.utils.Ui;

/**
 * Represents the command to add a todo task.
 */
public class TodoCommand extends Command {

    public TodoCommand(String[] commandArray) {
        super(commandArray);
    }

    /**
     * Adds a todo task to the task list.
     *
     * @param tasks
     * @param ui
     * @param storage
     *
     * @throws SigmaException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws SigmaException {
        String message = "???? You're missing the task! Write \"todo <task>\"!";
        if (commandArray == null) {
            throw new SigmaException("Why is commandArray null? Sigma is confused.");
        }
        if (commandArray.length < 2) {
            throw new SigmaException(message);
        }
        String description = commandArray[1].strip();
        if (description == "") {
            throw new SigmaException(message);
        }
        TodoTask toDoTask = new TodoTask(description);
        tasks.add(toDoTask);
        return "Productive! Added: \n" + toDoTask
                + "\nNow you have " + tasks.size() + " tasks in the list!";
    }

}
