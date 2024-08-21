import java.util.Scanner;

public class Megamind {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";

    public static void main(String[] args) {
        String logo = """
                  _____  
                 /     \\ 
                | () () |
                 \\  ^  / 
                  |||||  
                  |||||  
                """;
        Scanner scanner = new Scanner(System.in);

        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello from\n" + logo);

        greet();

        // Start a loop to read commands from the user
        while (true) {
            System.out.print("> ");
            String command = scanner.nextLine().trim();

            // Exit the loop if the user types "bye"
            if (command.equalsIgnoreCase("bye")) {
                exit();
                break;
            }

            // Echo the command
            System.out.println(command + "\n");
            System.out.println(HORIZONTAL_LINE);
        }
    }

    public static void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("What's good, I'm Megamind.\nWhat can I do for you? \n");
        System.out.println(HORIZONTAL_LINE);
    }

    public static void exit() {
        System.out.println("See you around! \n");
        System.out.println(HORIZONTAL_LINE);
    }
}
