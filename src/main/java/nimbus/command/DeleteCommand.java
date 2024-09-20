package nimbus.command;

import java.util.ArrayList;

import nimbus.task.Task;
import nimbus.ui.TaskList;
import nimbus.ui.Ui;

/**
 * DeleteCommand deletes particular task entries requested by user
 */

public class DeleteCommand extends Command {
    private final String userInput;
    private final ArrayList<Task> tasks;
    private final TaskList taskList;

    /**
     * Creates a DeleteCommand Object
     * Stores userInput and allows taskList to be passed in
     *
     * @param userInput
     * @param taskList
     */

    public DeleteCommand(String userInput, TaskList taskList) {
        super(userInput, taskList);
        this.userInput = userInput;
        this.tasks = taskList.getTaskList();
        this.taskList = taskList;
    }

    /**
     * Removes a task from the list based on the index in userInput
     */
    @Override
    public String execute() {
        assert tasks != null : "Task list should not be null";
        if (userInput.length() <= 7) {
            return "Please enter the task number!!";
        }

        int x = Integer.parseInt(userInput.substring(7).trim());
        int index = x - 1;

        assert index >= 0 : "Task index should not be negative";

        if (index >= tasks.size()) {
            return "There is no task " + (index + 1);
        } else {
            String temp = tasks.get(index).toString();
            Task task = tasks.get(index);

            assert task != null : "Task should not be null";

            taskList.delete(task);
            String output = "Nimbus has removed the task! \n"
                    + "    " + temp + "\n" + "You have " + tasks.size()
                    + " tasks left!" + Ui.HORIZONTAL_LINE;
            System.out.println(output);
            return output;
        }
    }
}
