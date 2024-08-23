import java.util.Scanner;

public class Jude {
    public static void main(String[] args) {

        // Initialize the variables
        String name = "Jude";
        String[] list = new String[100];
        int listSize = 0;
        Scanner scanner = new Scanner(System.in);

        // Initiate the chat
        System.out.println("Hello I'm " + name);
        System.out.println("What can I do for you, poyo?");

        // Receive first user input
        String userInput = scanner.nextLine();

        // Terminate input if is bye, iterate otherwise.
        while (!userInput.equals("bye")) {

            // If the input is list, shows the list of tasks saved.
            if (userInput.equals("list")) {
                for (int i = 0; i < listSize; i++) {
                    System.out.printf("%d. %s\n", (i + 1), list[i]);
                }
            } else {
                list[listSize++] = userInput;
                System.out.println("added: " + userInput);
            }
            userInput = scanner.nextLine();
        }

        // Terminate the chat
        System.out.println("Bye. Hope to see you again soon, poyo!");
        scanner.close();
    }
}

