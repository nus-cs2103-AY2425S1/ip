package milutrock.stubs;

import milutrock.TaskList;
import milutrock.tasks.Task;

public class TaskListStub extends TaskList {
    public enum Function {
        NONE,
        ADD_TASK,
        REMOVE_TASK,
        MARK_TASK_AS_DONE,
        UNMARK_TASK_AS_DONE,
        NO_OF_TASKS,
        TASK_AT_INDEX_TO_STRING
    }

    public Function lastFunctionCalled;
    public Task taskInput;
    public int integerInput;

    public Task dummyTask;

    public void reset() {
        this.lastFunctionCalled = Function.NONE;
        this.taskInput = null;
        this.integerInput = 0xDEAD;
    }
    
    @Override
    public void addTask(Task task) {
        this.lastFunctionCalled = Function.ADD_TASK;
        this.taskInput = task;
    }

    @Override
    public Task removeTask(int i) {
        this.lastFunctionCalled = Function.REMOVE_TASK;
        this.integerInput = i;
        
        this.dummyTask = new Task("DummyTask");
        return this.dummyTask;
    }

    @Override
    public void markTaskAsDone(int i) {
        this.lastFunctionCalled = Function.MARK_TASK_AS_DONE;
        this.integerInput = i;
    }

    @Override
    public void unmarkTaskAsDone(int i) {
        this.lastFunctionCalled = Function.UNMARK_TASK_AS_DONE;
        this.integerInput = i;
    }

    @Override
    public int getNumberOfTasks() {
        this.lastFunctionCalled = Function.NO_OF_TASKS;

        return 0;
    }

    @Override
    public String getTaskAtIndexAsString(int i) {
        this.lastFunctionCalled = Function.TASK_AT_INDEX_TO_STRING;
        this.integerInput = i;
        
        return "DummyString";
    }
}

