package nuffle.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> inputList;

    public TaskList(ArrayList<Task> inputList) {
        this.inputList = inputList;
    }

    public void addTask(Task task) {
        inputList.add(task);
    }

    public Task deleteTask(int index) {
        return inputList.remove(index);
    }

    public Task getTask(int index) {
        return inputList.get(index);
    }

    public int getSize() {
        return inputList.size();
    }

    public ArrayList<Task> getInputList() {
        return inputList;
    }
}
