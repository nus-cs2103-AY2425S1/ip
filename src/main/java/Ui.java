public class Ui {
    public static void printLine() {
        System.out.println("____________________________________________________________");
    }

    public static void printWelcomeMessage() {
        printLine();
        System.out.println("Hello! I'm Papagu");
        System.out.println("What can I do for you?");
        printLine();
    }

    public static void printList(TaskList list) {
        printLine();
        System.out.println("Here are the tasks in your list:");
        System.out.println(list);
        printLine();
    }

    public static void printMarkAsDone(TaskList list, int taskNumber) {
        printLine();
        System.out.println("Nice! I've marked this task as done:");
        printLine();
    }

    public static void printMarkAsNotDone(TaskList list, int taskNumber) {
        printLine();
        System.out.println("OK, I've marked this task as not done yet:");
        printLine();
    }

    public static void printBye() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
        printLine();
    }

    public static void printDelete(Task task) {
        printLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(task.toString());
        printLine();
    }

    public static void printAdded(Task task) {
        printLine();
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        printLine();
    }

    public static void printLoadingError() {
        System.out.println("Error loading file");
    }
}