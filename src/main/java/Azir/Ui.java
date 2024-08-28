package Azir;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ui {
    public void showLine() {
        System.out.println("----------------------------------");
    }

    public void showWelcome() {
        System.out.println("----------------------------------");
        System.out.println("Hello! I'm Azir");
        System.out.println("What can I do for you?");
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String readCommand(Scanner obj) {
        String command = obj.nextLine();
        return command;
    }

    public void showCommandEndMessage() {
        System.out.println("Here are the tasks in your list:");
    }

    public void showCommandEndMessage(String command, String task) {
        switch (command) {
        case "mark":
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task);
            break;

        case "unmark":
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(task);
            break;

        case "delete":
            System.out.println("Noted. I've removed this task:");
            System.out.println(task);
            break;

        case "todo":
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            break;

        case "deadline":
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            break;

        case "event":
            System.out.println("Got it. I've added this task:");
            System.out.println(task);
            break;
        }
    }

    public void showTaskNumber(int size) {
        System.out.printf("Now you have %d %s in the list\n", size, size == 1 ? "task" : "tasks");
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
