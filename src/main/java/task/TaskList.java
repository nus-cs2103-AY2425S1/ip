package task;

import task.Task;
import prince.Prince;
import exception.IncompleteDescException;
import exception.UnknownWordException;

import java.util.ArrayList;

public class TaskList {

    private static ArrayList<Task> list;

    public TaskList(ArrayList<Task> tasks) {
        list = tasks;
    }

    public static ArrayList<Task> getList() {
        return list;
    }

    public static void addTask(Task task){
        // add task to the List
        // return a string
        list.add(task);
        //return "added: " + task.getDescription();
    }

    public static void delTask(int num){
        // add task to the List
        // return a string
        if(num >= 0 && num < list.size()) {
            list.remove(num);
        }
        //return "added: " + task.getDescription();
    }

    public Task getTask(int index) {
        return list.get(index);
    }

}
