package seanbot;

public class Ui {

    public void showWelcomeMessage() {
        System.out.println("Hello! I'm SeanBot\n" + "What can I do for you?");
    }

    public void showExitMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showError(String message) {
        System.out.println("OOPS!!! " + message);
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.getTask(i));
        }
    }
}
