package bob.commands;

import bob.storage.FileWriting;
import bob.storage.Storage;
import bob.data.TaskList;
import bob.ui.Ui;

import java.io.IOException;

/**
 * Exits the program.
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
    public void execute(TaskList list, Ui ui, Storage storage) {
        try {
            FileWriting.saveTasks(list);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
