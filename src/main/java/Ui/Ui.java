package Ui;

import java.util.List;
import Task.*;
public class Ui {
    public String uiGreet() {
        return "Hello! I'm Daniel\nWhat can I do for you?\n";

    }
    public String uiBye() {
        return "Bye hope to see you again soon";
    }
    public static String uiList(List<Task> array) {
        String s = "Here are the tasks in your list\n";
        int i = 1;
        for ( Task element : array) {
            s = s + "." + element.toString() + "\n";
            i += 1;
        }
        return s;
    }
    public static String uiTodo(int size, Todo x) {
        return "Got it. I've added this task:\n" + x.toString() + "\n"
                + "Now you have " + size + " task in the list";
    }
    public static String uiDeadline(int size, Deadline x) {
        return "Got it. I've added this task;\n" + x.toString()
                + "\n" + "Now you have " + size + " task in the list";
    }
    public static String uiEvent(int size, Event x) {
        return "Got it. I've added this task;\n" + x.toString() + "\n"
                + "Now you have " + size + " task in the list";
    }
    public static String uiMark(Task t) {
        return "Nice I have marked this task as done:\n" + t.toString();
    }
    public static String uiUnMark(Task t) {
        return "OK, I've marked this task as not done yet:\n" + t.toString();
    }
    public static String uiDelete(Task t, int size) {
        return "Noted. I've removed this task:\n" + t.toString() + "Now you have " + size +" tasks in the list.";
    }
}
