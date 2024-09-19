package Commands;

import exception.InvalidDeadlineException;
import task.DeadlinesTask;
import task.TaskList;
import ui.Ui;

/**
 * DeadlineCommand class extends GeneralTaskCommand to handle commands related
 * to creating and adding deadline tasks.
 * It parses a command to extract task details and deadline, and adds task to the tasklist.
 */

public class DeadlineCommand extends GeneralTaskCommand{

    public DeadlineCommand(String c) {
        super(c);
    }

    /**
     * Executes the command to create and add a deadline task based on the provided command string.
     * It splits the command to extract the task description and deadline date, then creates a DeadlineTask.
     * It is then added to the taskList and throws the relevant exceptions in the case that the format is incorrect
     * or the deadline is invalid.
     * @return String message showing confirmation of creation of a deadlineTask or an error message.
     */
    @Override
    public String commandAction() {
        // before splitting further in the deadline, need to check if correct format
        try {
            // split again after by
            String[] splitAgain = this.cmd.split(" /by ", 2);
            assert splitAgain.length == 2 : "Deadline task should be properly formatted with '/by'";

            String taskDes = splitAgain[0];
            String deadline = splitAgain[1];

            DeadlinesTask tsk = new DeadlinesTask(taskDes, deadline);
            TaskList.addTask(tsk);
            return Ui.taskAddDescription(tsk);
        } catch (InvalidDeadlineException e) {
            return e.getMessage();
        }
    }
}
