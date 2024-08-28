package bruno.command;

import bruno.task.TaskList;
import bruno.Ui;
import bruno.exceptions.BrunoException;

public class UnmarkCommand extends Command {
    private String taskNum;
    public UnmarkCommand(TaskList tasks, String taskNum) {
        super(tasks);
        this.taskNum = taskNum;
    }
    @Override
    public void execute() {
        try {
            getTasks().unmarkTask(taskNum);
        } catch (BrunoException e) {
            Ui.printErrorMessage(e);
        }
    }
}
