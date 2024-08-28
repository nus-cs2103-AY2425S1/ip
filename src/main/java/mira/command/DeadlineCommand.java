package mira.command;

import java.io.IOException;

import mira.Deadline;
import mira.Savable;
import mira.Storage;

/**
 * Represents a command to add a deadline task to the task list.
 */
public class DeadlineCommand extends Command implements Savable {
    private final Deadline deadline;

    /**
     * Constructs a {@code DeadlineCommand} with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param by The due date of the deadline task.
     */
    public DeadlineCommand(String description, String by) {
        this.deadline = new Deadline(description, by);
    }

    /**
     * Executes the command by adding the deadline task to the task list.
     *
     * @return A message indicating that the task has been added.
     */
    @Override
    public String execute() {
        taskList.addTask(deadline);
        return "Got it. I've added this task:\n  " + deadline
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Saves the deadline task to the specified storage.
     *
     * @param storage The storage to save the task to.
     * @throws IOException If there is an error in file operations.
     */
    @Override
    public void save(Storage storage) throws IOException {
        storage.saveTask(deadline);
    }
}
