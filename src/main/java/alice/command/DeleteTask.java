package alice.command;

import java.io.IOException;

import alice.storage.TaskList;
import alice.task.InvalidTaskException;
import alice.task.Task;

/** Command to delete task. */
public class DeleteTask extends Command {
    public DeleteTask(TaskList taskList) {
        super(taskList);
    }

    /**
     * Deletes a task from the taskList.
     *
     * Input should be: delete &lt;task number&gt;
     *
     * @param input the input line from the user
     */
    @Override
    public String execute(String input) throws InvalidTaskException {
        String[] tokens = input.split(" ", 2);
        if (tokens.length != 2) {
            throw new InvalidTaskException("Missing task number. Usage: delete <task number>");
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(tokens[1]);
        } catch (NumberFormatException exception) {
            throw new InvalidTaskException("Invalid task number. Usage: delete <task number>");
        }

        Task removedTask;
        try {
            int index = taskNumber - 1;
            removedTask = taskList.deleteTask(index);
            assert removedTask != null;
            return String.format("Noted. I've removed this task:\n\t%s", removedTask);
        } catch (IndexOutOfBoundsException exception) {
            throw new InvalidTaskException("Task number out of bounds. Usage: delete <task number>");
        } catch (IOException exception) {
            throw new InvalidTaskException("Unable to save task.");
        }
    }
}
