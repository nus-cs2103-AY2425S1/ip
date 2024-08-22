import java.util.Scanner;
public class Samson {
    public static void main(String[] args) {
        final String chatBoxName = "Samson";
        Greeting greeting = new Greeting(chatBoxName);
        greeting.greet();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                greeting.exit();
                break;
            }

            // Echo the user input
            System.out.println("____________________________________________________________");
            System.out.println(" " + userInput);
            System.out.println("____________________________________________________________");
        }

        scanner.close();

    }
}
