package optimus;

import optimus.exceptions.InvalidTaskNumberException;
import optimus.tasks.Task;

import java.util.ArrayList;
import java.util.stream.Stream;

public class TaskList {
    ArrayList<Task> list;

    public TaskList(ArrayList<Task> loadedFromStorage) {
        this.list = loadedFromStorage;
    }

    public Task getTask(int taskNum) throws InvalidTaskNumberException {
        if (isInvalidTaskNum(taskNum)) {
            throw new InvalidTaskNumberException();
        } else {
            return list.get(taskNum);
        }
    }

    public int getNumOfTasks() {
        return list.size();
    }

    public Task removeTask(int taskNum) throws InvalidTaskNumberException {
        if (isInvalidTaskNum(taskNum)) {
            throw new InvalidTaskNumberException();
        } else {
            return list.remove(taskNum);
        }
    }

    /**
     * Returns a stream of Tasks whose descriptions contain the keyword
     * @param keyword
     * @return
     */
    public Stream<Task> filterByKeyword(String keyword) {
        return list.stream()
                .filter(task -> task.getTaskDesc().contains(keyword));
    }

    public void addTask(Task task) {
        list.add(task);
    }
    private boolean isInvalidTaskNum(int taskNum) {
        return taskNum < 0 || taskNum >= list.size();
    }

    public ArrayList<Task> getList() {
        return list;
    }
}
