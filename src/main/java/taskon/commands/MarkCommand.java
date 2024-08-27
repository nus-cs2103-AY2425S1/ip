package taskon.commands;

import taskon.exception.TaskonException;
import taskon.storage.Storage;
import taskon.task.TaskList;
import taskon.ui.Ui;

public class MarkCommand extends Command {

    public static final String COMMAND_WORD = "mark";
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.getTask(this.index).markAsDone();
            ui.mark(taskList.getTask(this.index));
            storage.saveTasks(taskList);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("You only have " + taskList.size() + " tasks!\n");
        } catch (TaskonException e) {
            System.out.println(e.getMessage());
        }
    }
}
