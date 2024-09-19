package echoa.command;

import echoa.exception.InvalidEventContentException;
import echoa.main.Parser;
import echoa.main.Storage;
import echoa.main.TaskList;
import echoa.main.Ui;
import echoa.task.Event;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * EventCommand is a class which encapsulates Event Commands.
 * It extends from Command.
 */
public class EventCommand extends Command {
    public EventCommand(Ui ui, Parser parser, TaskList taskList, Storage storage) {
        super(ui, parser, taskList, storage);
    }

    /**
     * Adds an event task.
     *
     * @param line input line given by user.
     */
    @Override
    public void execute(String line) throws InvalidEventContentException, IOException {
        Object[] event = parser.parseEventTask(line);
        String eventDescription = (String) event[0];
        LocalDateTime startDateAndTime = (LocalDateTime) event[1];
        LocalDateTime endDateAndTime = (LocalDateTime) event[2];
        taskList.addTask(new Event(eventDescription, startDateAndTime, endDateAndTime));
        storage.handleChange(taskList);
    }

    /**
     * Returns Echoa's reply to the adding the Event Command, the event task.
     *
     * @return a String message of the added event task.
     */
    @Override
    public String getMessage() {
        return ui.getAddTaskMessage(taskList);
    }
}
