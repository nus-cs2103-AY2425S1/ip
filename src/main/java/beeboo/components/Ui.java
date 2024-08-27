package beeboo.components;

import beeboo.exception.BeeBooExceptions;
import beeboo.task.Tasks;

import java.util.Scanner;

public class Ui {
    private Scanner input;

    public Ui() {
        input = new Scanner(System.in);
    }
    public void markdoneMessage(Tasks task) {
        chatBox("Nice! I've marked this task as done:\n" + task);
    }

    public void unmarkDoneMessage(Tasks task) {
        chatBox("OK, I've marked this task as not done yet:\n" + task);
    }

    public void deleteItemMessage(Tasks task,int size) {
        chatBox("Ok i have removed the following item\n" + task + "\n" + "You have " + size + " tasks left");
    }

    public void loadingError() {
        chatBox("You don't have any data right now.Let me create a new TaskList");
    }
    public void produceList(String list) {
        chatBox(list);
    }

    public void chatBox(String str) {
        for (int i = 0; i < 60; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.println(str);
        System.out.println();
        for (int i = 0; i < 60; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    public void addList(Tasks task, int size) {
        chatBox("added: " + task + "\n" + "You have " + size + " tasks in the list");
    }

    public void showWelcomeMessage() {
        chatBox("Hello! I'm BeeBoo\nWhat can i do for you?");
    }

    public void byeMessageMessage() {
        chatBox("Bye.Hope to see you again soon!");
    }

    public void showError(BeeBooExceptions e) {
        chatBox(e.toString());
    }

    public String handleCommand() {
        if (input == null) {  // Ensure Scanner is initialized
            input = new Scanner(System.in);
        }
        return input.nextLine().trim().toLowerCase();
    }

    public void close() {
        input.close();
    }
}
