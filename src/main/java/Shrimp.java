import java.util.Scanner;

public class Shrimp {

    private static final String PARTITION = "____________________________________________________________\n";

    public static void main(String[] args) {
        //program initialize
        programStart();

        //echo user's command
        Scanner sc = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = sc.nextLine();  // Read the next line of user input

            if (userInput.equalsIgnoreCase("bye")) {
                break;  // Exit the loop if "bye" is entered
            }

            String output = PARTITION + userInput + "\n" + PARTITION;
            System.out.println(output);
        }
        programExit();
    }

    static void programStart() {
        String greetings = "Domo! Same desu~ I am shrimp, and I am happy to assist you! Hewwo? <3\n";
        String logo = """
                
                       .__          .__               \s
                  _____|  |_________|__| _____ ______ \s
                 /  ___/  |  \\_  __ \\  |/     \\\\____ \\\s
                 \\___ \\|   Y  \\  | \\/  |  Y Y  \\  |_> >
                /____  >___|  /__|  |__|__|_|  /   __/\s
                     \\/     \\/               \\/|__|   \s
                                                      \s
                """;
        String output = PARTITION + greetings + logo + "\n" + PARTITION;
        System.out.println(output);
    }

    static void programExit() {
        String output = "Byebye~ It's time to say goodbye for the day~ Hope you enjoyed and had fuuun~ " +
                "I'll see you later~\n" + PARTITION;
        System.out.println(output);
    }
}
