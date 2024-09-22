package elysia.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

import elysia.exception.InvalidDateTimeInputException;
import elysia.storage.Storage;
import elysia.task.Event;
import elysia.task.Task;
import elysia.ui.Ui;

/**
 * Represents a create-event command in the application.
 */
public class EventCommand extends Command {
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    public EventCommand(String description, LocalDateTime startTime, LocalDateTime endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    /**
     * @param tasks a list of tasks
     * @return String telling user that the event has been added to list.
     */
    public String execute(ArrayList<Task> tasks) throws InvalidDateTimeInputException {
        Ui ui = new Ui();
        Task task = new Event(this.description, this.startTime, this.endTime);

        checkStartTimeInput();
        checkEndTimeInput();

        assert task != null : "task is null";
        tasks.add(task);

        try {
            Storage.appendToFile(task);
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }

        return ui.getAddedMessage(tasks, task);
    }

    private void checkStartTimeInput() throws InvalidDateTimeInputException {
        LocalDateTime now = LocalDateTime.now();
        if (this.startTime.compareTo(now) < 0) {
            throw new InvalidDateTimeInputException("start time");
        }
    }

    private void checkEndTimeInput() throws InvalidDateTimeInputException {
        if (this.endTime.compareTo(startTime) < 0) {
            throw new InvalidDateTimeInputException("end time", "start time");
        }
    }
}
