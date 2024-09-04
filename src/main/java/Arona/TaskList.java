package Arona;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TaskList {

    private static ArrayList<Task> list = new ArrayList<>(100);

    /**
     * Constructor for TaskList, called once at the start of the program, lines from data.txt fed into this class to
     * initialise the task list
     * @param  taskStringArray  arraylist of raw strings from data.txt
     */
    public TaskList(ArrayList<String> taskStringArray) {
        for (String s : taskStringArray) {
            process(s);
        }
    }

    /**
     * @return integer of number of tasks in list
     */
    public int size() {
        return list.size();
    }

    /**
     * @param  i  index of task to return
     * @return Task object at index i
     */
    public Task get(int i) throws AronaException {
        // Check if exist
        if (i >= list.size()) {
            throw new AronaException("Error! This task does not exist!");
        }

        // Check if negative
        if (i < 0) {
            throw new AronaException("Error! Please input a positive number.");
        }

        return list.get(i);
    }

    /**
     * @return Task at largest index (ie most recently added task)
     */
    public Task peek() {
        return list.get(list.size() - 1);
    }

    /**
     * Overloaded method, adds to do task
     */
    public void add(String data) {
        list.add(new Todos(data));
    }

    /**
     * Overloaded method, adds deadline task
     */
    public void add(String data, LocalDate byDate) {
        list.add(new Deadline(data, byDate));

    }

    /**
     * Overloaded method, adds event task
     */
    public void add(String data, LocalDate fromDate, LocalDate toDate) {
        list.add(new Events(data, fromDate, toDate));
    }

    /**
     * Removes task at index i and returns the removed task
     * @param  i  index of task to delete
     * @return Task that was removed
     */
    public Task remove(int i) throws AronaException {
        Task task = get(i);
        list.remove(i);
        return task;
    }

    /**
     * Removes set status of task at index i to true (done) or false (undone),
     * no indicator to show if action had any real effect
     * @param  i  index of task
     * @param  status  the new status the task should be changed to
     * @return Task that had status changed
     */
    public Task setStatus(int i, boolean status) throws AronaException {
        get(i).setStatus(status);
        return get(i);
    }

    /**
     * Creates a new arraylist of tasks whose description contains (subset) the keyword,
     * not case-sensitive, whitespace sensitive
     * @param  keyword  string of keyword
     * @return arraylist of filtered tasks
     */
    public ArrayList<Task> filter(String keyword) {
        return list.stream().filter(x -> x.toString().toLowerCase().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Adds a task to the task list by creating the appropriate task object
     * @param  line  a line from data.txt in the format of task.toString
     */
    private static void process(String line) {
        String[] data = line.split("]", 3);

        switch (data[1]) {
            case "[T": {
                list.add(new Todos(data[2].substring(1)));
                list.get(list.size()-1).setStatus(data[0].equals("[X"));
                break;
            }
            case "[D": {
                String[] taskData = data[2].split(" \\(by: ", 2);
                list.add(new Deadline(taskData[0].substring(1), LocalDate.parse(taskData[1].substring(0, taskData[1].length() - 1))));
                list.get(list.size()-1).setStatus(data[0].equals("[X"));
                break;
            }
            case "[E": {
                String[] taskData = data[2].split(" \\(from: | to: ", 3);
                list.add(new Events(taskData[0].substring(1), LocalDate.parse(taskData[1]), LocalDate.parse(taskData[2].substring(0, taskData[2].length() - 1))));
                list.get(list.size()-1).setStatus(data[0].equals("[X"));
                break;
            }
        }
    }
}
