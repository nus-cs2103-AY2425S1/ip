package Tasks;

import Exceptions.InvalidTaskException;
import Tasks.Task;

import java.util.ArrayList;

public class List {

    private ArrayList<Task> tasks;
    String line = "____________________________________________________________\n";

    public List(){
        this.tasks = new ArrayList<>(100);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns a string that displays the current number of tasks in the list
     *
     * @return A string to be displayed
     */
    public String numOfTasks() {
        if (tasks.size() < 2) {
            return "Now you have " + tasks.size() + " task in the list.\n";
        } else {
            return "Now you have " + tasks.size() + " tasks in the list.\n";
        }
    }

    /**
     * Adds a task to the current list
     *
     * @param task A task to be added
     */
    public void addTaskToList(Task task) {
        tasks.add(task);
    }

    /**
     * Returns a string to be displayed when task is successfully added to list
     * Displays that it has been added and the updated number of tasks
     *
     * @param task A task that was added
     * @return A string to be displayed
     */
    public String addedNotification(Task task) {
        return line + task.addedString() + numOfTasks() + line;
    }

    /**
     * Returns a string to be displayed when user commands "list"
     *
     * @return A string of list of tasks
     */
    public String displayList() {
        StringBuilder list = new StringBuilder();
        list.append("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            list.append(i + 1).append(".").append(tasks.get(i).displayTask());
        }
        return line + list + line;
    }

    public Task getTask(int index) throws InvalidTaskException {
        if (tasks.get(index) == null) {
            throw new InvalidTaskException(index);
        }
        return tasks.get(index);
    }

    /**
     * Deletes a task of a specified index
     *
     * @param index Index of task specified by user
     * @throws InvalidTaskException If index of task does not exist
     */
    public void deleteTask(int index) throws InvalidTaskException {
        try {
            Task task = tasks.get(index);
            String display = line + "Noted. I've removed this task:\n"
                    + task.displayTask();
            tasks.remove(index);
            display = display + numOfTasks() + line;
            System.out.println(display);
        } catch (Exception e) {
            throw new InvalidTaskException(index);
        }
    }

    /**
     * Returns a list of tasks containing a specific keyword
     *
     * @param keyword Keyword specified by user
     * @return List of tasks containing the keyword
     */
    public List filterByKeyword(String keyword) {
        List filteredList = new List();
        for (Task task : tasks) {
            if (task.containsKeyword(keyword)) {
                filteredList.addTaskToList(task);
            }
        }
        return filteredList;
    }
}
