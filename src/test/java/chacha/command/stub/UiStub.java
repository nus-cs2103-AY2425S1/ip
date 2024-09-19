package chacha.command.stub;

import chacha.Ui;
import chacha.task.Task;
import chacha.task.TaskList;

/**
 * Stub for Ui class.
 */
public class UiStub extends Ui {
    private String response;

    public UiStub(String response) {
        this.response = response;
    }

    @Override
    public String printAdd(Task task, TaskList tasks) {
        return response;
    }
}
