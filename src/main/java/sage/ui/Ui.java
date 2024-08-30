package sage.ui;

import sage.task.TaskList;

import java.util.Scanner;

public class Ui {
    public void showWelcomeMessage() {
        System.out.println("______________________________________________________");
        System.out.println("Hello! I'm Sage");
        System.out.println("How can I help you today? ;)");
        System.out.println("______________________________________________________");
    }

    public void showGoodbyeMessage() {
        System.out.println("______________________________________________________");
        System.out.println(" " + "Bye!! Hope you come and visit again soon!");
        System.out.println("______________________________________________________");
    }

    public void showMessage(String message) {
        System.out.println("______________________________________________________");
        System.out.println(message);
        System.out.println("______________________________________________________");
    }

    public void showTaskList(TaskList tasks) {
        System.out.println("______________________________________________________");
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i <tasks.size(); i++) {
            System.out.println(" " + (i + 1) + ". " + tasks.get(i));
        }
        System.out.println("______________________________________________________");
    }

    public void showLine() {
        System.out.println("______________________________________________________");
    }

    public String readCommand(Scanner scanner) {
        return scanner.nextLine().trim();
    }
}
