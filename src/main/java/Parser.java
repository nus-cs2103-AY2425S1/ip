import java.util.Scanner;

public class Parser {
    public static void parse() {
        Scanner scanner = new Scanner(System.in);
        String command;
         do {
            command = scanner.nextLine().toLowerCase();

            switch (command) {
                case "list": System.out.println(command);
                    break;
                case "blah":
                    System.out.println(command);
                    break;
                case "bye":
                    System.out.println(command);
                    System.out.println("Thanks for using me!\nExiting the program...");
                    break;
                default:
                    System.out.println("Unknown command: " + command);
            }
            Duke.lnDiv();
        } while (!command.equals("bye"));
        scanner.close();
    }
}