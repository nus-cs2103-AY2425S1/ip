package nen.utils;

import java.util.ArrayList;
import java.util.PriorityQueue;

import nen.exceptions.FailToParseDataException;
import nen.tasks.Task;
import nen.tasks.Todo;


/**
 * This class represents list of tasks
 * @author Gan Ren Yick (A0276246X)
 */
public class TaskList {
    private static ArrayList<Task> listOfTasks = new ArrayList<>();

    private static PriorityQueue<Task> listOfTasksWithDeadline = new PriorityQueue<>();

    public TaskList() {

    }

    /**
     * Constructs a list of tasks
     * @param arr of string read from saved data
     */
    public TaskList(String[] arr) {
        try {
            for (String data : arr) {
                listOfTasks.add(Todo.parseData(data));
            }
        } catch (FailToParseDataException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns an array of strings which represent all the tasks which will be saved in file
     * @return array of task string
     */
    public String[] toDataStringArr() {
        String[] out = new String[listOfTasks.size()];
        return listOfTasks.stream().map(Task::toData).toArray(String[]::new);
    }

    /**
     * Finds and returns array of tasks contain the given keyword
     * @param keyword to find tasks
     * @return array of tasks
     */
    public Task[] findTaskWithKeyword(String keyword) {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Task t : listOfTasks) {
            for (String word : t.getDescription().split(" ")) {
                if (word.equals(keyword)) {
                    tasks.add(t);
                }
            }
        }
        return tasks.toArray(new Task[0]);
    }

    /**
     * Returns amount of tasks
     * @return amount of tasks
     */
    public int size() {
        return listOfTasks.size();
    }

    /**
     * Returns task of the given index
     * @param index of tasks
     * @return Task
     */
    public Task get(int index) {
        return listOfTasks.get(index);
    }

    /**
     * Remove task of the given index
     * @param index of task
     */
    public void remove(int index) {
        listOfTasks.remove(index);
    }

    /**
     * Adds given task to the list
     * @param t task to be added
     */
    public void add(Task t) {
        listOfTasks.add(t);
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < listOfTasks.size(); i++) {
            str += String.valueOf(i + 1) + "." + listOfTasks.get(i) + "\n";
        }
        return str;
    }
}
