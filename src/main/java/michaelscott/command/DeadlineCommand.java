package michaelscott.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import michaelscott.task.Deadline;
import michaelscott.task.TaskList;
import michaelscott.utils.MichaelScottException;

/**
 * Represents a command to create a new deadline task.
 * This class parses the input string to extract the task description and deadline date,
 * and creates a new Deadline task when executed.
 */
public class DeadlineCommand implements Command {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final String description;
    private final LocalDateTime deadlineDate;

    /**
     * Constructs a new DeadlineCommand by parsing the given arguments.
     *
     * @param args The string containing the task description and deadline date.
     * @throws MichaelScottException If the input format is invalid or the date cannot be parsed.
     */
    public DeadlineCommand(String args) throws MichaelScottException {
        String[] deadlineParts = args.split(" /by ");

        if (deadlineParts.length != 2) {
            throw new MichaelScottException(
                    "Please provide both the task description and deadline "
                            + "(Here is an example: 'deadline homework /by 2024-03-02 12:00')."
            );
        }

        this.description = deadlineParts[0].trim();

        try {
            this.deadlineDate = LocalDateTime.parse(deadlineParts[1], FORMATTER);
        } catch (java.time.format.DateTimeParseException e) {
            throw new MichaelScottException(
                    "Invalid date format. Please use the format YYYY-MM-DD HH:MM."
            );
        }
    }

    @Override
    public String execute(TaskList tasks) {
        Deadline deadlineTask = new Deadline(this.description, this.deadlineDate);
        tasks.addTask(deadlineTask);
        return "Got it. I've added this task:\n" + deadlineTask
                + "\nNow you have " + tasks.size()
                + (tasks.size() > 1 ? " tasks" : " task") + " in the list.";
    }
}
