import java.util.Scanner;

public class Parser {
    public static void parse() {
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();
        String command;
        String text;
         do {
            text = scanner.nextLine();
            command = text.toLowerCase();
            switch (command) {
                case "list": System.out.println(storage.toString());
                    break;
                case "blah":
                    System.out.println(command);
                    break;
                case "bye":
                    System.out.println(command);
                    System.out.println("Thanks for using me!\nExiting the program...");
                    break;
                default:
                    System.out.println("Added " + '\"'+ text + "\"" + " as a new task");
            }
            Duke.lnDiv();
        } while (!command.equals("bye"));
        scanner.close();
    }
}