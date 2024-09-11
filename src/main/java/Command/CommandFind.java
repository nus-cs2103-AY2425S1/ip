package command;

import java.util.ArrayList;
import java.util.stream.Collectors;

import blitz.Storage;
import blitz.TaskList;
import blitz.Ui;

import exception.BlitzEmptyTaskListException;
import exception.BlitzException;

import task.Task;

/**
 * Represents a "find" command in the Blitz application.
 */
public class CommandFind extends Command {
    private String parameter;

    /**
     * Constructs a new CommandFind object with specified command String and a parameter String.
     *
     * @param command Command String to be associated with this Command object.
     * @param parameter String containing the parameter for this command.
     */
    public CommandFind(String command, String parameter) {
        super(command);
        this.parameter = parameter;
    }

    /**
     * Executes the command.
     *
     * @param list TaskList to find the tasks containing the parameter.
     * @param ui Ui to print the required text.
     * @param storage Storage to be used if required.
     * @return Execution result of the command as String.
     * @throws BlitzException If TaskList is empty or no matching item found.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) throws BlitzException {
        if (list.isEmpty()) {
            throw new BlitzEmptyTaskListException();
        }

        ArrayList<Task> allTasks = list.getAllTask();

        TaskList matchedTasks = TaskList.convertStringListToTaskList(allTasks.stream()
                .map(Task::convertTaskToString)
                .filter(str -> str.contains(this.parameter))
                .collect(Collectors.toCollection(ArrayList::new)));

        if (matchedTasks.isEmpty()) {
            throw new BlitzEmptyTaskListException();
        }

        return ui.getStringForTasksMatched(matchedTasks);
    }
}
