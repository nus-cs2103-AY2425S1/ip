package bruno.command;

import bruno.task.TaskList;
import bruno.Ui;
import bruno.exceptions.BrunoException;

public class MarkCommand extends Command {
    private String taskNum;

    public MarkCommand(TaskList tasks, String taskNum) {
        super(tasks);
        this.taskNum = taskNum;

    }

    @Override
    public void execute() {
        try {
            getTasks().markTask(taskNum);
        } catch (BrunoException e) {
            Ui.printErrorMessage(e);
        }
    }
}