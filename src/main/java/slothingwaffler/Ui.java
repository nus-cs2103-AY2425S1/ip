package slothingwaffler;

public class Ui {

    public void greet() {
        System.out.println("""
                Hello! I'm the Slothing Waffler!
                Let's stop slothing and get cracking!""");
    }

    public void exit() {
        System.out.println("See you next time! Remember to get a waffle!");
    }

    public void printError(String message) {
        System.out.println("Error: " + message);
    }

    public void addTaskMessage(TaskList tasks) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + tasks.get(tasks.size() - 1).toString());
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void markTaskMessage(TaskList tasks, int i) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + tasks.get(i).toString());
    }

    public void deleteTaskMessage(TaskList tasks, int taskNum) {
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + tasks.get(taskNum));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }
}
