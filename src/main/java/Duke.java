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
            String[] parts = command.split(" ");

            if (commandLowerCase.equals("bye")) {
                System.out.println("See you loser");
            } else if (commandLowerCase.equals("list")) {
                System.out.println(DukeManager.listItems());

            } else if (parts.length == 2 && (parts[0].toLowerCase().equals("mark") || parts[0].toLowerCase().equals("unmark"))) {
                String action = parts[0].toLowerCase();
                String numberStr = parts[1];
                 try {
                    int number = Integer.parseInt(numberStr);
                    // Check for valid index (e.g., positive integers only)
                    if (number > 0) {
                        if (action.equals("mark")) {
                            DukeManager.setDone(true, number);
                            System.out.println("Nice! I've marked this task as done:\n" + DukeManager.getItem(number));

                        } else {
                            DukeManager.setDone(false, number);
                            System.out.println("OK, I've marked this task as not done yet:\n" + DukeManager.getItem(number));
                        }
                    } else {
                        System.out.println("Give me a positive number man");
                    }
                 } catch (NumberFormatException e) {
                     System.out.println("You didnt even type a number !!.");
                 }

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
