import java.util.ArrayList;
import java.util.Scanner;

public class Ui {
    private Scanner input;

    public Ui() {
        input = new Scanner(System.in);
    }
    protected void markdoneMessage(Tasks task) {
        chatBox("Nice! I've marked this task as done:\n" + task);
    }

    protected void unmarkDoneMessage(Tasks task) {
        chatBox("OK, I've marked this task as not done yet:\n" + task);
    }

    protected void deleteItemMessage(Tasks task,int size) {
        chatBox("Ok i have removed the following item\n" + task + "\n" + "You have " + size + " tasks left");
    }

    protected void loadingError() {
        chatBox("You don't have any data right now.Let me create a new TaskList");
    }
    protected void produceList(String list) {
        chatBox(list);
    }

    protected void chatBox(String str) {
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

    protected void addList(Tasks task, int size) {
        chatBox("added: " + task + "\n" + "You have " + size + " tasks in the list");
    }

    protected void showWelcomeMessage() {
        chatBox("Hello! I'm BeeBoo\nWhat can i do for you?");
    }

    protected void byeMessageMessage() {
        chatBox("Bye.Hope to see you again soon!");
    }

    protected void showError(BeeBooExceptions e) {
        chatBox(e.toString());
    }

    protected String handleCommand() {
        if (input == null) {  // Ensure Scanner is initialized
            input = new Scanner(System.in);
        }
        return input.nextLine().trim().toLowerCase();
    }

    protected void close() {
        input.close();
    }
}
