package processes;

import tasks.Task;

import java.util.Scanner;
import java.util.ArrayList;

public class Ui {

    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }


    public static void printLine () {
        int length = 75;
        for (int i = 0; i < length; i++) {
            System.out.print('-');
            if (i == length - 1) {
                System.out.println();
            }
        }
    }

    public void showWelcomeMessage(String name) {
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you? \n");
        printLine();
    }

    public void showGoodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon! \n");
        printLine();
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showTaskList(ArrayList<Task> taskList) {
        System.out.println("Your current tasks are: ");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i).toString());
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
        printLine();
    }

    public void close() {
        scanner.close();
    }
}