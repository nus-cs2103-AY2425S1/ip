package qwerty.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import qwerty.QwertyException;
import qwerty.Storage;
import qwerty.TaskList;
import qwerty.Ui;
import qwerty.task.Event;
import qwerty.task.Task;

/**
 * This class encapsulates an 'event' command.
 */
public class EventCommand extends Command {

    public EventCommand(HashMap<String, String> args) {
        super(args);
    }

    @Override
    public boolean isExitCommand() {
        return false;
    }

    /**
     * Creates a new Event task and adds it to the TaskList.
     *
     * @param tasks The TaskList component handling storage of Task objects.
     * @param ui The Ui component handling user interaction.
     * @param storage The Storage component handling read/write to hard disk.
     * @throws QwertyException If arguments are missing or date arguments are not formatted correctly.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws QwertyException {
        String description = getArgs().get("main");
        String strFrom = getArgs().get("from");
        String strTo = getArgs().get("to");
        if (description == null) {
            throw new QwertyException("Your task is missing a name.");
        }
        if (strFrom == null) {
            throw new QwertyException("You didn't tell me when your event starts.");
        }
        if (strTo == null) {
            throw new QwertyException("Your didn't tell me when your event ends.");
        }
        try {
            LocalDateTime from = LocalDateTime.parse(
                    strFrom, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            LocalDateTime to = LocalDateTime.parse(
                    strTo, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));
            Task event = new Event(description, from, to);
            tasks.addTask(event);
            ui.showMessage("\nAdded this task:\n" + event
                    + "\nNow you have " + tasks.getSize() + (tasks.getSize() == 1 ? " task " : " tasks ")
                    + "in the list.\nBetter get to it.");
        } catch (DateTimeParseException e) {
            ui.showError("I don't like the way you write dates.\nUse this format: dd/MM/yyyy HHmm");
        }
    }

}
