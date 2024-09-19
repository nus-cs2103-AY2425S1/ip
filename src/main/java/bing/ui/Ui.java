package bing.ui;

import java.util.Scanner;
import bing.task.TaskList;

public class Ui {

    private Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println("______________________________");
        System.out.println("Hi! My name is bing.Bing");
        System.out.println("How can I help you?");
        System.out.println("______________________________");
    }

    public void showBye() {
        System.out.println("______________________________");
        System.out.println("Bye. Have a good day.");
        System.out.println("______________________________");
    }

    public void showByeMessage() {
        System.out.println("______________________________\n"
                + "Bye. Have a good day.\n"
                + "______________________________\n");
    }

    public void showTasks(TaskList taskList) {
        System.out.println("______________________________");
        System.out.println("All tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + ". " + taskList.get(i).toString());
        }
        System.out.println("______________________________");
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

}
