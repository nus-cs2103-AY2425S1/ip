package kietwoforone.commands;

import kietwoforone.exceptions.KieTwoForOneException;
import kietwoforone.storage.Storage;
import kietwoforone.tasks.TaskList;
import kietwoforone.ui.UI;

/**
 * Represents any command the user can call in the chatbot.
 */
public abstract class Command {

    /**
     * Abstract method that executes any command that user can input.
     * Throws a KieTwoForOne exception if the user puts an invalid/incomplete input.
     *
     * @param tasks
     * @param ui
     * @param storage
     * @throws KieTwoForOneException
     */
    public abstract void execute(TaskList tasks, UI ui, Storage storage) throws KieTwoForOneException;

    /**
     * Abstract method that returns a boolean to determine whether to close the chatbot.
     * True if the command closes the chatbot and false otherwise.
     *
     * @return Boolean to determine whether to close the chatbot.
     */
    public abstract boolean isExit();

}
