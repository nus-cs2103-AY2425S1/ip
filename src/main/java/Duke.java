import java.util.Scanner;

public class Duke {
    private static String name = "Duke";
    private ListManager DukeManager = new ListManager();

    private void exit() {
        System.out.println("Bye! Hope to see you again my G");
    }

    private void greet() {
        Boolean endChat = false;
        System.out.println("Hello! I'm " + name + " aka ChatGPT on Crack!\nWhat assistance are you in need of today?");
        while (!endChat) {
            Scanner scanner = new Scanner(System.in);
            String command = scanner.nextLine();
            String commandLowerCase = command.toLowerCase();
            if (commandLowerCase.equals("bye")) {
                System.out.println("See you loser");
            } else if (commandLowerCase.equals("list")) {
                System.out.println(DukeManager.listItems());
            } else {
                DukeManager.createItem(command);
                System.out.println("added " + command);
            }
        }


    }

    public static void main(String[] args) {
        Duke MrDuke = new Duke();
        MrDuke.exit();
        MrDuke.greet();

    }
}
