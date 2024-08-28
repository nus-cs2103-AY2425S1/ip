package monique.tasklist;

import monique.task.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    //operations: Add, Delete, Mark,
    public void addTask(Task task){
        this.taskList.add(task);
    }

    public void deleteTask(int taskNum){
        this.taskList.remove(taskNum);
    }

    public void markTask(int taskNum){
        this.taskList.set(taskNum, taskList.get(taskNum).mark());
    }

    public void unmarkTask(int taskNum) {
        this.taskList.set(taskNum, taskList.get(taskNum).unmark());
    }

    public ArrayList<Task> getTaskList(){
        return this.taskList;
    }

    public Task getTask(int i) {
        return this.taskList.get(i);
    }

    public int getNumItems(){
        return this.taskList.size();
    }
}
