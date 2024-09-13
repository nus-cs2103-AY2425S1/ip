package main;

import java.util.ArrayList;

import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * This class is used to handle operations regarding the list of tasks
 * such as adding or deleting
 */
public class TaskList {

    /**
     * Creates TaskList object with the given taskList by the Storage class
     * @param taskList ArrayList of type Task to be be used
     */
    private final ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Returns size of taskList to be used for loops or other operations
     * @return size of taskList
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Displays the list of tasks in taskList
     */
    public String list() {
        System.out.println("________________________________");
        String output = "";
        for (int i = 0; i < taskList.size(); i++) {
            String display = String.valueOf(i + 1) + ". " + taskList.get(i).getString();
            System.out.println(display);
            output += display + "\n";
        }
        System.out.println("________________________________");
        return output;
    }

    /**
     * Displays the list of task where description contains the word
     * @param word String to be found that exists in description of task
     */
    public String searchTask(String word) {
        ArrayList<Task> foundTask = new ArrayList<>();
        String output = "";
        for (Task i: taskList) {
            if (i.getString().contains(word)) {
                foundTask.add(i);
            }
        }
        System.out.println("________________________________");
        if (foundTask.isEmpty()) {
            System.out.println("No such task found!");
            return "No such task found!";
        } else {
            output = getListOutput(foundTask, output);
        }
        System.out.println("________________________________");
        return output;
    }

    private static String getListOutput(ArrayList<Task> foundTask, String output) {
        int index = 1;
        for (Task i : foundTask) {
            String current = String.valueOf(index) + ". " + i.getString();
            System.out.println(current);
            index += 1;
            output += current + "\n";
        }
        return output;
    }

    /**
     * Adds task of type todo into taskList
     * @param description The description of todo task
     * @return The added task to be displayed by UI class
     */
    public Task addTodo(String description) {
        Task current = new Todo(description);
        taskList.add(current);
        return current;
    }

    /**
     * Adds task of type Event into taskList
     * @param description The description of Event task
     * @return The added task to be displayed by UI class
     */
    public Task addEvent(String description) {
        String[] taskDetails = description.split(" /from ");
        String[] taskTimings = taskDetails[1].split(" /to ");
        Task current = new Event(taskDetails[0], taskTimings[0], taskTimings[1]);
        taskList.add(current);
        return current;
    }

    /**
     * Adds task of type Deadline into taskList
     * @param description The description of Deadline task
     * @return The added task to be displayed by UI class
     */
    public Task addDeadline(String description) {
        String[] taskDetails = description.split(" /by ");
        Task current = new Deadline(taskDetails[0], taskDetails[1]);
        taskList.add(current);
        return current;
    }

    /**
     * Takes in the index of task to be marked as done and returns the task for UI to display
     * Throws error if it is already marked as done or index not in list
     * @param index The index of task in taskList to be marked as done
     * @return the tasked that is marked to be displayed by UI class
     */
    public Task mark(int index) throws DukeException {
        try {
            if (index < 0 || index >= taskList.size()) {
                throw new DukeException("Invalid position!");
            }
            Task currentTask = taskList.get(index);
            if (currentTask.isDone()) {
                throw new DukeException("It is already marked!");
            }
            currentTask.mark();
            return currentTask;
        } catch (DukeException e) {
            System.out.println("________________________________");
            System.out.println(e.getMessage() + "\n________________________________");
            return null;
        }
    }
    /**
     * Takes in the index of task to be marked as undone and returns the task for UI to display
     * Throws error if it is already marked as done or index not in list
     * @param index The index of task in taskList to be marked as undone
     * @return the tasked that is marked to be displayed by UI class
     */
    public Task unmark(int index) {
        try {
            if (index < 0 || index >= taskList.size()) {
                throw new DukeException("Invalid position!");
            }
            Task currentTask = taskList.get(index);
            if (!currentTask.isDone()) {
                throw new DukeException("It is already unmarked!");
            }
            currentTask.mark();
            return currentTask;
        } catch (DukeException e) {
            System.out.println("________________________________");
            System.out.println(e.getMessage() + "\n________________________________");
            return null;
        }
    }
    /**
     * Takes in the index of task to be deleted and returns the task for UI to display
     * Throws error if index not in list
     * @param index The index of task in taskList to be deleted
     * @return the tasked that is deleted to be displayed by UI class
     */
    public Task delete(int index) {
        try {
            if (index < 0 || index >= taskList.size()) {
                throw new DukeException("Invalid position!");
            }
            return taskList.remove(index);
        } catch (DukeException e) {
            System.out.println("________________________________");
            System.out.println(e.getMessage() + "\n________________________________");
            return null;
        }
    }

    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }
}
