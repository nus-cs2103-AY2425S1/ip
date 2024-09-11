package bob.commands;

import java.io.IOException;

import bob.data.TaskList;
import bob.storage.FileWriting;
import bob.storage.Storage;
import bob.ui.Ui;

/**
 * Represents the bye command.
 */
public class Bye extends Command {
    /**
     * Saves the list of tasks to the file.
     * Exits the program.
     *
     * @param list The list of tasks.
     * @param ui The user interface.
     * @param storage The storage of tasks.
     * @throws RuntimeException if there is an error saving the tasks to the file.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        try {
            FileWriting.saveTasks(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "Bye. Hope to see you again soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
