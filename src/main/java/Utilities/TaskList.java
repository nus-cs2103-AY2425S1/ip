package utilities;

import java.util.ArrayList;

import tasks.Task;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(Storage s) {
        this.taskList = s.loadTaskListFromFile();
    }

    public void addToTaskList(Task t, String name) {
        this.taskList.add(t);
        UI.updateUserOnAddition(name, this.taskList.size());
    }

    public void removeFromTaskList(int index) {
        Task t = this.taskList.get(index);
        this.taskList.remove(index);
        UI.updateUserOnDeletion(t);
    }

    public void updateTaskListStatus(int index, boolean status) {
        Task t = this.taskList.get(index);
        if (status) {
            t.markAsDone();
            UI.updateUserOnCompletion(t);
        } else {
            t.markAsNotDone();
            UI.updateUserOnUncompletion(t);
        }
    }

    public int getSize() {
        return this.taskList.size();
    }

    public void findTasks(String input) {
        System.out.println("I found some matching tasks in your list:");
        int count = 1;
        for (Task t : this.taskList) {
            if (t.toString().contains(input)) {
                System.out.println(String.format("%d.%s", count, t.toString()));
                count++;
            }
        }
    }

    @Override
    public String toString() {
        String output = "";
        int count = 1;
        for (Task t : this.taskList) {
            String temp = String.format("%d.%s \n", count, t.toString());
            output += temp;
            count++;
        }

        return output;
    }
}
