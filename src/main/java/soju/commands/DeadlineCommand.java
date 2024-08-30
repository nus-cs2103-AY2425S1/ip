package soju.commands;

import java.time.LocalDate;

import soju.SojuException;
import soju.Storage;
import soju.TaskList;
import soju.Ui;
import soju.tasks.Deadline;

/**
 * DeadlineCommand handles commands starting with deadline
 */

public class DeadlineCommand extends Command {
    private Deadline deadlineTask;

    /**
     * Returns a DeadlineCommand
     * @param input command input as a String
     * @throws SojuException if invalid input
     */
    public DeadlineCommand(String input) throws SojuException {
        try {
            if (!input.startsWith("deadline ")) {
                throw new SojuException("The description of a deadline cannot be empty.");
            }

            // Extract the part after "deadline "
            String[] parts = input.substring(9).split(" /by ", 2);

            // The description is before the "/by"
            String description = parts[0].trim();
            if (description.isEmpty()) {
                throw new SojuException("The description of a deadline cannot be empty.");
            }

            // The due date is after the "/by"
            String by = parts[1].trim();
            LocalDate localDate = LocalDate.parse(by);

            // Create a new Tasks.Deadline task
            deadlineTask = new Deadline(description, localDate);
        } catch (Exception e) {
            throw new SojuException(e.getMessage());
        }

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.printString("Got it. I've added this task:");
        ui.printString("  " + tasks.addTask(deadlineTask));
        ui.printString("Now you have " + tasks.size() + " tasks in the list.");
    }
}
