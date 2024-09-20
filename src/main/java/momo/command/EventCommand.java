package momo.command;

import java.time.DateTimeException;
import java.time.LocalDate;

import momo.Storage;
import momo.StorageException;
import momo.exception.InvalidCommandException;
import momo.task.Event;
import momo.task.Task;
import momo.task.TaskList;

/**
 * Handles the functionality of adding an event, updating the {@link TaskList} and
 * {@link Storage} to include it. It also validates the initial user input. If it was
 * not properly formatted an {@link InvalidCommandException} is thrown which prompts
 * user to submit a proper input.
 */
public class EventCommand extends AddCommand {

    public static final int COMMAND_PREFIX_OFFSET = 5;

    /**
     * Executes the event command, creating a new event task from the user input,
     * adding it to the task list, and storing it in persistent storage. The method
     * validates the input and handles task management and file I/O operations.
     * It provides feedback to the user once the task is successfully added.
     *
     * @param input The user input that describes the event task (expected to contain task details).
     * @param storage The storage handler responsible for saving the new event task to the file.
     * @param tasks The task list to which the new event will be added.
     * @return A string message indicating that the event has been successfully added and the total task count.
     * @throws InvalidCommandException If the input is improperly formatted or the event task could not be created.
     * @throws StorageException If there is an error saving the task to storage.
     */
    public static String run(String input, Storage storage, TaskList tasks) throws InvalidCommandException,
            StorageException {
        try {
            Task event = getTask(input);
            tasks.addTask(event);
            addToStorage(storage, event);
            return "Noted. I've added this task:\n" + event
                    + returnCountString(tasks);


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
