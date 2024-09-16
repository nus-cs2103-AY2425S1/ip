package elysia.command;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import elysia.storage.Storage;
import elysia.task.Deadline;
import elysia.task.Task;
import elysia.ui.Ui;

/**
 * Represents a create-deadline command in the application.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDate by;

    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.by = by;
    }

    /**
     * Executes the command to add the deadline task to the list and update the storage.
     *
     * @param tasks The list of tasks to which the new deadline task will be added.
     * @return A message indicating the result of the operation, including confirmation of the task addition.
     */
    public String execute(ArrayList<Task> tasks) {
        Ui ui = new Ui();
        Task task = new Deadline(this.description, this.by);
        assert task != null : "task is null";
        tasks.add(task);
        try {
            Storage.appendToFile(task);
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }
        return ui.getAddedMessage(tasks, task);
    }
}
