package kietwoforone.ui;

import kietwoforone.exceptions.KieTwoForOneException;
import kietwoforone.tasks.Task;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    private static String separationLine = "_________________________________________";
    private static String chatBotName = "KieTwoForOne";
    private static Scanner scanner;

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public static String readCommand() {
        return scanner.nextLine();
    }

    public static void showLine() {
        System.out.println(separationLine);
    }

    public static void showWelcome() {
        showLine();
        System.out.println("Hello! I'm " + chatBotName + ".");
        System.out.println("What can I do for you?");
        showLine();
    }

    public void showAddTasks(ArrayList<Task> tasks, Task newTask) {
        System.out.println("Got it. I've added this task:");
        System.out.println("    " + newTask);
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
    }

    public void showDeleteTask(ArrayList<Task> tasks, Task removedTask) {
        System.out.println("Noted. I've removed the task");
        System.out.println("    " + removedTask);
        System.out.println(String.format("Now you have %d tasks in the list.", tasks.size()));
    }

    public void showTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, tasks.get(i).toString()));
        }
    }

    public void showMarkTask(String task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("    " + task);
    }

    public void showUnmarkTask(String task) {
        System.out.println("OK. I've marked this task as incomplete:");
        System.out.println("    " + task);
    }

    public void showSameDate(ArrayList<Task> tasks, String date) throws KieTwoForOneException {
        ArrayList<Task> taskList = new ArrayList<>(100);
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (currTask.compareDate(date)) {
                taskList.add(currTask);
            }
        }
        System.out.println("Here are the tasks occurring on this date:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, taskList.get(i).toString()));
        }
    }

    public void showMatchingTask(ArrayList<Task> tasks, String keyword) {
        ArrayList<Task> taskList= new ArrayList<>(100);
        for (int i = 0; i < tasks.size(); i++) {
            Task currTask = tasks.get(i);
            if (currTask.compareString(keyword)) {
                taskList.add(currTask);
            }
        }
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(String.format("%d. %s", i + 1, taskList.get(i).toString()));
        }
    }

    public void showErrorMessage(KieTwoForOneException e) {
        System.out.println(e.getMessage());
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

}
