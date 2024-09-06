package Ui;

import java.util.List;
import Task.*;
public class Ui {
    public String uiGreet() {
        String s = "Hello! I'm Daniel\nWhat can I do for you?\n";
        System.out.println("Hello! I'm Daniel\nWhat can I do for you?\n");
        return s;

    }
    public String uiBye() {
        String s = "Bye hope to see you again soon";
        System.out.println("Bye hope to see you again soon");
        return s;
    }
    public static String uiList(List<Task> array) {
        String s = "Here are the tasks in your list\n";
        System.out.println("Here are the tasks in your list");
        int i = 1;
        for ( Task element : array) {
            System.out.println(i + "." + element.toString());
            s = s + "." + element.toString() + "\n";
            i += 1;
        }
        return s;
    }
    public static String uiTodo(int size, Todo x) {
        String s = "Got it. I've added this task:\n" + x.toString() + "\n"
                + "Now you have " + size + " task in the list";
        System.out.println("Got it. I've added this task:\n" + x.toString());
        System.out.println("Now you have " + size + " task in the list");
        return s;
    }
    public static String uiDeadline(int size, Deadline x) {
        String s = "Got it. I've added this task;\n" + x.toString()
                + "\n" + "Now you have " + size + " task in the list";
        System.out.println("Got it. I've added this task;\n" + x.toString());
        System.out.println("Now you have " + size + " task in the list");
        return s;
    }
    public static String uiEvent(int size, Event x) {
        String s = "Got it. I've added this task;\n" + x.toString() + "\n"
                + "Now you have " + size + " task in the list";
        System.out.println("Got it. I've added this task;\n" + x.toString());
        System.out.println("Now you have " + size + " task in the list");
        return s;
    }
    public static String uiMark(Task t) {
        String s = "Nice I have marked this task as done:\n" + t.toString();
        System.out.println("Nice I have marked this task as done:");
        System.out.println(t.toString());
        return s;
    }
    public static String uiUnMark(Task t) {
        String s = "OK, I've marked this task as not done yet:\n" + t.toString();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t.toString());
        return s;
    }
    public static String uiDelete(Task t, int size) {
        String s = "Noted. I've removed this task:\n" + t.toString() + "Now you have " + size +" tasks in the list.";
        System.out.println("Noted. I've removed this task:\n" + t.toString());
        System.out.println("Now you have " + size +" tasks in the list.");
        return s;
    }
}
