package quack.command;

import java.io.IOException;

import quack.Quack;
import quack.Storage;
import quack.TaskList;

/**
 * This class is responsible for handling the stopping of Quack.
 */
public class ExitCommand extends Command {

    /** Quack chatbot object */
    private Quack quack;
    /** To store all of the users tasks */
    private TaskList taskList;
    /** Sotrage object to load and save data */
    private Storage storage;

    /**
     * Creates a ExitCommand object.
     * @param quack The chatbot object quack.
     * @param taskList A list that stores all the tasks tracked by Quack.
     * @param storage Storage object to save and load data from the save file.
     */
    public ExitCommand(Quack quack, TaskList taskList, Storage storage) {
        this.quack = quack;
        this.taskList = taskList;
        this.storage = storage;
    }

    @Override
    public void execute() {

        try {
            storage.saveData(taskList);
        } catch (IOException IoError) {
            System.out.println(IoError.getMessage());
        } finally {
            quack.shutDown();
        }
    }
}
