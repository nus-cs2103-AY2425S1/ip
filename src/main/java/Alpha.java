import java.util.Scanner;
public class Alpha {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Storage storage = new Storage();
        String initialResponse = "____________________________________________________________\n"
                + "Hello! I'm Alpha \n"
                + "What can I do for you? \n"
                + "____________________________________________________________ \n";
        
        System.out.println("Hello from\n" + initialResponse);
    
        while (true) {
            String s1 = scanner.nextLine();
        
            // Check if the user input is "bye"
            if (s1.equalsIgnoreCase("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;  // Exit the loop
            }
        
            storage.storeWord(s1);
            String userInput = storage.lastWord();
            String echoResponse = "____________________________________________________________ \n"
                    + userInput + "\n"
                    + "____________________________________________________________ \n";
            System.out.println(echoResponse);
        }
    
        // Close the scanner after the loop ends
        scanner.close();
    }
}