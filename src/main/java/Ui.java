import java.util.ArrayList;
import java.util.Scanner;
public class Ui {

    Scanner scanner = new Scanner(System.in);
    public Ui(){}

    public void showGreeting() {
        System.out.println("\nRoads? Where We're Going, We Don't Need Roads?! So, what can I help you with today?");
    }

    public void showLine() {
        System.out.println("——————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————");
    }

    public void showEnd() {
        System.out.println("Your future is whatever you make it, so make it a good one! Until next time, keep the DeLorean ready!");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showTaskCreation(Task task) {
        System.out.println(task.toUIString());
        System.out.println(task);
    }

    public void showMarkTask(Task markTask) {
        System.out.println("Task complete! If my calculations are correct, when this baby hits 88 miles per hour... you're gonna see some serious progress!\n");
        System.out.println(markTask.toString());
    }

    public void showUnmarkTask(Task unmarkTask) {
        System.out.println("Looks like we're going back to the future—task unmarked! Time to revisit this one.\n");
        System.out.println(unmarkTask.toString());
    }

    public void showList() {
        System.out.println("Here’s your list! Like Doc Brown’s flux capacitor, this will help keep everything in perfect sync!\n");
    }

    public void showDeleteTask(Task deleteTask) {
        System.out.println("That task's history has been erased! Just like Marty’s fading photo—it's gone, like it never existed!\n");
        System.out.println(deleteTask.toString());
    }

    public void showCount(TaskList tasks) {
        System.out.println("\nYour total count is now " + tasks.getCount() + "! Like the time circuits, everything's in sync – keep those tasks ticking along!\"");
    }

    public void showDefault() {
        System.out.println("I’m from the future, and even I don’t know what that means.");
    }

    public String readCommand() {
        String input = scanner.nextLine();
        return input;
    }

    public void closeCommand() {
        scanner.close();
    }
}
