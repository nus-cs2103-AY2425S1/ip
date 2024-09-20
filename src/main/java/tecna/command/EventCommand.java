package tecna.command;

import java.time.format.DateTimeParseException;

import tecna.collection.TaskList;
import tecna.exception.WrongFormatException;
import tecna.storage.Storage;
import tecna.task.Event;
import tecna.ui.Ui;
import tecna.util.DateTimeUtil;

import static tecna.util.DateTimeUtil.DATE_TIME_PATTERN;

/**
 * Represents the Command of type EventCommand (add an event task).
 *
 * @author Solution below inspired by https://github.com/Feng1231/ip.
 */
public class EventCommand extends Command {
    /**
     * Constructs a EventCommand instance.
     *
     * @param message The whole command input in String.
     */
    public EventCommand(String message) {
        super(message);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        super.execute(taskList, storage, ui);
        Event event;

        try {
            event = parseEventCommand();
        } catch (WrongFormatException e) {
            return ui.printError(e.getMessage());
        }

        assert event != null;

        try {
            taskList.addItem(event);
        } catch (tecna.exception.TaskDuplicateException e) {
            return ui.printTaskDuplicateError(event);
        }

        return ui.printAddItemMsg(taskList, event);
    }

    /**
     * Interprets the input.
     *
     * @return An Event object which needs to be added if the command is valid.
     * @throws WrongFormatException If the Event command is in wrong format.
     */
    public Event parseEventCommand() throws WrongFormatException {
        Event event;

        assert message.contains("event");

        int fromIndex = message.indexOf("/from");
        int toIndex = message.indexOf("/to");

        if (fromIndex == -1 || toIndex == -1 || fromIndex > toIndex) {
            throw new WrongFormatException("event", "Event task should in the format of \"event [task name] /from [" + DATE_TIME_PATTERN + "] /to [" + DATE_TIME_PATTERN + "]\"");
        }

        String[] description = message.split("event | /from | /to ");

        for (String s : description) {
            System.out.println("Description: " + s);
        }

        if (description.length < 2) {
            throw new WrongFormatException("event", "Event task should in the format of \"event [task name] /from [" + DATE_TIME_PATTERN + "] /to [" + DATE_TIME_PATTERN + "]\"");
        }


        try {
            event = new Event(description[1].trim(), DateTimeUtil.parseDateTime(description[2].trim()), DateTimeUtil.parseDateTime(description[3].trim()));
            if (description[1].isBlank()) {
                throw new WrongFormatException("event", "Event task's [task name] must not be empty");
            }
        } catch (ArrayIndexOutOfBoundsException | DateTimeParseException e) {
            throw new WrongFormatException("event", "Event task should in the format of \"event [task name] /from [" + DATE_TIME_PATTERN + "] /to [" + DATE_TIME_PATTERN + "]\"");
        }

        return event;
    }

}
