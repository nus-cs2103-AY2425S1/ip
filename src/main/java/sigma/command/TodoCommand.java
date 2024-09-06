package sigma.command;

import sigma.utils.Storage;
import sigma.utils.TaskList;
import sigma.utils.Ui;
import sigma.exception.SigmaException;
import sigma.task.TodoTask;

/**
 * Represents the command to add a todo task.
 */
public class TodoCommand extends Command {

    public TodoCommand(String[] split) {
        super(split);
    }

    /**
     * Adds a todo task to the task list.
     * @param tasks
     * @param ui
     * @param storage
     * @throws SigmaException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws SigmaException {
        TodoTask toDoTask = null;
        try {
            if (split.length < 2 || split[1].strip() == "") {
                ui.throwError("???? You're missing the task! Write \"todo <task>\"!");
            }
            toDoTask = new TodoTask(split[1].strip());
        } catch (NullPointerException e) {
            ui.throwError("Please don't run me on null!");
        }
        tasks.add(toDoTask);
        return ui.print("Productive! Added: \n" + toDoTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list!");
    }

}
