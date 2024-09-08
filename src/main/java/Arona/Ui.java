package Arona;

import Arona.AronaExceptions.AronaException;

import Arona.Tasks.Task;
import java.util.ArrayList;

public class Ui {

    /**
     * Handles all text outputs including AronaException
     */
    public Ui() {}

    public String showGreeting() {
        return "Hello! I'm Arona." + "/n" + "What can I do for you?";
    }

    public String showFarewell() {
        return "Bye. Hope to see you again soon!";
    }

    /**
     * Debug: method to print absolute location of data.txt file
     */
    public String showStorage(Storage storage) {
        return storage.getStorageLocation();
    }

    /**
     * Used for list command
     * @param  taskList  a TaskList object containing the current instance's list
     */
    public String showList(TaskList taskList) throws AronaException {
        if (taskList.size() == 0) {
            return "The task list is empty!";
        } else {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                s.append(i + 1).append(". ").append(task.toFriendlyString()).append("\n");
            }
            return s.toString();
        }
    }

    /**
     * Used for find command
     * @param  taskList  an arraylist of filtered Task objects
     */
    public String showFilterList(ArrayList<Task> taskList) throws AronaException {
        if (taskList.isEmpty()) {
            return "The task list is empty!";
        } else {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                s.append(i + 1).append(". ").append(task.toFriendlyString()).append("\n");
            }
            return s.toString();
        }
    }

    public String showDelete(int size, Task task) {
        String s = "";
        s += "Got it. I've removed this task:" + "\n";
        s += task.toFriendlyString() + "\n";
        s += "Now you have " + size + " tasks in the list.";
        return s;
    }

    public String showMark(Task task, boolean action) {
        String s = "";
        if (action) {
            s += "Nice! I've marked this task as done:" + "\n";
        } else {
            s += "OK, I've marked this task as not done yet:" + "\n";
        }
        s += task.toFriendlyString();
        return s;
    }

    public String showAdd(int size, Task task) {
        String s = "";
        s += "Got it. I've added this task:" + "\n";
        s += task.toFriendlyString() + "\n";
        s += "Now you have " + size + " tasks in the list.";
        return s;
    }

    public String showException(Exception e) {
        return (e.getMessage());
    }

    public String showFileException() {
        return ("Error! data.txt file or folder cannot be read.");
    }

    public String showPathException() {
        return ("Error! Invalid path provided in main function.");
    }
}
