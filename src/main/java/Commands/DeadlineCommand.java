package Commands;

import exception.InvalidDeadlineException;
import task.DeadlinesTask;
import task.TaskList;
import ui.Ui;

public class DeadlineCommand extends GeneralTaskCommand{

    public DeadlineCommand(String c) {
        super(c);
    }

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
