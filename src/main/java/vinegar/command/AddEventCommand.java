package vinegar.command;

import vinegar.TaskList;
import vinegar.Validator;
import vinegar.VinegarException;
import vinegar.storage.Storage;
import vinegar.task.Event;
import vinegar.task.Task;
import vinegar.ui.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command {
    private String description;
    private LocalDateTime from;
    private LocalDateTime to;

    public AddEventCommand(String[] inputParts) throws VinegarException {
        Validator.validateParts(inputParts, 1, "Please specify the description and event time.");
        String[] eventParts = inputParts[1].split(" /from | /to ");
        Validator.validateParts(eventParts, 3, "Please specify the event time using /from and /to in the format yyyy-MM-dd HH:mm.");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        // Parse and validate 'from' date
        try {
            this.from = LocalDateTime.parse(eventParts[1], formatter);
        } catch (DateTimeParseException e) {
            throw new VinegarException("Invalid 'from' date format. Please use yyyy-MM-dd HH:mm format.");
        }

        // Parse and validate 'to' date
        try {
            this.to = LocalDateTime.parse(eventParts[2], formatter);
        } catch (DateTimeParseException e) {
            throw new VinegarException("Invalid 'to' date format. Please use yyyy-MM-dd HH:mm format.");
        }

        this.description = eventParts[0];
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws VinegarException, IOException {
        Task eventTask = new Event(description, from, to);
        tasks.addTask(eventTask);
        ui.printTaskAdded(eventTask, tasks.size());
        storage.save(tasks.getTasks());
    }
}
