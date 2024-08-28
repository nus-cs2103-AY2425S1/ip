package ui;

import task.Task;

public class Ui {
    public void sendMessage(String message) {
        System.out.println("[Bob] " + message);
    }

    public void introBobUi() {
        sendMessage("Hello! I'm Bob!");
        sendMessage("What can I do for you?");
    }

    public void exitBobUi() {
        sendMessage("Bye. Hope to see you again soon!");
    }
    public void addTaskUi(Task t, int size) {
        sendMessage("Got it. I've added this task:");
        sendMessage(t.toString());
        sendMessage("Now you have " + size + " tasks in the list.");
    }

    public void markTaskUI(Task t) {
        sendMessage("Nice! I've marked this task as done:");
        sendMessage(t.toString());
    }

    public void unmarkTaskUI(Task t) {
        sendMessage("OK, I've marked this task as not done yet:");
        sendMessage(t.toString());
    }

    public void deleteTaskUI(Task t, int size) {
        sendMessage("Noted. I've removed this task:");
        sendMessage(t.toString());
        sendMessage("Now you have " + size + " tasks in the list.");
    }

    public void savingError() {
        sendMessage("An error has occurred when trying to save.");
    }

    public void errorMessageUi(Exception e) {
        sendMessage(e.getMessage());
    }
}
