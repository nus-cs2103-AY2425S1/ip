package froggy;

/**
 * Find a task by searching for keyword in task description.
 */
public class FindCommand extends Command {

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
            System.out.println("Task(s) found:");
            searchedTaskList.printTasks();
            ui.showLine();
        }
    }

    @Override
    public String executeAndGetOutput(TaskList taskList, Ui ui, Storage storage) {
        if (input.length() <= 5) {
            return "[INFO] Error: Please enter the term to find.\n" + ui.getLine();
        } else {
            String desc = input.substring(5);
            TaskList searchedTaskList = new TaskList(taskList.searchTasks(desc));
            return "Task(s) found:\n" + searchedTaskList.getTasksToString() + "\n" + ui.getLine();
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
