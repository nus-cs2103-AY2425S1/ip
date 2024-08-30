package snowy.common;

import snowy.data.SnowyException;

public class ListCommand extends Command {

    @Override
    public CommandResult execute() throws SnowyException {
        taskList.displayList();
        return new CommandResult("Displayed all tasks");
    }
}
