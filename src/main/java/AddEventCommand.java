import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command {
    private String description;
    private String from;
    private String to;

    public AddEventCommand(String[] inputParts) throws VinegarException {
        Validator.validateParts(inputParts, 1, "Please specify the description and event time.");
        String[] eventParts = inputParts[1].split(" /from | /to ");
        if (eventParts.length < 3) {
        throw new VinegarException("Please specify the event time using /from and /to in the format yyyy-MM-dd HH:mm.");
        }
        this.description = eventParts[0];
        try {
            LocalDateTime.parse(eventParts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            throw new VinegarException("Invalid 'from' date format. Please use yyyy-MM-dd HH:mm format.");
        }
        try {
            LocalDateTime.parse(eventParts[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        } catch (DateTimeParseException e) {
            throw new VinegarException("Invalid 'to' date format. Please use yyyy-MM-dd HH:mm format.");
        }
        this.from = eventParts[1];
        this.to = eventParts[2];

    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws VinegarException, IOException {
        Task eventTask = new Event(description, from, to);
        tasks.addTask(eventTask);
        ui.printTaskAdded(eventTask, tasks.size());
        storage.save(tasks.getTasks());
    }
}
