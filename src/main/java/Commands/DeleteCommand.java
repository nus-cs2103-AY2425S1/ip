package Commands;

import Default.CommandManager;
import Default.Storage;
import Default.TaskList;
import Default.Ui;
import Exceptions.NedException;
import Tasks.Task;
import java.util.ArrayList;

public class DeleteCommand implements Command {
    private final String REGEX = "^delete.*";

    public DeleteCommand() {
    }

    @Override
    public void execute(TaskList taskList, Ui uiInstance, Storage storageInstance, String userInput) throws NedException {
        String[] words = userInput.split(" ");
        try {
            if (words.length != 2) {
                throw new NedException("Sorry m'lord, you must give me a list index with the delete command. No more," +
                        " no less" + uiInstance.getCommandMessage());
            } else {
                String possibleIndex = words[1];
                int index = Integer.parseInt(possibleIndex) - 1;
                taskList.removeTask(index, uiInstance);
                storageInstance.save(taskList);
            }
        } catch (NumberFormatException e) {
            throw new NedException("Sorry m'lord, your command must specify a valid number" + uiInstance.getCommandMessage());
        } catch (IndexOutOfBoundsException e) {
            throw new NedException("Sorry m'lord, your command must specify an index within the bounds of the list " +
                    "size" + uiInstance.getCommandMessage());
        }
    }

    @Override
    public String getRegex() {
        return this.REGEX;
    }
}
