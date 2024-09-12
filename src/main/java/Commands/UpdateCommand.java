package Commands;

import Exceptions.InvalidFormatException;
import Exceptions.InvalidIndexException;

public class UpdateCommand extends Command {
    String userInput;

    /**
     * Constructor for UpdateCommand
     * Initializes the class with the user's input string which contains the task number,
     * update type, and the new value for updating the task.
     *
     * @param userInput The string containing the command in the format
     *                  'update <taskNum> <updateType> /to <newValue>'
     */
    public UpdateCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Executes the update command by parsing the user input to extract task number,
     * update type (e.g., description, date), and the new value.
     * It validates the format, task number, and update type before performing the update.
     * Throws relevant exceptions for invalid formats or task numbers.
     *
     * @return A success message after the task has been updated.
     * @throws InvalidIndexException   If the task number is not valid.
     * @throws InvalidFormatException  If the input format is incorrect.
     */
    @Override
    public String execute() throws InvalidIndexException, InvalidFormatException {
            // Split the string by "/to"
            String[] words = userInput.split(" /to ");

            // Check if there are exactly two parts (before and after "/to")
            if (words.length != 2) {
                throw new InvalidFormatException("Invalid format. Use: update <taskNum> <updateType> /to <newValue>");
            }

            // Split the first part to extract task number and update type
            String[] words1 = words[0].split(" ");

            // Check if we have exactly 3 parts (e.g., "update 2 desc")
            if (words1.length != 3) {
                throw new InvalidFormatException("Invalid format. Use: update <taskNum> <updateType> /to <newValue>");
            }

            // Check if the task number is a valid integer
            int itemNum;
            try {
                itemNum = Integer.parseInt(words1[1]);
            } catch (NumberFormatException e) {
                throw new InvalidFormatException("Invalid task number. It must be an integer.");
            }

            // Check if update type is valid
            String updateType = words1[2];
            if (!updateType.equals("desc") && !updateType.equals("date") && !updateType.equals("time")
                    && !updateType.equals("startTime") && !updateType.equals("endTime")) {
                throw new InvalidFormatException("Invalid update type. Valid types are: desc, date1, date2, time, startTime, endTime.");
            }

            // Extract the new value for the update
            String newValue = words[1].trim();
            if (newValue.isEmpty()) {
                throw new InvalidFormatException("New value cannot be empty.");
            }

            // Update the task with the given values
            return storeList.updateTask(itemNum, updateType, newValue);
    }

@Override
    public boolean isExit() {

        return false;
    }
}
