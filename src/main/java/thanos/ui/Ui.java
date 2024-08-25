package thanos.ui;

import java.util.ArrayList;

import thanos.tasks.Task;

public class Ui {
    public void display(String s) {
        System.out.print(s);
        printDivider();
    }

    public void displayWelcome() {
        this.display("Hello! I'm Thanos!\nWhat can I do for you?\n");
    }

    public void displayTasks(ArrayList<Task> taskList, String header) {
        if (taskList.isEmpty()) {
            this.display("No tasks found\n");
            return;
        }

        System.out.println(header);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            sb.append(String.format("%d.%s\n", i + 1, task));
        }
        this.display(sb.toString());
    }

    public void displayTaskAdded(Task task, int size) {
        this.display(String.format(
                "Got it. I've added this task:\n  %s\nNow you have %d tasks in the list.\n", task, size
        ));
    }

    public void displayTaskRemoved(Task task, int size) {
        this.display(String.format(
                "Noted. I've removed this task:\n  %s\nNow you have %d tasks in the list.\n", task, size
        ));
    }

    public void displayTaskMarked(Task task) {
        this.display(String.format("Nice! I've marked this task as done:\n  %s\n", task));
    }

    public void displayTaskUnmarked(Task task) {
        this.display(String.format("OK, I've marked this task as not done yet:\n  %s\n", task));
    }

    public static void printDivider() {
        System.out.println("-".repeat(50));
    }
}
