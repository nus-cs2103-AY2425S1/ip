package nimbus.command;

import java.util.ArrayList;

import nimbus.task.Task;
import nimbus.ui.TaskList;
import nimbus.ui.Ui;

/**
 * MarkCommand marks tasks as done when provided with an index
 */
public class MarkCommand extends Command {
    private final String userInput;
    private final ArrayList<Task> tasks;

    /**
     * Creates a MarkCommand Object
     * Stores userInput and allows taskList to be passed in
     *
     * @param userInput
     * @param taskList
     */
    public MarkCommand(String userInput, TaskList taskList) {
        super(userInput, taskList);
        this.userInput = userInput;
        this.tasks = taskList.getTaskList();
    }

    /**
     * checks if index given in the userInput is valid
     * marks task as completed if task is incomplete
     */
    @Override
    public String execute() {
        int x = Integer.parseInt(userInput.substring(5).trim());
        int index = x - 1;

        if (index >= tasks.size()) {
            return "There is no task " + (index + 1);
        } else if (tasks.get(index).isComplete()) {
            return "Already Marked";
        } else {
            tasks.get(index).setComplete();
            String output = "Nimbus.Nimbus shall mark this as done:\n"
                    + "    " + tasks.get(index).toString() + Ui.HORIZONTAL_LINE;
            return output;
        }
    }
}
