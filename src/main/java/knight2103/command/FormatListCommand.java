package knight2103.command;

import knight2103.Ui;
import knight2103.files.Storage;
import knight2103.tasks.Task;
import knight2103.tasks.TaskList;

/**
 * Models commands that require showing or formatting the list of tasks
 */
public abstract class FormatListCommand extends Command {
    FormatListCommand(CommandVerb verb) {
        super(verb);
    }

    FormatListCommand(CommandVerb verb, String search) {
        super(verb, search); // verb must be CommandVerb.FIND
    }

    /**
     * Executes the Command which process the tasks and returns a message to be shown in the bot GUI.
     *
     * @param tasks The object storing the list of tasks found in the bot.
     * @param ui The user interface of the bot.
     * @param storage The object containing the file that saves the list of tasks.
     * @return The message to be shown by the bot in GUI after command execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage);

    /**
     * Stringifies the list of tasks found in the bot and formats it in a particular way. If there is a
     * filter function, the list of task will be filtered first before being stringified to a list to be
     * shown in the GUI.
     *
     * @param tasks The object storing the list of tasks found in the bot.
     * @param filterWords The filter function.
     * @return The list of task in String ready to be part of the bot's response in GUI.
     */
    // consider filter function rather than filterWords. [flter the list to be printed]
    protected String formatToList(TaskList tasks, String... filterWords) {
        String stringToReturn = "";
        int bulletPoint = 0;
        for (int i = 0; i < tasks.getSize(); i++) { // IndexOutOfBounds possibility
            Task currentTask = tasks.getTask(i);
            if (!(filterWords.length == 0 || currentTask.getDescription().contains(filterWords[0]))) {
                continue;
            }
            bulletPoint = i + 1;
            stringToReturn += bulletPoint + ". " + currentTask + "\n";
        }
        return stringToReturn;
    }

}
