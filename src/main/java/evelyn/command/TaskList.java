package evelyn.command;

import evelyn.task.Deadline;
import evelyn.task.Event;
import evelyn.task.Task;
import evelyn.task.Todo;

import java.io.BufferedReader;
import java.io.IOException;

import java.util.ArrayList;

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
        this.list = new ArrayList(100);
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
    public void listTask() {
        System.out.println(Ui.horizontalLine);
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < this.listSize(); i++) {
            System.out.println((i + 1) + "." + this.getTask(i).toString());
        }
        System.out.println(Ui.horizontalLine);
    }

    /**
     * Removes a task from the list at a particular index.
     * @param index Index of the task
     */
    public void removeTask(int index) {
        Task task = (Task) list.get(index);
        list.remove(index);
        System.out.println(Ui.horizontalLine);
        System.out.println("Noted. I've removed this task:");
        System.out.println("   " + task.toString());
        System.out.println(this.listSize() > 1 ? "Now you have " + this.listSize() + " tasks in this list"
                : "Now you have " + this.listSize() + " task in this list");
        System.out.println(Ui.horizontalLine);
    }

    /**
     * Adds a task to the list.
     * @param task Task to be added to the list.
     */
    public void addTask(Task task) {
        try {
            list.add(task);
            storage.writeToFile(task);
            System.out.println(Ui.horizontalLine);
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + task.toString());
            System.out.println(this.listSize() > 1 ? "Now you have " + this.listSize() + " tasks in this list"
                    : "Now you have " + this.listSize() + " task in this list");
            System.out.println(Ui.horizontalLine);
        } catch (IOException e) {
            System.err.println("Error reading/writing to file: " + e);
        }
    }

    /**
     * Gets a Task at a particular index from the list.
     * @param index Index of the task to get.
     * @return Returns a task at the specified index.
     */
    public Task getTask(int index) {
        Task task = (Task) list.get(index);
        return task;
    }

    /**
     * Marks the Task at a specified index as complete.
     * @param index Index of the task in the list to be marked.
     */
    public void markTask(int index) {
        Task task = (Task) list.get(index);
        System.out.println(Ui.horizontalLine);
        task.mark();
        System.out.println(Ui.horizontalLine);
    }

    /**
     * Unmarks a completed Task at a specified index.
     * @param index Index of the task to be unmarked.
     */
    public void unmarkTask(int index) {
        Task task = (Task) list.get(index);
        System.out.println(Ui.horizontalLine);
        task.unmark();
        System.out.println(Ui.horizontalLine);
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
                Task task = (Task) this.getTask(i);
                storage.writeToFile(task);
            }
        } catch (IOException e) {
            System.err.println("Error saving to file: " + e);
        }
    }

    /**
     * Deciphers the String data from the saved file and inputs it as a Task
     * @param data String representation of a task form the save data.
     * @throws IOException Exception thrown if the file data input is erroneous.
     */
    private void fileDataToList(String data) throws IOException {
        boolean isMarked;
        if (data.startsWith("[T]")) {
            if (data.contains("[X]")) {
                isMarked = true;
            } else {
                isMarked = false;
            }
            System.out.println(isMarked ? "True" : "False");
            String description = data.substring(7);
            Todo newTodo = new Todo(description, isMarked);
            this.list.add(newTodo);
        } else if (data.startsWith("[D]")) {
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
        } else if (data.startsWith("[E]")) {
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
        } else {
            throw new IOException();
        }
    }

}