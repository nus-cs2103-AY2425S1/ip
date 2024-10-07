package citadel.commands;

import citadel.exception.CitadelException;
import citadel.exception.CitadelInvalidArgException;
import citadel.exception.CitadelTaskNoInput;
import citadel.task.Task;
import citadel.task.TaskList;

/**
 * Represents the command to remove a tag from a task from the task list in the Citadel application.
 * This class extends {@link Command} and provides the functionality to remove a tag from a task based on user input.
 */
public class RemoveTag extends Command {
    /**
     * Constructs a new command with the specified input and task list.
     *
     * @param input The input string associated with the command.
     * @param tasks The task list that the command will operate on.
     */
    public RemoveTag(String input, TaskList tasks) {
        super(input, tasks);
    }

    @Override
    public String run() throws CitadelException {
        try {
            String taskNumWithTag = input.substring(10).trim();

            if (taskNumWithTag.isEmpty()) {
                throw new CitadelTaskNoInput();
            }

            String taskNum = taskNumWithTag.substring(0, 1);
            int index = Integer.parseInt(taskNum);
            Task task = tasks.get(index - 1);
            String tagIndex = taskNumWithTag.substring(2).trim();
            int tagIndexInt = Integer.parseInt(tagIndex);

            if (tagIndex.isEmpty()) {
                throw new CitadelTaskNoInput();
            }

            String removedTag = task.removeTag(tagIndexInt - 1);
            String op = "Tag: " + removedTag + " has been removed from the task:"
                    + "\n" + task;
            System.out.println(op);
            return op;
        } catch (IndexOutOfBoundsException e) {
            throw new CitadelInvalidArgException();
        }
    }
}
