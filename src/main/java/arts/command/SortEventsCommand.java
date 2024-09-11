package arts.command;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import arts.ArtsException;
import arts.task.Event;
import arts.task.Task;
import arts.task.TaskList;
import arts.util.Storage;
import arts.util.Ui;



/**
 * Represents a command to sort event tasks by their start date.
 */
public class SortEventsCommand implements Command {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructs a SortEventsCommand with the specified task list, storage, and UI.
     *
     * @param tasks The list of tasks.
     * @param storage The storage used to save tasks.
     * @param ui The user interface for displaying messages.
     */
    public SortEventsCommand(TaskList tasks, Storage storage, Ui ui) {
        assert tasks != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";
        assert ui != null : "Ui cannot be null";

        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Executes the command to sort event tasks by their start date.
     *
     * @throws ArtsException If any error occurs during sorting or saving.
     */
    @Override
    public String execute() throws ArtsException {
        ArrayList<Task> allTasks = tasks.getTasks();

        List<Event> sortedEvents = allTasks.stream()
                .filter(task -> task instanceof Event)
                .map(task -> (Event) task)
                .sorted((e1, e2) -> e1.getFrom().compareTo(e2.getFrom()))
                .collect(Collectors.toList());

        // Collect other types of tasks that are not events
        List<Task> otherTasks = allTasks.stream()
                .filter(task -> !(task instanceof Event))
                .collect(Collectors.toList());

        // Combine the sorted events with the other tasks
        ArrayList<Task> combinedTasks = new ArrayList<>(sortedEvents);
        combinedTasks.addAll(otherTasks);

        tasks.getTasks().clear();
        tasks.getTasks().addAll(combinedTasks);

        storage.save(tasks.getTasks());

        return "✨ The stars have aligned, and your events are now sorted by time! ⏰✨\n"
                + "Embark on your epic journey with clarity and purpose, noble hero! 🌟";
    }

}
