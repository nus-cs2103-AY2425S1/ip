import java.util.Scanner;
import java.util.ArrayList;

public class Bob {
    private static final String line = "____________________________________________________________\n";
    private static ArrayList<String> commands = new ArrayList<>();

    private static String greeting() {
        return Bob.lineFormat(" Hello! I'm Bob\n" +
                " What can I do for you?");
    }
    private static String farewell() {
        return  Bob.lineFormat(" Bye. Hope to see you again soon!");
    }
    private static String lineFormat(String input) {
        return Bob.line + input + "\n" + Bob.line;
    }
    private static void addCommand(String command) {
        Bob.commands.add(command);
        System.out.println(Bob.lineFormat("Added: " + command));
    }
    private static void listCommands() {
        System.out.print(Bob.line);
        for (int i = 0; i < Bob.commands.size(); i++) {
            System.out.println(String.valueOf(i) + ". " + commands.get(i));
        }
        System.out.print(Bob.line);
    }

    public static void main(String[] args) {
        System.out.println(Bob.greeting());
        String input = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter your command: ");
            input = scanner.nextLine();

            if (input.compareTo("bye") == 0) {
                System.out.println(Bob.farewell());
                break;
            }
            if (input.compareTo("list") == 0) {
                Bob.listCommands();
                continue;
            }

            Bob.addCommand(input);
        }
    }
}
