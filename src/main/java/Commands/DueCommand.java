package Commands;

import Main.Ui;

public class DueCommand extends Command {
    String userInput;

    public DueCommand(String userInput) {

        this.userInput = userInput;
    }

    /**
     * Displays items due via method in storelist class
     *
     * @return a string
     */
    @Override
    public String execute() {
        return storeList.dueOnDate(userInput.substring(4).trim());
    }

    @Override
    public boolean isExit() {

        return false;
    }
}
