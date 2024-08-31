package momo.command;

import momo.InvalidCommandException;
import momo.Storage;
import momo.StorageException;
import momo.task.*;

import java.time.DateTimeException;
import java.time.LocalDate;

/**
 * Handles the functionality of adding an event, updating the {@link TaskList} and
 * {@link Storage} to include it. It also validates the initial user input. If it was
 * not properly formatted an {@link InvalidCommandException} is thrown which prompts
 * user to submit a proper input
 */
public class EventCommand extends AddCommand {
    public static void run(String input, Storage storage, TaskList tasks) throws InvalidCommandException, StorageException {
        try {
            String desc = input.substring(5).trim();
            if (!desc.contains("/from")) {
                throw new InvalidCommandException("Your event needs a /from YYYY-MM-DD input, don't cross the line");
            }
            if (!desc.contains("/to")) {
                throw new InvalidCommandException("Your event needs a /to YYYY-MM-DD input, don't cross the line");
            }

            String task =  desc.split("/",2)[0].trim();
            if (task.isEmpty()) {
                throw new InvalidCommandException("Where is your event description?!");
            }

            String from = desc.split("/from")[1].split("/to")[0].trim();
            String to = desc.split("/to")[1].trim();

            Task event = new Event(task, LocalDate.parse(from), LocalDate.parse(to),false);
            tasks.addTask(event);
            addToStorage(storage,event);
            printTaskAdded(event);
            System.out.printf("Now you have %d task(s) in the list%n", tasks.getCount());

        }
        catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("You better format your event properly or else [REDACTED]");
        } catch (DateTimeException dte) {
            throw new InvalidCommandException("You better format your event dates in a valid YYYY-MM-DD format or else...");
        }


    }

}
