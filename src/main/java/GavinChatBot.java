import java.util.Scanner;

public class GavinChatBot {
    public static void main(String[] args) {
        /*
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        */

        String[] inputList = new String[100];
        int inputIndex = 0;
        String horizontalLine = "_________________________________\n";

        // print welcome message
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Gavin\n");
        System.out.println("What can I do for you?\n");
        System.out.println(horizontalLine);


        // create scanner to read user input
        Scanner scanner = new Scanner(System.in);
        String input;

        // echo input by user until user types bye
        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                // print exit message
                System.out.println(horizontalLine);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(horizontalLine);
                break;
            } else if (input.equalsIgnoreCase("list")) {
                // print out list of items
                System.out.println(horizontalLine);
                for (int i = 0; i < inputList.length; i++) {
                    if (inputList[i] == null) {
                        break;
                    }
                    System.out.println(i + ". " + inputList[i]);
                }
                System.out.println(horizontalLine);
                continue;
            }
            System.out.println(horizontalLine);
            inputList[inputIndex] = input;
            inputIndex++;
            System.out.println("added:" + input);
            System.out.println(horizontalLine);
        }
    }
}
