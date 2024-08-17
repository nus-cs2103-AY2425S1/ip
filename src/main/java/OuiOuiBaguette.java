import java.util.Scanner;

public class OuiOuiBaguette {
    public static void main(String[] args) {
        String greetings = """
                ____________________________________________________________
                Bone-jaw! I'm Oui Oui Baguette.
                What can I do for you?
                Oui Oui!
                ____________________________________________________________
                """;

        String farewell = """
                ____________________________________________________________
                Bye! Hope to see you soon!
                Oui Oui!
                ____________________________________________________________
                """;
    
        // Say greetings
        System.out.println(greetings);

        // Initialise scanner
        Scanner sc = new Scanner(System.in); 

        // Main event loop
        while (true) {
            String cmd = sc.nextLine();

            if (cmd.equals("bye")) {
                break;
            }

            // Simply echo back command
            System.out.println("____________________________________________________________\n"
                    + cmd
                    + "\n____________________________________________________________");
        }

        System.out.println(farewell);
    }
    
}
