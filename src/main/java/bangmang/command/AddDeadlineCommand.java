package bangmang.command;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import bangmang.storage.Storage;
import bangmang.tasks.TaskList;
import bangmang.ui.Ui;
import bangmang.exception.InvalidCommandException;
import bangmang.tasks.Deadline;

/**
 * Represents a command to add a deadline task.
 */

public class AddDeadlineCommand extends Command {
    private String description;
    private String byString;

    /**
     * Constructs an AddDeadlineCommand with the specified description and deadline time.
     *
     * @param description A description of the deadline task.
     * @param byString The deadline time as a String, which may be in the format 'yyyy-MM-ddTHH:mm' or 'yyyy-MM-dd'.
     */
    public AddDeadlineCommand(String description, String byString) {
        this.description = description;
        this.byString = byString;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
        LocalDateTime by;
        try {
            if (byString.equalsIgnoreCase("today")) {
                // Use current date and set time to 23:59
                by = LocalDateTime.now().withHour(23).withMinute(59);
            } else {
                // Try parsing by LocalDateTime
                by = LocalDateTime.parse(byString);
            }
        } catch (DateTimeParseException e) {
            // If parsing fails, try parsing as LocalDate and set time to 23:59
            try {
                LocalDate deadlineDate = LocalDate.parse(byString);
                by = deadlineDate.atTime(23, 59);
            } catch (DateTimeParseException ex) {
                throw new InvalidCommandException("Alamak, invalid date format for deadline. Please use 'yyyy-MM-ddTHH:mm' or 'yyyy-MM-dd'.");
            }
        }

        Deadline deadline = new Deadline(this.description, by);
        tasks.add(deadline);
        storage.save(tasks.getTasks());
        return ui.showAddedNewTask(deadline, tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
