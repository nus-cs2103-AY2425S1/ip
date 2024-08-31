package momo.command;

import momo.InvalidCommandException;
import momo.Storage;
import momo.StorageException;
import momo.task.Task;
import momo.task.TaskList;
import momo.task.Todo;

/**
 * Represents the command for adding Todo Tasks into the {@link Storage} and {@link TaskList},
 * validating user input which begins with "todo" and adding it to the {@link TaskList}
 * If user input is not properly formatted it throws a {@link InvalidCommandException}
 */
public class TodoCommand extends AddCommand {
    public static void run(String input, Storage storage, TaskList tasks) throws InvalidCommandException, StorageException {
        String desc = input.substring(4).trim();

        if (desc.isEmpty()) {
            throw new InvalidCommandException("Where's the description of your task?!");
        }

        Task todo = new Todo(desc, false);

        tasks.addTask(todo);
        addToStorage(storage,todo);
        printTaskAdded(todo);
        System.out.printf("Now you have %d task(s) in the list%n", tasks.getCount());
    }

}
