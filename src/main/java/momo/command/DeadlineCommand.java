package momo.command;

import java.time.DateTimeException;
import java.time.LocalDate;

import momo.Storage;
import momo.StorageException;
import momo.Ui;
import momo.exception.InvalidCommandException;
import momo.task.Deadline;
import momo.task.Task;
import momo.task.TaskList;


/**
 * Represents the Deadline command's functionalities, which includes validating
 * input which begins with "deadline" and adding it to the {@link TaskList}
 * If user input is not properly formatted it throws a {@link InvalidCommandException}
 */
public class DeadlineCommand extends AddCommand {
    public static final int COMMAND_PREFIX_OFFSET = 8;

    /**
     * Runs the deadline command, adding the deadline to storage and the task list
     * @param input is the user input
     * @param storage is the file object
     * @param tasks is the task list
     * @param ui is the ui object
     * @throws InvalidCommandException when user input is invalid
     * @throws StorageException when there is an issue with loading the storage file
     */
    public static void run(String input, Storage storage, TaskList tasks, Ui ui) throws InvalidCommandException,
            StorageException {

        try {
            Task deadline = getTask(input);
            tasks.addTask(deadline);
            addToStorage(storage, deadline);
            printTaskAdded(deadline, ui);
            ui.printDialogue(String.format("Now you have %d task(s) in the list%n", tasks.getCount()));

        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("You better format your deadline properly or else [REDACTED]");
        } catch (DateTimeException dte) {
            throw new InvalidCommandException("You better format your deadline date in a valid YYYY-MM-DD format or "
                    + "else...");
        }
    }

    private static Task getTask(String input) throws InvalidCommandException {
        String desc = input.substring(COMMAND_PREFIX_OFFSET).trim();
        if (!desc.contains("/by")) {
            throw new InvalidCommandException("Your deadline needs a /by YYYY-MM-DD input, don't cross the line");
        }

        String task = desc.split("/", 2)[0].trim();
        if (task.isEmpty()) {
            throw new InvalidCommandException("Where is your deadline description?!");
        }

        String by = desc.split("/by", 2)[1].trim();
        return new Deadline(task, LocalDate.parse(by), false);
    }
}
