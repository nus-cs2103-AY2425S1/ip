package MichaelScott.Command;

import MichaelScott.Task.Deadline;
import MichaelScott.Exception.MichaelScottException;
import MichaelScott.Task.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a command to add a new deadline task to the task list.
 * This command parses the user input to
 * create a new Deadline task with a description and a due date.
 */
public class DeadlineCommand implements Command {
    private final String description;
    private final LocalDateTime deadlineDate;

    /**
     * Constructs a new DeadlineCommand by parsing the given arguments.
     *
     * @param args The command arguments containing the task description and deadline.
     *             Expected format: "description /by YYYY-MM-DD HH:MM"
     * @throws MichaelScottException If the input format is invalid or the date cannot be parsed.
     */
    public DeadlineCommand(String args) throws MichaelScottException {
        String[] deadlineParts = args.split(" /by ");
        if (deadlineParts.length != 2) {
            throw new MichaelScottException("Please provide both the task description and deadline (Here is an example: deadline homework /by 2024-03-02 12:00).");
        }
        this.description = deadlineParts[0].trim();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            this.deadlineDate = LocalDateTime.parse(deadlineParts[1], formatter);
        } catch (java.time.format.DateTimeParseException e) {
            throw new MichaelScottException("Invalid date format. Please use the format YYYY-MM-DD HH:MM.");
        }
    }

    /**
     * Executes the command by adding a new Deadline task to the task list.
     *
     * @param tasks The TaskList to which the new Deadline task will be added.
     * @return A string confirming the addition of the task and showing the updated task count.
     */
    @Override
    public String execute(TaskList tasks) {
        Deadline DeadlineTask = new Deadline(this.description, this.deadlineDate);
        tasks.addTask(DeadlineTask);
        return "Got it. I've added this task:\n" + DeadlineTask.toString() +
                "\nNow you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.";
    }
}
