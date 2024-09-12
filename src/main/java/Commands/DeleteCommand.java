package Commands;

import Exceptions.InvalidIndexException;
import Main.Ui;

public class DeleteCommand extends Command {
    String userInput;

    public DeleteCommand(String userInput) {

        this.userInput = userInput;
    }

    /**
     * deletes item in list via method in storelist class
     *
     * @return a string
     */
    @Override
    public String execute() throws InvalidIndexException {
        // Split the string by spaces
        String[] words = userInput.split(" ");
        int itemNum = Integer.parseInt(words[1]);
        return storeList.deleteItem(itemNum);
    }

    @Override
    public boolean isExit() {

        return false;
    }
}
