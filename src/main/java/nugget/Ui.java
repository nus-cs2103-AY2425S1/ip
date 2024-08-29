package nugget;

import nugget.Task;

public class Ui {

    public void line() {
        System.out.println("________________________________________");
    }
    public void printIntro() {
        System.out.println("________________________________________");
        System.out.println("Hello! I'm nugget.Nugget");
        System.out.println("What can I do for you?");
        System.out.println("________________________________________");
    }

    public void printEnd() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("________________________________________");
    }

    public void showError(String message) {
        System.out.println("________________________________________");
        System.out.println(message);
        System.out.println("________________________________________");
    }

    public void showTaskAdded(Task task, int taskCount) {
        System.out.println("________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("________________________________________");
    }

    public void showTaskRemoved(Task task, int taskCount) {
        System.out.println("________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println("________________________________________");
    }

    public void showMarkedTask(Task task) {
        System.out.println("________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        System.out.println("________________________________________");
    }

    public void showUnmarkedTask(Task task) {
        System.out.println("________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
        System.out.println("________________________________________");
    }
}
