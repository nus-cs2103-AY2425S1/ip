package commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import exceptions.InvalidDateException;
import exceptions.MizzException;
import tasks.Event;
import tasks.Task;
import util.Storage;
import util.TaskList;
import util.Ui;
import util.Utility;

/**
 * Concrete implementation of event command class.
 */
public class EventCommand extends Command {
    public EventCommand(CommandTypes type) {
        this.command = type;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage, String... details)
            throws MizzException {
        assert tl != null : "Task list must not be null";
        assert ui != null : "Ui must not be null";
        assert storage != null : "Storage must not be null";
        assert details != null : "Details must not be null";

        assert tl != null : "Task list must not be null";
        assert ui != null : "Ui must not be null";
        assert storage != null : "Storage must not be null";
        assert details != null : "Details must not be null";

        try {
            LocalDate fromDate = LocalDate.parse(details[2]);
            LocalDate toDate = LocalDate.parse(details[3]);
            Task newTask = new Event(details[1], fromDate, toDate);
            tl.addTask(newTask, storage);
            ui.setResponse("Got it I've added this task:", Utility.INDENT + newTask.toString(),
                    String.format("You now have %d tasks in your list.", tl.size()));
            ui.printResponse();
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(e.getMessage());
        }
    }
}
