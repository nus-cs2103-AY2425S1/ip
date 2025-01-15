package evelyn.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

import evelyn.task.Deadline;
import evelyn.task.Event;
import evelyn.task.Task;
import evelyn.task.Todo;

/**
 * Houses all the logic for a Tasklist in the chatbot.
 */
public class TaskList {
    private Storage storage;
    private ArrayList<Task> list;

    /**
     * Constructor of a TaskList object.
     * @param storage Storage used for the chatbot.
     */
    public TaskList(Storage storage) {
        this.list = new ArrayList<>(100);
        try {
            this.storage = storage;
            if (!this.storage.fileExists()) {
                this.storage.createNewDataFile();
            } else {
                BufferedReader br = new BufferedReader(this.storage.getFileReader());
                String line;
                while ((line = br.readLine()) != null) {
                    fileDataToList(line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error creating new file / restoring file data: " + e);
        }
    }

    /**
     * Lists all the tasks that have been input.
     */
    public String listTask() {
        String str = "Here are the tasks in your list: \n";
        for (int i = 0; i < this.listSize(); i++) {
            str = str + (i + 1) + ". " + this.getTask(i).toString() + "\n";
        }
        return str;
    }

    /**
     * Removes a task from the list at a particular index.
     * @param index Index of the task
     */
    public String removeTask(int index) {
        try {
            Task task = list.get(index);
            list.remove(index);
            String str = "Noted. I've removed this task: \n";
            str = str + "   " + task.toString() + "\n";
            str = str + ((this.listSize() > 1) ? "Now you have " + this.listSize() + " tasks in this list"
                    : "Now you have " + this.listSize() + " task in this list");
            return str;
        } catch (java.lang.IndexOutOfBoundsException e) {
            return "You don't have a task at this index!";
        }
    }

    /**
     * Adds a task to the list.
     * @param task Task to be added to the list.
     *
     */
    public String addTask(Task task) {
        try {
            list.add(task);
            storage.writeToFile(task);
            String str = "Got it. I've added this task: \n";
            str = str + "  " + task.toString() + "\n";
            str = str + (this.listSize() > 1 ? "Now you have " + this.listSize() + " tasks in this list"
                    : "Now you have " + this.listSize() + " task in this list");
            return str;
        } catch (IOException e) {
            System.err.println("Error reading/writing to file: " + e);
            return "E";
        }
    }

    /**
     * Gets a Task at a particular index from the list.
     * @param index Index of the task to get.
     * @return Returns a task at the specified index.
     */
    public Task getTask(int index) {
        return (Task) list.get(index);
    }

    /**
     * Marks the Task at a specified index as complete.
     * @param index Index of the task in the list to be marked.
     */
    public String markTask(int index) {
        if (index < list.size()) {
            Task task = list.get(index);
            return task.mark();
        } else {
            return "You don't have a task at this index!";
        }
    }

    /**
     * Unmarks a completed Task at a specified index.
     * @param index Index of the task to be unmarked.
     */
    public String unmarkTask(int index) {
        if (index < list.size()) {
            Task task = list.get(index);
            return task.unmark();
        } else {
            return "You don't have a task at this index!";
        }
    }

    /**
     * Gets the size of the current list
     * @return Returns the size of the current list of tasks.
     */
    public int listSize() {
        return list.size();
    }

    /**
     * Saves all the tasks into a .txt file.
     */
    public void saveTasks() {
        try {
            this.storage.saveData();
            for (int i = 0; i < this.listSize(); i++) {
                Task task = this.getTask(i);
                storage.writeToFile(task);
            }
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e);
        }
    }

    /**
     * Finds all tasks that contain the specified keyword
     * @param keyword
     */
    public String find(String keyword) {
        boolean hasFoundSomething = false;
        String str = "The following are all tasks and their indexes that contain the keyword: " + keyword + "\n";
        for (int i = 0; i < list.size(); i++) {
            String taskString = list.get(i).toString();
            if (taskString.contains(keyword)) {
                hasFoundSomething = true;
                str = str + (i + 1) + ". " + taskString + "\n";
            }
        }

        if (hasFoundSomething) {
            return str;
        } else {
            str = "It seems like there is no task that matches your specified keyword."
                   + " Try checking your spelling.";
            return str;
        }
    }

    /**
     * Deciphers the String data from the saved file and inputs it as a Task
     * @param data String representation of a task form the save data.
     * @throws IOException Exception thrown if the file data input is erroneous.
     */
    private void fileDataToList(String data) throws IOException {
        if (data.startsWith("[T]")) {
            inputTodo(data);
        } else if (data.startsWith("[D]")) {
            inputDeadline(data);
        } else if (data.startsWith("[E]")) {
            inputEvent(data);
        } else {
            throw new IOException();
        }
    }

    private void inputTodo(String data) {
        boolean isMarked;
        if (data.contains("[X]")) {
            isMarked = true;
        } else {
            isMarked = false;
        }
        System.out.println(isMarked ? "True" : "False");
        String description = data.substring(7);
        Todo newTodo = new Todo(description, isMarked);
        this.list.add(newTodo);
    }

    private void inputDeadline(String data) {
        boolean isMarked;
        if (data.contains("[X]")) {
            isMarked = true;
        } else {
            isMarked = false;
        }
        String descAndDate = data.substring(7);
        String[] parts = descAndDate.split(" \\(by: ");
        String description = parts[0];
        String deadline = parts[1].substring(0, parts[1].length() - 1);
        Deadline newDeadline = new Deadline(description, deadline, isMarked);
        this.list.add(newDeadline);
    }

    private void inputEvent(String data) {
        boolean isMarked;
        if (data.contains("[X]")) {
            isMarked = true;
        } else {
            isMarked = false;
        }
        String descAndDate = data.substring(7);
        String[] parts1 = descAndDate.split(" \\(from: ");
        String description = parts1[0];
        String[] parts2 = parts1[1].split(" to: ");
        String start = parts2[0];
        String end = parts2[1].substring(0, parts2[1].length() - 1);

        Event newEvent = new Event(description, start, end, isMarked);
        this.list.add(newEvent);
    }
}
