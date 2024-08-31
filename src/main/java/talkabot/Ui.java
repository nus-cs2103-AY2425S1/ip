package talkabot;

import talkabot.exceptions.WrongTaskTypeException;
import talkabot.task.Deadline;
import talkabot.task.Event;
import talkabot.task.Task;
import talkabot.task.TaskList;
import talkabot.task.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private static final String HELLO = "Hello, I'm Talk-a-Bot!\nWhat can I do for you?";
    private static final String GOODBYE = "Bye. Hope to see you again soon!";

    public void hello() {
        System.out.println(HELLO);
    }

    public void goodbye() {
        System.out.println(GOODBYE);
    }

    public void echo(Task task, int total) {
        System.out.println(String.format("Got it. I've added this task:\n%s\nto your list!"
                        + "\nYou now have " + total + " tasks.", task));
    }

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String getLine() {
        return this.sc.nextLine();
    }

    public void displayList(TaskList taskList) {
        String output = "Here's your to-do list:";
        for (int i = 0; i < taskList.size(); i++) {
            output += String.format("\n%d. ", i + 1) + taskList.get(i);
        }
        System.out.println(output);
    }

    public void mark(Task task) {
        System.out.println("Nice! I've marked this talkabot.task as done:\n" + task);
    }

    public void unmark(Task task) {
        System.out.println("No problem! I've marked this talkabot.task as not done yet:\n" + task);
    }

    public void delete(Task task, int total) {
        System.out.println("Got it! I've removed this talkabot.task:\n" + task +
                "\nYou now have " + total + " tasks in total!");
    }

    public void error(String message) {
        System.out.println(message);
    }

    public void getDay(Task task) {
        if (task instanceof ToDo) {
            throw new WrongTaskTypeException("does not have a deadline!");
        } else if (task instanceof Deadline) {
            Deadline d = (Deadline) task;
            System.out.println("This talkabot.task is due on a " + d.getDay() + "!");
        } else if (task instanceof Event) {
            Event e = (Event) task;
            System.out.println("This talkabot.task occurs from " + e.getDay() + "!");
        }
    }

    public void dashedLine() {
        StringBuilder sb = new StringBuilder(100);
        for(int n = 0; n < 100; ++n)
            sb.append('-');
        System.out.println(sb.toString());
    }

    /**
     * Prints all the tasks matching the user's input
     *
     * @param taskList list of matching tasks
     */
    public static void returnMatches(TaskList taskList) {
        String output = "Here are the matching tasks in your to-do list:";
        for (int i = 0; i < taskList.size(); i++) {
            output += String.format("\n%d. ", i + 1) + taskList.get(i);
        }
        System.out.println(output);
    }

}
