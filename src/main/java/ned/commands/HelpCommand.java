package ned.commands;

import ned.Storage;
import ned.TaskList;
import ned.Ui;

public class HelpCommand implements Command {
    private static final String REGEX = "help";

    @Override
    public void execute(TaskList taskList, Ui uiInstance, Storage storageInstance, String userInput) {
        uiInstance.addToNedDialogue(uiInstance.getCommandMessage());
    }

    @Override
    public String getRegex() {
        return REGEX;
    }
}
