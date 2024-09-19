package nen.commands;

import nen.utils.TaskList;

/**
 *  This class represents a command which delete the task of the provided index
 * @author Gan Ren Yick (A0276246X)
 */
public class DeleteCommand extends Command {
    /**
     * Instantiates DeleteCommand
     * @param name of the command
     */
    public DeleteCommand(String name, String arg) {
        this.name = name;
        this.arg = Integer.parseInt(arg);
    }

    /**
     * Executes the command
     * @param taskList to be manipulated
     */
    @Override
    public void execute(TaskList taskList) {
        description = "Noted. I've removed this task:\n"
                + taskList.get(arg - 1).toString() + "\n";
        taskList.remove(arg - 1);
        description += "Now you have " + taskList.size() + " tasks in the list." + "\n";
    }

}
