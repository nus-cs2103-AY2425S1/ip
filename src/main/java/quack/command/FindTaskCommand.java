package quack.command;

import quack.TaskList;
import quack.Ui;

/**
 * This class is responsible for finding tasks similar
 * similar to the description entered by the user.
 */
public class FindTaskCommand extends Command {

    /** List to store all tasks by Quack */
    private TaskList taskList;
    /** Ui to handle all user display interactions */
    private Ui ui;

    /**
     * Creates a FindCommand object.
     * @param taskList A list that stores all the tasks tracked by Quack.
     * @param ui The ui object that handles user interface requests.
     */
    public FindTaskCommand(TaskList taskList, Ui ui) {
        super();
        this.taskList = taskList;
        this.ui = ui;
    }

    @Override
    public void prompt() {

        ui.requestSearchPrompt();
    }

    @Override
    public void execute(String input) {

        // Ensures that the input is not null as if it is null then Quack is not getting the input from the user
        assert(input != null);

        TaskList filteredTask = taskList.filterTasks(input);
        ui.printSearchResult(filteredTask);
        this.completeCommand();
    }
}
