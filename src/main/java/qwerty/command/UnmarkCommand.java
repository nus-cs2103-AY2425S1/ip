package qwerty.command;

import java.util.HashMap;

import qwerty.Storage;
import qwerty.TaskList;
import qwerty.Ui;
import qwerty.task.Task;

public class UnmarkCommand extends Command {

    public UnmarkCommand(HashMap<String, String> args) {
        super(args);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            // Get the task from the task list
            int index = Integer.parseInt(getArgs().get("main"));
            Task task = tasks.getTask(index);

            // Mark the task as not done and print a message
            tasks.markTaskAsNotDone(index);
            ui.showMessage("\nMarked task as not done:\n" + task
                    + "\nMore work for you, boohoo.");
        } catch (NumberFormatException e) {
            ui.showError("You did not give a number as the index.");
        } catch (IndexOutOfBoundsException e) {
            ui.showError("That index is out of bounds.");
        }
    }
}
