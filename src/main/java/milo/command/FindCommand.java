package milo.command;

import milo.lists.ClientsList;
import milo.lists.TaskList;
import milo.tasks.TaskTypes;
import milo.ui.ClientUi;
import milo.ui.TaskUi;

public class FindCommand extends Command {

    private TaskList matchingTaskList;

    private final String[] arrOfInput;

    public FindCommand(String[] arrOfInput) {
        this.arrOfInput = arrOfInput;
    }

    @Override
    public void execute(TaskList taskList, ClientsList clientsList) {
        // Check for proper formatting
        if (arrOfInput.length != 2) {
            super.hasError = true;
            super.errorDesc = "The description of a find cannot be empty";
            this.matchingTaskList = new TaskList();
        } else {
            String desc = arrOfInput[1].strip();
            this.matchingTaskList = taskList.findMatchingTask(desc);
        }
    }

    @Override
    public String commandToString(TaskUi ui, ClientUi cUi, TaskList taskList, ClientsList clientsList) {
        if (super.hasError) {
            return ui.printError(TaskTypes.TaskType.TODO, super.errorDesc);
        }
        return ui.printFoundTask(matchingTaskList, matchingTaskList.getNumberOfTasks());
    }
}
