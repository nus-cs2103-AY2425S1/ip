package Commands;

import Messages.ReturnMessage;
import Tasks.Task;

import java.nio.file.Path;
import java.util.ArrayList;

public class ListCommand extends Command {
    public ListCommand(String[] additionalInput) {
        super(additionalInput);
    }
    @Override
    public ReturnMessage execute() {
        if (taskList.size() == 0) {
            return new ReturnMessage("  ~  No tasks in the list, add some To Dos, Events," +
                    " and Deadlines first :)");
        } else {
            return new ReturnMessage(super.taskList.enumerateTasks());
        }
    }
}
