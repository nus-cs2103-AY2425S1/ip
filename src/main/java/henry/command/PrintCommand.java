package henry.command;

import henry.util.Ui;
import henry.util.TaskList;
import henry.util.Storage;
import henry.HenryException;

/**
 * Deals with printing tasks recorded in TaskList
 */
public class PrintCommand extends Command{

    /**
     * Prints all the tasks recorded
     *
     * @param taskList instance of a TaskList class that contains
     *                 an array of tasks
     * @param ui instance of a Ui class that interacts with the user
     */
    public void execute(TaskList taskList, Ui ui) throws HenryException {
        //check if there is any task to print
        int numOfTasks = taskList.getTasks().size();
        if (numOfTasks == 0) {
            throw new HenryException("You do not have any tasks!");
        }
        System.out.println("\nHere are the tasks in your list:");
        for (int i = 0; i < numOfTasks; i++) {
            System.out.println(i + 1
                    + "."
                    + taskList.getTasks().get(i).toString());
        }
        System.out.println();
    }

}
