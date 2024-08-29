package xbot.ui;

import java.util.Scanner;
import xbot.TaskList;
import xbot.XBotException;

public class Ui {
    private Scanner scanner = new Scanner(System.in);

    public void showTaskList(TaskList list) {
        if (list.size() == 0) {
            System.out.println("Yayy!! You have no task in your list");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.size(); i++) {
                int index = i + 1;
                System.out.println(index + ". " + list.get(i).toString());
            }
        }
    }

    public void showWelcome() {
        System.out.println("Hello! I'm XBot\n" + "What can I do for you?");
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public String readCommand() {
        return scanner.nextLine().trim();
    }

    public void mainErrorMessage(XBotException e) {
        System.out.println("Oh No!! " + e.getMessage());
    }

    public void close() {
        scanner.close();
    }
}
