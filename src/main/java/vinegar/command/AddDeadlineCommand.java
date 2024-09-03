package vinegar.command;

import vinegar.storage.Storage;
import vinegar.ui.Ui;
import vinegar.VinegarException;
import vinegar.Validator;
import vinegar.TaskList;
import vinegar.task.Task;
import vinegar.task.Deadline;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {
    private String description;
    private String by;

    public AddDeadlineCommand(String[] inputParts) throws VinegarException {
        Validator.validateParts(inputParts, 1, "Please specify the description and deadline.");
        String[] deadlineParts = inputParts[1].split(" /by ");

        // Validate if split operation resulted in two parts
        if (deadlineParts.length < 2) {
            throw new VinegarException("Please specify the deadline using /by in the format yyyy-MM-dd HH:mm.");
        }

        this.description = deadlineParts[0];
        try {
            // Attempt to parse the date/time here
            LocalDateTime.parse(deadlineParts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            this.by = deadlineParts[1];
        } catch (DateTimeParseException e) {
            throw new VinegarException("Invalid date format. Please use yyyy-MM-dd HH:mm format.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws VinegarException, IOException {
        Task deadlineTask = new Deadline(description, by);
        tasks.addTask(deadlineTask);
        ui.printTaskAdded(deadlineTask, tasks.size());
        storage.save(tasks.getTasks());
    }
}
