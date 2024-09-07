package citadel.commands;

import citadel.Task.Task;
import citadel.Task.TaskList;
import citadel.exception.CitadelException;
import citadel.exception.CitadelInvalidArgException;
import citadel.exception.CitadelTaskNoInput;

public class AddTag extends Command {
    /**
     * Constructs a new command with the specified input and task list.
     *
     * @param input The input string associated with the command.
     * @param tasks The task list that the command will operate on.
     */
    public AddTag(String input, TaskList tasks) {
        super(input, tasks);
    }

    @Override
    public String run() throws CitadelException {
        try {
            String taskNumWithTag = input.substring(7).trim();

            if (taskNumWithTag.isEmpty()) {
                throw new CitadelTaskNoInput();
            }

            String taskNum = taskNumWithTag.substring(0, 1);
            int index = Integer.parseInt(taskNum);
            Task task = tasks.get(index - 1);
            String tag = taskNumWithTag.substring(2).trim();

            if (tag.isEmpty()) {
                throw new CitadelTaskNoInput();
            }

            task.addTag(tag);

            String op = "Tag: " + tag + " has been added to task:"
                    + "\n" + task;
            System.out.println(op);
            return op;
        } catch (IndexOutOfBoundsException e) {
            throw new CitadelInvalidArgException();
        }
    }
}
