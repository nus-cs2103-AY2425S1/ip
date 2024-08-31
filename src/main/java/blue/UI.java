package blue;

import blue.task.Task;

import java.util.ArrayList;

public class UI {

    public static void greet() {
        System.out.println("--------------------------------------------");
        System.out.println("Hello! I'm blue.Blue! Woof Woof! Yap Yap!");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------------------");
    }
    public static void farewell() {
        System.out.println("Bye Bye! Hope to see you again soon!");
        System.out.println("_     /)---(\\          /~~~\\");
        System.out.println("\\\\   (/ . . \\)        /  .. \\");
        System.out.println(" \\\\__)-\\(*)/         (_,\\  |_)");
        System.out.println(" \\_       (_         /   \\@/    /^^^\\");
        System.out.println(" (___/-(____) _     /      \\   / . . \\");
        System.out.println("              \\\\   /  `    |   V\\ Y /V");
        System.out.println("               \\\\/  \\   | _\\    / - \\");
        System.out.println("                \\   /__'|| \\\\_  |    \\");
        System.out.println("                 \\_____)|_).\\_).||(__V");
        System.out.println("--------------------------------------------");
    }

    public static void displayAfterMark(Task task) {
        System.out.println("--------------------------------------------");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.toString());
        System.out.println("--------------------------------------------");
    }

    public static void displayAfterUnMark(Task task) {
        System.out.println("--------------------------------------------");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.toString());
        System.out.println("--------------------------------------------");
    }

    public static void displayAfterDelete(Task task, int noOfTask) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        String result = "Now you have " + noOfTask + " tasks in the list.";
        System.out.println(result);
        System.out.println("____________________________________________________________");
    }

    public static void displayList(ArrayList<Task> myList, int noOfTask) {
        System.out.println("Here are the tasks in your list:");
        System.out.println("--------------------------------------------");
        for (int i = 0; i < noOfTask; i++) {
            String result = i + 1 + ". " + myList.get(i).toString();
            System.out.println(result);
        }
        System.out.println("--------------------------------------------");
    }

}
