package peppa.commands;

import java.io.IOException;

import peppa.data.TaskList;
import peppa.storage.FileWriting;
import peppa.storage.Storage;
import peppa.ui.Ui;

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
        return "Bye! Oink Oink. See you soon!";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
