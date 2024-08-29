package Commands;

import Main.Ui;

public class ListCommand extends Command {
    String userInput;

    public ListCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Displays items in list via method in storelist class
     *
     */
    @Override
    public void execute() {
        System.out.println(Ui.LINE);
        storeList.displayItems();
        System.out.println(Ui.LINE);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
