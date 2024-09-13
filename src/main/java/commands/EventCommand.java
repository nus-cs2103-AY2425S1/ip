package commands;

import exceptions.CenaInvalidEventException;
import storage.Storage;
import tasks.Event;
import tasks.Task;

import java.util.ArrayList;

public class EventCommand implements Command {
    private ArrayList<Task> tasks;
    private String description;
    private String from;
    private String to;

    public EventCommand(ArrayList<Task> tasks, String description, String from, String to) {
        this.tasks = tasks;
        this.description = description;
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute() throws CenaInvalidEventException {
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new CenaInvalidEventException("Incorrect description for event. It should contain /from and /to.");
        }
        Task task = new Event(description, from, to);
        tasks.add(task);
        Storage.saveTasks(tasks);
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        System.out.println("____________________________________________________________");

    }
}
