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

    public TodoCommand(String[] split) {
        super(split);
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
        if (split.length < 2 || split[1].strip() == "") {
            Ui.throwError("???? You're missing the task! Write \"todo <task>\"!");
        }
        TodoTask toDoTask = new TodoTask(split[1].strip());
        tasks.add(toDoTask);
        return "Productive! Added: \n" + toDoTask
                + "\nNow you have " + tasks.size() + " tasks in the list!";
    }

}
