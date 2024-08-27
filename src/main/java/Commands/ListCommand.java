package Commands;

import Default.Storage;
import Default.TaskList;
import Tasks.Task;
import Default.Ui;
import java.util.ArrayList;

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
