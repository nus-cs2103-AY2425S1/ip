package command;

import exception.ExecuteCommandException;
import helper.Storage;
import helper.TaskList;
import helper.Ui;
import task.Deadline;

import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private String description;
    private LocalDateTime dueDate;

    public DeadlineCommand(String description, LocalDateTime dueDate) {
        this.description = description;
        this.dueDate = dueDate;
    }

    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws ExecuteCommandException {
        tasks.addTask(new Deadline(description, dueDate));
        // TODO: return smt??
    }
}
