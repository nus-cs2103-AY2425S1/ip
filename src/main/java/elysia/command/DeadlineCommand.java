package elysia.command;

import elysia.storage.Storage;
import elysia.task.Deadline;
import elysia.task.Task;
import elysia.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class DeadlineCommand extends Command {
    private final String description;
    private LocalDate by;
    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }


    public String execute(ArrayList<Task> tasks) {
        Ui ui = new Ui();
        Task task = new Deadline(this.description, this.by);
        assert task != null: "task is null";
        tasks.add(task);
        try {
            Storage.appendToFile(task);
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }
        return ui.getAddedMessage(tasks, task);
    }
}
