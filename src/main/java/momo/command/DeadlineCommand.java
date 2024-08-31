package momo.command;

import momo.InvalidCommandException;
import momo.Storage;
import momo.StorageException;
import momo.task.Deadline;
import momo.task.Task;
import momo.task.TaskList;

import java.time.DateTimeException;
import java.time.LocalDate;

public class DeadlineCommand extends AddCommand {
    public static void run(String input, Storage storage, TaskList tasks) throws InvalidCommandException, StorageException {

        try {
            String desc = input.substring(8).trim();
            if (!desc.contains("/by")) {
                throw new InvalidCommandException("Your deadline needs a /by YYYY-MM-DD input, don't cross the line");
            }

            String task = desc.split("/", 2)[0].trim();
            if (task.isEmpty()) {
                throw new InvalidCommandException("Where is your deadline description?!");
            }

            String by = desc.split("/by", 2)[1].trim();
            Task deadline = new Deadline(task, LocalDate.parse(by), false);
            tasks.addTask(deadline);
            addToStorage(storage,deadline);
            printTaskAdded(deadline);
            System.out.printf("Now you have %d task(s) in the list%n", tasks.getCount());

        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("You better format your deadline properly or else [REDACTED]");
        } catch (DateTimeException dte) {
            throw new InvalidCommandException("You better format your deadline date in a valid YYYY-MM-DD format or else...");
        }
    }
}
