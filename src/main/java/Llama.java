import java.util.Scanner;

public class Llama {
    public static void displayString(String str) {
        System.out.println("\t" + str);
    }
    public static void main(String[] args) {
        String logo = "";
        String hr = "____________________________________________________________" ;
        Scanner sc = new Scanner(System.in);
        // Assume less than 100 tasks
        String[] taskArray = new String[100];
        int last = 0;

        // Initializing message
        displayString(hr);
        displayString("Hello! I'm Llama!");
        displayString(logo);
        displayString("What can I do for you?");

        // Get user input
        boolean shouldContinue = true;
        while (shouldContinue) {
            displayString(hr);
            String input = sc.nextLine();
            displayString(hr);

            if (input.equals("bye")) {
                // End program
                shouldContinue = false;
                sc.close();
            } else {
                // Add text into a list
                taskArray[last] = input;
                last++;
                displayString("Added: " + input);
            }
        }

        // Exit message
        displayString("Baaaaaa byeeee. Come baaaaack soon!");
    }
}
