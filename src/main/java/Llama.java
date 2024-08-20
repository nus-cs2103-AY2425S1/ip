import java.util.Scanner;
import java.util.ArrayList;

public class Llama {
    public static void main(String[] args) {
        String logo = "";
        String hr = "\t____________________________________________________________" ;
        Scanner sc = new Scanner(System.in);

        ArrayList<String> taskList = new ArrayList<>();

        // Initializing message
        System.out.println(hr);
        System.out.println("\tHello! I'm Llama!\n" + logo);
        System.out.println("\tWhat can I do for you?");

        // Get user input
        boolean shouldContinue = true;
        while (shouldContinue) {
            System.out.println(hr);
            String input = sc.nextLine();
            System.out.println(hr);

            if (input.equals("bye")) {
                shouldContinue = false;
                sc.close();
            } else {
                // Add text into a list
                taskList.add(input);
                System.out.println("\tAdded: " + input);
            }
        }

        // Exit message
        System.out.println("Baaaaaa byeeee. Come baaaaack soon!");

    }
}
