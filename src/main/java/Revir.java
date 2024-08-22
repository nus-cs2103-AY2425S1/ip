import java.util.ArrayList;

public class Revir {
    public static void main(String[] args) {
        String name = "Revir";
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        
        ArrayList<String> userInputList =  new ArrayList<String>();
        while (true) {
            String input = System.console().readLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("history")) {
                // Print the user input history
                System.out.println("History:");
                for (String userInput : userInputList) {
                    System.out.println(userInput);
                }
            } else {
                // Add user input to the list
                userInputList.add(input);
                // Differentiate the print from the user input
                System.out.println("You wrote: " + input);
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
