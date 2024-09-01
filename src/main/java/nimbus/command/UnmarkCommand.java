package nimbus.command;

import nimbus.task.Task;
import nimbus.ui.TaskList;
import nimbus.ui.Ui;

import java.util.ArrayList;

public class UnmarkCommand extends Command {
    private String userInput;
    private ArrayList<Task> tasks;

    public UnmarkCommand(String userInput, TaskList taskList) {
        super(userInput, taskList);
        this.userInput = userInput;
        this.tasks = taskList.getTaskList();
    }

    @Override
    public void execute() {
        int x = Integer.parseInt(userInput.substring(7).trim());
        int index = x - 1;

        if (index >= tasks.size()) {
            System.out.println("There is no task " + (index + 1));
        } else if (!tasks.get(index).isCompleted()) {
            System.out.println("Already Unmarked");
        } else if (index < tasks.size()) {
            tasks.get(index).incomplete();
            System.out.println("Nimbus.Nimbus shall mark this as not done:\n" +
                    "    " + tasks.get(index).toString() + Ui.horizontalLine);
        }
    }
}
