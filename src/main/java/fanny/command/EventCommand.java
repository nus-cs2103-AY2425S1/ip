package fanny.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import fanny.task.Event;
import fanny.task.Task;
import fanny.task.TaskList;
import fanny.ui.Ui;

/**
 * Represents the command that handles the "event" prompt.
 */
public class EventCommand extends Command {

    /** String representation of the description of the event task. */
    private String description;

    private Event event;

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
     * Handles any errors that may occur during parsing.
     *
     * @param list The task list to which the event task is added.
     * @param ui The UI object to interact with the user.
     */
    @Override
    public String executeCmd(TaskList list, Ui ui) {
        String message = "";
        ui.showHorizontalLine();

        try {
            generateEvent();
            list.add(this.event);
            message = ui.showAddTaskMsg(this.event, list);
        } catch (ArrayIndexOutOfBoundsException e) {
            message = ui.showMessage("Event description and duration cannot be empty");
        } catch (DateTimeParseException e) {
            message = ui.showMessage("Please enter a valid date and time: YYYY-MM-DD HH:MM");
        } finally {
            ui.showHorizontalLine();
        }

        return message;
    }

    /**
     * Parses the description to extract the event information and duration.
     * Generate an event based on the extracted information.
     */
    public void generateEvent() {
        String[] cmdEvent = this.description.split("/from ", 2);
        String info = cmdEvent[0];
        String[] duration = cmdEvent[1].split(" /to ", 2);
        LocalDateTime startTime = LocalDateTime.parse(duration[0], formatter);
        LocalDateTime endTime = LocalDateTime.parse(duration[1], formatter);
        this.event = new Event(info, startTime, endTime);
    }

    /**
     * Indicates that executing this command should not exit the application.
     *
     * @return {@code false}, indicating that the application should not exit.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }

}
