package tayoo.command;

import tayoo.Storage;
import tayoo.Tasklist;
import tayoo.Ui;
import tayoo.exception.TayooException;
import tayoo.tasks.Task;

/**
 * The AddTaskCommand class contains all the sub-commands that should be executed when an Add Task command is given by
 * the user
 */
public class AddTaskCommand extends Command {

    private final Task taskToAdd;

    public AddTaskCommand(Task task) {
        this.taskToAdd = task;
    }

    private String addTaskCommandHelper(Tasklist tasklist, Storage storage) throws TayooException{
        StringBuilder toReturn = new StringBuilder();

        if (tasklist.addTask(this.taskToAdd)) {
            storage.addToTxt(this.taskToAdd);

            toReturn.append("Got it. I've added this task: \n")
                    .append(this.taskToAdd.toString())
                    .append(tasklist.numberOfTasksLeft());

        } else {
            throw new TayooException("You have too many tasks in your tasklist!");
        }
        return toReturn.toString();
    }

    /**
     * Executes the add task command and prints out the task that has been added.
     *
     * @param tasklist that stores all the tasks.
     * @param ui ui representing the I/O system with the user.
     * @param storage represents the .txt file in which the tasks are stored
     * @throws TayooException if there is any error with executing the command (i.e. invalid input)
     */
    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        ui.printText(addTaskCommandHelper(tasklist, storage));
    }

    /**
     * Executes the add task command and returns the task that has been added.
     *
     * @param tasklist that stores all the tasks.
     * @param ui ui representing the I/O system with the user.
     * @param storage represents the .txt file in which the tasks are stored
     * @return String response of the chatbot after executing this AddTaskCommand
     * @throws TayooException if there is any error with executing the command (i.e. invalid input)
     */
    @Override
    public String guiExecute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {
        return addTaskCommandHelper(tasklist, storage);
    }



    @Override
    public String toString() {
        return this.taskToAdd.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
