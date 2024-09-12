package tasks;

import tasks.EventTask;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
public class TaskList {
    private final List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<Task>();
    }
    /**
     * Adds the task to the list of tasks.
     * @param task Task to be added to the list.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }
    /**
     * Removes the task from the list of tasks.
     * @param index the index of the task in the list to be removed.
     */
    public String removeTask(int index) {
        if (index >= 0 && index < taskList.size()) {
            Task deletedTask = taskList.get(index);
            taskList.remove(index);
            return String.format("Noted. I've removed this task:\n%s\nNow you have %d tasks in the list.\n",
                    deletedTask.getDescription(), taskList.size());
        } else {
            return "There is no such task number!";
        }
    }
    /**
     * Writes all tasks in the list into the file save point.
     * @param filePath the save point.
     * @throws IOException when the file cannot be found.
     */
    public void write(String filePath) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        for (Task task : taskList) {
            String line = taskToString(task);
            writer.write(line);
            writer.newLine();
        }
        writer.close();
    }
    /**
     * Gets the string of all the different types of tasks to be saved into the save point.
     * @param task the task to be converted into the proper format.
     * @return a string of the task details.
     */
    private String taskToString(Task task) {

        if (task instanceof EventTask) {
            EventTask eventTask = (EventTask) task;
            return eventTask.toString();
        } else if (task instanceof DeadlineTask) {
            DeadlineTask deadlineTask = (DeadlineTask) task;
            return deadlineTask.toString();
        } else {
            ToDoTask toDoTask = (ToDoTask) task;
            return toDoTask.toString();
        }
    }
    /**
     * Gets the size of the TaskList.
     * @return an integer to denote the size of the TaskList.
     */
    public int size() {
        return this.taskList.size();
    }

    /**
     * Prints the entire TaskList.
     */
    public String printList() {
        if (taskList.isEmpty()) {
            return "No items in the list yet!\n";
        }
        String resultString = "Here are the tasks in your list: \n";
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            String response = String.format("%d. %s", i + 1, task.getDescription());
            resultString = resultString.concat("\n" + response);
        }
        return resultString;

    }
    /**
     * Marks the indexed task as done.
     * @param index the index of the task in the list.
     */
    public String mark(int index) {
        if (index >= 0 && index < taskList.size()) {
            Task t = taskList.get(index);
            t.markAsDone();
            return String.format("Nice I've marked this task as not done yet: \n%s %s",
                    line(), t.getDescription());
        } else {
            return "Invalid Task Number.\n";
        }
    }
    /**
     * Marks the indexed task as undone.
     * @param index the index of the task in the list.
     */
    public String unmark(int index) {
        if (index >= 0 && index < taskList.size()) {
            Task t = taskList.get(index);
            t.unMark();
            String response = String.format("%s", t.getDescription());
            return "OK, I've marked this task as not done yet: \n"
                    + line() + response;
        } else {
            return "Invalid Task Number.\n";
        }
    }
    /**
     * Returns a filtered Tasklist based on the command give.
     * @param command is the condition that the filter is based on.
     * @return a Tasklist that is filtered according to the command.
     */

    public TaskList find(String command) {
        TaskList newList = new TaskList();
        for (Task task : taskList) {
            if (task.getDescription().contains(command)) {
                newList.addTask(task);
            }
        }
        return newList;
    }
    public Task getTask(int index) {
        return taskList.get(index);
    }

    public void update(int index, Task updatedTask) {
        this.taskList.remove(index);
        this.taskList.add(index, updatedTask);
    }

    /**
     * A line to separate messages.
     * @return a string message of the segmentation between messages.
     */
    public String line() {
        return "_______________________________________________________\n";

    }
}
