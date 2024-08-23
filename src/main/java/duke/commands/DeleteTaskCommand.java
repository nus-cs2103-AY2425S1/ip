package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

public class DeleteTaskCommand extends Command {
    private int taskIndex;

    public DeleteTaskCommand(int taskIndex) {
        super();
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (this.taskIndex != -1) {
            Task task = taskList.deleteTask(this.taskIndex);
            System.out.println(Ui.formatDeleteTask(task, taskList.getTasks().size()));
        }
    }
}
