package Commands;

public class ListCommand extends Command {
    String userInput;

    public ListCommand(String userInput) {

        this.userInput = userInput;
    }

    /**
     * Displays items in list via method in storelist class
     *
     * @return
     */
    @Override
    public String execute() {
        return storeList.displayItems();
    }

    @Override
    public boolean isExit() {

        return false;
    }
}
