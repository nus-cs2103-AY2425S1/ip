package johncena.commands;

import java.util.ArrayList;

import johncena.exceptions.CenaInvalidEventException;
import johncena.storage.Storage;
import johncena.tasks.Event;
import johncena.tasks.Task;

/**
 * The {@code EventCommand} class implements the {@code Command} interface and provides
 * the functionality to execute the "event" command, which adds an event task to the task list.
 */
public class EventCommand implements Command {
    private ArrayList<Task> tasks;
    private String description;
    private String from;
    private String to;

    /**
     * Constructs a new {@code EventCommand} with the specified task list, description, start time, and end time.
     *
     * @param tasks the list of tasks
     * @param description the description of the event task
     * @param from the start time of the event
     * @param to the end time of the event
     */
    public EventCommand(ArrayList<Task> tasks, String description, String from, String to) {
        this.tasks = tasks;
        this.description = description;
        this.from = from;
        this.to = to;
    }


    /**
     * Executes the "event" command. Adds an event task to the task list.
     *
     * @throws CenaInvalidEventException if the description for the event is incorrect
     */
    @Override
    public String execute() {
//        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
//            throw new CenaInvalidEventException("Incorrect description for event. It should contain /from and /to.");
//        }
//        Task task = new Event(description, from, to);
//        tasks.add(task);
//        Storage.saveTasks(tasks);
//
//        StringBuilder sb = new StringBuilder();
//        sb.append(" Got it. I've added this task:\n");
//        sb.append("   ").append(task).append("\n");
//        sb.append(" Now you have ").append(tasks.size()).append(" tasks in the list.\n");
//
//        return sb.toString();
        assert description != null : "Description should not be null";
        assert from != null : "Start time should not be null";
        assert to != null : "End time should not be null";
        try {
            if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new CenaInvalidEventException("Incorrect description for event. It should contain /from and /to.");
            }
            Task task = new Event(description, from, to);
            tasks.add(task);
            Storage.saveTasks(tasks);

            StringBuilder sb = new StringBuilder();
            sb.append(" Got it. I've added this task:\n");
            sb.append("   ").append(task).append("\n");
            sb.append(" Now you have ").append(tasks.size()).append(" tasks in the list.\n");
            return sb.toString();
        } catch (CenaInvalidEventException e) {
            return e.getMessage();
        }

    }
}
