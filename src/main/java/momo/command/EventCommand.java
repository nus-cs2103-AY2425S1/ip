package momo.command;

import java.time.DateTimeException;
import java.time.LocalDate;

import momo.Storage;
import momo.StorageException;
import momo.Ui;
import momo.exception.InvalidCommandException;
import momo.task.Event;
import momo.task.Task;
import momo.task.TaskList;

/**
 * Handles the functionality of adding an event, updating the {@link TaskList} and
 * {@link Storage} to include it. It also validates the initial user input. If it was
 * not properly formatted an {@link InvalidCommandException} is thrown which prompts
 * user to submit a proper input
 */
public class EventCommand extends AddCommand {

    public static final int COMMAND_PREFIX_OFFSET = 5;

    /**
     * Runs event command
     * @param input
     * @param storage
     * @param tasks
     * @param ui
     * @throws InvalidCommandException
     * @throws StorageException
     */
    public static void run(String input, Storage storage, TaskList tasks, Ui ui) throws InvalidCommandException,
            StorageException {
        try {
            Task event = getTask(input);
            tasks.addTask(event);
            addToStorage(storage, event);
            printTaskAdded(event, ui);
            ui.printDialogue(String.format("Now you have %d task(s) in the list%n", tasks.getCount()));

        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("You better format your event properly or else [REDACTED]");
        } catch (DateTimeException dte) {
            throw new InvalidCommandException("You better format your event dates in a valid YYYY-MM-DD format or "
                    + "else...");
        }
    }

    private static Task getTask(String input) throws InvalidCommandException {
        String desc = input.substring(COMMAND_PREFIX_OFFSET).trim();
        if (!desc.contains("/from")) {
            throw new InvalidCommandException("Your event needs a /from YYYY-MM-DD input, don't cross the line");
        }
        if (!desc.contains("/to")) {
            throw new InvalidCommandException("Your event needs a /to YYYY-MM-DD input, don't cross the line");
        }

        String task = desc.split("/", 2)[0].trim();
        if (task.isEmpty()) {
            throw new InvalidCommandException("Where is your event description?!");
        }

        String from = desc.split("/from")[1].split("/to")[0].trim();
        String to = desc.split("/to")[1].trim();

        return new Event(task, LocalDate.parse(from), LocalDate.parse(to), false);
    }

}
