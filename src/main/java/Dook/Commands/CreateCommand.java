package Dook.Commands;

import Dook.Tasks.TaskList;
import Dook.Tasks.Task;
import Dook.Tasks.Event;
import Dook.Tasks.Deadline;
import Dook.Tasks.Todo;
import Dook.Storage.Storage;
import Dook.Ui.Ui;
import Dook.DookException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Creates a Task object and adds it to the TaskList.
 */
public class CreateCommand extends Command{

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
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DookException, IOException {
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
        } catch (DateTimeParseException e) {
            throw new DookException("Invalid date format. Enter your date in dd/MM/yyyy HH:mm format");
        }
    }

}
