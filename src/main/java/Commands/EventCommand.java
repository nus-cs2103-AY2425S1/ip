package Commands;

public class EventCommand extends Command {
    String userInput;

    public EventCommand(String userInput) {

        this.userInput = userInput;
    }

    /**
     * Adds items to list via method in storelist class
     *
     * @return a string
     */
    @Override
    public String execute() {
        return storeList.addItem(userInput.substring(5), "event");
    }

    @Override
    public boolean isExit() {

        return false;
    }
}
