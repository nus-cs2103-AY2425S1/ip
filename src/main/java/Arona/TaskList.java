package Arona;

import java.time.LocalDate;
import java.util.ArrayList;

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

    public int size() {
        return list.size();
    }

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

    public Task peek() {
        return list.get(list.size() - 1);
    }

    public void add(String data) {
        list.add(new Todos(data));
    }

    public void add(String data, LocalDate byDate) {
        list.add(new Deadline(data, byDate));

    }

    public void add(String data, LocalDate fromDate, LocalDate toDate) {
        list.add(new Events(data, fromDate, toDate));
    }

    public Task remove(int i) throws AronaException {
        Task task = get(i);
        list.remove(i);
        return task;
    }

    public Task setStatus(int i, boolean status) throws AronaException {
        get(i).setStatus(status);
        return get(i);
    }

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
