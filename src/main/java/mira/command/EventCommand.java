package mira.command;

import java.io.IOException;

import mira.Event;
import mira.Savable;
import mira.Storage;

/**
 * Represents a command to add an event task to the task list.
 */
public class EventCommand extends Command implements Savable {
    private final Event event;

    /**
     * Constructs a {@code EventCommand} with the specified description and due date.
     *
     * @param description The description of the deadline task.
     * @param from The start date of the event task.
     * @param to The end date of the event task.
     */
    public EventCommand(String description, String from, String to) {
        this.event = new Event(description, from, to);
    }

    /**
     * Executes the command by adding the event task to the task list.
     *
     * @return A message indicating that the task has been added.
     */
    @Override
    public String execute() {
        taskList.addTask(event);
        return "Got it. I've added this task:\n  " + event
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Saves the event task to the specified storage.
     *
     * @param storage The storage to save the task to.
     * @throws IOException If there is an error in file operations.
     */
    @Override
    public void save(Storage storage) throws IOException {
        storage.saveTask(event);
    }
}
