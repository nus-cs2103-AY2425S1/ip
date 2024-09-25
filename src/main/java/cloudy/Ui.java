package cloudy;


/**
 * The Ui class handles all interactions with the user.
 * It is responsible for displaying messages, including greetings, task lists,
 * and error messages, in a formatted manner.
 */

import java.util.ArrayList;

public class Ui {

    /**
     * Displays the greeting message when the program starts.
     */
    public void showGreeting() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Cloudy.");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the farewell message when the user exists the program.
     */
    public void showBye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Displays the list of tasks in the user's task list.

    public String showList(TaskList tasks) {
        return "Here are the tasks in your list:" +
        for (int i = 0; i < tasks.size(); i++) {
            (i + 1) + ". " + tasks.getTask(i).printTaskOnList();
        }
    }
    */

    public void showMark(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task.printTaskOnList());
        System.out.println("____________________________________________________________");
    }

    public void showUnmark(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task.printTaskOnList());
        System.out.println("____________________________________________________________");
    }

    public void showAddTask(Task task, int totalTasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task.printTaskOnList());
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void showDeleteTask(Task task, int totalTasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void showFindTask(ArrayList<Task> tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).printTaskOnList());
        }
        System.out.println("____________________________________________________________");
    }

    public void showInvalidCommand() {
        System.out.println("____________________________________________________________");
        System.out.println("Invalid command. Try again.");
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("____________________________________________________________");
        System.out.println("Error occurred while loading tasks.");
        System.out.println("____________________________________________________________");
    }

    public void showInvalidTaskFormat() {
        System.out.println("____________________________________________________________");
        System.out.println("Invalid task format. Please enter a valid task.");
        System.out.println("____________________________________________________________");
    }

    public void showInvalidTaskNum() {
        System.out.println("____________________________________________________________");
        System.out.println("Please enter a valid task number.");
        System.out.println("____________________________________________________________");
    }

    public void showInvalidDeadline() {
        System.out.println("____________________________________________________________");
        System.out.println("Invalid format. Please enter a deadline with the format [task] /by dd/mm/yyyy.");
        System.out.println("____________________________________________________________");
    }

    public void showInvalidEvent() {
        System.out.println("____________________________________________________________");
        System.out.println(
                "Invalid format. Please enter an event with the format [event] /from dd/mm/yyyy /to dd/mm/yyyy");
        System.out.println("____________________________________________________________");
    }

}
