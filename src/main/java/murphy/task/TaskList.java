package murphy.task;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

import murphy.MurphyException;

/**
 * Implements a list of Tasks.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Populates a TaskList using an existing save file.
     * @param scanner Scanner which reads from the save file.
     * @throws MurphyException If the content in the save file is not as per the expected format.
     */
    public TaskList(Scanner scanner) throws MurphyException {
        tasks = new ArrayList<>();
        while (scanner.hasNext()) {
            String nextTask = scanner.nextLine();
            Task task = parseSaveTask(nextTask);
            tasks.add(task);
        }
    }

    private Task parseSaveTask(String nextTask) throws MurphyException {
        String[] taskDetails = nextTask.split("\\|");
        Task task;
        if (Objects.equals(taskDetails[0], "T") && taskDetails.length == 3) {
            task = new Todo(taskDetails[2], Boolean.parseBoolean(taskDetails[1]));
        } else if (Objects.equals(taskDetails[0], "D") && taskDetails.length == 4) {
            task = new Deadline(taskDetails[2], Boolean.parseBoolean(taskDetails[1]),
                    taskDetails[3]);
        } else if (Objects.equals(taskDetails[0], "E") && taskDetails.length == 5) {
            task = new Event(taskDetails[2], Boolean.parseBoolean(taskDetails[1]),
                    taskDetails[3], taskDetails[4]);
        } else {
            throw new MurphyException("Save file seems to be corrupted. Overriding save.");
        }
        return task;
    }

    /**
     * Adds a new task to the list.
     * @return A String to be displayed by the UI.
     */
    public String addItem(Task task) {
        tasks.add(task);
        return String.format("Got it. I've added this task:\n%s\nNow you have %d task(s) in the list.",
                task, tasks.size());
    }

    /**
     * Deletes a task from the list.
     * @param index Index of the item to be deleted (1-indexed).
     * @return A String to be displayed by the UI.
     * @throws MurphyException If the index chosen is out of bounds.
     */
    public String deleteItem(int index) throws MurphyException {
        if (index <= 0 || index > tasks.size()) {
            throw new MurphyException("The task number you chose is out of the range of tasks!");
        }
        Task task = tasks.remove(index - 1);
        return String.format("Got it. I've deleted this task:\n%s\nNow you have %d task(s) in the list.",
                task, tasks.size());
    }

    /**
     * Marks a task as completed.
     * @param index Index of the item to be marked (1-indexed).
     * @return A String to be displayed by the UI.
     * @throws MurphyException If the index chosen is out of bounds.
     */
    public String markItem(int index) throws MurphyException {
        if (index <= 0 || index > tasks.size()) {
            throw new MurphyException("Index is outside the range of tasks!");
        }
        tasks.get(index - 1).mark();
        return String.format("Nice! I've marked this task as done:\n%s", tasks.get(index - 1));
    }

    /**
     * Unmarks a task.
     * @param index Index of the item to be unmarked (1-indexed).
     * @return A String to be displayed by the UI.
     * @throws MurphyException If the index chosen is out of bounds.
     */
    public String unmarkItem(int index) throws MurphyException {
        if (index <= 0 || index > tasks.size()) {
            throw new MurphyException("Index is outside the range of tasks!");
        }
        tasks.get(index - 1).unmark();
        return String.format("Ok, I've unmarked this task. Guess Murphy struck?\n%s", tasks.get(index - 1));
    }

    /**
     * Returns a String representation of the TaskList which is suitable to be stored in a save file.
     */
    public String toSaveString() {
        int sz = tasks.size();
        if (sz == 0) {
            return "Your list is currently empty. Add some tasks to get started!";
        }
        StringBuilder string = new StringBuilder();
        for (Task task : tasks) {
            string.append(String.format("%s\n", task.toSaveString()));
        }
        string.deleteCharAt(string.length() - 1); //remove the last newline
        return string.toString();
    }

    @Override
    public String toString() {
        int sz = tasks.size();
        if (sz == 0) {
            return "Your list is currently empty. Add some tasks to get started!";
        }
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < sz; i++) {
            string.append(String.format("%d. %s\n", i + 1, tasks.get(i)));
        }
        string.deleteCharAt(string.length() - 1); //remove the last newline
        return string.toString();
    }

    /**
     * Finds tasks in the list which have a matching search term in the task description.
     * @return The String representation of the filtered TaskList.
     * @throws MurphyException If search string is empty.
     */
    public String find(String search) throws MurphyException {
        if (search.isEmpty()) {
            throw new MurphyException("Error finding tasks: search term cannot be empty.");
        }
        TaskList matchedTasks = new TaskList();
        for (Task task : tasks) {
            if (task.containsString(search)) {
                matchedTasks.addItem(task);
            }
        }
        return matchedTasks.toString();
    }
}

