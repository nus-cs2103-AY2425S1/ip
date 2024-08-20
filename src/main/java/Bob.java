import java.util.Scanner;

public class Bob {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = "";
        int taskCounter = 0;
        String[] tasks = new String[100];

        System.out.println("\t------------------------------------------");
        System.out.println("\tHello! I'm Bob!\n\tHow can I help you today?");
        System.out.println("\t------------------------------------------");

        while (!input.equals("bye")) {
            input = scanner.nextLine();

            // add task to tasks array
            if (!input.equals("bye")) {
                tasks[taskCounter] = input;
                taskCounter++;

                System.out.println("\t------------------------------------------");
                System.out.println("\tadded: " + input);
                System.out.println("\t------------------------------------------");
            }

        }

        System.out.println("\t------------------------------------------");
        System.out.println("\tBye, see you again!");
        System.out.println("\t------------------------------------------");
    }
}


