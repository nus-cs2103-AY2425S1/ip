package commands;

import exception.TaskonException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.getTask(this.index).markAsUndone();
            ui.unmark(taskList.getTask(this.index));
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You only have " + taskList.size() + " tasks!\n");
        } catch (TaskonException e) {
            System.out.println(e.getMessage());
        }
    }
}
