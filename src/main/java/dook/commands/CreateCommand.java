package dook.commands;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import dook.DookException;
import dook.storage.Storage;
import dook.tasks.*;
import dook.ui.Ui;

/**
 * Creates a Task object and adds it to the TaskList.
 */
public class CreateCommand extends Command {

    private String description;
    private String start;
    private String end;
    private String by;
    private TaskType type;

    /**
     * Creates a CreateCommand for a Todo task.
     *
     * @param description Description of the Todo.
     * @param type The type of task (TODO).
     */
    public CreateCommand(String description, TaskType type) {
        this.description = description;
        this.type = type;
    }

    /**
     * Creates a CreateCommand for an Event task.
     *
     * @param description Description of the Event.
     * @param start Start date of the Event.
     * @param end End date of the Event.
     * @param type The type of task (EVENT).
     */
    public CreateCommand(String description, String start, String end, TaskType type) {
        this.description = description;
        this.start = start;
        this.end = end;
        this.type = type;
    }

    /**
     * Creates a CreateCommand for a Deadline task.
     *
     * @param description Description of the Deadline.
     * @param by Due date of the Deadline.
     * @param type The type of task (DEADLINE).
     */
    public CreateCommand(String description, String by, TaskType type) {
        this.description = description;
        this.by = by;
        this.type = type;
    }

    /**
     * Executes the CreateCommand, adding the task.
     * If the task type requires specific date and time input (for Deadline or Event), this method
     * parses the dates and times and handles potential exceptions.
     *
     * @param tasks The TaskList to which the new task will be added.
     * @param ui The Ui object that handles user interactions.
     * @param storage The Storage object to handle saving the updated TaskList.
     * @return A confirmation message that the task has been added.
     * @throws DookException If any input is invalid or if a required field is empty.
     * @throws IOException If an I/O error occurs while saving the task.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DookException, IOException {
        if (this.description.isEmpty()) {
            throw new DookException("Need a description for your task");
        } else if (type == TaskType.DEADLINE && this.by.isEmpty()) {
            throw new DookException("Need a due date for your deadline");
        } else if (type == TaskType.EVENT && (this.start.isEmpty() || this.end.isEmpty())) {
            throw new DookException("Need a start and end time for your event");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            Task task;

            switch (this.type) {
            case TODO:
                task = new Todo(this.description);
                break;
            case DEADLINE:
                LocalDateTime time = LocalDateTime.parse(this.by, formatter);
                task = new Deadline(this.description, time);
                break;
            case EVENT:
                LocalDateTime startTime = LocalDateTime.parse(this.start, formatter);
                LocalDateTime endTime = LocalDateTime.parse(this.end, formatter);
                task = new Event(this.description, startTime, endTime);
                break;
            default:
                throw new DookException("Invalid task type");
            }

            tasks.add(task);
            storage.write(tasks);

            ui.separate();
            ui.showMessage("Got it. I've added this task:");
            ui.showMessage(task.toString());
            ui.showMessage("Now you have " + tasks.numOfTasks() + " tasks in the list");
            ui.separate();

            return "Got it. I've added this task:\n" + task.toString() + "\nNow you have"
                    + tasks.numOfTasks() + " tasks in the list";
        } catch (DateTimeParseException e) {
            throw new DookException("Invalid date format. Enter your date in dd/MM/yyyy HH:mm format");
        }
    }

}
