package kotori.command;

import static kotori.ui.Ui.printListWithMessages;
import static kotori.ui.Ui.printMessages;

import kotori.tasklist.TaskList;

/**
 * This is a find command.
 * */
public class FindCommand extends Command {
    private TaskList taskList;
    private String target;

    /**
     * Create a find command
     * */
    public FindCommand(TaskList taskList, String target) {
        this.taskList = taskList;
        this.target = target;
    }

    @Override
    public String execute() {
        TaskList result = taskList.findAll(target);
        if (taskList.isEmpty()) {
            return printMessages("Sorry~ There is no matching task");
        } else {
            return printListWithMessages(result, "These are(is) the task(s) that match the description");
        }
    }
}
