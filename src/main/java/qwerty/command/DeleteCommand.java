package qwerty.command;

import java.util.HashMap;
import qwerty.Storage;
import qwerty.TaskList;
import qwerty.Ui;
import qwerty.task.Task;

public class DeleteCommand extends Command {

    public DeleteCommand(HashMap<String, String> args) {
        super(args);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int index = Integer.parseInt(getArgs().get("main"));
            Task task = tasks.getTask(index);
            tasks.deleteTask(index);
            if (task.getIsDone()) {
                ui.showMessage("\nRemoved this completed task:\n" + task
                        + "\nFinally, some progress.");
            } else {
                ui.showMessage("\nRemoved this incomplete task:\n" + task
                        + "\nYou aren't slacking off, are you?");
            }
        } catch (NumberFormatException e) {
            ui.showError("You did not give a number as the index.");
        } catch (IndexOutOfBoundsException e) {
            ui.showError("That index is out of bounds.");
        }
    }
}
