package Commands;

import task.Task;
import task.TaskList;
import ui.Ui;

/**
 * The MarkUnmarkDeleteCommand class extends Command to handle commands related to marking, unmarking
 * and deleting tasks. It parses the command to discern which command is given, and executes the action accordingly.
 * Then, it updates the task list accordingly.
 */
public class MarkUnmarkDeleteCommand extends Command {

    public MarkUnmarkDeleteCommand(String c) {
        super(c);
    }

    /**
     * Executes the command to mark, unmark, or delete a task based on the command input.
     * It splits the command string to extract the action and task number, performs the corresponding action
     * returns a success or failure message indicating the result of the action.
     *
     * - If the command is "mark", it marks the specified task as done.
     * - If the command is "unmark", it marks the specified task as incomplete.
     *
     * In the case that the task is previously marked or unmarked already, a message is displayed saying the action has
     * already been previously done.
     *
     * - If the command is "delete", it deletes the specified task from the list.
     *
     * The task number must be within the valid range of the current task list. If the number is out of range,
     * an appropriate error message is returned.
     *
     * @return a string message indicating the result of the action performed on the task
     */
    @Override
    public String commandAction() {
        // used the library function .startsWith() to match the prefix to mark/unmark
        // used .split("") to split up the words
        // used .parseInt(num) to extract integer from the string

        String[] stringList = this.cmd.split(" ");
        int taskNum = Integer.parseInt(stringList[1]); //second word is the number
        assert taskNum > 0 && taskNum < TaskList.getList().size() : "Task number should be within the correct " +
                "range.";

        Task t = TaskList.getList().get(taskNum - 1);

        if (taskNum < 1 || taskNum > TaskList.getList().size()) {
            return "Task number is out of range. Please retry.";
        }

        if (stringList[0].equals("mark")) {
            if(t.getProgress()) {
                return "This task has already been marked as done!";
            }
            return t.markDone();
        } else if (stringList[0].equals("unmark")){
            if(!t.getProgress()) {
                return "This task has not been completed yet!";
            }
            return t.markIncomplete();
        } else {
            TaskList.delTask(taskNum - 1);
            return Ui.taskDelDescription(taskNum, t);
        }
    }
}
