package trackie.commands;

import trackie.storage.Storage;
import trackie.storage.TaskList;
import trackie.ui.TrackieException;

/**
 * Represents a command to mark a certain task a completed.
 */
public class MarkCommand extends Command {
    /**
     * Constructs a new MarkCommand with the given arguments.
     *
     * @param arguments An array of Strings containing the arguments from the user\.
     *                  Typically, this would include the index of the task to be marked completed.
     */
    public MarkCommand(String[] arguments) {
        super.arguments = arguments;
    }

    /**
     * Executes the mark command.
     * This method causes the tasklist to mark a task as completed.
     * The user is then notified of the tasks' completion by the chatbot.
     *
     * @param tasklist The TaskList object from which a task will be marked.
     * @param storage The Storage object used to save the updated task list.
     */
    @Override
    public String execute(TaskList tasklist, Storage storage) throws TrackieException {
        try {
            if (arguments.length == 1) {
                throw new TrackieException("Please specify an index to mark!");
            }

            int number = Integer.parseInt(arguments[1]);

            if (number < 1 || number > tasklist.size()) {
                throw new TrackieException("Invalid index.");
            }

            tasklist.markTask(number - 1);
            storage.save();
            return "Gratz, you've completed: " + tasklist.getTasks().get(number - 1).toString();
        } catch (TrackieException e) {
            return e.getMessage();
        }
    }
}
