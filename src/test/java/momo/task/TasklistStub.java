package momo.task;

import java.util.ArrayList;

public class TasklistStub extends TaskList {
    private final ArrayList<Task> list = new ArrayList<>();
    public TasklistStub(String fileText) {
        super();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public ArrayList<Task> getTaskList() {
        return list;
    }

    @Override
    public Task getTask(int index) {
        return list.get(index);
    }

    public void deleteTask(int index) {
        list.remove(index);
    }

    @Override
    public void addTask(Task task) {
        list.add(task);
    }


}
