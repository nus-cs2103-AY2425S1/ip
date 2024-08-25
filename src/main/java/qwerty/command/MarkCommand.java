package qwerty.command;

import java.util.HashMap;
import qwerty.Storage;
import qwerty.TaskList;
import qwerty.Ui;
import qwerty.task.Task;

public class MarkCommand extends Command {

    public MarkCommand(HashMap<String, String> args) {
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
            tasks.markTaskAsDone(index);
            ui.showMessage("\nMarked task as done:\n" + task
                    + "\nYou actually did it, right?");
        } catch (NumberFormatException e) {
            ui.showError("You did not give a number as the index.");
        } catch (IndexOutOfBoundsException e) {
            ui.showError("That index is out of bounds.");
        }
    }
}
