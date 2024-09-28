package oyster.commands;

import oyster.LogicController;
import oyster.tasks.TaskList;

/**
 * ListCommand populates its message with TaskList when executed.
 */
public class ListCommand extends Command {
    /**
     * Creates a ListCommand that populates itself with a message of the TaskList when executed.
     */
    public ListCommand() {

    }

    /**
     * Populates the message of the Command with the TaskList when executed.
     */
    @Override
    public void execute() {
        TaskList taskList = LogicController.getTaskList();

        String[] message;
        if (taskList.isEmpty()) {
            setMessage("Oops, nothing to see here!");
        } else {
            setMessage(new String[] {
                "Here is your current list!",
                taskList.toString()
            });
        }
    }
}
