package rasputin.command;

import javafx.application.Platform;
import rasputin.task.RasputinException;
import rasputin.task.TaskList;

import rasputin.storage.Storage;

import rasputin.gui.Ui;


/**
 * Represents the command to close the program when the user types "bye".
 */
public class ExitCommand extends Command {
    private boolean isTerminated;
    private Storage storage;
    private TaskList tasks;

    public ExitCommand(Storage storage, TaskList tasks) {
        isTerminated = false;
        this.storage = storage;
        this.tasks = tasks;
    }

    /**
     * Writes the TaskList into the .txt file and prints the farewell message.
     *
     */
    @Override
    public String execute() {
        isTerminated = true;
        storage.writeFile(tasks);
        Platform.exit();
        return Ui.printFarewell();
    }


    /**
     * Will always return true as program will be terminated.
     *
     * @return True.
     */
    @Override
    public boolean isTerminated() {
        return isTerminated;
    }
}
