package main.LittleMissHelpful.Command;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import main.LittleMissHelpful.Exception.InvalidCommandException;
import main.LittleMissHelpful.Task.Deadline;
import main.LittleMissHelpful.TaskList;
import main.LittleMissHelpful.Ui;
import main.LittleMissHelpful.Storage;

public class AddDeadlineCommand extends Command {
    private String description;
    private String byString;

    public AddDeadlineCommand(String description, String byString) {
        this.description = description;
        this.byString = byString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidCommandException {
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
                throw new InvalidCommandException("Invalid date format for deadline. Please use 'yyyy-MM-ddTHH:mm' or 'yyyy-MM-dd'.");
            }
        }

        Deadline deadline = new Deadline(this.description, by);
        tasks.add(deadline);
        ui.showAddedNewTask(deadline, tasks);
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
