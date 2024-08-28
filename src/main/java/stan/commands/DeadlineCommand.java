package stan.commands;
import stan.TaskList;
import stan.Ui;
import stan.Storage;
import stan.tasks.Task;
import stan.tasks.Deadline;
import stan.exceptions.StanMissingArgumentException;
import stan.exceptions.StanInvalidArgumentException;
import stan.exceptions.StanInvalidDateTimeFormatException;

public class DeadlineCommand extends Command {
    private final String description;
    private final String by;

    public DeadlineCommand(String[] words) throws StanMissingArgumentException, StanInvalidArgumentException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new StanMissingArgumentException("The description of a deadline cannot be empty.");
        }

        if (!words[1].contains("/by")) {
            throw new StanInvalidArgumentException("The deadline description is present but the '/by' clause is missing. Please add the time followed by the '/by' clause.");
        }

        String[] parts = words[1].split(" /by ", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new StanInvalidArgumentException("The deadline description and time cannot be empty.");
        }

        this.description = parts[0];
        this.by = parts[1];
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StanInvalidDateTimeFormatException {
        Task task = new Deadline(description, by);
        tasks.add(task);
        ui.showTaskAdded(task, tasks.size());
        storage.saveTasks(tasks.getTasks());
    }
}
