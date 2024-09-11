package command;

import java.time.LocalDateTime;

import task.TaskList;

public class UpdateTaskCommand extends Command {
    private String newDesc;
    private LocalDateTime fromDate;
    private LocalDateTime toDate;

    public UpdateTaskCommand(int taskNum, String newDesc, LocalDateTime from, LocalDateTime to) {
        super(taskNum, null);
        this.newDesc = newDesc;
        this.fromDate = from;
        this.toDate = to;
    }

    @Override
    public String execute(TaskList tasks) {
        if (this.newDesc == null && this.fromDate == null && this.toDate == null) {
            return (new ErrorCommand("Seems like you're forgetting a few flags!")).execute(tasks);
        }
        try {
            tasks.update(this.getTaskIndex(), this.newDesc, this.fromDate, this.toDate);
            return "Task updated successfully!\n" + tasks.getTask(this.getTaskIndex());
        } catch (NullPointerException e) {
            return (new ErrorCommand("Hang on, you don't have that many tasks!")).execute(tasks);
        }   
    }
}
