package main.command;

import main.exceptions.PrinceException;
import main.tasks.Event;
import main.tasks.TaskList;
import main.util.Storage;
import main.util.Ui;

/**
 * Creates an event task.
 */
public class EventCommand extends Command {
    private String input;

    /**
     * Constructor for EventCommand class.
     * @param input Input by the user.
     */
    public EventCommand(String input) {
        this.input = input;
    }

    /**
     * Returns the string "event"
     * @param input Input of the user.
     * @return "event".
     */
    private String getEvent(String input) {
        String[] arr = input.split("event|/from|/to");
        String event = arr[1].trim();
        return event;
    }

    /**
     * Returns a String representing the start of the event.
     * @param input Input of the user.
     * @return Date of start of event.
     */
    private String getFrom(String input) {
        String[] arr = input.split("/from|/to");
        String from = arr[1].trim();
        return from;
    }

    /**
     * Returns a String representing the end of the event.
     * @param input Input of the user.
     * @return Date of end of event.
     */
    private String getTo(String input) {
        String[] arr = input.split("/from|/to");
        String to = arr[2].trim();
        return to;
    }

    /**
     * Creates an Event task.
     * Adds it to the list of tasks.
     * Saves task to storage.
     * Display output for user.
     * @param input    Input by the user.
     * @param taskList List of tasks.
     * @param storage  Storage.
     * @param ui Ui as initialised in main.
     */
    private void handleEvent(String input, TaskList taskList, Storage storage, Ui ui)
            throws PrinceException {
        if (input.equals("event")) {
            throw new PrinceException("Please describe your event task in more detail!");
        }
        ui.showAdd();
        String desc = getEvent(input);
        String from = getFrom(input);
        String to = getTo(input);
        Event event = new Event(desc, from, to);
        taskList.add(event);
        ui.showTaskToString(event);
        ui.showNumberOfTasks(taskList);
        storage.saveToFile(event, taskList);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws PrinceException {
        handleEvent(input, tasks, storage, ui);
    }
}
