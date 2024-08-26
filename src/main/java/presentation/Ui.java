package presentation;

import tasks.Task;

import java.util.ArrayList;

public class Ui {
    public Ui() {}

    public void greetDialog() {
        String logo =
                " __\n"
                        + "| |    _   _ _  _____\n"
                        + "| |   | | | | |/ / _ \\\n"
                        + "| |__ | |_| |   <  __/\n"
                        + "|____| \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Yo! I'm Luke.\nHow's it hanging?");
    }

    public void closingDialog() {
        System.out.println("Aight, Cya later.");
    }

    public void addTaskDialog(Task t, int size) {
        System.out.println("Got it. I've added this task:");
        System.out.println(t);
        System.out.printf("Now you have %d tasks in the list.\n", size);
    }

    public void deleteTaskDialog(Task t, int size) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(t);
        System.out.printf("Now you have %d tasks in the list.\n", size);
    }

    public void listTaskDialog() {
        System.out.println("Here are the tasks in your list:");
    }

    public void markDialog(Task t) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(t);
    }

    public void unMarkDialog(Task t) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(t);
    }

    public void findDialog(ArrayList<Task> foundTasks) {
        for (int i = 0; i < foundTasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, foundTasks.get(i).toString());
        }
    }
}
