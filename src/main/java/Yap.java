import java.util.Scanner;
public class Yap {
    public static void main(String[] args) {
        // Initialize Scanner
        Scanner scanner = new Scanner(System.in);

        // Print logo and introductions
        String logo = "_    _  __     ______\n"
                    + " \\  // //\\    ||__| |\n"
                    + "  \\// //__\\   ||____/\n"
                    + "  || //____\\  ||\n"
                    + "  ||//      \\ ||\n";
        String separator = "_____________________________________";
        System.out.println("Hello from\n" + logo);
        System.out.println("What would you like me to do for you? ");
        System.out.println(separator);

        // Infinite loop to get user input
        String[] userInputs = new String[100];
        int inputCount = 0;
        while (true) {
            userInputs[inputCount] = scanner.nextLine();
            System.out.println(separator);

            // Bye functionality
            if (userInputs[inputCount].equalsIgnoreCase("bye")) {
                System.out.println("Bye! It was really nice talking to you, see you soon :)");
                break;
            }

            // List functionality
            if (userInputs[inputCount].equalsIgnoreCase("list")) {
                for (int input = 0; input < inputCount; ++input) {
                    System.out.println((input + 1) + ". " + userInputs[input]);
                }
            } else {
                System.out.println("Added: " + userInputs[inputCount++]);
            }
            System.out.println(separator);
        }
    }
}
