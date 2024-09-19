package nen.commands;

import nen.exceptions.ArgumentMissingException;
import nen.exceptions.DateTimeFormatIncorrectException;
import nen.exceptions.EmptyDescriptionException;
import nen.exceptions.InvalidInputException;
import nen.tasks.Task;
import nen.utils.TaskList;

/**
 *  This class represents a end command which turn off Nen2
 * @author Gan Ren Yick (A0276246X)
 */
public class AddTaskCommand extends Command {

    /**
     * Instantiates AddTaskCommand
     * @param name of the command
     */
    public AddTaskCommand(String name) {
        this.name = name;
    }

    /**
     * Executes the command
     * @param taskList to be manipulated
     */
    @Override
    public void execute(TaskList taskList) {
        try {
            Task t = Task.of(name);
            taskList.add(t);
            description += "Got it. I've added this task: \n" + t + "\n"
                    + "Now you have " + taskList.size()
                    + " tasks in the list." + "\n";
            name = "add";
        } catch (InvalidInputException
                     | ArgumentMissingException
                     | EmptyDescriptionException
                     | DateTimeFormatIncorrectException e) {
            description = e.getMessage() + "\n";
        }
    }

}
