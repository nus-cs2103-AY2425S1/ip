package fanny.command;

import fanny.task.TaskList;
import fanny.ui.Ui;
import fanny.task.Task;
import fanny.task.Event;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

/**
 * Represents the command that handles the "event" prompt.
 */
public class EventCommand extends Command {

    /** String representation of the description of the event task. */
    private String description;

    /**
     * Constructs an {@code EventCommand} with the specified description.
     *
     * @param description The description of the event task,
     *                    including the event information and time period.
     *
     */
    public EventCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the command to add an event task to the task list.
     * Parses the description to extract the event information and duration.
     * Handles any errors that may occur during parsing.
     *
     * @param list The task list to which the event task is added.
     * @param ui The UI object to interact with the user.
     */
    @Override
    public void actionable(TaskList list, Ui ui) {
        ui.showHorizontalLine();
        try {
            String[] cmdEvent = description.split("/from ", 2);
            String info = cmdEvent[0];
            String[] duration = cmdEvent[1].split(" /to ", 2);
            LocalDateTime from = LocalDateTime.parse(duration[0], formatter);
            LocalDateTime to = LocalDateTime.parse(duration[1], formatter);
            Task event = new Event(info, from, to);
            list.add(event);
            ui.showMessage("Fanny:\nGot it. I've added this task:");
            ui.showMessage(event.toString());
            ui.showMessage("Now you have " + list.getLength() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.showMessage("Event description and duration cannot be empty");
        } catch (DateTimeParseException e) {
            ui.showMessage("Please enter a valid date and time: YYYY-MM-DD HH:MM:SS");
        } finally {
            ui.showHorizontalLine();
        }
    }

    /**
     * Indicates that executing this command should not exit the application.
     *
     * @return {@code false}, indicating that the application should not exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
