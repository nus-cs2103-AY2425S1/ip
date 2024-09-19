package alice.command;

import java.io.IOException;

import alice.storage.TaskList;
import alice.task.InvalidTaskException;
import alice.task.Task;

/** Command to mark task. */
public class MarkTask extends Command {
    public MarkTask(TaskList taskList) {
        super(taskList);
    }

    /**
     * Marks a task as complete.
     *
     * Input should be: mark &lt;task number&gt;
     *
     * @param input the input line from the user
     */
    @Override
    public String execute(String input) throws InvalidTaskException {
        String[] tokens = input.split(" ", 2);
        if (tokens.length != 2) {
            throw new InvalidTaskException("Missing task number. Usage: mark <task number>");
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException exception) {
            throw new InvalidTaskException("Invalid task number. Usage: mark <task number>");
        }

        try {
            int index = taskNumber - 1;
            Task markedTask = taskList.markTask(index);
            assert markedTask != null;
            return String.format("Nice! I've marked this task as done:\n\t%s", markedTask);
        } catch (IndexOutOfBoundsException exception) {
            throw new InvalidTaskException("Task number out of bounds. Usage: mark <task number>");
        } catch (IOException exception) {
            throw new InvalidTaskException("Unable to save task.");
        }
    }
}
