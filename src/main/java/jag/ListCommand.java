package jag;

/**
 * Represents and instance of a ListCommand and it includes
 * a method to execute said command
 */
public class ListCommand extends Command {
    /**
     * Executes the desired command by calling toString on every task
     * in the task list so that it can be passed as a concatenated
     * string to the Ui instance to display the right message
     *
     * @param tasks TaskList so the every task toString can be called upon
     * @param ui Ui instance so that the right message can be displayed to the user
     * @param storage Storage instance which is not used here but still needs to be
     *                included as this is an abstract method from the Command Class
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder list = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            list.append(i + 1).append(". ").append(task.toString()).append("\n");
        }
        ui.list(list.toString());
    }

    /**
     * Returns false so that Jag.java does not exit for loop
     *
     * @return a default false so the run() in Jag.java does not exit
     * the while loop
     */
    @Override
    public Boolean isExit() {
        return false;
    }
}
