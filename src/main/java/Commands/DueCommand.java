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
     */
    @Override
    public void execute() {
        System.out.println(Ui.LINE);
        storeList.dueOnDate(userInput.substring(4).trim());
        System.out.println(Ui.LINE);
    }

    @Override
    public boolean isExit() {

        return false;
    }
}
