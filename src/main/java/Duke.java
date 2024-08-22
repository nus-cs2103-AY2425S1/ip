import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String chatBotName = "Carine";
        String horizontalLine = "____________________________________________________________";

        // Greeting message
        System.out.println(horizontalLine);
        System.out.println(" Hello! I'm " + chatBotName);
        System.out.println(" What can I do for you?");
        System.out.println(horizontalLine);

        Echo();

        // Goodbye message
        System.out.println(" Bye. Hope to see you again soon!");
        System.out.println(horizontalLine);
    }

    public static void Echo() {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        while (true) {
            userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                break;
            }

            String horizontalLine = "____________________________________________________________";
            System.out.println(horizontalLine);
            System.out.println(" " + userInput);
            System.out.println(horizontalLine);
        }

        scanner.close();
    }

}
