package wenjiebot.commands;

import wenjiebot.Storage;
import wenjiebot.TaskList;
import wenjiebot.Ui;

/**
 * Represents a command to exit the application.
 * This command will save all tasks to storage and display a farewell message to the user.
 */
public class ByeCommand extends Command {

    /**
     * Constructs a ByeCommand with the specified exit status and input.
     * This command always sets the exit status to true.
     *
     * @param isExit boolean indicating whether this command will cause WenJie Bot to exit after executing.
     * @param input the input associated with this command.
     */
    public ByeCommand(boolean isExit, String input) {
        super(true, input);
    }

    /**
     * Executes the ByeCommand, which involves saving tasks to storage,
     * displaying a farewell message, and setting the exit status to true.
     *
     * @param tasks the TaskList that contains all the tasks.
     * @param ui the Ui used for interaction with the user.
     * @param storage the Storage used to store and retrieve tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.writeTasks();
        ui.showFarewell();
        super.setIsExit(true);
        ui.exitApp();
    }
}
