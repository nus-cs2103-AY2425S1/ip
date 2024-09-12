package Commands;

import Main.Ui;

public class DeadlineCommand extends Command {
    String userInput;

    public DeadlineCommand(String userInput) {

        this.userInput = userInput;
    }


    /**
     * Adds items to list via method in storelist class
     *
     * @return a string
     */
    @Override
    public String execute() {
        return storeList.addItem(userInput.substring(8), "deadline");
    }

    @Override
    public boolean isExit() {

        return false;
    }
}
