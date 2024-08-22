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
                case "list": System.out.println(storage);
                    break;
                case "bye":
                    System.out.println("It's finally over... *yawn*\nI'm heading to bed\nExiting the program...");
                    break;
                default:
                    storage.add(text);
                    System.out.println("Added " + '\"'+ text + "\"" + " as a new task I guess");
            }
            Duke.lnDiv();
        } while (!command.equals("bye"));
        scanner.close();
    }
}