package commands;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import exceptions.InvalidDateException;
import exceptions.MizzException;
import tasks.Deadline;
import tasks.Task;
import util.Storage;
import util.TaskList;
import util.Ui;
import util.Utility;

/**
 * Concrete implementation of deadline command class.
 */
public class DeadlineCommand extends Command {
    public DeadlineCommand(CommandTypes type) {
        this.command = type;
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage, String... details)
            throws MizzException {
        try {
            LocalDate date = LocalDate.parse(details[2]);
            Task newTask = new Deadline(details[1], date);
            tl.addTask(newTask, storage);
            ui.printResponse("Got it I've added this task:", Utility.INDENT + newTask.toString(),
                    String.format("You now have %d tasks in your list.", tl.size()));
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(e.getMessage());
        }
    }
}
