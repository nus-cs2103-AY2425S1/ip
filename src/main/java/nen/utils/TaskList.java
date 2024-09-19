package nen.utils;

import java.util.ArrayList;
import java.util.Collections;

import nen.exceptions.FailToParseDataException;
import nen.tasks.Task;
import nen.tasks.Todo;


/**
 * This class represents list of tasks
 * @author Gan Ren Yick (A0276246X)
 */
public class TaskList {
    private ArrayList<Task> listOfTasks = new ArrayList<>();

    public TaskList() {

    }

    /**
     * Constructs a list of tasks
     * @param arr of string read from saved data
     */
    public TaskList(String[] arr) {
        try {
            for (String data : arr) {
                listOfTasks.add(Task.parseData(data));
            }
        } catch (FailToParseDataException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Constructs a list of tasks
     * @param taskList of type ArrayList of Task
     */
    public TaskList(ArrayList<Task> taskList) {
        listOfTasks.addAll(taskList);
    }

    /**
     * Returns an array of strings which represent all the tasks which will be saved in file
     * @return array of task string
     */
    public String[] toDataStringArr() {
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
     * Removes task of the given index
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

    /**
     * Sort the task list of the given type of task
     * @param taskClassName string of the task type
     * @return ArrayList of Task which are sorted
     */
    public TaskList sort(String taskClassName) {
        ArrayList<Task> temp = new ArrayList<>();
        TaskList out;
        for (int i = listOfTasks.size() - 1; i >= 0; i--) {
            if (listOfTasks.get(i).getClassName().equals(taskClassName)) {
                temp.add(listOfTasks.get(i));
                listOfTasks.remove(i);
            }
        }
        Collections.sort(temp);
        out = new TaskList(temp);
        temp.addAll(listOfTasks);
        listOfTasks = temp;
        return out;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < listOfTasks.size(); i++) {
            str.append(String.valueOf(i + 1)).append(".").append(listOfTasks.get(i)).append("\n");
        }
        return str.toString();
    }
}
