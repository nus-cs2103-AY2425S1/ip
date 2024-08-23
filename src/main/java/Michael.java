import java.util.Scanner;
import java.util.ArrayList;

public class Michael {
    private static final String border = "--------------------------------------";
    public static void printer(String text) { // Function to help print each interaction
        System.out.println(border);
        System.out.println(text);
        System.out.println(border);
    }

    private static ArrayList<String> texts = new ArrayList<>(); // store user inputs

    public static void main(String[] args) {
        Scanner user = new Scanner(System.in); // scanner for user input

        // Greet user first
        printer("Hello! I'm Michael. \n" + "What can I do for you?");

        // Read user's input
        while (true) {
            String input = user.nextLine();
            if (input.equals("bye")) { // special bye command to exit
                break;
            }

            if (input.equals("list")) { // list user inputs thus far
                String list = "";
                for (int i = 0; i < texts.size(); i++) {
                    String elem = String.valueOf(i + 1) + ". " + texts.get(i) + "\n";
                    list = list.concat(elem);
                }
                printer(list.substring(0, list.length() - 1)); // substring to remove last line break
            } else {
                texts.add(input);
                printer("added: " + input);
            }
        }

        // Exit
        printer("Bye. Hope to see you again soon!");
    }
}
