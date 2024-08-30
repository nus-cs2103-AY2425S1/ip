package derek.task;

import derek.exception.TaskNotFoundException;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList = new ArrayList<>();

    public void populateTaskList(String task) {
        String[] components = task.split("\\|");
        if (components[0].equals("D")) {
            Task newTask = Task.deadlineTask(components[2], components[3], components[1]);
            taskList.add(newTask);
        } else if (components[0].equals("E")) {
            Task newTask = Task.eventTask(components[2], components[3], components[4], components[1]);
            taskList.add(newTask);
        } else {
            Task newTask = Task.toDoTask(components[2], components[1]);
            taskList.add(newTask);
        }
    }

    public int size() {
        return this.taskList.size();
    }

    public Task get(int index) {
        return this.taskList.get(index);
    }

    public void remove(int index) {
        this.taskList.remove(index);
    }

    public void add(Task task) {
        this.taskList.add(task);
    }

    public Task find(String taskDescription) throws TaskNotFoundException {
        for (int i = 0; i < this.size(); i++) {
            Task task = this.taskList.get(i);
            String name = task.getName();
            if (name.contains(taskDescription)) {
                return task;
            }
        }

        throw new TaskNotFoundException("Sorry, Derek searched high and low but could not find your task!"
                + "Maybe you are the problem!");
    }

    @Override
    public String toString(){
        String list = "";
        for (int i = 0; i < taskList.size(); i++) {
            list += String.format((i+1) + ". " + taskList.get(i).toString() + "\n");
        }
        return list;
    }

}
