package Commands;

import Exceptions.InvalidIndexException;

public class UnmarkCommand extends Command {
    String userInput;

    public UnmarkCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Marks item as incomplete
     *
     * @return
     */
    @Override
    public String execute() throws InvalidIndexException {
        // Split the string by spaces
        String[] words = userInput.split(" ");
        int itemNum = Integer.parseInt(words[1]);
        return storeList.UnmarkItem(itemNum);
    }

    @Override
    public boolean isExit() {

        return false;
    }
}
