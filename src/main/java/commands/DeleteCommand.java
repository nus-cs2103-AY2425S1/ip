package commands;

import storage.Storage;
import task.Task;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task task = taskList.getTask(index);
            taskList.deleteTask(index);
            ui.showDeleted(task, taskList.size());
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You only have " + taskList.size() + " tasks!\n");
        } catch (NumberFormatException e) {
            System.out.println("Invalid Integer Input!\n");
        }
    }
}
