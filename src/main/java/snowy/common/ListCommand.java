package snowy.common;

import snowy.data.SnowyException;

public class ListCommand extends Command {

    /**
     * Executes the command to display all tasks in the task list.
     *
     * @return a CommandResult indicating that the tasks have been displayed
     * @throws SnowyException if there is an error displaying the task list
     */
    @Override
    public CommandResult execute() throws SnowyException {
        taskList.displayList();
        return new CommandResult("Displayed all tasks");
    }
}
