package demurebot.command;

import demurebot.task.Deadline;
import demurebot.task.TaskList;
import demurebot.ui.Ui;

public class DeadlineCommand extends Command {
    private final Deadline deadline;

    public DeadlineCommand(Deadline deadline) {
        super(false);
        this.deadline = deadline;
    }

    @Override
    public void execute(TaskList list, Ui ui) {
        list.addTask(this.deadline);
        ui.displayAddTask(this.deadline, list.getSize());
    }
}
