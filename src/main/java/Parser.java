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
                    break;
                case "bye":
                    System.out.println("It's finally over... *yawn!\nExiting the program...");
                    break;
                default:
                    System.out.println("Added " + '\"'+ text + "\"" + " as a new task");
            }
            Duke.lnDiv();
        } while (!command.equals("bye"));
        scanner.close();
    }
}