package BobChatBot.Command;

import BobChatBot.Tasks.TaskList;
import BobChatBot.UI;

public class ListCommand extends Command{

    public ListCommand() {
        super(true);
    }

    public void execute(TaskList taskList) {
        UI.printTaskList(taskList);
    }
}
