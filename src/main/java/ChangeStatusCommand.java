/**
 * Deals with marking and unmarking tasks
 */
public class ChangeStatusCommand extends Command {
    private String[] inputList;

    public ChangeStatusCommand(String[] inputList) {
        this.inputList = inputList;
    }

    /**
     * Changes status of task
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
            Task task = taskList.getTasks().get(number-1);
            if ((this.inputList)[0].equals("mark")) {
                //check if task is already marked
                if (task.isDone()) {
                    throw new HenryException("The task is already marked!");
                }
                task.mark();
                System.out.println("\nNice! I've marked this task as done:\n"
                        + task.toString()
                        + "\n");
            } else {
                //check if task is already unmarked
                if (!task.isDone()) {
                    throw new HenryException("The task is already unmarked!");
                }
                task.unmark();
                System.out.println("\nOK, I've marked this task as not done yet:\n"
                        + task.toString()
                        + "\n");
            }
            save(taskList, storage);
        } catch(NumberFormatException e) {
            throw new HenryException("This is not a number!!");
        }
    }

}
