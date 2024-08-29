package UI;

import Tasks.Task;

public class UI {
    public void welcomeMessage() {
        System.out.println("Hello! I'm Delphi, the greatest oracle in all of the classical world.");
        System.out.println("To quote a very famous person who will arrive a bit later, ask and ye shall find");
    }
    public void goodbyeMessage() {
        System.out.println("Bye. Hope to see you again soon!");
    }
    public static void taskMessage(Task tsk, int num) {
        System.out.println("    Got it. I've added this task:");
        System.out.println("      " + tsk);
        System.out.println("Now you have " + num + " tasks in the list.");
    }
    public static void markingTask(boolean completed, Task tsk) {
        if (completed) {
            System.out.println("    Nice! I've marked this task as done:");
            System.out.println("      " + tsk);
        } else {
            System.out.println("    OK, I've marked this task as not done yet:");
            System.out.println("      " + tsk);
        }
    }
    public static void removingTask(Task removedTask, int num) {
        System.out.println("    Noted. I've removed this task:");
        System.out.println("      " + removedTask);
        System.out.println("    Now you have " + num + " tasks in the list");
    }
}
