package mahesh.util;

import mahesh.task.Task;

public class Ui {
    /**
     * The logo to be displayed when the application starts.
     */
    private static final String LOGO = 
      "#     #                                       ######                               ###### \n"
    + "##   ##   ##   #    # ######  ####  #    #    #     #   ##   #      #              #      \n"
    + "# # # #  #  #  #    # #      #      #    #    #     #  #  #  #      #              #      \n"
    + "#  #  # #    # ###### #####   ####  ######    #     # #    # #      #      #####   #####  \n"
    + "#     # ###### #    # #           # #    #    #     # ###### #      #              #      \n"
    + "#     # #    # #    # #      #    # #    #    #     # #    # #      #              #      \n"
    + "#     # #    # #    # ######  ####  #    #    ######  #    # ###### ######         ###### \n";

    /**
     * Divider line used for separating sections in the console output.
     */
    private static final String DIVIDER = "-------------------------------------------------------";
    
    public static void printStartupMessage() {
        System.out.println(Ui.LOGO);
        System.out.println("Hello! I'm Mahesh Dall-E [but you can call me Mahesh ;)]\nWhat can I do for you?");
        System.out.println(DIVIDER);
    }

    public static void printExitMessage() {
        System.out.println("Bye! Hope to see you again soon!");
        System.out.println(DIVIDER);
    }

    public static void printTaskAdded(Task task, int taskCount) {
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    public static void printTaskDeleted(Task task, int taskCount) {
        System.out.println("Noted. I've removed this task:");       
        System.out.println("  " + task);
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        System.out.println(DIVIDER);
    }

    public static void printMarkedAsDone(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        System.out.println(DIVIDER);
    }

    public static void printUnmarkedAsDone(Task task) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + task);
        System.out.println(DIVIDER);
    }

    public static void printErr(MaheshException err) {
        System.out.println(err.getMessage());
        System.out.println(DIVIDER);
    }

    public static void printNoSuchTaskErr(int taskCount) {
        System.out.println("There is no such task. You currently have " + taskCount + " task(s).");
        System.out.println("Use the \"list\" command to view all your tasks.");
        System.out.println(DIVIDER);
    }

    public static void printIncompleteCommandErr(MaheshException err) {
        System.out.println("The command is incomplete/incorrect.");
        System.out.println(err.getMessage());
        System.out.println(DIVIDER);
    }

    public static void printEmptyListErr() {
        System.out.println("You have no tasks! Add a few tasks (todo, deadline or event)");
        System.out.println(DIVIDER);
    }

    public static void printCorruptedDataErr() {
        System.out.println("Some tasks may not have loaded correctly due to corrupted data file.");
        System.out.println(DIVIDER);
    }

    public static void printDivider() {
        System.out.println(DIVIDER);
    }

}
