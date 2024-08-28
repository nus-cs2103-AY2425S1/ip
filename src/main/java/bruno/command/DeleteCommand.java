package bruno.command;

import bruno.task.TaskList;
import bruno.Ui;
import bruno.exceptions.BrunoException;

public class DeleteCommand extends Command {
    private String taskNum;
    public DeleteCommand(TaskList tasks, String taskNum) {
        super(tasks);
        this.taskNum = taskNum;
    }
    @Override
    public void execute() {
        try {
            getTasks().deleteTask(taskNum);
        } catch (BrunoException e) {
            Ui.printErrorMessage(e);
        }
    }
}
