package milo.command;

import milo.tasks.TaskList;
import milo.tasks.TaskTypes;
import milo.ui.Ui;

public class FindCommand extends Command {

    private TaskList matchingTaskList;

    private final String[] arrOfInput;

    public FindCommand(String[] arrOfInput) {
        this.arrOfInput = arrOfInput;
    }

    @Override
    public void execute(TaskList taskList) {
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
    public String commandToString(Ui ui, TaskList taskList) {
        if (super.hasError) {
            return ui.printError(TaskTypes.TaskType.TODO, super.errorDesc);
        }
        return ui.printFoundTask(matchingTaskList, matchingTaskList.getNumberOfTasks());
    }
}
