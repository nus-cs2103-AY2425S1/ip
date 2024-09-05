package froggy;

/**
 * Find a task by searching for keyword in task description.
 */
public class FindCommand extends Command{

    private String input;

    /**
     * Constructor that takes in raw input given for Find command.
     *
     * @param input raw input.
     */
    public FindCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (input.length() <= 5) {
            System.out.println("[INFO] Error: Please enter the term to find.");
            ui.showLine();
        } else {
            String desc = input.substring(5);
            TaskList searchedTaskList = new TaskList(taskList.searchTasks(desc));
            searchedTaskList.printTasks();
            ui.showLine();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
