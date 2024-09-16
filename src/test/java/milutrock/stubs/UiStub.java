package milutrock.stubs;

import milutrock.TaskList;
import milutrock.Ui;
import milutrock.tasks.Task;

public class UiStub extends Ui {
    public enum Function {
        NONE,
        PRINT_BANNER,
        PRINT_LINE_BREAK,
        PRINT_BYE_MESSAGE,
        PRINT_TASK,
        PRINT_TASK_LIST,
        PRINT_MARK_MESSAGE,
        PRINT_UNMARK_MESSAGE,
        PRINT_DELETE_MESSAGE,
        PRINT_ADD_MESSAGE
    }
    
    public Function lastFunctionCalled;
    public Task taskInput;
    public int integerInput;

    public UiStub() {
        super("", new TaskList());
    }

    public void reset() {
        this.lastFunctionCalled = Function.NONE;
        this.taskInput = null;
        this.integerInput = 0xDEAD;
    }

    @Override
    public void printBanner() {
        this.lastFunctionCalled = Function.PRINT_BANNER;
    }

    @Override
    public void printLineBreak() {
        this.lastFunctionCalled = Function.PRINT_LINE_BREAK;
    }

    @Override
    public void printByeMessage() {
        this.lastFunctionCalled = Function.PRINT_BYE_MESSAGE;
    }

    @Override
    public void printTask(int i) {
        this.lastFunctionCalled = Function.PRINT_TASK;
        this.integerInput = i;
    }

    @Override
    public void printTaskList() {
        this.lastFunctionCalled = Function.PRINT_TASK_LIST;
    }

    @Override
    public void printMarkMessage(int i) {
        this.lastFunctionCalled = Function.PRINT_MARK_MESSAGE;
        this.integerInput = i;
    }

    @Override
    public void printUnmarkMessage(int i) {
        this.lastFunctionCalled = Function.PRINT_UNMARK_MESSAGE;
        this.integerInput = i;
    }
    
    @Override
    public void printDeleteMessage(Task task) {
        this.lastFunctionCalled = Function.PRINT_DELETE_MESSAGE;
        this.taskInput = task;
    }

    @Override
    public void printAddMessage() {
        this.lastFunctionCalled = Function.PRINT_ADD_MESSAGE;
    }

}
