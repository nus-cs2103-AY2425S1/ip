package command;

import blitz.Storage;
import blitz.TaskList;
import blitz.Ui;
import exception.BlitzEmptyTaskListException;
import exception.BlitzException;
import task.Task;

import java.util.ArrayList;

public class CommandFind extends Command {
    private String param;

    public CommandFind(String command, String param) {
        super(command);
        this.param = param;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws BlitzException {
        ArrayList<Task> allTasks = list.getAllTask();
        TaskList found = new TaskList(new ArrayList<>());

        for (Task task : allTasks) {
            if (task.taskToString().contains((this.param))) {
                found.addTask(task);
            }
        }

        if (found.size() == 0) {
            throw new BlitzEmptyTaskListException();
        }

        String[] toPrint = new String[found.size() + 1];
        toPrint[0] = "Here are the matching tasks in your list:";

        for (int i = 0; i < found.size(); i++) {
            Task curr = found.getTask(i);
            toPrint[i + 1] = "    " + (i + 1) + ".[" + curr.getType() + "]" + "[" + (curr.getStatus() ? "X" : " ") + "] " + curr;
        }

        ui.printInDivider(toPrint);
    }
}
