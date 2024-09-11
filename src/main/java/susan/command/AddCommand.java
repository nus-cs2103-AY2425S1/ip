package susan.command;

import susan.task.*;
import susan.ui.Storage;
import susan.ui.SusanException;
import susan.ui.Ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a command to add tasks.
 */
public class AddCommand extends Command {
    private String taskType;
    private String[] commandParts;

    public AddCommand(String[] commandParts) {
        this.taskType = commandParts[0];
        this.commandParts = commandParts;
    }

    public Task toDoTask() {
        return new ToDo(commandParts[1]);
    }

    public Task deadlineTask() throws SusanException {
        Task task;
        String[] deadlineParts = commandParts[1].split(" /by ");
        // Incorrect number of parameters to create deadline task
        if (deadlineParts.length != 2) {
            throw new SusanException("To add a deadline: deadline + description + /by yyyy-MM-dd");
        }
        // Incorrect date format
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate by = LocalDate.parse(deadlineParts[1], formatter);
            task = new Deadline(deadlineParts[0], by);
        } catch (Exception e) {
            throw new SusanException("Please key date in the format: yyyy-MM-dd");
        }
        return task;
    }

    public Task eventTask() throws SusanException {
        Task task;
        String[] eventParts = commandParts[1].split(" /from | /to ");
        // Incorrect number of parameters to create event task
        if (eventParts.length != 3) {
            throw new SusanException("To add an event: event + description + /from yyyy-MM-dd HHmm + /to yyyy-MM-dd HHmm");
        }
        // Incorrect date/time format
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime from = LocalDateTime.parse(eventParts[1], formatter);
            LocalDateTime to = LocalDateTime.parse(eventParts[2], formatter);
            task = new Event(eventParts[0], from, to);
        } catch (Exception e) {
            throw new SusanException("Please key date/time in the format: yyyy-MM-dd HHmm");
        }
        return task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws SusanException {
        if (commandParts.length < 2) {
            throw new SusanException("The description of a task cannot be empty.");
        }

        Task task;
        switch (taskType) {
        case "todo":
            task = toDoTask();
            break;
        case "deadline":
            task = deadlineTask();
            break;
        case "event":
            task = eventTask();
            break;
        default:
            throw new SusanException("Failed to add task.");
        }

        tasks.add(task);
        storage.load(tasks);
        return ui.showAddTask(task, tasks.size());
    }
}