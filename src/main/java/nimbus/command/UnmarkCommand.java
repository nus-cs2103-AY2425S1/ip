package nimbus.command;

import java.util.ArrayList;

import nimbus.task.Task;
import nimbus.ui.TaskList;
import nimbus.ui.Ui;

/**
 * UnmarkCommand marks tasks as done when provided with an index
 */
public class UnmarkCommand extends Command {
    private final String userInput;
    private final ArrayList<Task> tasks;

    /**
     * Creates a UnmarkCommand Object
     * Stores userInput and allows taskList to be passed in
     *
     * @param userInput user input about task
     * @param taskList taskList object getting passed to add task in
     */
    public UnmarkCommand(String userInput, TaskList taskList) {
        super(userInput, taskList);
        this.userInput = userInput;
        this.tasks = taskList.getTaskList();
    }

    /**
     * checks if index given in the userInput is valid
     * marks task as incomplete if task is completed
     */
    @Override
    public String execute() {
        assert tasks != null : "Task list should not be null";
        int x = Integer.parseInt(userInput.substring(7).trim());
        int index = x - 1;

        assert index >= 0 : "Index should not be negative";

        if (index >= tasks.size()) {
            return "There is no task " + (index + 1);
        } else if (!tasks.get(index).isComplete()) {
            return "Already Unmarked";
        } else {
            tasks.get(index).setIncomplete();
            String output = "Nimbus shall mark this as not done:\n"
                    + "    " + tasks.get(index).toString() + Ui.HORIZONTAL_LINE;
            System.out.println(output);
            return output;
        }
    }
}
