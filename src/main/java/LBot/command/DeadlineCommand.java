package LBot.command;

import LBot.exception.ExecuteCommandException;
import LBot.exception.FileException;
import LBot.helper.Storage;
import LBot.helper.TaskList;
import LBot.helper.Ui;
import LBot.task.Deadline;
import LBot.task.Task;

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
        Task deadline = new Deadline(description, dueDate);
        tasks.addTask(deadline);
        storage.saveTaskToFile(tasks);
        ui.printTaskAddedMessage(deadline);
    }

    @Override
    public String toString() {
        return "deadline command " + description + " " + dueDate;
    }
}
