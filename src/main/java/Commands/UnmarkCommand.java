package Commands;

import Main.Ui;

public class UnmarkCommand extends Command {
    String userInput;

    public UnmarkCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Marks item as incomplete
     *
     */
    @Override
    public void execute() {
        // Split the string by spaces
        String[] words = userInput.split(" ");
        System.out.println(Ui.LINE);
        int itemNum = Integer.parseInt(words[1]);
        storeList.UnmarkItem(itemNum);
        System.out.println(Ui.LINE);
    }

    @Override
    public boolean isExit() {

        return false;
    }
}
