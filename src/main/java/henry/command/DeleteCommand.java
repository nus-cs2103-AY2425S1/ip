package henry.command;

import henry.HenryException;
import henry.task.Task;
import henry.util.TaskList;
import henry.util.Ui;

/**
 * Deals with deleting task for TaskList
 */
public class DeleteCommand extends Command {
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Returns a string telling user that task is deleted
     *
     * @param taskList instance of a TaskList class that contains
     *                 an array of tasks
     * @param ui instance of an Ui class that interacts with the user
     * @return a string mentioning that task is deleted
     */
    public String execute(TaskList taskList, Ui ui) throws HenryException {
        int numOfTasks = taskList.getTasks().size();
        //check for invalid number
        try {
            int number = getNumber(numOfTasks);
            Task task = taskList.getTasks().get(number - 1);
            taskList.getTasks().remove(number - 1);
            return "\nNoted. I've removed this task:\n"
                    + task.toString()
                    + "\nNow you have "
                    + (numOfTasks - 1)
                    + (numOfTasks - 1 <= 1 ? " task" : " tasks")
                    + " in the list.\n";
        } catch (NumberFormatException e) {
            throw new HenryException("This is not a number!!");
        }
    }

    /**
     * Returns the task number that user wants to delete
     *
     * @param numOfTasks number of tasks recorded in taskList object
     * @return the task number that user is asking for
     */
    private int getNumber(int numOfTasks) throws HenryException {
        int number = Integer.parseInt(this.input);
        //check if number is out of range
        if (number > numOfTasks) {
            throw new HenryException("There "
                    + (numOfTasks <= 1 ? "is " : "are ")
                    + "only "
                    + numOfTasks
                    + (numOfTasks <= 1 ? " task" : " tasks")
                    + "!");
        } else if (number <= 0) {
            throw new HenryException("Number must be greater than zero!");
        }
        return number;
    }
}
