package milutrock.stubs;

import java.util.ArrayList;

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
    public String printBanner() {
        this.lastFunctionCalled = Function.PRINT_BANNER;
        return "";
    }

    @Override
    public String printTaskList() {
        this.lastFunctionCalled = Function.PRINT_TASK_LIST;
        return "";
    }

    @Override
    public String printMarkMessage(int i) {
        this.lastFunctionCalled = Function.PRINT_MARK_MESSAGE;
        this.integerInput = i;
        return "";
    }

    @Override
    public String printUnmarkMessage(int i) {
        this.lastFunctionCalled = Function.PRINT_UNMARK_MESSAGE;
        this.integerInput = i;
        return "";
    }
    
    @Override
    public String printDeleteMessage(ArrayList<Task> tasks) {
        this.lastFunctionCalled = Function.PRINT_DELETE_MESSAGE;
        this.taskInput = tasks.get(0);
        return "";
    }

    @Override
    public String printAddMessage() {
        this.lastFunctionCalled = Function.PRINT_ADD_MESSAGE;
        return "";
    }

}
