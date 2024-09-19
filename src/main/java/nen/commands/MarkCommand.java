package nen.commands;

import nen.utils.TaskList;

/**
 *  This class represents a command which mark the task of the provided index as done
 * @author Gan Ren Yick (A0276246X)
 */
public class MarkCommand extends Command {
    /**
     * Instantiates MarkCommand
     * @param name of the command
     */
    public MarkCommand(String name, String arg) {
        this.name = name;
        this.arg = Integer.parseInt(arg);
    }

    /**
     * Executes the command
     * @param taskList to be manipulated
     */
    @Override
    public void execute(TaskList taskList) {
        taskList.get(arg - 1).markAsDone();
        description += "Nice! I've marked this task as done:\n"
                + taskList.get(arg - 1).toString() + "\n";
    }

}
