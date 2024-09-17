package Commands;

import task.Task;
import task.TaskList;
import ui.Ui;

public class MarkUnmarkDeleteCommand extends Command {

    public MarkUnmarkDeleteCommand(String c) {
        super(c);
    }

    @Override
    public String commandAction() {
        // used the library function .startsWith() to match the prefix to mark/unmark
        // use.split("") to split up the words
        // use.parseInt(num) to extract integer from the string

        // if mark, then extract integer and mark that task of the list as done
        // if unmark then extract integer and unmark that task of the list as not done

        String[] stringList = this.cmd.split(" ");
        int taskNum = Integer.parseInt(stringList[1]); //second word is the number
        assert taskNum > 0 && taskNum < TaskList.getList().size() : "Task number should be within the correct " +
                "range.";

        Task t = TaskList.getList().get(taskNum - 1);

        if (taskNum < 1 || taskNum > TaskList.getList().size()) {
            return "Task number is out of range. Please retry.";
        }

        if (stringList[0].equals("mark")) {
            return t.markDone();
        } else if (stringList[0].equals("unmark")){
            return t.markIncomplete();
        } else {
            TaskList.delTask(taskNum - 1);
            return Ui.taskDelDescription(taskNum, t);
        }
    }
}
