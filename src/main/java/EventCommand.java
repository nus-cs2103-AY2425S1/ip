import java.io.IOException;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public EventCommand(String[] inputs) {
        if (inputs.length != 3) {
            throw new FridayException("\tInvalid input. 'event' command requires a description," +
                    " start, and end time.\n\tusage: event <string> /from <yyyy-MM-dd HHmm>" +
                    " /to <yyyy-MM-dd HHmm>.");
        }
        this.description = inputs[0];
        this.from = inputs[1];
        this.to = inputs[2];
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, FridayException {
        try {
            Task task = new Event(description, from, to);
            tasks.addTask(task);
            ui.showTaskAdded(task, tasks.getSize());
            storage.saveTasks(tasks.getTasks());
        } catch (DateTimeParseException e) {
            System.out.println("\tInvalid date format. Please use the format: yyyy-MM-dd HHmm.");
        }
    }
}
