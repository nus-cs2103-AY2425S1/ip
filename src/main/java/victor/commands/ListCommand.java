package victor.commands;

import victor.messages.ReturnMessage;

public class ListCommand extends Command {
    public ListCommand(String[] additionalInput) {
        super(additionalInput);
    }
    @Override
    public ReturnMessage execute() {
        if (taskList.getSize() == 0) {
            return new ReturnMessage("  ~  No tasks in the list, add some To Dos, Events,"
                + " and Deadlines first :)");
        } else {
            return new ReturnMessage(super.taskList.enumerateTasks());
        }
    }
}
