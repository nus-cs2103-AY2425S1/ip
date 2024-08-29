package fanny.command;

import fanny.ui.Ui;
import fanny.task.TaskList;
import fanny.task.Task;
import fanny.task.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents the command that handles the "deadline" prompt.
 */
public class DeadlineCommand extends Command {

    /** String representation of the description of the deadline task. */
    private String description;

    /**
     * Constructs an {@code DeadlineCommand} with the specified description.
     *
     * @param description The description of the deadline task,
     *                    including the task information and due time.
     *
     */
    public DeadlineCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add an deadline task to the task list.
     * Parses the description to extract the task information and deadline.
     * Handles any errors that may occur during parsing.
     *
     * @param list The task list to which the event task is added.
     * @param ui The UI object to interact with the user.
     */
    @Override
    public void actionable(TaskList list, Ui ui) {
        ui.showHorizontalLine();
        try {
            String[] cmdDeadline = description.split("/by ", 2);
            LocalDateTime time = LocalDateTime.parse(cmdDeadline[1], super.formatter);
            String description = cmdDeadline[0];
            Task deadline = new Deadline(description, time);
            list.add(deadline);
            ui.showMessage("Fanny:\nGot it. I've added this task:");
            ui.showMessage(deadline.toString());
            ui.showMessage("Now you have " + list.getLength() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showMessage("Task description and deadline cannot be empty");
        } catch (DateTimeParseException e) {
            ui.showMessage("Please enter a valid date and time: YYYY-MM-DD HH:MM:SS");
        } finally {
            ui.showHorizontalLine();
        }
    }

    /**
     * Indicates that executing this command should not exit the application.
     *
     * @return {@code false}, indicating that the application should not exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
