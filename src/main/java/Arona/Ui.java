package Arona;

public class Ui {
    public Ui() {}

    public void showGreeting() {
        print("Hello! I'm Arona.");
        print("What can I do for you?");
    }

    public void showFarewell() {
        print("Bye. Hope to see you again soon!");
    }

    public void showList(TaskList taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i) != null) {
                Task task = taskList.get(i);
                print(i + 1 + ". " + task.getStatusIcon() + task.getCategory() + " " + task.toFriendlyString());
            }
        }
    }

    public void showDelete(int size, Task task) {
        print("Got it. I've removed this task:");
        print(task.getStatusIcon() + task.getCategory() + ": " + task.toFriendlyString());
        print("Now you have " + size + " tasks in the list.");
    }

    public void showMark(Task task, boolean action) {
        if (action) {
            print("Nice! I've marked this task as done:");
        } else {
            print("OK, I've marked this task as not done yet:");
        }
        print(task.getStatusIcon() + task.getCategory() + ": " + task.toFriendlyString());
    }

    public void showAdd(int size, Task task) {
        print("Got it. I've added this task:");
        print(task.getStatusIcon() + task.getCategory() + ": " + task.toFriendlyString());
        print("Now you have " + size + " tasks in the list.");
    }


    public void showException(Exception e) {
        print(e.getMessage());
    }

    private static void print(String message) {
        System.out.println(message);
    }
}
