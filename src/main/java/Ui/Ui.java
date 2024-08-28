package Ui;

import java.util.List;
import Task.*;
public class Ui {
    public void uiGreet() {
        System.out.println("Hello! I'm Daniel\nWhat can I do for you?\n");
    }
    public void uiBye() {
        System.out.println("Bye hope to see you again soon");
    }
    public static void uiList(List<Task> array) {
        System.out.println("Here are the tasks in your list");
        int i = 1;
        for ( Task element : array) {
            System.out.println(i + "." + element.toString());
            i += 1;
        }
    }
    public static void uiTodo(int size, Todo x) {
        System.out.println("Got it. I've added this task:\n" + x.toString());
        System.out.println("Now you have " + size + " task in the list");
    }
    public static void uiDeadline(int size, Deadline x) {
        System.out.println("Got it. I've added this task;\n" + x.toString());
        System.out.println("Now you have " + size + " task in the list");
    }
    public static void uiEvent(int size, Event x) {
        System.out.println("Got it. I've added this task;\n" + x.toString());
        System.out.println("Now you have " + size + " task in the list");
    }
    public static void uiMark(Task t) {
        System.out.println("Nice I have marked this task as done:");
        System.out.println(t.toString());
    }
    public static void uiUnMark(Task t) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t.toString());
    }
    public static void uiDelete(Task t, int size) {
        System.out.println("Noted. I've removed this task:\n" + t.toString());
        System.out.println("Now you have " + size +" tasks in the list.");
    }
}
