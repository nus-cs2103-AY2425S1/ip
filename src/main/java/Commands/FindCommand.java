package Commands;

import Main.Ui;

public class FindCommand extends Command {
    private String userInput;

    public FindCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Displays items in list with matching word via method in storelist class
     */
    @Override
    public void execute() {
        System.out.println(Ui.LINE);
       
        String[] keywords = userInput.substring(4).trim().split("\\s+");

        storeList.displayItemsWithWord(keywords);
        System.out.println(Ui.LINE);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}