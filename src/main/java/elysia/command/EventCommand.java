package elysia.command;

import elysia.storage.Storage;
import elysia.task.Deadline;
import elysia.task.Event;
import elysia.task.Task;
import elysia.ui.Ui;

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


    public void execute(ArrayList<Task> tasks, Storage storage) {
        Ui ui = new Ui();
        Task task = new Event(this.description, this.startTime, this.endTime);
        tasks.add(task);
        ui.showAddedMessage(tasks, task);
    }
}
