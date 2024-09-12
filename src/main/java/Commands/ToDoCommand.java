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
     * @return
     */
    @Override
    public String execute() {
        return storeList.addItem(userInput.substring(4), "todo");
    }

    @Override
    public boolean isExit() {

        return false;
    }
}
