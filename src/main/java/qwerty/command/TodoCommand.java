package qwerty.command;

import java.util.HashMap;
import qwerty.QwertyException;
import qwerty.Storage;
import qwerty.TaskList;
import qwerty.Ui;
import qwerty.task.Task;
import qwerty.task.Todo;

/**
 * This class encapsulates a 'todo' command.
 */
public class TodoCommand extends Command {
    public TodoCommand(HashMap<String, String> args) {
        super(args);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    /**
     * Creates a new Todo task and adds it to the TaskList.
     *
     * @param tasks The TaskList component handling storage of Task objects.
     * @param ui The Ui component handling user interaction.
     * @param storage The Storage component handling read/write to hard disk.
     * @throws QwertyException If the description parameter is missing its argument.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws QwertyException {
        String description = getArgs().get("main");
        if (description == null) {
            throw new QwertyException("Your task is missing a name.");
        }
        Task todo = new Todo(getArgs().get("main"));
        tasks.addTask(todo);
        ui.showMessage("\nAdded this task:\n" + todo
                + "\nNow you have " + tasks.getSize() + (tasks.getSize() == 1 ? " task " : " tasks ")
                + "in the list.\nBetter get to it.");
    }

}
