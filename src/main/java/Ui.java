public class Ui {
    private static void printLine() {
        /**
         * This method will print out a border
         */
        // This method will be used to print the border
        System.out.println("---------------------------------------------");
    }
    public static void welcomeMessage() {
        // Greeting the users
        printLine();
        System.out.println("Nuffle > Good day! I'm Nuffle.");
        System.out.println("Nuffle > What can I do for you today?");
        printLine();
    }

    public static void markTaskMessage(Task task) {
        printLine();
        System.out.println("Nice! I have marked this task as done!");
        System.out.println(" " + task);
        printLine();
    }

    public static void markTaskError() {
        printLine();
        System.out.println("Opps! There appears to be an index error!");
        printLine();
    }

    public static void unmarkTaskMessage(Task task) {
        printLine();
        System.out.println("OK! I have marked this task as not done yet.");
        System.out.println(" " + task);
        printLine();
    }

    public static void unmarkTaskError() {
        printLine();
        System.out.println("Opps! There appears to be an index error!");
        printLine();
    }

    public static void addTaskMessage(Task task, int listSize) {
        printLine();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + task);
        System.out.println(" Now you have " + listSize + " tasks in the list.");
        printLine();
    }

    public static void deleteTaskMessage(Task task, int listSize) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println("   " + task);
        System.out.println("Now you have " + listSize + " tasks in the list.");
        printLine();
    }

    public static void deleteTaskError() {
        printLine();
        System.out.println("Hmmm... The index provided seems to be out of range. Please try again.");
        printLine();
    }

    public static void byeMessage() {
        printLine();
        System.out.println("Nuffle > Bye. Hope to see you again soon!");
        printLine();
    }

    public static void exceptionErrorMessage(NuffleException e) {
        printLine();
        System.out.println("Nuffle caught an error > " + e.getMessage());
        printLine();

    }

}
