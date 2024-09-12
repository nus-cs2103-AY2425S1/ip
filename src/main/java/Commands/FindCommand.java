package Commands;

import Main.Ui;

public class FindCommand extends Command {
    private String userInput;

    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Displays items in list with matching word via method in storelist class
     *
     * @return a string
     */
    @Override
    public String execute() {
       
        String[] keywords = userInput.substring(4).trim().split("\\s+");

        return storeList.displayItemsWithWord(keywords);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}