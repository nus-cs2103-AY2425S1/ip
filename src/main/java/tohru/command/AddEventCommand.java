package tohru.command;

import tohru.exception.TohruException;
import tohru.storage.FileStore;
import tohru.task.EventItem;
import tohru.task.TodoList;
import tohru.ui.Ui;

import java.time.LocalDateTime;

/**
 * AddEventCommand represents the command to add an event task to the to-do list
 */
public class AddEventCommand extends Command {

    /** Prefix used to invoke the add deadline command **/
    public static final String COMMAND_PREFIX = "event";

    public AddEventCommand(String arguments) {
        super(arguments);
    }

    @Override
    public void execute(TodoList list, Ui ui, FileStore store) throws TohruException {
        // Check if no arguments are provided
        if (super.arguments == null) {
            throw new TohruException("Missing argument: Please specify description, from datetime and to datetime");
        }

        String[] dissectedArgument = super.arguments.split("/from|/to", 3);
        // Check if all arguments are present
        if (dissectedArgument.length < 3) {
            throw new TohruException("Missing argument: Missing either description, from datetime or to datetime");
        }

        String eventContent = dissectedArgument[0];
        // Check for valid description
        if (eventContent.isBlank()) {
            throw new TohruException("Missing argument: Please specify description");
        }

        String fromStr = dissectedArgument[1];
        // Check for valid from datetime
        if (fromStr.isBlank()) {
            throw new TohruException("Missing argument: Please specify from datetime");
        }

        String toStr = dissectedArgument[2];
        // Check for valid to datetime
        if (toStr.isBlank()) {
            throw new TohruException("Missing argument: Please specify to datetime");
        }

        LocalDateTime eventFromDate = LocalDateTime.parse(fromStr.trim(), Command.DATE_TIME_FORMATTER);
        LocalDateTime eventToDate = LocalDateTime.parse(toStr.trim(), Command.DATE_TIME_FORMATTER);
        if (eventFromDate.isAfter(eventToDate)) {
            throw new TohruException("Invalid argument: To datetime cannot be earlier than From datetime");
        }

        EventItem newEvent = new EventItem(eventContent, eventFromDate, eventToDate);
        list.addItem(newEvent);

        ui.showText("Added event entry:");
        ui.showText(newEvent.toString());

        ui.showText(String.format("There are now %d total entries", list.getTotal()));

        store.saveTodoList(list.getTodoList());

    }

}
