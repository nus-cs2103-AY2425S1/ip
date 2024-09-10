package ui;

import tasks.Task;
/**
 * Ui class that encapsulates all the cat prints
 */
public class Ui {

    /**
     * Says goodbye
     */
    public static void bidFarewell() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints a cute welcome cat print
     */
    public static void sayWelcome() {
        // Detailed and fancy cat welcome message
        System.out.println("     /\\_/\\   ");
        System.out.println("   =( °w° )=   ");
        System.out.println("     )   (   //");
        System.out.println("    (__ __)//   ");
        System.out.println(" ");
        System.out.println(" *~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~* ");
        System.out.println(" |                                                    |");
        System.out.println(" |     Welcome to the Purr-fect Java Program!         |");
        System.out.println(" |   Where every line of code is simply claw-some!    |");
        System.out.println(" |                                                    |");
        System.out.println(" *~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~* ");
        System.out.println(" ");
        System.out.println("       |\\_/|   ");
        System.out.println("       |q p|   /}");
        System.out.println("       ( 0 )\"\"\"\"\\");
        System.out.println("       |\"^\"`    |");
        System.out.println("       ||_/=\\\\__|");
        System.out.println(" ");
        System.out.println("Get ready to have a purr-ific time coding!");
        System.out.println("Hello! I'm TheOrangeRatchetCat");
        System.out.println("What can I do for you?");
        System.out.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
    }

    /**
     * Prints a sleepy cat
     */
    public static void sleepyCat() {
        // Sleepy cat
        System.out.println("     |\\___/|   ");
        System.out.println("   z | o   o | zzz");
        System.out.println("   - (  =^=  ) -  ");
        System.out.println("      (     )  ");
        System.out.println("      (      ) ");
        System.out.println("     (        )))))))))))");
        System.out.println("");
    }

    /**
     * Prints a goodBye cat print
     */
    public static void goodByeCat() {
        System.out.println("      /\\_____/\\");
        System.out.println("     /  o   o  \\");
        System.out.println("    ( ==  ^  == )");
        System.out.println("     )         (");
        System.out.println("    (           )");
        System.out.println("   ( (  )   (  ) )");
        System.out.println("  (__(__)___(__)__)");
        System.out.println("");
        System.out.println("    Goodbye, and have a purr-fect day!");
    }

    /**
     * Prints the specific task that has been marked
     * @param task that will be marked as done
     */
    public static void markingTaskPrint(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("[" + task.getStatusIcon() + "] " + task.getDes());
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the specific task that has been unmarked
     * @param task that will be marked as undone
     */
    public static void unmarkingTaskPrint(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Nice! I've marked this task as not done:");
        System.out.println("[" + task.getStatusIcon() + "] " + task.getDes());
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the specific task to be deleted
     * @param task that will be deleted
     */
    public static void deletingTaskPrint(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the task of type ToDo that has been added
     * @param task of type ToDo that will be added to the list of tasks
     */
    public static void addingToDoPrint(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the task of type Deadline that has been added
     * @param task of type Deadline that will be added to the list of tasks
     */
    public static void addingDeadlinePrint(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    /**
     * Prints the task of type Event that has been added
     * @param task of type Event that will be added to the list of tasks
     */
    public static void addingEventPrint(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + Task.getTaskCount() + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }
}
