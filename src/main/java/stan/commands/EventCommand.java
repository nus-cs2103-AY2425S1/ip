package stan.commands;
import stan.TaskList;
import stan.Ui;
import stan.Storage;
import stan.Task;
import stan.Event;
import stan.StanMissingArgumentException;
import stan.StanInvalidArgumentException;
import stan.StanInvalidDateTimeFormatException;
public class EventCommand extends Command {
    private final String description;
    private final String from;
    private final String to;

    public EventCommand(String[] words) throws StanMissingArgumentException, StanInvalidArgumentException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new StanMissingArgumentException("The description of an event cannot be empty.");
        }

        String[] parts = words[1].split(" /from ", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty()) {
            throw new StanInvalidArgumentException("The event description cannot be empty.");
        }

        String[] times = parts[1].split(" /to ", 2);
        if (times.length < 2 || times[0].trim().isEmpty() || times[1].trim().isEmpty()) {
            throw new StanInvalidArgumentException("The event start time and end time cannot be empty.");
        }

        this.description = parts[0];
        this.from = times[0];
        this.to = times[1];
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StanInvalidDateTimeFormatException {
        Task task = new Event(description, from, to);
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
        storage.saveTasks(tasks.getTasks());
    }
}

