package alice.command;

import java.io.IOException;

import alice.storage.TaskList;
import alice.task.InvalidTaskException;
import alice.task.Task;

/** Command to tag task. */
public class TagTask extends Command {
    public TagTask(TaskList taskList) {
        super(taskList);
    }

    /**
     * Tags a task within a given tag description.
     *
     * Input should be: tag &lt;task number&gt; &lt;tag description&gt;
     *
     * @param input the input line from the user
     */
    @Override
    public String execute(String input) throws InvalidTaskException {
        String[] tokens = input.split(" ", 3);
        if (tokens.length != 3) {
            throw new InvalidTaskException("Invalid usage. Usage: tag <task number> <tag description>");
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException exception) {
            throw new InvalidTaskException("Invalid task number. Usage: tag <task number> <tag description>");
        }

        try {
            int index = taskNumber - 1;
            String taskDescription = tokens[2].trim();
            Task taggedTask = taskList.tagTask(index, taskDescription);
            assert taggedTask != null;
            return String.format("Nice! I've tagged this task:\n\t%s", taggedTask);
        } catch (IndexOutOfBoundsException exception) {
            throw new InvalidTaskException("Task number out of bounds. Usage: tag <task number> <tag description>");
        } catch (IOException exception) {
            throw new InvalidTaskException("Unable to save task.");
        }
    }
}
