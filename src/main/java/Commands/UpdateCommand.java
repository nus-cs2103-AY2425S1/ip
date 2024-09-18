package Commands;

import Exceptions.InvalidIndexException;
import Exceptions.UnknownCommandException;

/**
 * Represents a command to update a task in the list.
 * The user can update different aspects of a task (date, time, description)
 * based on the given type and new value. The command is triggered by
 * user input in the format: 'update <num> <type> /to <newValue>'.
 */
public class UpdateCommand extends Command {
    String userInput;

    /**
     * Constructs an UpdateCommand with the specified user input.
     *
     * @param userInput the raw input string from the user, containing the
     * task number, the type of update (date, time, desc),
     * and the new value to update to.
     */
    public UpdateCommand(String userInput) {

        this.userInput = userInput;
    }


    /**
     * Executes the command to update a specific task based on the provided input.
     * It splits the input to extract the task number, the type of update,
     * and the new value. If the input is incorrectly formatted,
     * it returns an error message.
     *
     * @return the result of the update operation, or an error message if
     * the input format is invalid.
     * @throws InvalidIndexException if the task number provided is out of bounds.
     * @throws UnknownCommandException if the task number provided is out of bounds.
     */
    @Override
    public String execute() throws InvalidIndexException, UnknownCommandException {
        // Split the string by /to
        String[] words = userInput.split(" /to ");

        // Ensure that there are enough parts after the split
        if (words.length < 2) {
            throw new UnknownCommandException("Invalid command format. Please use the format: 'update <num> <type> /to <newValue>'.");
        }

        //Split first half by spaces to get update and num and type(date/time/desc etc)
        String[] words1 = words[0].split(" ");

        // Ensure that there are enough parts in words1
        if (words1.length < 3) {
            throw new UnknownCommandException("Invalid command format. Please use the format: 'update <num> <type> /to <newValue>'.");
        }

        String num = words1[1];
        String type = words1[2];
        int itemNum = Integer.parseInt(num);

        String newValue = words[1];
        return storeList.updateTask(itemNum, type, newValue);
    }

    @Override
    public boolean isExit() {

        return false;
    }
}
