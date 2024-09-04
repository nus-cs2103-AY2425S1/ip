package sigma.command;

import sigma.utils.Storage;
import sigma.utils.TaskList;
import sigma.utils.Ui;
import sigma.exception.SigmaException;
import sigma.task.ToDoTask;

/**
 * Represents the command to add a todo task.
 */
public class ToDoCommand extends Command {

    public ToDoCommand(String[] split) {
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
        ToDoTask toDoTask = null;
        try {
            if (split.length < 2 || split[1].strip() == "") {
                ui.throwError("???? You're missing the task! Write \"todo <task>\"!");
            }
            toDoTask = new ToDoTask(split[1].strip());
        } catch (NullPointerException e) {
            ui.throwError("Please don't run me on null!");
        }
        tasks.add(toDoTask);
        return ui.print("Productive! Added: \n" + toDoTask.toString()
                + "\nNow you have " + tasks.size() + " tasks in the list!");
    }

}
