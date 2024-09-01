package kai.commands;

import kai.KaiException;
import kai.TaskList;
import kai.Ui;

/**
 * This Command lists out the Tasks present when invoked
 */
public class ListCommand extends Command {
    private final TaskList taskList;

    /**
     * Constructs a ListCommand with the TaskList to list out
     *
     * @param taskList the TaskList to be listed out
     * @param ui the Ui used to display messages on screen
     */
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void invoke(Ui ui) {
        try {
            if (taskList.isEmpty()) {
                throw new KaiException("\t There are no tasks to list. Congratulations!");
            } else {
                System.out.println("\t Here are the tasks in your list:");
            }
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println("\t " + (i + 1) + ". " +
                        taskList.getTask(i).toString());
            }
        }
        catch (KaiException e) {
            System.out.println(e.getMessage());
        }
    }
}
