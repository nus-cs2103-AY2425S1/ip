package commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import MizzExceptions.InvalidDateException;
import MizzExceptions.MizzException;
import tasks.Event;
import tasks.Task;
import util.Storage;
import util.TaskList;
import util.Ui;
import util.Utility;

public class EventCommand extends Command {
    public EventCommand(CommandTypes type) {
        this.command = type;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage, String... details) throws MizzException {
        try {
            LocalDate fromDate = LocalDate.parse(details[2]);
            LocalDate toDate = LocalDate.parse(details[3]);
            Task newTask = new Event(details[1], fromDate, toDate);
            tl.addTask(newTask, storage);
            ui.printResponse("Got it I've added this task:", Utility.INDENT + newTask.toString(),
                    String.format("You now have %d tasks in your list.", tl.size()));
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(e.getMessage());
        }
    }
}
