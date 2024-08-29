package Commands;

import Main.Ui;

public class EventCommand extends Command {
    String userInput;

    public EventCommand(String userInput) {

        this.userInput = userInput;
    }

    /**
     * Adds items to list via method in storelist class
     *
     */
    @Override
    public void execute() {
        System.out.println(Ui.LINE);
        storeList.addItem(userInput.substring(5), "event");
        System.out.println(Ui.LINE);
    }

    @Override
    public boolean isExit() {

        return false;
    }
}
