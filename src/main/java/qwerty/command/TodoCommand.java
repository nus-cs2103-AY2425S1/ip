package qwerty.command;

import java.util.HashMap;

import qwerty.QwertyException;
import qwerty.Storage;
import qwerty.TaskList;
import qwerty.Ui;
import qwerty.task.Task;
import qwerty.task.Todo;

public class TodoCommand extends Command {
    public TodoCommand(HashMap<String, String> args) {
        super(args);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws QwertyException {
        // Check if arguments exist
        String description = getArgs().get("main");
        if (description == null) {
            throw new QwertyException("Your task is missing a name.");
        }

        // Create a new todo task and add it to the task list
        Task todo = new Todo(getArgs().get("main"));
        tasks.addTask(todo);

        ui.showMessage("\nAdded this task:\n" + todo
                + "\nNow you have " + tasks.getSize() + (tasks.getSize() == 1 ? " task " : " tasks ")
                + "in the list.\nBetter get to it.");
    }

}
