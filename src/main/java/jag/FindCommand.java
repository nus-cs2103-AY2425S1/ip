package jag;

/**
 * A FindCommand Class that extends the Command Class. It is responsible for
 * finding a given task in a taskList upon calling the command as a user input
 */
public class FindCommand extends Command {

    /**
     * Call upon the Ui instance to get the description that is
     * to be found,
     *
     * @param tasks Tasklist instance to be used to find any task that
     *              descriptions matches the same desription that has been
     *              enetered by the user, after calling getDescription()
     *              on the Ui instance that is passed
     * @param ui Ui instance to break down the command sent so that the
     *           users searched description can be looked for in the for loop
     *           for the TaskList instance.
     * @param storage Storage instance that is not needed but still included
     *                as this is an abstract method that is overriden
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String searchDescription = ui.getDescription('F');
        TaskList foundTasks = new TaskList();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.getTask(i).getDescription().contains(searchDescription)) {
                Task foundTask = tasks.getTask(i);
                foundTasks.addTask(foundTask);
            }
        }
        ui.findResponse(foundTasks);
    }

    /**
     * Return false so that Jag.run() while loop does not end
     *
     * @return false by default so that Jag.run() while loop does not end
     */
    @Override
    public Boolean isExit() {
        return false;
    }
}
