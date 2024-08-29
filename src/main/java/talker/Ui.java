package talker;

import talker.task.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private static final String LINE = "____________________________________________________________";
    private final String name;
    private Scanner scanner;

    public Ui(String name) {
        this.name = name;
        scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void printWelcome() {
        System.out.println(LINE);
        System.out.printf("Hello! I'm %s\n", name);
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

    public void printGoodBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printLine() {
        System.out.println(LINE);
    }

    public void printError(Exception e) {
        System.out.println("Error: " + e.getMessage());
    }

    public void printTaskList(ArrayList<Task> list) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d.%s\n", i + 1, list.get(i));
        }
    }

    public void printTask(Task task) {
        System.out.println(task);
    }

    public void printTaskDelete(Task task, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", size);
    }

    public void printTaskAdd(Task task, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.printf("Now you have %d tasks in the list.\n", size);
    }

    public void printTasksOn(String date) {
        System.out.println("These are your tasks on " + date + ":");
    }

    public void printTaskMarked(String mark) {
        System.out.println(mark);
    }

    public void printTaskUnmarked(String unmark) {
        System.out.println(unmark);
    }
}
