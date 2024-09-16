package thanos.commands;

import static thanos.utility.ResponseFormatter.generateTaskListResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import thanos.exceptions.InvalidCommandException;
import thanos.tasks.Task;
import thanos.tasks.TaskList;

/**
 * Represents a command to delete a task from the {@code TaskList}.
 */
public class DeleteCommand extends Command {
    /**
     * Constructs a {@code DeleteCommand} with the given argument.
     *
     * @param argument the argument associated with this command.
     */
    public DeleteCommand(String argument) {
        super(argument);
    }

    /**
     * Executes the command to delete one or more tasks from the {@code TaskList}.
     *
     * @param taskList the list of tasks from which the tasks will be removed.
     * @return a formatted string confirming the removal of the tasks and displaying the updated task count.
     * @throws InvalidCommandException if the argument is empty, incorrectly formatted, contains duplicate indices, or
     *                                 if any index is invalid (either not an integer or out of range).
     */
    @Override
    public String execute(TaskList taskList) throws InvalidCommandException {
        ArrayList<Integer> indices = getTaskIndices();
        indices.sort(Collections.reverseOrder());

        ArrayList<Task> deletedTasks = deleteTasks(taskList, indices);

        String response = generateTaskListResponse("Noted. I've removed these task(s):",
                deletedTasks.toArray(new Task[0]));
        return response + String.format("Now you have %d tasks in the list.\n", taskList.size());
    }

    /**
     * Parses the command argument to extract and return a list of task indices.
     *
     * @return an {@code ArrayList} of valid task indices.
     * @throws InvalidCommandException if the argument is empty, contains non-integer values or duplicate indices.
     */
    private ArrayList<Integer> getTaskIndices() throws InvalidCommandException {
        if (this.getArgument().isEmpty()) {
            throw new InvalidCommandException("No task index provided.");
        }

        String[] parts = this.getArgument().split(" ");
        ArrayList<Integer> indices = new ArrayList<>();
        HashSet<Integer> seenIndices = new HashSet<>();

        try {
            for (String part : parts) {
                int index = Integer.parseInt(part.trim()) - 1;
                if (!seenIndices.add(index)) {
                    throw new InvalidCommandException("Duplicate task index: " + (index + 1));
                }
                indices.add(index);
            }
            return indices;
        } catch (NumberFormatException e) {
            throw new InvalidCommandException("Invalid task index. The task index provided is not an integer.");
        }
    }

    /**
     * Removes a task from the {@code TaskList} based on the provided index.
     *
     * @param index the zero-based index of the task to be removed.
     * @param taskList the list of tasks from which the task will be removed.
     * @return the removed {@code Task}.
     * @throws InvalidCommandException if the index is out of range.
     */
    private Task deleteTask(int index, TaskList taskList) throws InvalidCommandException {
        try {
            return taskList.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidCommandException("Invalid task index. The task index provided is out of range.");
        }
    }

    /**
     * Deletes multiple tasks from the {@code TaskList} based on the provided indices.
     *
     * @param taskList the list of tasks from which the tasks will be removed.
     * @param indices a list of indices specifying which tasks to remove from the {@code TaskList}.
     * @return an {@code ArrayList<Task>} containing the tasks that were removed from the {@code TaskList}.
     * @throws InvalidCommandException if any of the provided indices are invalid or out of range
     */
    private ArrayList<Task> deleteTasks(TaskList taskList, ArrayList<Integer> indices) throws InvalidCommandException {
        ArrayList<Task> deletedTasks = new ArrayList<>();
        for (int index : indices) {
            deletedTasks.add(deleteTask(index, taskList));
        }
        return deletedTasks;
    }
}
