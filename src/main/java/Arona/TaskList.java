package Arona;

import Arona.AronaExceptions.AronaException;
import Arona.AronaExceptions.ListIndexException;

import Arona.Tasks.Task;
import Arona.Tasks.Todo;
import Arona.Tasks.Event;
import Arona.Tasks.Deadline;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
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
    public TaskList(ArrayList<String> taskStringArray) throws AronaException {
        for (String s : taskStringArray) {
            process(s);
        }
    }

    /**
     * Constructor for blank TaskList
     */
    public TaskList() {

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
    public Task get(int i) throws ListIndexException {
        // Check if exist or if index is negative
        if (i >= list.size() || i < 0) {
            throw new ListIndexException();
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
        list.add(new Todo(data));
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
        list.add(new Event(data, fromDate, toDate));
    }

    /**
     * Removes task at index i and returns the removed task
     * @param  i  index of task to delete
     * @return Task that was removed
     */
    public Task remove(int i) throws ListIndexException {
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
    public Task setStatus(int i, boolean status) throws ListIndexException {
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
        return list.stream().filter(x -> x.getDescription().toLowerCase().contains(keyword))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Deletes the current list of tasks, used by archive command
     */
    public void deleteAll() {
        list.clear();
    }

    /**
     * Returns the task list as a stream
     */
    public Stream<Task> toStream() {
        return list.stream();
    }

    /**
     * Adds a task to the task list by creating the appropriate task object
     * @param  line  a line from data.txt in the format of task.toString
     */
    private void process(String line) throws AronaException {
        String[] data = line.split("]", 3);
        if (data.length != 3) {
            throw new AronaException("Error! Contents of data.txt file cannot be parsed!");
        }

        switch (data[1]) {
        case "[T": {
            add(data[2].substring(1));
            peek().setStatus(data[0].equals("[X"));
            break;
        }
        case "[D": {
            String[] taskData = data[2].split(" \\(by: ", 2);
            try {
                add(taskData[0].substring(1),
                        LocalDate.parse(taskData[1].substring(0, taskData[1].length() - 1)));
                peek().setStatus(data[0].equals("[X"));
            }
            catch (DateTimeParseException e) {
                throw new AronaException("Error! Contents of data.txt file cannot be parsed!");
            }
            break;
        }
        case "[E": {
            String[] taskData = data[2].split(" \\(from: | to: ", 3);
            try {
                add(taskData[0].substring(1),
                        LocalDate.parse(taskData[1]),
                        LocalDate.parse(taskData[2].substring(0, taskData[2].length() - 1)));
                peek().setStatus(data[0].equals("[X"));
            }
            catch (DateTimeParseException e) {
                throw new AronaException("Error! Contents of data.txt file cannot be parsed!");
            }
            break;
        }
        default:
            throw new AronaException("Error! Contents of data.txt file cannot be parsed!");
        }
    }
}
