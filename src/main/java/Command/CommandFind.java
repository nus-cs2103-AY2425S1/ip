package command;

/* My import */
import blitz.Storage;
import blitz.TaskList;
import blitz.Ui;

import exception.BlitzEmptyTaskListException;
import exception.BlitzException;

import task.Task;

import java.util.ArrayList;

public class CommandFind extends Command {
    private String param;

    /**
     * Constructs a new CommandFind object with specified command String and a parameter String.
     *
     * @param command Command String to be associated with this Command object.
     * @param param String containing the parameter for this command.
     */
    public CommandFind(String command, String param) {
        super(command);
        this.param = param;
    }

    /**
     * Executes the command.
     *
     * @param list TaskList to find the tasks containing the parameter.
     * @param ui Ui to print the required text.
     * @param storage Storage to be used if required.
     * @throws BlitzException If TaskList is empty or no matching item found.
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws BlitzException {
        ArrayList<Task> allTasks = list.getAllTask();
        TaskList found = new TaskList(new ArrayList<>());

        for (Task task : allTasks) {
            if (task.convertTaskToString().contains((this.param))) {
                found.addTask(task);
            }
        }

        if (found.getSize() == 0) {
            throw new BlitzEmptyTaskListException();
        }

        String[] toPrint = new String[found.getSize() + 1];
        toPrint[0] = "Here are the matching tasks in your list:";

        for (int i = 0; i < found.getSize(); i++) {
            Task curr = found.getTask(i);
            toPrint[i + 1] = "    " + (i + 1) + ".[" + curr.getType() + "]" + "[" + (curr.getStatus() ? "X" : " ") + "] " + curr;
        }

        ui.printInDivider(toPrint);
    }
}
