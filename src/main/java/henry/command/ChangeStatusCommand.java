package henry.command;

import henry.HenryException;
import henry.task.Task;
import henry.util.TaskList;
import henry.util.Ui;

/**
 * Deals with marking and unmarking tasks.
 */
public class ChangeStatusCommand extends Command {
    private String[] inputList;

    public ChangeStatusCommand(String[] inputList) {
        this.inputList = inputList;
    }

    /**
     * Returns a string telling user that the status has been changed.
     *
     * @param taskList Instance of a TaskList class that contains
     *                 an array of tasks.
     * @param ui Instance of an Ui class that interacts with the user.
     * @return A string telling user that task is marked or unmarked
     *                 depending on user input.
     * @throws HenryException If user input is not a number.
     */
    public String execute(TaskList taskList, Ui ui) throws HenryException {
        checkValidCommand(this.inputList);

        int numOfTasks = taskList.getTasks().size();
        //check for invalid number
        try {
            Task task = getTask(taskList, numOfTasks);
            if ((this.inputList)[0].equals("mark")) {
                return markTask(task);
            } else {
                assert (this.inputList)[0].equals("unmark");
                return unmarkTask(task);
            }
        } catch (NumberFormatException e) {
            throw new HenryException("This is not a number!!");
        }
    }

    /**
     * Validates the user's command input to ensure it contains at least one task.
     *
     * @param inputList The array of command input strings to be validated.
     * @throws HenryException If the input does not contain enough arguments to mark or unmark a task.
     */
    private void checkValidCommand(String[] inputList) throws HenryException {
        if (inputList.length <= 1) {
            throw new HenryException("You need to input at least one task to be marked or unmarked! "
                    + "Example: mark 2");
        }
    }

    /**
     * Returns a string telling user that the specified task is unmarked if it is not already unmarked.
     *
     * @param task A Task object that is stored in taskList.
     * @return A confirmation message indicating that the task has been successfully unmarked.
     * @throws HenryException If task is already unmarked.
     */
    private static String unmarkTask(Task task) throws HenryException {
        //check if task is already unmarked
        if (!task.isDone()) {
            throw new HenryException("The task is already unmarked!");
        }
        task.unmark();
        return "OK, I've marked this task as not done yet:\n"
                + task
                + "\n";
    }

    /**
     * Returns a string telling user that the specified task is marked if it is not already marked.
     *
     * @param task The Task object to be marked as done.
     * @return A confirmation message indicating that the task has been successfully marked.
     * @throws HenryException If the task is already marked as done.
     */
    private static String markTask(Task task) throws HenryException {
        //check if task is already marked
        if (task.isDone()) {
            throw new HenryException("The task is already marked!");
        }
        task.mark();
        return "Nice! I've marked this task as done:\n"
                + task
                + "\n";
    }

    /**
     * Returns the task that user wants to mark or unmark.
     *
     * @param taskList Instance of a TaskList class that contains
     *                 an array of tasks.
     * @param numOfTasks Number of tasks recorded in taskList object.
     * @return The task that user is asking for.
     * @throws HenryException If number is below 1 or higher than the number of tasks in taskList.
     */
    private Task getTask(TaskList taskList, int numOfTasks) throws HenryException {
        int number = Integer.parseInt((this.inputList)[1]);
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
        return taskList.getTasks().get(number - 1);
    }
}
