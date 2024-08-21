import java.util.Scanner;

public class PandaBot {
    public static void main(String[] args) {
        String line = "_________________________________________";
        Scanner scanner = new Scanner(System.in);

        // Simple greeting to the user by PandaBot
        System.out.println(line);
        System.out.println("Hello! I'm PandaBot");
        System.out.println("What can I do for you?");
        System.out.println(line);

        // Echo user input until the user inputs "bye"
        while (true) {
            String input = scanner.nextLine();

            // if user has said "bye", stop echo and exit
            if (input.equalsIgnoreCase("bye")) {
                System.out.println(line);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(line);
                break;
            }

            // echo user input
            System.out.println(line);
            System.out.println(input);
            System.out.println(line);
        }

        scanner.close();
    }
}
