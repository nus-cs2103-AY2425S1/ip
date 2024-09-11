package fishman.command;

import fishman.task.Deadline;
import fishman.task.Event;
import fishman.task.Task;
import fishman.task.TaskList;
import fishman.utils.Ui;

import java.time.LocalDateTime;

public class UpdateCommand implements Command {
    private final int index;
    private final LocalDateTime newStartDateTime;
    private final LocalDateTime newEndDateTime;

    public UpdateCommand(int index, LocalDateTime newStartDateTime, LocalDateTime newEndDateTime) {
        this.index = index;
        this.newStartDateTime = newStartDateTime;
        this.newEndDateTime = newEndDateTime;
    }

    @Override
    public String execute(TaskList tasks, Ui ui){
        Task task = tasks.getTask(index);
        if (task instanceof Deadline) {
            if (newStartDateTime != null) {
                ((Deadline) task).setDeadlineDate(newStartDateTime);
            }
        } else if (task instanceof Event) {
            if (newStartDateTime != null) {
                ((Event) task).setEventStart(newStartDateTime);
            }
            if (newEndDateTime != null) {
                ((Event) task).setEventEnd(newEndDateTime);
            }
        }

        return ui.getUpdatedTaskMessage(task);
    }


}
