import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class LittleMissHelpful {
    public static void main(String[] args) {
        String name = "Little Miss Helpful";
        String lineBreak = "___________________________________";
        String greeting = "Hello! I'm " + name + ".\nWhat can I do for you?";
        String exitLine = "Bye. Hope to see you again soon!";

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Print greeting
        System.out.println(lineBreak);
        System.out.println(greeting);
        System.out.println(lineBreak);

        String[] list = new String[100];

        try {
            int counter = 0;
            while (true) { //there is continuous input
                String input = br.readLine();

                // Check if the user input is "bye" -> exit
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println(exitLine);
                    System.out.println(lineBreak);
                    break; // Exit the loop
                }

                // Check if the user input is "list" -> print list
                if (input.equalsIgnoreCase("list")) {
                    System.out.println(lineBreak);
                    for (int i = 0 ; i < counter ; i++) {
                        int listNumber = i + 1;
                        String listItem = list[i];
                        System.out.println(listNumber + ". " + listItem);
                    }
                    System.out.println(lineBreak);
                    continue;
                }

                // Adds commands to list
                list[counter] = input;
                System.out.println(lineBreak);
                System.out.println(input);
                System.out.println(lineBreak);
                counter++;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading input.");
            e.printStackTrace();
        }
    }
}
