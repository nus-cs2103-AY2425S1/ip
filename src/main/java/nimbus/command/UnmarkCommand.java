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
     * @param userInput
     * @param taskList
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
    public void execute() {
        int x = Integer.parseInt(userInput.substring(7).trim());
        int index = x - 1;

        if (index >= tasks.size()) {
            System.out.println("There is no task " + (index + 1));
        } else if (!tasks.get(index).isComplete()) {
            System.out.println("Already Unmarked");
        } else if (index < tasks.size()) {
            tasks.get(index).setIncomplete();
            System.out.println("Nimbus.Nimbus shall mark this as not done:\n"
                    + "    " + tasks.get(index).toString() + Ui.HORIZONTAL_LINE);
        }
    }
}
