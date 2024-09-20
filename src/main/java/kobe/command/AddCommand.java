package kobe.command;

import kobe.KobeException;
import kobe.task.Deadline;
import kobe.task.Event;
import kobe.task.TaskList;
import kobe.task.Todo;
import kobe.util.Storage;
import kobe.util.Ui;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a command to add a new task to the task list.
 * This command handles the creation of todo, deadline, and event tasks
 * based on user input and updates the storage accordingly.
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
     * @param ui      The Ui object that handles user interaction.
     * @param storage The Storage object that handles loading and saving task to file.
     * @throws IOException   If an error occurs while saving tasks to the file.
     * @throws KobeException If the user input is invalid, such as incorrect formatting or missing fields.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, KobeException {
        String[] words = fullCommand.trim().split(" ", 2);
        String taskType = words[0];

        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new KobeException("The description of a " + taskType + " cannot be empty.");
        }

        switch (taskType) {
            case "todo":
                String description = words[1].trim();
                Todo todo = new Todo(description);
                tasks.addTask(todo);
                ui.setResponse("Got it. I've added this task:\n  " + todo + "\nNow you have " + tasks.size() + " tasks in the list.");
                break;

            case "deadline":
                addDeadlineTask(words[1], tasks, ui);
                break;

            case "event":
                addEventTask(words[1], tasks, ui);
                break;

            default:
                throw new KobeException("Unknown task type: " + taskType);
        }

        storage.save(tasks.getTasks());
    }

    /**
     * Adds a new deadline task to the task list based on user input.
     *
     * @param details The task description and deadline in the format "<description> /by <yyyy-MM-dd HHmm>".
     * @param tasks   The TaskList object to which the task will be added.
     * @param ui      The Ui object for displaying feedback to the user.
     * @throws KobeException If the input format is incorrect or the date is invalid.
     */
    private void addDeadlineTask(String details, TaskList tasks, Ui ui) throws KobeException {
        String[] parts = details.split(" /by ", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
            throw new KobeException("Invalid deadline format. Usage: deadline <description> /by <yyyy-MM-dd HHmm>");
        }
        String description = parts[0].trim();
        String byStr = parts[1].trim();

        try {
            LocalDateTime by = LocalDateTime.parse(byStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            Deadline deadline = new Deadline(description, by);
            tasks.addTask(deadline);
            ui.setResponse("Got it. I've added this task:\n  " + deadline + "\nNow you have " + tasks.size() + " tasks in the list.");
        } catch (DateTimeParseException e) {
            throw new KobeException("Invalid date format for deadline. Please use yyyy-MM-dd HHmm.");
        }
    }

    /**
     * Adds a new event task to the task list based on user input.
     *
     * @param details The task description, start, and end time in the format
     *                "<description> /from <start yyyy-MM-dd HHmm> /to <end yyyy-MM-dd HHmm>".
     * @param tasks   The TaskList object to which the task will be added.
     * @param ui      The Ui object for displaying feedback to the user.
     * @throws KobeException If the input format is incorrect, the date is invalid, or the start time is after the end time.
     */
    private void addEventTask(String details, TaskList tasks, Ui ui) throws KobeException {
        String[] parts = details.split(" /from ", 2);
        if (parts.length < 2 || parts[0].trim().isEmpty()) {
            throw new KobeException("Invalid event format. Usage: event <description> /from <start yyyy-MM-dd HHmm> /to <end yyyy-MM-dd HHmm>");
        }
        String description = parts[0].trim();
        String[] dateTimeParts = parts[1].split(" /to ", 2);
        if (dateTimeParts.length < 2 || dateTimeParts[0].trim().isEmpty() || dateTimeParts[1].trim().isEmpty()) {
            throw new KobeException("Invalid event format. Usage: event <description> /from <start datetime> /to <end datetime>");
        }
        String fromStr = dateTimeParts[0].trim();
        String toStr = dateTimeParts[1].trim();

        try {
            LocalDateTime from = LocalDateTime.parse(fromStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            LocalDateTime to = LocalDateTime.parse(toStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            if (from.isAfter(to)) {
                throw new KobeException("Event start time cannot be after end time.");
            }
            Event event = new Event(description, from, to);
            tasks.addTask(event);
            ui.setResponse("Got it. I've added this task:\n  " + event + "\nNow you have " + tasks.size() + " tasks in the list.");
        } catch (DateTimeParseException e) {
            throw new KobeException("Invalid date format for event. Please use yyyy-MM-dd HHmm.");
        }
    }
}
