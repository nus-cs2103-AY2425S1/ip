package buddybot;

import java.util.NoSuchElementException;
import java.util.Scanner;
//test
/**
 * Class for UI
 */
public class Ui {
    private Scanner myObj = new Scanner(System.in);
//test
    /**
     * Allow the user to input
     * @return
     */
    public String readInput() {
            return this.myObj.nextLine();
    }

    /**
     * Closes the Scanner
     */
    public void closeInput() {
        this.myObj.close();
    }

    /**
     * Print a welcome message
     */
    public String welcomeMsg() {
        return " Hello! I'm BuddyBot" + "\n" +
                " What can I do for you?";
    }

    /**
     * Print a goodbye message
     */
    public String goodbyeMsg() {
        return "Bye. Hope to see you again soon!";
    }
    public String showLoadingError(String msg) {
        return msg;
    }

    /**
     * Print a message when task addition is successful
     * @param task
     * @param i
     */
    public String addTask(Task task, int i) {
        return "Got it. I've added this task: \n" + task.toString() + "\n" + "Now you have " + i + " tasks in the list.";
    }

    /**
     * Print a message when deletion is successful
     * @param task
     * @param i
     */
    public String showDelete(Task task, int i) {
        return "Got it. I've removed this task: \n" + task + "\n" + "Now you have " + i + "\" tasks in the list.\"";
    }

    /**
     * Print a message when Task is marked as done
     * @param task
     */
    public String showDone(Task task) {
        return "I've marked this task as done!" + "\n" + task.toString();
    }

    /**
     * Display the list of tasks the user has
     * @param taskList
     */
    public String showList(TaskList taskList) {
        if (taskList.isEmpty()) {
            return "There are no tasks in your list.";
        }

        String s = "These are the tasks in your list:";
        for (int i = 1; i <= taskList.size(); i++) {
            s = s + "\n" + i + ". " + taskList.get(i).toString();
        }
        return s;
    }

    public String showFound(TaskList taskList, String input) {
        if (taskList.isEmpty()) {
            return "There are no tasks in your list.";

        }
        TaskList temp = new TaskList();
        for (int i = 1; i <= taskList.size(); i++) {
            Task t = taskList.get(i);
            if (t.description.contains(input)) {
                temp.add(t);
            }
        }
        if (temp.isEmpty()) {
            return "There are no matching tasks in your list.";
        } else {
             StringBuilder s = new StringBuilder("Here are the matching tasks in your list:");
            for (int i = 1; i <= temp.size(); i++) {
                s.append("\n").append(i).append(". ").append(temp.get(i).toString());
            }
            return s.toString();
        }
    }

    /**
     * Print an error message from BuddyBotException
     * @param e
     */
    public String showBuddyBotException(BuddyBotException e) {
        return e.getMessage();
    }

    /**
     * Print a message when the end date is before the start date
     */
    public String showInvalidDateRange() {
       return "\tEnd date must be after the start date!\n";
    }

    /**
     * Print message when date is in the wrong format
     */
    public String showInvalidDateFormat() {
        return "\tPlease enter the start/end time in the format of <YYYY/MM/DD>!\n";
    }

}
