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
        System.out.println(separation);

        // Fixed size array to store items
        ArrayList<String> list = new ArrayList<>();

        // Loop echos commands entered by user
        do {
            // Takes in user input to add to list
            Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();

            // Echo user input, unless specific input is given
            if (input.equals("bye")) {
                // exit loop if input is "bye"
                break;
            } else if (input.equals("list")) {
                // Display numbered list of items in sequence if input is "list"
                System.out.println(separation);
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ". " + list.get(i - 1));
                }
                System.out.println(separation);
            } else {
                // Echo user input and add to list
                echo(input);
                list.add(input);
            }
        }  while (true);

        // Rex's exit message
        System.out.println(separation);
        System.out.println("Bye. Hope to see you again soon!" + rawr);
        System.out.println(separation);
    }

    private static void echo(String input) {
        // Display input text back to user through print statement
        System.out.println(separation);
        System.out.println("added: " + input);
        System.out.println(separation);
    }
}
