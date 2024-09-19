package nen.commands;

import nen.utils.TaskList;

/**
 *  This class represents a command which mark the task of the provided index as not done
 * @author Gan Ren Yick (A0276246X)
 */
public class UnmarkCommand extends Command {
    /**
     * Instantiates UnmarkCommand
     * @param name of the command
     */
    public UnmarkCommand(String name, String arg) {
        this.name = name;
        this.arg = Integer.parseInt(arg);
    }

    /**
     * Executes the command
     * @param taskList to be manipulated
     */
    @Override
    public void execute(TaskList taskList) {
        taskList.get(arg - 1).markAsNotDone();
        description += "OK, I've marked this task as not done yet:\n"
                + taskList.get(arg - 1).toString() + "\n";
    }

}
