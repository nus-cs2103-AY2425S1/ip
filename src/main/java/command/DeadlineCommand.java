package command;

import exception.ExecuteCommandException;
import exception.FileException;
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
    public void execute(Ui ui, Storage storage, TaskList tasks) throws ExecuteCommandException, FileException {
        tasks.addTask(new Deadline(description, dueDate));
        storage.saveTaskToFile(tasks);
        // TODO: return smt??
    }

    @Override
    public String toString() {
        return "deadline command " + description + " " + dueDate;
    }
}
