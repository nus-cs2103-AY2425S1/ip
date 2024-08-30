package cypherchatbot.util;

import cypherchatbot.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }

    public String addToList(Task task) {
        this.taskList.add(task);
        return String.format("Got it. I have added this task:\n  %s\nNow you have %d task in the list\n" ,task, this.taskList.size());
    }


    public String markTask(int i, Storage storage) {
        Task task = this.taskList.get(i);
        String oldLine = task.toStringinFile();
        task.completeTask();
        String newLine = task.toStringinFile();
        storage.editTask(oldLine,newLine);
        return "Nice! I have marked this task as completed:\n " + task;
    }

    public String unmarkTask(int i, Storage storage) {
        Task task = this.taskList.get(i);
        String oldLine = task.toStringinFile();
        task.incompleteTask();
        String newLine = task.toStringinFile();
        storage.editTask(oldLine,newLine);
        return "Ok! I have marked this task as incomplete:\n " + task;
    }

    public String delTask(int i, Storage storage) {
        Task task = this.taskList.remove(i);
        storage.delTaskFromStorage(task.toStringinFile());
        return "Noted! I have removed this task:\n " + task + String.format("Now you have %d task in the list%n", this.taskList.size());
    }

    public int size() {
       return this.taskList.size();
    }
    @Override
    public String toString() {
        if (this.taskList.isEmpty()) {
            return "You have no items in your list:";
        } else {
            String str = "";
            str += "Here are the items in your list:\n";
            for (int i = 0; i < this.taskList.size(); i++) {
                str += (i + 1) + ". " + this.taskList.get(i) + "\n";
            }
            return str;
        }
    }
}
