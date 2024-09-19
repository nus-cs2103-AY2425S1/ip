package lama.command;

import lama.LamaException;
import lama.Storage;
import lama.TaskList;
import lama.Ui;

/**
 * Represent and abstract command that can be executed.
 */
public abstract class Command {
    /**
     * Run the command with the given task list, storage and UI.
     * Implement by subclass.
     *
     * @param taskList Task list (Arraylist) on which the command operates.
     * @param storage Storage used to save or load task.
     * @param ui User interface that interacts with user.
     * @throws LamaException Throw exception if there is error occurs during the execution of the command.
     */
    public abstract String run(TaskList taskList, Storage storage, Ui ui) throws LamaException;

    /**
     * Indicates whether the command should exit.
     *
     * @return false by default.However, if command given should exit the loop, it will return true.
     */
    public boolean isExit() {
        return false;
    }

}
