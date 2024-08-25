package simon;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    private int count;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
        this.count = this.tasks.size();
    }
    public TaskList() {
        this(new ArrayList<Task>());
        this.count = 0;
    }
    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task pop (int index) {
        return this.tasks.remove(index);
    }

    public int size() {
        return this.tasks.size();
    }

    public Task get (int index) {
        return this.tasks.get(index);
    }
    public void markTask (boolean mark, int index) {
        if (mark) {
            this.tasks.get(index).markAsDone();
        }
        else{
            this.tasks.get(index).markAsNotDone();
        }

    }

    @Override
    public String toString() {
        String output = "";
        int count = 1;
        for (Task task: tasks) {
            output +=("\t")+(count)+(". ")+(task.toString()) +"/n";
        }
        return output;
    }

    public ArrayList<Task> toArr() {
        return this.tasks;
    }


}
