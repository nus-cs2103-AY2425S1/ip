package talkabot;

import java.util.Scanner;

import talkabot.exceptions.UnknownInputException;
import talkabot.exceptions.WrongTaskTypeException;
import talkabot.task.Deadline;
import talkabot.task.Event;
import talkabot.task.Task;
import talkabot.task.TaskList;
import talkabot.task.ToDo;

/**
 * Ui Class handles interactions with the user,
 * printing out responses based on what is done by Talk-a-Bot.
 */
public class Ui {
    private static final String HELLO = "Hello, I'm Talk-a-Bot!\nWhat can I do for you?";
    private static final String GOODBYE = "Bye. Hope to see you again soon!";
    private Scanner sc;

    /**
     * Constructs an instance of the Ui class.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints out hello line.
     */
    public void printHello() {
        System.out.println(HELLO);
    }

    /**
     * Returns Goodbye line.
     *
     * @return String representation of Goodbye line.
     */
    public String getGoodbye() {
        return GOODBYE;
    }

    /**
     * Returns response indicating the task being added, as well as current total tasks.
     *
     * @param task Task to be added.
     * @param total Total number of tasks after addition.
     * @return String representation of response to adding task.
     */
    public String addTask(Task task, int total) {
        return String.format("Got it. I've added this task:\n%s\nto your list!"
                + "\nYou now have " + total + " tasks.", task);
    }

    /**
     * Returns next line input by the user.
     *
     * @return User input.
     */
    public String getLine() {
        return this.sc.nextLine();
    }

    /**
     * Returns the current list of tasks.
     *
     * @param taskList Current list of tasks.
     * @return String representation of list of tasks.
     */
    public String displayList(TaskList taskList) {
        String output = "Here's your to-do list:";
        for (int i = 0; i < taskList.size(); i++) {
            output += String.format("\n%d. ", i + 1) + taskList.get(i);
        }
        return output;
    }

    /**
     * Returns response indicating the task being marked.
     *
     * @param task Task to be marked.
     * @return String representation of response to task being marked.
     */
    public String mark(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Returns response indicating the task being unmarked.
     *
     * @param task Task to be unmarked.
     * @return String representation of response to task being unmarked.
     */
    public String unmark(Task task) {
        return "No problem! I've marked this task as not done yet:\n" + task;
    }

    /**
     * Returns response indicating the task being deleted and the new total number of tasks.
     *
     * @param task Task to be deleted.
     * @param total Total number of tasks after deletion.
     * @return String representation of response to task being deleted.
     */
    public String delete(Task task, int total) {
        return "Got it! I've removed this task:\n" + task
                + "\nYou now have " + total + " tasks in total!";
    }

    /**
     * Returns error message.
     *
     * @param message Error message.
     * @return Error message to be output.
     */
    public String error(String message) {
        return "Wait wait wait...hold on...\n" + message;
    }

    /**
     * Returns the task's important date(s) as days of the week.
     *
     * @param task Task to get important day(s) of.
     * @throws WrongTaskTypeException If task is a ToDo.
     */
    public String getDay(Task task) throws WrongTaskTypeException {
        if (task instanceof ToDo) {
            throw new WrongTaskTypeException("does not have a deadline!");
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            return "This task is due on a " + d.getDay() + "!";
        } else if (task instanceof Event) {
            Event e = (Event) task;
            return "This task occurs from " + e.getDay() + "!";
        }
        throw new UnknownInputException("Huh");
    }

    /**
     * Prints out a dashed line.
     */
    public void printDashedLine() {
        StringBuilder sb = new StringBuilder(100);
        for (int n = 0; n < 100; ++n) {
            sb.append('-');
        }
        System.out.println(sb);
    }

    /**
     * Returns all the tasks matching the user's input.
     *
     * @param taskList List of matching tasks.
     * @return String representation of all matching tasks.
     */
    public String returnMatches(TaskList taskList) {
        String output = "Here are the matching tasks in your to-do list:";
        for (int i = 0; i < taskList.size(); i++) {
            output += String.format("\n%d. ", i + 1) + taskList.get(i);
        }
        return output;
    }

}
