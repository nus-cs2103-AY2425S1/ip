package ned.commands;

import ned.Storage;
import ned.TaskList;
import ned.Ui;

/**
 * Represents the list command, which when executed, retrieves all tasks from the list of tasks and causes it to be
 * displayed to the user.
 */
public class ListCommand implements Command {
    private static final String REGEX = "list";

    /**
     * This method will call the taskList::listTasks method.
     *
     * @param taskList        The TaskList instance associated with the Ned instance, used to store the list of tasks
     *                        and to modify them
     * @param uiInstance      The Ui instance associated with the Ned instance, used to display user output, and take in
     *                        user input
     * @param storageInstance The storage instance associated with the Ned instance, used to load in saved tasks
     *                        from the cache file as well as to modify the cache file when the list of tasks are
     *                        changed
     * @param userInput       The string of user input, is parsed for relevant information needed to execute commands
     *                        to the user
     */
    @Override
    public void execute(TaskList taskList, Ui uiInstance, Storage storageInstance, String userInput) {
        taskList.listTasks(uiInstance);
    }

    /**
     * Returns the regex expression used to identify the list command.
     * Used in Parser class to find the associated command.
     *
     * @return The regex pattern associated with this command
     */
    @Override
    public String getRegex() {
        return this.REGEX;
    }
}
