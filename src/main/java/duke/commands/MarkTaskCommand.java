package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class MarkTaskCommand extends Command {
    private boolean markAsDone;
    private int taskIndex;

    public MarkTaskCommand(boolean markAsDone, int taskIndex) {
        super();
        this.markAsDone = markAsDone;
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (this.taskIndex != -1) {
            Task task = taskList.getTask(this.taskIndex);
            if (this.markAsDone) {
                task.setDone();
                System.out.println(Ui.formatMarkTask(task));
            } else {
                task.setNotDone();
                System.out.println(Ui.formatUnmarkTask(task));
            }
        }
    }
}
