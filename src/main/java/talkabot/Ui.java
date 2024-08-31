package talkabot;

import talkabot.exceptions.WrongTaskTypeException;
import talkabot.task.Deadline;
import talkabot.task.Event;
import talkabot.task.Task;
import talkabot.task.TaskList;
import talkabot.task.ToDo;

import java.util.Scanner;

/**
 * Ui Class handles interactions with the user,
 * printing out responses based on what is done by Talk-a-Bot.
 */
public class Ui {
    private Scanner sc;
    private static final String HELLO = "Hello, I'm Talk-a-Bot!\nWhat can I do for you?";
    private static final String GOODBYE = "Bye. Hope to see you again soon!";

    /**
     * Prints out hello line.
     */
    public void hello() {
        System.out.println(HELLO);
    }

    /**
     * Prints out goodbye line.
     */
    public void goodbye() {
        System.out.println(GOODBYE);
    }

    /**
     * Prints out the task being added, as well as current total tasks.
     */
    public void echo(Task task, int total) {
        System.out.println(String.format("Got it. I've added this task:\n%s\nto your list!"
                        + "\nYou now have " + total + " tasks.", task));
    }

    /**
     * Constructor for Ui class.
     */
    public Ui() {
        this.sc = new Scanner(System.in);
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
     * Prints out the current list of tasks.
     */
    public void displayList(TaskList taskList) {
        String output = "Here's your to-do list:";
        for (int i = 0; i < taskList.size(); i++) {
            output += String.format("\n%d. ", i + 1) + taskList.get(i);
        }
        System.out.println(output);
    }

    /**
     * Prints out the task being marked.
     */
    public void mark(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    /**
     * Prints out the task being unmarked.
     */
    public void unmark(Task task) {
        System.out.println("No problem! I've marked this task as not done yet:\n" + task);
    }

    /**
     * Prints out the task being deleted and the new total number of tasks.
     */
    public void delete(Task task, int total) {
        System.out.println("Got it! I've removed this task:\n" + task +
                "\nYou now have " + total + " tasks in total!");
    }

    /**
     * Prints out the error message.
     */
    public void error(String message) {
        System.out.println(message);
    }

    /**
     * Prints out the task's important date as days of the week.
     *
     * @throws WrongTaskTypeException If task is a ToDo.
     */
    public void getDay(Task task) {
        if (task instanceof ToDo) {
            throw new WrongTaskTypeException("does not have a deadline!");
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            System.out.println("This task is due on a " + d.getDay() + "!");
        } else if (task instanceof Event) {
            Event e = (Event) task;
            System.out.println("This task occurs from " + e.getDay() + "!");
        }
    }

    /**
     * Prints out a dashed line.
     */
    public void dashedLine() {
        StringBuilder sb = new StringBuilder(100);
        for(int n = 0; n < 100; ++n)
            sb.append('-');
        System.out.println(sb);
    }

}
