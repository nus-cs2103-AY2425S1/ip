package Commands;

import Exceptions.InvalidIndexException;

public class UpdateCommand extends Command {
    String userInput;

    public UpdateCommand(String userInput) {

        this.userInput = userInput;
    }


    @Override
    public String execute() throws InvalidIndexException {
        // Split the string by /to
        String[] words = userInput.split(" /to ");

        //Split first half by spaces to get update and num
        String[] words1 = words[0].split(" ");

        String num = words1[1];
        int itemNum = Integer.parseInt(num);

        String newValue = words[2];
        return storeList.updateTask(itemNum, newValue);
    }

    @Override
    public boolean isExit() {

        return false;
    }
}
