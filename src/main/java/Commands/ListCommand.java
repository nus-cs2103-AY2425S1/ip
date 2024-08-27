package Commands;

import Default.TaskList;
import Tasks.Task;
import Default.Ui;
import java.util.ArrayList;

public class ListCommand implements Command {
    private final String REGEX = "list";

    @Override
    public void execute(String userInput, TaskList taskList) {
        taskList.listTasks();
    }
    @Override
    public String getRegex() {
        return this.REGEX;
    }
}
