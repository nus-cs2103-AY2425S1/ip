import java.util.Scanner;

public class Ui {
    Scanner s;

    public Ui() {
        s = new Scanner(System.in);
    }

    public void printWelcomeMessage() {
        String logo = """
                        ########   #######   ########   #######\s
                        #       #     #      #       #     #   \s
                        ########      #      ########      #   \s
                        #       #     #      #       #     #   \s
                        #       #     #      #       #     #   \s
                        ########   #######   ########   #######\s
                      """;

        System.out.println("Hello from\n" + logo + "\n"
                + "How can I help you?");
    }

    public String readInput() {
        return s.nextLine();
    }

    public void printExitMessage() {
        printHorizontalLine();
        System.out.println("See you soon :3");
        printHorizontalLine();
    }

    public void printListMessage(TaskList tasks) {
        printHorizontalLine();
        tasks.printTaskList();
        printHorizontalLine();
    }

    public void printTaskMarkedMessage(Task t) {
        System.out.printf("Alrighty, marked the following task as done:%n");
        System.out.println(t);
    }

    public void printTaskUnmarkedMessage(Task t) {
        System.out.printf("Oops, we'll get 'em next time:%n");
        System.out.println(t);
    }

    public void printTaskAddedMessage(Task t, int size) {
        System.out.printf("Added: \"%s\" to task list%n", t);
        System.out.printf("You now have %d task(s) to do%n", size);
    }

    public void printTaskRemovedMessage(Task t, int size) {
        System.out.println("You will never see this task ever again >:(");
        System.out.printf("Removed %s from task list%n", t.toString());
        System.out.printf("You now have %d task(s) to do%n", size);
    }

    public void printInvalidSyntaxMessage(String message) {
        System.out.printf("Invalid command syntax: %s%n", message);
    }

    public void printUnknownCommandMessage(String cmd) {
        printHorizontalLine();
        System.out.printf("%s is an unknown command%n", cmd);
        printHorizontalLine();
    }

    public void printHorizontalLine() {
        System.out.println("------------------------------------------------------------");
    }
}
