import java.util.Scanner;

public class Optimus {
    public static void main(String[] args) {

        //greeting for user
        String greeting = "Hello! I'm Optimus.\n" +
                "What can I do for you?\n";
        System.out.println(greeting);

        //Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        //while loop to keep taking in user input
        while (true) {
            String userInput = scanner.nextLine();

            //exit program if user enters "bye"
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!\n");
                scanner.close();
                System.exit(0);
            }

            // else echo user input
            System.out.println(userInput + "\n");
        }
    }
}
