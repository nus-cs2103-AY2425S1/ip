package seanbot;

import java.util.List;

import seanbot.Tasks.Task;

/**
 * The UI class for words and decoration to the chat
 */
public class Ui {

    // Displays the welcome message.
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm SeanBot\n" + "What can I do for you?");
    }

    // Displays the exit message.
    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    // Displays an error message.
    public void showError(String message) {
        System.out.println("OOPS!!! " + message);
    }

    // Displays the current list of tasks.
    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
    }
    
    // Displays a list of tasks found by a keyword search.
    public void showFoundTasks(List<Task> foundTasks) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < foundTasks.size(); i++) {
            System.out.println((i + 1) + ". " + foundTasks.get(i));
        }
    }
}