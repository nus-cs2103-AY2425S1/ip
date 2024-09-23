package beechat.command;

import beechat.task.DeadlineTask;
import beechat.task.EventTask;
import beechat.task.TaskList;
import beechat.task.TodoTask;
import beechat.util.Storage;
import beechat.util.Ui;

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
     * @param tasks   The TaskList object that contains all tasks.
     * @param ui      The Ui object that handles all user interactions.
     * @param storage The Storage object that saves and loads tasks.
     * @throws IOException If an error occurs while executing the command.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        String[] words = fullCommand.split(" ", 2);
        String taskType = words[0];

        // Assert that a description exists for the task
        assert words.length > 1 : "The command must have a non-empty description part";

        if (taskType.equals("todo")) {
            String description = words[1];
            TodoTask todo = new TodoTask(description);
            tasks.addTask(todo);
            ui.showTasks(tasks);
        } else if (taskType.equals("deadline")) {
            String[] parts = words[1].split(" /by ");
            String description = parts[0];
            LocalDateTime by = LocalDateTime.parse(parts[1], DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
            DeadlineTask deadline = new DeadlineTask(description, by);
            tasks.addTask(deadline);
            ui.showTasks(tasks);
        } else if (taskType.equals("event")) {
            String[] parts = words[1].split(" /from ");
            String description = parts[0];
            String[] dateTimeParts = parts[1].split(" /to ");
            LocalDateTime from = LocalDateTime.parse(dateTimeParts[0], DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
            LocalDateTime to = LocalDateTime.parse(dateTimeParts[1], DateTimeFormatter.ofPattern("MMM dd yyyy HHmm"));
            EventTask event = new EventTask(description, from, to);
            tasks.addTask(event);
            ui.showTasks(tasks);
        }
        storage.saveTasks(tasks.getTasks());
    }
}