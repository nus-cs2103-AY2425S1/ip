package ned.commands;

import ned.Storage;
import ned.TaskList;
import ned.Ui;
import ned.exceptions.InvalidIndexException;
import ned.exceptions.MissingIndexException;
import ned.exceptions.NedException;

/**
 * Represents the delete command, which when executed, causes the selected task to be removed from the list of tasks.
 */
public class DeleteCommand implements Command {
    private final String REGEX = "^delete.*";

    /**
     * Returns an instance of the delete command
     */
    public DeleteCommand() {
    }

    /**
     * Executes the delete command, by checking first for whether the specified task exists, by searching for its
     * index. It then removes the task and calls the Storage::save method
     *
     * @param taskList        An object which contains the ArrayList that stores the list of tasks. In addition, also
     *                        handles modifications and reading from the ArrayList
     * @param uiInstance      An object which handles output that is displayed to users and input from users
     * @param storageInstance An object which handles loading in and modifications to the cached list of tasks
     * @param userInput       A string which represents input from users into Ned
     * @throws NedException Thrown if the number specified in the delete command is not a valid number or if the
     *                      number is not a valid index
     */
    @Override
    public void execute(TaskList taskList, Ui uiInstance, Storage storageInstance, String userInput)
            throws NedException {
        String[] words = userInput.split(" ");
        try {
            if (words.length != 2) {
                throw new MissingIndexException("Sorry m'lord, you must give me a list index with the delete command. "
                        + "No more, no less" + uiInstance.getCommandMessage());
            } else {
                String possibleIndex = words[1];
                int index = Integer.parseInt(possibleIndex) - 1;
                taskList.removeTask(index, uiInstance);
                storageInstance.save(taskList);
            }
        } catch (NumberFormatException e) {
            throw new InvalidIndexException("Sorry m'lord, your command must specify a valid number"
                    + uiInstance.getCommandMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidIndexException("Sorry m'lord, your command must specify an index within the bounds of "
                    + "the list size" + uiInstance.getCommandMessage());
        }
    }

    @Override
    public String getRegex() {
        return this.REGEX;
    }
}
