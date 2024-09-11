package elysia.command;

import elysia.storage.Storage;
import elysia.task.Deadline;
import elysia.task.Event;
import elysia.task.Task;
import elysia.ui.Ui;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class EventCommand extends Command{
    private String description;
    private String startTime;
    private String endTime;
    public EventCommand(String description, String startTime, String endTime) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
    }


    public String execute(ArrayList<Task> tasks) {
        Ui ui = new Ui();
        Task task = new Event(this.description, this.startTime, this.endTime);
        assert task != null: "task is null";
        tasks.add(task);
        try {
            Storage.appendToFile(task);
        } catch (IOException e) {
            return "Something went wrong: " + e.getMessage();
        }
        return ui.getAddedMessage(tasks, task);
    }
}
