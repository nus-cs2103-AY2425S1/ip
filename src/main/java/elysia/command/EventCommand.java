package elysia.command;

import java.io.IOException;
import java.util.ArrayList;

import elysia.storage.Storage;
import elysia.task.Event;
import elysia.task.Task;
import elysia.ui.Ui;

/**
 * Represents a create-event command in the application.
 */
public class EventCommand extends Command {
    private String description;
    private String startTime;
    private String endTime;

    public EventCommand(String description, String startTime, String endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    /**
     * @param tasks a list of tasks
     * @return String telling user that the event has been added to list.
     */
    public String execute(ArrayList<Task> tasks) {
        Ui ui = new Ui();
        Task task = new Event(this.description, this.startTime, this.endTime);
        assert task != null : "task is null";
        tasks.add(task);
        try {
            Storage.appendToFile(task);
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }
        return ui.getAddedMessage(tasks, task);
    }
}
