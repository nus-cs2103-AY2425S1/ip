package elysia.tasks;

import java.util.ArrayList;
import java.util.List;

public class TaskListStub extends TaskList{
    public boolean isToStringCalled = false;
    public boolean isPrintTaskCalled = false;
    public boolean isDeleteTaskCalled = false;
    public boolean isSizeCalled = false;
    public boolean isAddTaskCalled = false;
    public int taskNumber;
    public Task task;

    @Override
    public void addTask(Task task) {
        isAddTaskCalled = true;
        this.task = task;
    }

    @Override
    public void markTask (int taskNumber) {
        if (taskNumber == -1) {
            throw new IndexOutOfBoundsException("task not in tasklist");
        }
        this.taskNumber = taskNumber;
    }

    @Override
    public void unmarkTask (int taskNumber) {
        if (taskNumber == -1) {
            throw new IndexOutOfBoundsException("task not in tasklist");
        }
        this.taskNumber = taskNumber;
    }

    @Override
    public Task deleteTask(int index) throws IndexOutOfBoundsException {
        isDeleteTaskCalled = true;
        if (index == -1) {
            throw new IndexOutOfBoundsException();
        }
        return new Task("");
    }

    @Override
    public String getSizeAsString() {
        isSizeCalled = true;
        return "";
    }

    @Override
    public String printTask(int taskNumber) {
        isPrintTaskCalled = true;
        return "";
    }

    @Override
    public String toString() {
        isToStringCalled = true;
        return "";
    }
}
