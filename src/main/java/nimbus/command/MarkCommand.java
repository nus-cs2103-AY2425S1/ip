package nimbus.command;

import nimbus.task.Task;
import nimbus.ui.TaskList;
import nimbus.ui.Ui;

import java.util.ArrayList;

/**
 * MarkCommand marks tasks as done when provided with an index
 */

public class MarkCommand extends Command {
    private String userInput;
    private ArrayList<Task> tasks;

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
    public void execute() {
        int x = Integer.parseInt(userInput.substring(5).trim());
        int index = x - 1;

        if (index >= tasks.size()) {
            System.out.println("There is no task " + (index + 1));
        } else if (tasks.get(index).isComplete()) {
            System.out.println("Already Marked");
        } else if (index < tasks.size()) {
            tasks.get(index).setComplete();
            System.out.println("Nimbus.Nimbus shall mark this as done:\n" +
                    "    " + tasks.get(index).toString() + Ui.horizontalLine);
        }
    }
}
