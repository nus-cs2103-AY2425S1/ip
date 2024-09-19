package nen.commands;

import nen.utils.TaskList;

/**
 *  This class represents a end command that do nothing
 * @author Gan Ren Yick (A0276246X)
 */
public class DoNothingCommand extends Command {

    /**
     * Instantiate DoNothingCommand
     * @param description of the command
     */
    public DoNothingCommand(String description) {
        this.description = description;
        this.name = "doNothing";
    }

    /**
     * Execute the command
     * @param taskList to be manipulated
     */
    @Override
    public void execute(TaskList taskList) {
        return;
    }

}
