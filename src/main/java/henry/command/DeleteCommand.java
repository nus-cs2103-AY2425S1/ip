package henry.command;

import henry.HenryException;
import henry.task.Task;
import henry.util.TaskList;
import henry.util.Ui;

/**
 * Deals with deleting task for TaskList.
 */
public class DeleteCommand extends Command {
    private String[] input;

    public DeleteCommand(String[] input) {
        this.input = input;
    }

    /**
     * Returns a string telling user that task is deleted.
     *
     * @param taskList Instance of a TaskList class that contains
     *                 an array of tasks.
     * @param ui Instance of an Ui class that interacts with the user.
     * @return A string mentioning that task is deleted.
     * @throws HenryException If user input is not a number.
     */
    public String execute(TaskList taskList, Ui ui) throws HenryException {
        int numOfTasks = taskList.getTasks().size();
        //check for invalid number
        try {
            if (this.input.length == 1) {
                throw new HenryException("The delete description is wrong!! "
                        + "Ensure that you include the number you want to delete "
                        + "or write \"delete all\" if you want to clear the list. "
                        + "Example: delete 2");
            }

            if (this.input[1].equals("all")) {
                taskList.getTasks().clear();
                return "Noted. I've removed all the tasks.\n"
                        + "Now you have 0 task in the list.\n";
            }

            int number = getNumber(numOfTasks);
            Task task = taskList.getTasks().get(number - 1);
            taskList.getTasks().remove(number - 1);
            return "Noted. I've removed this task:\n"
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
     * Returns the task number that user wants to delete.
     *
     * @param numOfTasks Number of tasks recorded in taskList object.
     * @return The task number that user is asking for.
     * @throws HenryException If number is below 1 or higher than the number of tasks in taskList.
     */
    private int getNumber(int numOfTasks) throws HenryException {
        int number = Integer.parseInt(this.input[1]);
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
