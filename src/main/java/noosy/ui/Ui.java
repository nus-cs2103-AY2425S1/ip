package noosy.ui;

import noosy.task.Task;
import noosy.task.TaskList;

import java.util.Scanner;

public class Ui {

    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void showLine() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public void showWelcome() {
        System.out.println("Heyo! This is Noosy! \n" +
                "Noosy is da best, tell me what you need! :>");
    }

    public void showGoodbye() {
        System.out.println("Alright, hope that helped. \n" +
                "See ya!");
    }

    public void showTaskAdded(TaskList tasks, Task task) {
        System.out.println("I added it to the list! \n" + task);
        System.out.println("We've now got " + tasks.size() + " tasks in the bucket!");
    }

    public void showTaskDone(Task done) {
        System.out.println("Hooray you've done this: \n" + done);
    }

    public void showUndone(Task undone) {
        System.out.println("Ok don't worry, you can continue working on this: \n" + undone);
    }

    public void showDeleted(Task deleted) {
        System.out.println("I've kicked it out for you: \n" + deleted);
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("Heyo here are the task(s) we have: \n");
        tasks.printTasks();
    }

    public void showInvalidDate() {
        System.out.println("Please enter the date in the format yyyy-MM-dd:");
    }

    public void showInvalidTime() {
        System.out.println("Please enter the start time in the format yyyy-MM-dd HH:mm:");
    }

    public void showIncompleteCommand() {
        System.out.println("I think you forgot to mention something...");
    }

    public void showInvalidCommand() {
        System.out.println("Beep Boop, Noosy no understand");
    }

    public void showError(String errorMsg) {
        System.out.println(errorMsg);
    }
    public void showLoadingError() {
        System.out.println("Error loading tasks");
    }
}
