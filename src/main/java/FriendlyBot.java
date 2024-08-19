import java.util.Scanner;

public class FriendlyBot {
    private static String[] tasks;
    private static int numTasks;

    private static void printHorizontalBar() {
        System.out.println("    ____________________________________________________________");
    }

    public static void main(String[] args) {
        // Initialise variables
        Scanner reader = new Scanner(System.in);
        tasks = new String[100];
        numTasks = 0;
        // Start of Chat Bot
        FriendlyBot.printHorizontalBar();
        System.out.println("    Hello! I'm Friendly Bot");
        System.out.println("    What can I do for you?");
        FriendlyBot.printHorizontalBar();
        String response = "";
        // Commands
        while (!response.equals("bye")) {
            response = reader.nextLine();
            FriendlyBot.printHorizontalBar();
            if (response.equals("bye")) {
                System.out.println("    Bye. Hope to see you again soon!");
            } else if (response.equals("list")) {
                for (int i = 1; i <= numTasks; i++) {
                    System.out.println("    " + i + ". " + tasks[i - 1]);
                }
            } else {
                tasks[numTasks] = response;
                numTasks++;
                System.out.println("    added: " + response);
            }
            FriendlyBot.printHorizontalBar();
        }
    }
}
