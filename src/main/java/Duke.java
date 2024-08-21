import java.util.Scanner;

public class Duke {
    private static String name = "Duke";

    private static void exit() {
        System.out.println("Bye! Hope to see you again my G");
    }

    private static void greet() {
        Boolean endChat = false;
        System.out.println("Hello! I'm " + name + " aka ChatGPT on Crack!\nWhat assistance are you in need of today?");
        while (!endChat) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            if (command.toLowerCase().equals("bye")) {
                System.out.println("See you loser");
            } else {
                System.out.println("I see, you need help with " + command + "!");
            }
        }


    }

    public static void main(String[] args) {
        exit();
        greet();

    }
}
