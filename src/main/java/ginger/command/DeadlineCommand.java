package ginger.command;

import ginger.task.Task;
import ginger.task.TaskHandler;
import ginger.ui.Ui;
import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private final String title;
    private final LocalDateTime deadline;

    public DeadlineCommand(String title, LocalDateTime deadline) {
        this.title = title;
        this.deadline = deadline;
    }
    @Override
    public void execute(TaskHandler taskHandler, Ui ui) {
        Task t = taskHandler.addDeadline(this.title, this.deadline);
        ui.outputMessage(String.format("Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.",
                t, taskHandler.taskCount()));
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DeadlineCommand d) {
            return d.title.equals(this.title) && d.deadline.equals(this.deadline);
        }
        return false;
    }
}
