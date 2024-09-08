package chacha;

import java.time.LocalDate;
import java.util.ArrayList;

import chacha.task.Task;
import chacha.task.TaskList;

/**
 * Represents the object that handles all user interactions.
 */
public class Ui {
    private static final String GREETING = "Hello! I'm ChaCha the ChatBot. :) \n"
            + "What can I do for you? \n";
    private static final String EXIT = "Bye! Hope to talk to you again soon! ";

    public Ui() {
    }

    /**
     * Returns string representation of Greeting.
     *
     * @return Greeting string.
     */
    public String printGreeting() {
        return GREETING;
    }

    /**
     * Returns string representation of Exit.
     *
     * @return Exit string.
     */
    public String printExit() {
        return EXIT;
    }

    /**
     * Returns string representation for when Task is added.
     *
     * @param task Task to be added.
     * @param tasks TaskList to add Task into.
     * @return Add Task string.
     */
    public String printAdd(Task task, TaskList tasks) {
        return "Got it. I've added this task: \n"
                + "  " + task.printTask() + "\n"
                + "Now you have " + tasks.getTotalNumber() + " tasks in the list. \n";
    }

    /**
     * Returns string representation for when Task is deleted from the list.
     *
     * @param task Task to be deleted.
     * @param tasks TaskList to delete Task from.
     * @return Delete Task string.
     */
    public String printDelete(Task task, TaskList tasks) {
        return "Okay! I've removed this task: \n"
                + "  " + task.printTask() + "\n"
                + "Now you have " + tasks.getTotalNumber() + " tasks in the list. \n";
    }

    /**
     * Returns string representation for when Task is marked done.
     *
     * @param task Task to be marked done.
     * @return Mark Task done string.
     */
    public String printMark(Task task) {
        return "Nice! I've marked this task as done: \n"
                + "  " + task.printTask() + "\n";
    }

    /**
     * Returns string representation for when Task is marked undone.
     *
     * @param task Task to be marked undone.
     * @return Mark Task undone string.
     */
    public String printUnmark(Task task) {
        return "Okay! I've marked this task as not done yet: \n"
                + "  " + task.printTask() + "\n";
    }


    /**
     * Returns string representation including LINE and tasks in the array.
     *
     * @param arrOfTasks Array of tasks to be added formatted with LINE.
     * @return String representation.
     */
    public String printList(ArrayList<Task> arrOfTasks, String text) {
        String result = text;
        for (int i = 0; i < arrOfTasks.size(); i++) {
            String index = String.valueOf(i + 1);
            result += index + ". " + arrOfTasks.get(i).printTask() + "\n";
        }

        return result;
    }

    public String printError() {
        return "Hmmm... I don't seem to understand this. Please input another command! \n";
    }
    /**
     * Returns string representation for the array of String..
     *
     * @param arrOfString Array of string to be formatted.
     * @return String representation.
     */
    public String printStrings(String[] arrOfString) {
        String result = "";
        for (int i = 0; i < arrOfString.length; i++) {
            result += arrOfString[i] + "\n";
        }
        return result;
    }

    /**
     * Returns string representation for To Do Task in the form to be written in the chacha.txt.
     *
     * @param description Description of To Do Task.
     * @return String representation.
     */
    public String printToDo(String description) {
        return "T | 0 | " + description + "\n";
    }

    /**
     * Returns string representation for Deadline Task in the form to be written in the chacha.txt.
     *
     * @param description Description of Deadline Task.
     * @param date Date of Deadline Task.
     * @return String representation.
     */
    public String printDeadline(String description, LocalDate date) {
        return "D | 0 | " + description + " | " + date.toString() + "\n";
    }

    /**
     * Returns string representation for Event Task in the form to be written in the chacha.txt.
     *
     * @param description Description of Event Task.
     * @param date Date of Event Task.
     * @param startTime Start time of Deadline Task.
     * @param endTime End time of Deadline Task.
     * @return String representation.
     */
    public String printEvent(String description, LocalDate date, String startTime, String endTime) {
        return "E | 0 | " + description
                + " | " + date.toString() + " | " + startTime + "-" + endTime + "\n";
    }
}
