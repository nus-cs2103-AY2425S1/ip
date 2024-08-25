package ui;

import tasks.Task;

public class Ui {
    public void display(String s) {
        System.out.print(s);
        printDivider();
    }

    public void displayWelcome() {
        this.display("Hello! I'm Thanos!\nWhat can I do for you?\n");
    }

    public void displayTaskAdded(Task task, int size) {
        this.display(String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.\n", task, size
        ));
    }

    private void printDivider() {
        System.out.println("-".repeat(50));
    }
}
