package Commands;

import Main.Ui;

public class ToDoCommand extends Command {
    String userInput;

    public ToDoCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Adds items to list via method in storelist class
     *
     */
    @Override
    public void execute() {
        System.out.println(Ui.LINE);
        storeList.addItem(userInput.substring(4), "todo");
        System.out.println(Ui.LINE);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
