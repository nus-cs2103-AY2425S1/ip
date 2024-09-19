package tayoo;

import tayoo.tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * A stub that replicates the behaviour of an empty tasklist
 */
public class TasklistEmptyStub extends Tasklist{

    public TasklistEmptyStub(List<Task> tasklist) {
        super(tasklist);
    }

    public TasklistEmptyStub() {
        super(new ArrayList<Task>());
    }

    @Override
    public boolean addTask(Task task) {
        return true;
    }

    @Override
    public Task deleteTask(int taskNumber) {
        return null;
    }

    @Override
    public String printTaskList() {
        return "Here are the tasks in your list: \n";
    }

    @Override
    public boolean deleteAll() {
        return false;
    }

    @Override
    public boolean markTask(int taskNumber) {
        return false;
    }

    @Override
    public boolean unmarkTask(int taskNumber) {
        return false;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public String getTaskStr(int taskNumber) {
        return null;
    }

    @Override
    public String find(String substring) {
        return "";
    }

    @Override
    public String numberOfTasksLeft() {
        return "\n Now you have 0 tasks in your list";
    }

}
