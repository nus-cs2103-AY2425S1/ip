package ned.commands;

import ned.Storage;
import ned.TaskList;
import ned.Ui;

public class ListCommand implements Command {
    private final String REGEX = "list";

    @Override
    public void execute(TaskList taskList, Ui uiInstance, Storage storageInstance, String userInput) {
        taskList.listTasks(uiInstance);
    }
    @Override
    public String getRegex() {
        return this.REGEX;
    }
}
