package barney.action.commands;

import java.util.HashMap;

import barney.data.TaskList;
import barney.data.exception.InvalidArgumentException;
import barney.data.task.DeadlineTask;
import barney.ui.Ui;

/**
 * Represents a command for creating a deadline task.
 * 
 * This command is used to create a deadline task with the specified arguments.
 */
public class DeadlineCommand extends Command {
    HashMap<String, String> argumentMap;

    /**
     * Represents a command for creating a deadline task.
     *
     * This command is used to create a deadline task with the specified arguments.
     * The arguments are provided as a HashMap, where the key represents the
     * argument name and the value represents the argument value.
     *
     * Example usage: HashMap<String, String> argumentMap = new HashMap<>();
     * argumentMap.put("taskName", "Finish report"); argumentMap.put("dueDate",
     * "2022-12-31"); DeadlineCommand deadlineCommand = new
     * DeadlineCommand(argumentMap);
     *
     * @param argumentMap A HashMap containing the arguments for creating the
     *                    deadline task.
     */
    public DeadlineCommand(HashMap<String, String> argumentMap) {
        super("deadline");
        this.argumentMap = argumentMap;
    }

    /**
     * Executes the DeadlineCommand.
     *
     * This method is responsible for executing the DeadlineCommand. It adds a new
     * DeadlineTask to the TaskList with the provided description and deadline. It
     * also prints a message to the user indicating that the task has been added
     * successfully.
     *
     * @param tasks The TaskList object that stores the list of tasks.
     * @param ui    The Ui object that handles user interface interactions.
     * @return true if the command is executed successfully, false otherwise.
     * @throws InvalidArgumentException if the argumentMap is invalid.
     */
    @Override
    public boolean execute(TaskList tasks, Ui ui) throws InvalidArgumentException {
        verifyFlags(argumentMap);

        String description = argumentMap.get("description");
        String by = argumentMap.get("by");

        DeadlineTask newDeadline = new DeadlineTask(description, by);
        tasks.add(newDeadline);
        ui.printAddedTask(newDeadline, tasks.size());

        return true;
    }
}
