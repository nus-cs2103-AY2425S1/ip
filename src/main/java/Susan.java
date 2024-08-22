import java.util.Scanner;

public class Susan {
    public static void main(String[] args) {
        // Greet
        System.out.println("Hi there, Susan's at your service!");
        System.out.println("How may I assist you?");

        // Read user input
        Scanner scanner = new Scanner(System.in);
        String userInput = "";

        // Echo
        while (!userInput.equalsIgnoreCase("bye")) {
            System.out.println(userInput);
            userInput = scanner.nextLine();
        }
        // Exit
        System.out.println("Good bye, slay the day!");
    }
}
