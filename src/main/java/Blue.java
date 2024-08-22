import java.util.Scanner;
public class Blue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Greet the user
        System.out.println("--------------------------------------------");
        System.out.println("Hello! I'm Blue");
        System.out.println("What can I do for you?");
        System.out.println("--------------------------------------------");

        String input = "";

        // Keep echoing user input until "bye" is typed
        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("bye")) {
                break;
            }
            System.out.println(input);
            System.out.println("--------------------------------------------");
        }

        // Farewell message
        System.out.println("I'm sorry I was mimicking you! Bye Bye! Hope to see you again soon!");
        System.out.println("_     /)---(\\          /~~~\\");
        System.out.println("\\\\   (/ . . \\)        /  .. \\");
        System.out.println(" \\\\__)-\\(*)/         (_,\\  |_)");
        System.out.println(" \\_       (_         /   \\@/    /^^^\\");
        System.out.println(" (___/-(____) _     /      \\   / . . \\");
        System.out.println("              \\\\   /  `    |   V\\ Y /V");
        System.out.println("               \\\\/  \\   | _\\    / - \\");
        System.out.println("                \\   /__'|| \\\\_  |    \\");
        System.out.println("                 \\_____)|_).\\_).||(__V");
        System.out.println("--------------------------------------------");

        // Close the scanner
        scanner.close();
    }
}
