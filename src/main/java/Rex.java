import java.util.ArrayList;
import java.util.Scanner;

public class Rex {
    // Horizontal line separation
    private static String separation = "____________________________________________________________";

    // "rawr" string added to end of each print statement for customization
    private static String rawr = " rawr";
    public static void main(String[] args) {
        // Rex's greeting message
        System.out.println(separation);
        System.out.println("Hello! I'm Rex!" + rawr);
        System.out.println("What can I do for you?" + rawr);

        // Fixed size array to store items
        ArrayList<String> list = new ArrayList<>();

        while (true) {
            System.out.println(separation);
            // Takes in user input to add to list
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            // Process user command
            switch (input) {
            // Rex's goodbye message, exit program
            case "bye":
                System.out.println(separation);
                System.out.println("Bye. Hope to see you again soon!" + rawr);
                System.out.println(separation);
                return;
           // Display items added as a numbered list
            case "list":
                System.out.println(separation);
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
                break;
            // Echo user input otherwise
            default:
                 echo(input);
                 list.add(input);
                 break;
            }
        }
    }

    private static void echo(String input) {
        // Display input text back to user through print statement
        System.out.println(separation);
        System.out.println("added: " + input);
    }
}
