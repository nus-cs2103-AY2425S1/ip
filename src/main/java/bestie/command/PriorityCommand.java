package bestie.command;

import bestie.Storage;
import bestie.TaskList;
import bestie.Ui;
import bestie.task.Priority;

public class PriorityCommand extends Command{

    private Priority priority;

    public PriorityCommand(Priority priority) {
        // priority of tasks user wants to find
        this.priority = priority;
    }

    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTasksOfPriority(tasks.getTasks(), priority);
    }
}
