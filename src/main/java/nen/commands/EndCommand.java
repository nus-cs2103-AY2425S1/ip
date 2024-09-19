package nen.commands;

import nen.utils.TaskList;

/**
 *  This class represents a end command which turn off Nen2
 * @author Gan Ren Yick (A0276246X)
 */
public class EndCommand extends Command {

    /**
     * Instantiates EndCommand
     * @param name of the command
     */
    public EndCommand(String name) {
        this.name = name;
        description = "Bye. Hope to see you again soon!";
    }

    /**
     * Executes the command
     * @param taskList to be manipulated
     */
    @Override
    public void execute(TaskList taskList) {
        return;
    }

    /**
     * @return is end command
     */
    @Override
    public boolean isEnd() {
        return true;
    }

}
