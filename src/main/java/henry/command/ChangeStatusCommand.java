package henry.command;

import henry.HenryException;
import henry.task.Task;
import henry.util.TaskList;
import henry.util.Ui;

/**
 * Deals with marking and unmarking tasks
 */
public class ChangeStatusCommand extends Command {
    private String[] inputList;

    public ChangeStatusCommand(String[] inputList) {
        this.inputList = inputList;
    }

    /**
     * Returns a string telling user that the status has been changed
     *
     * @param taskList instance of a TaskList class that contains
     *                 an array of tasks
     * @param ui instance of a Ui class that interacts with the user
     * @return a string telling user that task is marked or unmarked
     *                 depending on user input
     */
    public String execute(TaskList taskList, Ui ui) throws HenryException {
        int numOfTasks = taskList.getTasks().size();
        //check for invalid number
        try {
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
            Task task = taskList.getTasks().get(number - 1);
            if ((this.inputList)[0].equals("mark")) {
                //check if task is already marked
                if (task.isDone()) {
                    throw new HenryException("The task is already marked!");
                }
                task.mark();
                return "\nNice! I've marked this task as done:\n"
                        + task.toString()
                        + "\n";
            } else {
                assert (this.inputList)[0].equals("unmark");
                //check if task is already unmarked
                if (!task.isDone()) {
                    throw new HenryException("The task is already unmarked!");
                }
                task.unmark();
                return "\nOK, I've marked this task as not done yet:\n"
                        + task.toString()
                        + "\n";
            }
        } catch (NumberFormatException e) {
            throw new HenryException("This is not a number!!");
        }
    }

}
