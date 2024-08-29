public class DeleteCommand extends Command{
    String userInput;

    public DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * deletes item in list via method in storelist class
     *
     */
    @Override
    public void execute() {
        // Split the string by spaces
        String[] words = userInput.split(" ");
        System.out.println(Ui.LINE);
        int itemNum = Integer.parseInt(words[1]);
        storeList.deleteItem(itemNum);
        System.out.println(Ui.LINE);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
