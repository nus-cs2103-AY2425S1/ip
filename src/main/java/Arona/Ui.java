package arona;

import arona.AronaExceptions.AronaException;

import arona.Tasks.Task;
import java.util.ArrayList;
import java.util.Random;

public class Ui {

    private final static String[] LIST_OF_GREETINGS = {
            "Any task you want to do in particular, Sensei?",
            "Manage tasks you need to complete from here!",
            "There's lots of work to do, but I know you can do it!",
            };

    /**
     * Handles all text outputs including AronaException
     */
    public Ui() {}

    public static String showGreeting() {
        Random rand = new Random();
        return LIST_OF_GREETINGS[rand.nextInt(3)];
    }

    public String showFarewell() {
        return "Goodbye Sensei! Hope to see you again soon!";
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
            return "Your task list is empty, Sensei!\n" +
                    "Do you want to play with Arona instead?";
        } else {
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                s.append(i + 1).append(". ").append(task.toFriendlyString()).append("\n");
            }
            if (taskList.size() >= 5) {
                s.append("That's a lot on your docket. Adults have it rough, huh?");
            }
            return s.toString();
        }
    }

    /**
     * Used for find command
     * @param  taskList  an arraylist of filtered Task objects
     */
    public String showFilteredList(ArrayList<Task> taskList) throws AronaException {
        if (taskList.isEmpty()) {
            return "Huh? There's no task with this keyword, Sensei!";
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
     * Used for archive command
     * @param  name  an arraylist of filtered Task objects
     */
    public String showArchive(String name) {
        return "Ok! The tasks have been archived in " + name + ".txt";
    }

    public String showDelete(int size, Task task) {
        String s = "";
        s += "Don't worry sensei, Arona will remove this task:" + "\n";
        s += task.toFriendlyString() + "\n";
        s += "Now you have " + size + " tasks in the list.";
        return s;
    }

    public String showMark(Task task, boolean action) {
        String s = "";
        if (action) {
            s += "Good job Sensei! I've marked this task as done:" + "\n";
        } else {
            s += "Oh no :( I've marked this task as not done yet:" + "\n";
        }
        s += task.toFriendlyString();
        return s;
    }

    public String showAdd(int size, Task task) {
        String s = "";
        s += "I got you, Sensei! Arona will add this task:" + "\n";
        s += task.toFriendlyString() + "\n";
        s += "Now you have " + size + " tasks in the list. \n";
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
