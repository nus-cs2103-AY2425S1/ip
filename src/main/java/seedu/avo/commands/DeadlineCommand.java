package seedu.avo.commands;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.avo.exceptions.AvoException;
import seedu.avo.tasks.Deadline;
import seedu.avo.tasks.TaskManager;
import seedu.avo.utils.DateTime;

public class DeadlineCommand extends Command {
    private final TaskManager manager;
    public DeadlineCommand(TaskManager manager) {
        this.manager = manager;
    }
    @Override
    public void execute(String userInput) throws AvoException {
        String[] inputs = userInput.split("deadline |/by ");
        if (inputs.length < 3) {
            throw new AvoException("OOPS!!! The description of a deadline cannot be empty.");
        }
        LocalDateTime dueDate = DateTime.parseWithTime("2024-09-09 18:00");
        manager.addTask(new Deadline(inputs[1].trim(), dueDate));
    }
}
