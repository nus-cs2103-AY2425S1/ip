package main;

import exception.CommandFoundButInvalidException;
import task.Task;

import java.util.List;

public class Ui {
    private StringBuilder message;
    private String display;
    public Ui() {
        this.message = new StringBuilder();
    }

    public void showWelcome() {
        message.append("Welcome to main.Hyperion!");
        System.out.println(this.message.toString());
    }
    public void showList(List<Task> allTasks) throws CommandFoundButInvalidException {
        String initialValues = new ListAll("", allTasks).message();
        System.out.println(initialValues);
    }
    public void displayError(String message) {
        System.out.println(message);
    }

    public String deleteMessage(Task t, int size) {
        String s1 = "Noted. I've removed this task:";
        String s2 = String.format("Now you have %d tasks in the list", size);
        return s1 + "\n" + " " + t.toString() + "\n" + s2;
    }

    public String addedMessage(Task t, int size) {
        String str1 = "Got it. I've added this task:\n";
        String str2 = String.format("Now you have %d tasks in the list", size);
        return str1 + t.toString() + "\n" + str2;
    }

    public String markedMessage(Task t) {
        return "Nice! I've marked this task as done:\n" + t.toString();
    }

    public String unmarkedMessage(Task t) {
        return "OK, I've marked this task as not done yet:\n" + t.toString();
    }

    public String bye() {
        return "Bye. Hope to see you again soon!";
    }
}
