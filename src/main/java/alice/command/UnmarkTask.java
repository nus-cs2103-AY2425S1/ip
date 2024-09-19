package alice.command;

import java.io.IOException;

import alice.storage.TaskList;
import alice.task.InvalidTaskException;
import alice.task.Task;

/** Command to unmark task. */
public class UnmarkTask extends Command {
    public UnmarkTask(TaskList taskList) {
        super(taskList);
    }

    /**
     * Marks a task as incomplete.
     *
     * Input should be: unmark &lt;task number&gt;
     *
     * @param input the input line from the user
     */
    @Override
    public String execute(String input) throws InvalidTaskException {
        String[] tokens = input.split(" ", 2);
        if (tokens.length != 2) {
            throw new InvalidTaskException("Missing task number. Usage: unmark <task number>");
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException exception) {
            throw new InvalidTaskException("Invalid task number. Usage: unmark <task number>");
        }

        try {
            int index = taskNumber - 1;
            Task unmarkedTask = taskList.unmarkTask(index);
            assert unmarkedTask != null;
            return String.format("OK, I've marked this task as not done yet:", unmarkedTask);
        } catch (IndexOutOfBoundsException exception) {
            throw new InvalidTaskException("Task number out of bounds. Usage: unmark <task number>");
        } catch (IOException exception) {
            throw new InvalidTaskException("Unable to save task.");
        }
    }
}
