package chacha.command.stub;

import chacha.Storage;
import chacha.Ui;
import chacha.exception.ChaChaException;
import chacha.exception.WrongDateFormatException;
import chacha.exception.WrongTimeFormatException;
import chacha.task.Task;
import chacha.task.TaskList;

/**
 * Stub for TaskList class.
 */
public class TaskListStub extends TaskList {
    private Task tasks;
    private ChaChaExceptionStub chaChaException;
    private WrongTimeFormatExceptionStub wrongTimeException;
    private WrongDateFormatException wrongDateException;
    private WrongCommandFormatExceptionStub wrongCommandException;

    public TaskListStub(Task tasks) {
        super();
        this.tasks = tasks;
    }

    public TaskListStub(Task tasks, WrongCommandFormatExceptionStub e) {
        super();
        this.tasks = tasks;
        this.wrongCommandException = e;
    }

    public TaskListStub(Task tasks, ChaChaExceptionStub e) {
        super();
        this.tasks = tasks;
        this.chaChaException = e;
    }

    public TaskListStub(Task tasks, WrongDateFormatExceptionStub e) {
        super();
        this.tasks = tasks;
        this.wrongDateException = e;
    }

    public TaskListStub(Task tasks, WrongTimeFormatExceptionStub e) {
        super();
        this.tasks = tasks;
        this.wrongTimeException = e;
    }

    @Override
    public Task addEvent(String userInput, Ui ui, Storage storage)
            throws ChaChaExceptionStub, WrongCommandFormatExceptionStub,
            WrongTimeFormatExceptionStub, WrongDateFormatExceptionStub {
        if (chaChaException != null) {
            throw chaChaException;
        } else if (wrongCommandException != null) {
            throw wrongCommandException;
        } else if (wrongTimeException != null) {
            throw wrongTimeException;
        } else if (wrongDateException != null) {
            throw wrongDateException;
        }
        return tasks;
    }
}
