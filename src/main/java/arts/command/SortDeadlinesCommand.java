package arts.command;

import arts.ArtsException;
import arts.task.Deadline;
import arts.task.Task;
import arts.task.TaskList;
import arts.util.Storage;
import arts.util.Ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a command to sort deadline tasks chronologically.
 */
public class SortDeadlinesCommand implements Command {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;

    /**
     * Constructs a SortDeadlinesCommand with the specified task list, storage, and UI.
     *
     * @param tasks The list of tasks.
     * @param storage The storage used to save tasks.
     * @param ui The user interface for displaying messages.
     */
    public SortDeadlinesCommand(TaskList tasks, Storage storage, Ui ui) {
        assert tasks != null : "TaskList cannot be null";
        assert storage != null : "Storage cannot be null";
        assert ui != null : "Ui cannot be null";

        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Executes the command to sort deadline tasks chronologically.
     *
     * @throws ArtsException If any error occurs during sorting or saving.
     */
    @Override
    public String execute() throws ArtsException {
        ArrayList<Task> allTasks = tasks.getTasks();

        List<Deadline> sortedDeadlines = allTasks.stream()
                .filter(task -> task instanceof Deadline)
                .map(task -> (Deadline) task)
                .sorted((d1, d2) -> d1.getBy().compareTo(d2.getBy()))
                .collect(Collectors.toList());

        // Collect other types of tasks that are not deadlines
        List<Task> otherTasks = allTasks.stream()
                .filter(task -> !(task instanceof Deadline))
                .collect(Collectors.toList());

        // Combine the sorted deadlines with the other tasks
        ArrayList<Task> combinedTasks = new ArrayList<>(sortedDeadlines);
        combinedTasks.addAll(otherTasks);

        tasks.getTasks().clear();
        tasks.getTasks().addAll(combinedTasks);

        storage.save(tasks.getTasks());

        // Anime-like response
        return "✨ Behold! The deadlines have been aligned in perfect harmony! 📅✨\n"
                + "Your journey through time is now clearer, brave adventurer! 🌟";
    }

}
