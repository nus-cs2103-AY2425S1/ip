package michaelscott.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import michaelscott.utils.MichaelScottException;
import michaelscott.task.Deadline;
import michaelscott.task.TaskList;

public class DeadlineCommand implements Command {
    private final String description;
    private final LocalDateTime deadlineDate;
    DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

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
