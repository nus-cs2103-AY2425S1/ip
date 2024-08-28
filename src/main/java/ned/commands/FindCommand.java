package ned.commands;

import ned.Ned;
import ned.TaskList;
import ned.Ui;
import ned.Storage;
import ned.exceptions.NedException;

public class FindCommand implements Command {
    private final String REGEX = "^find.*";

    @Override
    public void execute(TaskList taskList, Ui uiInstance, Storage storageInstance, String userInput) throws NedException{
        String[] splitInput = userInput.split(" ");
        if (splitInput.length < 1) {
            throw new NedException("Sorry m'lord, it seems that your find command is missing a search term.");
        }
        String parsedInput = splitInput[1];
        TaskList listOfRelatedTasks = taskList.findRelatedTasks(parsedInput);
        listOfRelatedTasks.listTasks(uiInstance);
    }

    @Override
    public String getRegex() {
        return this.REGEX;
    }
}
