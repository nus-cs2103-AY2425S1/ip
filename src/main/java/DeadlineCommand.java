public class DeadlineCommand extends Command{
    String userInput;

    public DeadlineCommand(String userInput) {
        this.userInput = userInput;
    }


    /**
     * Adds items to list via method in storelist class
     *
     */
    @Override
    public void execute() {
        System.out.println(Ui.LINE);
        storeList.addItem(userInput.substring(8), "deadline");
        System.out.println(Ui.LINE);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
