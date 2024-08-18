import java.util.Scanner;

public class Optimus {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";

        //greeting for user
        String greeting = line +
                "Hello! I'm Optimus.\n" +
                "What can I do for you?\n" +
                line;
        System.out.println(greeting);

        //Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        //while loop to keep taking in user input
        while (true) {
            String userInput = scanner.nextLine();

            //exit program if user enters "bye"
            if (userInput.equals("bye")) {
                System.out.println(line +
                        "Bye. Hope to see you again soon!\n" +
                        line);
                scanner.close();
                System.exit(0);
            }

            // else echo user input
            System.out.println(line +
                    userInput + "\n" + line);
        }
    }
}
