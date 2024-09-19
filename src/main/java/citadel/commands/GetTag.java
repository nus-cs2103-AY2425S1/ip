package citadel.commands;

import citadel.exception.CitadelException;
import citadel.exception.CitadelInvalidArgException;
import citadel.exception.CitadelTaskNoInput;
import citadel.task.Task;
import citadel.task.TaskList;

import java.util.ArrayList;

/**
 * Represents the command to get all tags from a task from the task list in the Citadel application.
 * This class extends {@link Command} and provides the functionality to get all tags from a task based on user input.
 */
public class GetTag extends Command {
    /**
     * Constructs a new command with the specified input and task list.
     *
     * @param input The input string associated with the command.
     * @param tasks The task list that the command will operate on.
     */
    public GetTag(String input, TaskList tasks) {
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
            ArrayList<String> tagList = task.getTag();
            String listOfTags = "Below are the list of tags related to task:"
                                + "\n" + task + "\n";
            for (int i = 0; i < tagList.size(); i++) {
                String printString = String.format("%d. %s%n", i + 1, tagList.get(i));
                assert printString != null : "task string representation cannot be null";
                listOfTags = listOfTags + printString;
            }

            System.out.println(listOfTags);
            return listOfTags;
        } catch (IndexOutOfBoundsException e) {
            throw new CitadelInvalidArgException();
        }
    }
}
