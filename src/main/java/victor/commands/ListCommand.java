package victor.commands;

import victor.messages.ReturnMessage;

/**
 * List command class that extends Command class, checks if any tasks exist and returns
 * enumerated version of tasks from taskList.
 */
public class ListCommand extends Command {
    public ListCommand(String[] additionalInput) {
        super(additionalInput);
    }

    /**
     * Overrides the execute method from the Command class. Processes user input and either returns
     * the list of tasks currently stored or a response telling the user there are no tasks in the list.
     * @return A return message with the delete action summary (successful) or a prompt to the user (unsuccessful).
     */
    @Override
    public ReturnMessage execute() {
        if (taskList.getSize() == 0) {
            return new ReturnMessage("  ~  No tasks in the list, add some To Dos, Events,"
                + " and Deadlines first :)");
        }
        return new ReturnMessage(super.taskList.enumerateTasks());
    }
}
