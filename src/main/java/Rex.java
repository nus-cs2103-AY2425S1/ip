import java.util.ArrayList;
import java.util.List;
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

        // ArrayList to store added items
        ArrayList<Task> list = new ArrayList<>();

        while (true) {
            System.out.println(separation);
            // Takes in user input to add to list
            Scanner scanner = new Scanner(System.in);
            String[] input = scanner.nextLine().split(" ");
            String command = input[0];

            // Process user command
            switch (command) {
            // Rex's goodbye message, exit program
            case "bye":
                System.out.println(separation);
                System.out.println("Bye. Hope to see you again soon!" + rawr);
                System.out.println(separation);
                return;
            // Display items added as a numbered list
            case "list":
                displayList(list);
                break;
            // Echo user input otherwise
            default:
                 String description = echo(input);
                 Task newTask = new Task(description);
                 list.add(newTask);
                 break;
            }
        }
    }

    private static void displayList(List<Task> list) {
        System.out.println(separation);
        for (int i = 1; i <= list.size(); i++) {
            System.out.println(i + "." + list.get(i - 1));
        }
    }

    private static String echo(String[] input) {
        // Display input text back to user through print statement
        System.out.println(separation);
        System.out.print("added: ");

        // Loop through each word in input array
        String output = "";
        for (String word : input) {
            output += word + " ";
        }
        System.out.println(output);

        // Return item description
        return output;
    }
}
