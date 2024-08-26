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

        String input = "";

            while (true) { //there is continuous inputs
                input = br.readLine();

                // Check if the user input is "bye"
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println(exitLine);
                    System.out.println(lineBreak);
                    break; // Exit the loop if the user types "bye"
                }

                // Echos commands
                System.out.println(lineBreak);
                System.out.println(input);
                System.out.println(lineBreak);
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading input.");
            e.printStackTrace();
        }
    }
}
