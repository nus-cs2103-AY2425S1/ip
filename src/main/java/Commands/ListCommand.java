package Commands;

import task.TaskList;
import ui.Ui;

/**
 * The ListCommand handles displaying tasks in a list format when "list" command is used by the user.
 * It retrieves all the current tasks and returns them in a String format.
 */
public class ListCommand extends Command {

    public ListCommand(String c) {
        super(c);
    }

    @Override
    public String commandAction() {
        return Ui.listDisplay(TaskList.getList());
    }
}
