package Commands;

import Exceptions.InvalidIndexException;

public class MarkCommand extends Command {
    String userInput;

    public MarkCommand(String userInput) {

        this.userInput = userInput;
    }

    /**
     * Marks item as completed via method in storelist class
     *
     */
    @Override
    public String execute() throws InvalidIndexException {
        // Split the string by spaces
        String[] words = userInput.split(" ");
        int itemNum = Integer.parseInt(words[1]);

        // Execute the mark item logic and get the result message
        return storeList.markItem(itemNum);


    }

    @Override
    public boolean isExit() {

        return false;
    }
}
