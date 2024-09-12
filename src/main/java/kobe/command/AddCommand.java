package kobe.command;

import kobe.task.Deadline;
import kobe.task.Event;
import kobe.task.TaskList;
import kobe.task.Todo;
import kobe.util.Storage;
import kobe.util.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a command to add a new task to the task list.
 * This command can handle adding todos, deadlines, and events based on user input.
 */
public class AddCommand extends Command {
    private final String fullCommand;

    /**
     * Constructs an AddCommand with the specified user input.
     *
     * @param fullCommand The full input string from the user.
     */
    public AddCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    /**
     * Executes the add command, which adds a new task (todo, deadline, or event)
     * to the task list and updates the storage.
     *
     * @param tasks   The TaskList object that stores all tasks.
     * @param ui      The Ui object that handles user interactions.
     * @param storage The Storage object that handles loading and saving tasks to the file.
     * @throws IOException If an error occurs while saving tasks to the file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        // Ensure that the input has at least two parts: command and description
        assert fullCommand != null && !fullCommand.trim().isEmpty() : "fullCommand should not be null or empty";

        String[] words = fullCommand.split(" ", 2);
        String taskType = words[0];

        // Assert that a description exists for the task
        assert words.length > 1 : "The command must have a description part";

        if (taskType.equals("todo")) {
            String description = words[1];
            Todo todo = new Todo(description);

            // Assert that the task was added successfully
            int initialSize = tasks.size();
            tasks.addTask(todo);
            assert tasks.size() == initialSize + 1 : "Task was not added correctly";

            ui.setResponse("Got it. I've added this task:\n  " + todo + "\nNow you have " + tasks.size() + " tasks in the list.");

        } else if (taskType.equals("deadline")) {
            String[] parts = words[1].split(" /by ");

            // Assert that the deadline description and time part exist
            assert parts.length == 2 : "Deadline command must have a description and /by clause";

            String description = parts[0];
            LocalDateTime by = LocalDateTime.parse(parts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            Deadline deadline = new Deadline(description, by);

            int initialSize = tasks.size();
            tasks.addTask(deadline);
            assert tasks.size() == initialSize + 1 : "Deadline task was not added correctly";

            ui.setResponse("Got it. I've added this task:\n  " + deadline + "\nNow you have " + tasks.size() + " tasks in the list.");

        } else if (taskType.equals("event")) {
            String[] parts = words[1].split(" /from ");

            // Assert that the event description and time part exist
            assert parts.length == 2 : "Event command must have a description and /from clause";

            String description = parts[0];
            String[] dateTimeParts = parts[1].split(" /to ");

            // Assert that the from and to dates exist
            assert dateTimeParts.length == 2 : "Event command must have /from and /to clauses";

            LocalDateTime from = LocalDateTime.parse(dateTimeParts[0], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            LocalDateTime to = LocalDateTime.parse(dateTimeParts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            Event event = new Event(description, from, to);

            int initialSize = tasks.size();
            tasks.addTask(event);
            assert tasks.size() == initialSize + 1 : "Event task was not added correctly";

            ui.setResponse("Got it. I've added this task:\n  " + event + "\nNow you have " + tasks.size() + " tasks in the list.");
        }

        // Ensure that tasks are saved correctly
        storage.save(tasks.getTasks());
        assert tasks.size() > 0 : "Tasks should not be empty after saving";
    }
}