package ned.commands;

import ned.Storage;
import ned.TaskList;
import ned.Ui;
import ned.exceptions.NedException;

public class MarkCommand implements Command {

    private final String REGEX = "^mark.*";
    public MarkCommand() {}
    @Override
    public void execute(TaskList taskList, Ui uiInstance, Storage storageInstance, String userInput) throws NedException {
        String[] words = userInput.split(" ");
        if (words.length != 2) {
            throw new NedException("Sorry m'lord, you must give me a list index with the mark command. No more, no " +
                    "less" + uiInstance.getCommandMessage());
        }
        String possibleIndex = words[1];
        try {
            int index = Integer.parseInt(possibleIndex) - 1;
            taskList.markTaskAsDone(index, uiInstance);
            storageInstance.save(taskList);
        } catch (NumberFormatException e) {
            throw new NedException("Sorry m'lord, your command must specify a valid number" + uiInstance.getCommandMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new NedException("Sorry m'lord, seems the item number you specified is not valid" + uiInstance.getCommandMessage());
        }
    }

    @Override
    public String getRegex() {
        return this.REGEX;
    }
}
