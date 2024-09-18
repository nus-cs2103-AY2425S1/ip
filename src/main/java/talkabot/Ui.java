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
    private static final String HELLO = "Hiya, pal! I'm Talk-a-Bot, welcome to the Clubhouse!\n"
            + "How can I help you on this fine day?";
    private static final String GOODBYE = "Aw, shucks. Alright then...\n"
            + "See ya real soon!";
    private Scanner sc;

    /**
     * Constructs an instance of the Ui class.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
    }

    /**
     * Returns Hello line.
     *
     * @return String representation of Hello line.
     */
    public static String getHello() {
        return HELLO;
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
        return String.format("Gosh! Another task to complete?\nWe've got a hustler in the Clubhouse!\n"
                + "Alright buddy, I've added this task:\n  %s\nto your list!"
                + "\nYou now have " + total + " tasks in total!", task);
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
        String output = "No problem at all, pal! Here's your to-do list:";
        for (int i = 0; i < taskList.size(); i++) {
            output += String.format("\n  %d. ", i + 1) + taskList.get(i);
        }
        output += "\nYou're such a go-getter!";
        return output;
    }

    /**
     * Returns response indicating the task being marked.
     *
     * @param task Task to be marked.
     * @return String representation of response to task being marked.
     */
    public String mark(Task task) {
        return "Hot dog! Gee, I really admire your determination!\n"
                + "Another task down:\n  " + task;
    }

    /**
     * Returns response indicating the task being unmarked.
     *
     * @param task Task to be unmarked.
     * @return String representation of response to task being unmarked.
     */
    public String unmark(Task task) {
        return "Aw, shucks. No worries, I've marked this task as not done yet:\n  " + task
                + "\nIt's alright, buddy!\nWith your dedication, I'm sure it'll be done in no time!";
    }

    /**
     * Returns response indicating the task being deleted and the new total number of tasks.
     *
     * @param task Task to be deleted.
     * @param total Total number of tasks after deletion.
     * @return String representation of response to task being deleted.
     */
    public String delete(Task task, int total) {
        return "Sure thing, no biggie! I've removed this task:\n  " + task
                + "\nYour list looks much neater now, pal!\nYou now have " + total + " tasks in total.";
    }

    /**
     * Returns error message.
     *
     * @param message Error message.
     * @return Error message to be output.
     */
    public String error(String message) {
        return "Hoo boy, I'm a bit lost!\n" + message;
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
            return "Gee whiz! Looks like this task is due on a " + d.getDay() + "!";
        } else if (task instanceof Event) {
            Event e = (Event) task;
            return "Wowee! Looks like this task occurs from " + e.getDay() + "!";
        }
        throw new UnknownInputException();
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
        String output = "Not a problem, Talk-a-Bot's here to help!\n"
                + "Alright pal, here's what I found based on your search:";
        for (int i = 0; i < taskList.size(); i++) {
            output += String.format("\n  %d. ", i + 1) + taskList.get(i);
        }
        return output;
    }

}
