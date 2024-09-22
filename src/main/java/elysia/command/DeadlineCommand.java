package elysia.command;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import elysia.exception.InvalidDateTimeInputException;
import elysia.storage.Storage;
import elysia.task.Deadline;
import elysia.task.Task;
import elysia.ui.Ui;

/**
 * Represents a create-deadline command in the application.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final LocalDate deadline;

    public DeadlineCommand(String description, LocalDate by) {
        this.description = description;
        this.deadline = by;
    }

    /**
     * Executes the command to add the deadline task to the list and update the storage.
     *
     * @param tasks The list of tasks to which the new deadline task will be added.
     * @return A message indicating the result of the operation, including confirmation of the task addition.
     * @throws InvalidDateTimeInputException If the deadline is earlier than the current date.
     */
    public String execute(ArrayList<Task> tasks) throws InvalidDateTimeInputException {
        Ui ui = new Ui();
        Task task = new Deadline(this.description, this.deadline);
        assert task != null : "task is null";
        checkDateTimeInput();
        tasks.add(task);

        // Sort the tasks by deadline if they are instances of Deadline
        tasks.sort((task1, task2) -> {
            if (task1 instanceof Deadline && task2 instanceof Deadline) {
                return ((Deadline) task1).getBy().compareTo(((Deadline) task2).getBy());
            }
            return 0; // Handle non-Deadline tasks if necessary
        });

        try {
            Storage storage = new Storage(tasks);
            storage.saveFile();
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }

        return ui.getAddedMessage(tasks, task);
    }

    private void checkDateTimeInput() throws InvalidDateTimeInputException {
        LocalDate today = LocalDate.now();
        if (this.deadline.compareTo(today) < 0) {
            throw new InvalidDateTimeInputException("deadline");
        }
    }
}
