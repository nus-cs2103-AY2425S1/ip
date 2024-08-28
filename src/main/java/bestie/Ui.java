package bestie;// deals with interactions with the user

import java.util.ArrayList;
import java.util.Scanner;
// mostly done??????????
public class Ui {

    private Scanner sc;

    public Ui(Scanner sc) {
        // no arguments in constructort
        this.sc = sc;
    }

    public String readNextCommand() {
        return sc.nextLine();
    }

    public void welcome() {
        // greet user at the start
        System.out.println("Hello! I'm bestie.Bestie, your personal assistant chatbot.");
        System.out.println("Let's get ready to have a productive day!");
        System.out.println("What can I do for you today :)?");
    }

    public void byeBye() {
        System.out.println("Bye. Hope to see you again soon! :)");
    }

    public void displayTasks(ArrayList<Task> tasks) {
        System.out.println("Sure! Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++){
            int index = i + 1;
            System.out.println(index +"." + tasks.get(i).toString());
        }
    }

    public void showTaskAdded(Task task, int size) {
        System.out.println("added: " + task.toString());
        System.out.println("Now you have " + size + " tasks in your list.");
    }

    public void showTaskMarked(Task task) {
        System.out.println("Awesome work! I've marked this task as done.");
        System.out.println("  " + task.toString());
    }

    public void showTaskUnmarked(Task task) {
        System.out.println("Noted! I've marked this task as not done yet:");
        System.out.println("  " + task.toString());
    }

    public void showTaskDeleted(int size) {
        System.out.println("Noted! The task has been removed.");
        if (size == 1) {
            System.out.println("You now have 1 task in your list.");
        } else {
            System.out.println("You now have " + size + " tasks in your list.");
        }
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void showIndexOutOfBoundsMessage(int taskSize) {
        if (taskSize == 1) {
            System.out.println("That task does not exist. There is only 1 task in your list!");
        } else {
            System.out.println("That task does not exist. There are only " + taskSize
                    + " tasks in your list!");
        }
        System.out.println("Please key in a valid index.");
    }

    public void invalidCommand() {
        System.out.println("Invalid command! Please remember to start with \"todo\", \"deadline\" " +
                "or \"event\".\nDouble check your spelling for other common commands like \"unmark\" or \"list\".");
    }


}
