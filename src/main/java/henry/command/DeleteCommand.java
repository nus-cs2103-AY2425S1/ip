package henry.command;

import henry.Ui;
import henry.TaskList;
import henry.Storage;
import henry.HenryException;
import henry.task.Task;

/**
 * Deals with deleting task for TaskList
 */
public class DeleteCommand extends Command{
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Deletes task into the array of tasks recorded
     *
     * @param taskList instance of a TaskList class that contains
     *                 an array of tasks
     * @param ui instance of a Ui class that interacts with the user
     * @param storage instance of a storage that contains tasks
     *                recorded previously
     */
    public void execute(TaskList taskList, Ui ui, Storage storage) throws HenryException {
        int numOfTasks = taskList.getTasks().size();
        //check for invalid number
        try {
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
            Task task = taskList.getTasks().get(number-1);
            System.out.println("\nNoted. I've removed this task:\n"
                    + task.toString()
                    + "\nNow you have "
                    + (numOfTasks - 1)
                    + (numOfTasks - 1 <= 1 ? " task" : " tasks")
                    + " in the list.\n");
            taskList.getTasks().remove(number - 1);
            save(taskList, storage);
        } catch(NumberFormatException e) {
            throw new HenryException("This is not a number!!");
        }
    }
}
