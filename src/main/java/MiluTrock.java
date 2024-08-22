import java.util.ArrayList;

public class MiluTrock {
    public static void main(String[] args) {
        String name = "MiluTrock";
        System.out.println("Hello! I'm " + name + "!");
        System.out.println("What can I do for you?");
        
        ArrayList<String> items = new ArrayList<String>();
        while (true) {
            String input = System.console().readLine();
            
            if (input.toLowerCase().equals("bye")) {
                // Exit if "bye" is entered
                break;
            } else if (input.toLowerCase().equals("list")) {
                // List all previous inputs if "list" is entered
                for (int i = 0; i < items.size(); i++) {
                    System.out.println((i+1) + ". " + items.get(i));
                }
            } else {
                // Otherwise, store input
                System.out.println("Added: " + input);
                items.add(input);
            }
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
