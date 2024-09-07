package elysia.command;

import elysia.storage.Storage;
import elysia.task.Deadline;
import elysia.task.Task;
import elysia.ui.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

public class DeadlineCommand extends Command {
    private final String description;
    private LocalDate by;
    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }


    public void execute(ArrayList<Task> tasks, Storage storage) {
        Ui ui = new Ui();
        Task task = new Deadline(this.description, this.by);
        tasks.add(task);
        ui.showAddedMessage(tasks, task);
    }
}
