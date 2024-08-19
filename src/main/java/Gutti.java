import java.util.Scanner;
import java.util.List;

public class Gutti {
    private static String[] tasks = new String[100];
    private static int noOfTasks = 0;

    private static void greetings() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Gutti");
        System.out.println("What can I do for you? Meow");
        System.out.println("____________________________________________________________");
    }

    private static void goodBye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon! Meow");
        System.out.println("____________________________________________________________");
    }

    private static void echo(){
        Scanner scanner = new Scanner(System.in);
        // User's input
        String input;
        // Loops until user says bye
        while (true) {
            input = scanner.nextLine();
            // Stop when user types bye
            if(input.equalsIgnoreCase("bye")) {
                break;
            }
            // Lists out all the tasks
            if(input.equalsIgnoreCase("list")) {
                listsTask();
            }
            // Adds tasks
            else {
                System.out.println("____________________________________________________________");
                System.out.println("Gutti painstakingly added: " + input);
                System.out.println("____________________________________________________________");
                tasks[noOfTasks] = input;
                Gutti.noOfTasks++;
            }
        }
        scanner.close();
    }
    private static void listsTask() {
        System.out.println("____________________________________________________________");
        for (int i = 0; i < noOfTasks; i++) {
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println("____________________________________________________________");
    }
    public static void main(String[] args) {
        // Display the greeting message
        greetings();
        // Echos
        echo();
        // Exit message
        goodBye();
    }
}
