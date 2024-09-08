package pacman;

import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = new ArrayList<>();
        this.list.addAll(list);
    }

    public void addTask(Task task) {
        this.list.add(task);
    }

    public Task toggleTask(int index, boolean status) {
        this.list.get(index).setMarkDone(status);
        return this.list.get(index);
    }

    public Task deleteTask(int index) {
        Task deletedTask = this.list.get(index);
        this.list.remove(index);
        return deletedTask;
    }

    public String toFile() {
        StringBuilder output = new StringBuilder();
        for (int index = 0; index < this.list.size(); index = index + 1) {
            output.append(this.list.get(index).toFile()).append("\n");
        }
        return output.toString();
    }

    public String findTask(String matcher) {
        StringBuilder output = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int index = 0, findIndex = 0; index < this.list.size(); index = index + 1) {
            if (this.list.get(index).isMatched(matcher)) {
                findIndex = findIndex + 1;
                output.append(findIndex).append(". ").append(this.list.get(index).toString()).append("\n");
            }
        }
        return output.toString();
    }

    public int getSize() {
        return list.size();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("Here are the tasks in your list:\n");
        for (int index = 0; index < this.list.size(); index = index + 1) {
            output.append(index + 1).append(". ").append(this.list.get(index).toString()).append("\n");
        }
        return output.toString();
    }
}
