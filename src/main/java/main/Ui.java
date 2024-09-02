package main;

import task.Task;

public class Ui {
    public Ui() {

    }

    public void displayGreetingMessage() {
        String greeting = "________________________________\n"
                + "Hello! I'm main.Bean\n"
                + "What can i do for you?\n"
                +"________________________________";
        System.out.println(greeting);
    }

    public void displayByeMessage() {
        String byeMsg =
                "________________________________\n"
                        + "Bye. Hope to see you again soon!\n"
                        + "________________________________";
        System.out.println(byeMsg);
    }

    public void displayAddMessage(Task task, int size) {
        String output = "________________________________\n" + "Got it. I've added this task:";
        System.out.println(output);
        System.out.println(task.getString());
        output = "Now you have " + String.valueOf(size) + " tasks in the list.\n" + "________________________________";
        System.out.println(output);
    }

    public void displayMarkedMessage(Task task) {
        System.out.println("________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.getString());
        System.out.println("________________________________");
    }

    public void displayDeletedMessage(Task task, int size) {
        System.out.println("________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.getString());
        System.out.println( "Now you have " + String.valueOf(size)
                + " tasks in the list.\n"
                    + "________________________________");
    }
}
