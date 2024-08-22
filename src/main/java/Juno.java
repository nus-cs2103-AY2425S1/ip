import java.util.Scanner;
import java.util.ArrayList;

public class Juno {
    private String logo;
    private String greeting;
    private String farewell;

    private final String exitString;

    private final String listTaskString;
    private ArrayList<String> tasks;

    public Juno() {
        this.logo = """
                
            .----------------.  .----------------.  .-----------------. .----------------.\s
            | .--------------. || .--------------. || .--------------. || .--------------. |
            | |     _____    | || | _____  _____ | || | ____  _____  | || |     ____     | |
            | |    |_   _|   | || ||_   _||_   _|| || ||_   \\|_   _| | || |   .'    `.   | |
            | |      | |     | || |  | |    | |  | || |  |   \\ | |   | || |  /  .--.  \\  | |
            | |   _  | |     | || |  | '    ' |  | || |  | |\\ \\| |   | || |  | |    | |  | |
            | |  | |_' |     | || |   \\ `--' /   | || | _| |_\\   |_  | || |  \\  `--'  /  | |
            | |  `.___.'     | || |    `.__.'    | || ||_____|\\____| | || |   `.____.'   | |
            | |              | || |              | || |              | || |              | |
            | '--------------' || '--------------' || '--------------' || '--------------' |
             '----------------'  '----------------'  '----------------'  '----------------'\s
            """;
        this.greeting = "🌟 Welcome to the Future! I'm Juno, your digital assistant.\n" +
                "How can I assist you on your journey today?";
        this.farewell = "👋 Farewell for now! Looking forward to our next interaction.";
        this.exitString = "bye";
        this.listTaskString = "list";
        this.tasks = new ArrayList<>();
    }

    public void run() {
        // start the chat bot
        System.out.println("___________________________________________________________________");
        System.out.println(this.logo);
        System.out.println("___________________________________________________________________");
        System.out.println(this.greeting);
        System.out.println("___________________________________________________________________");

        // detect what user inputs with a scanner
        this.detectUserInput();
    }

    public void detectUserInput() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String userInput = scanner.nextLine().trim();
            if (userInput.equalsIgnoreCase(this.exitString)) {
                this.farewellMessage();
                break;
            } else if (userInput.equalsIgnoreCase(this.listTaskString)){
                this.listTasks();
            } else if (userInput.isEmpty()) {
                this.invalidUserInput();
            }
            else {
                this.addTask(userInput);
            }
        }

        scanner.close();
    }

    private void farewellMessage() {
        System.out.println("____________________________________________________________");
        System.out.println(this.farewell);
        System.out.println("____________________________________________________________");
    }

    private void listTasks() {
        System.out.println("____________________________________________________________");
        if (this.tasks.isEmpty()) {
            System.out.println("No tasks added yet!");
        } else {
            System.out.println("Here's a rundown of all your tasks! \uD83D\uDE0A");
            for (int i = 0; i < this.tasks.size(); i++) {
                System.out.println((i + 1) + ". " + this.tasks.get(i));
            }
        }
        System.out.println("____________________________________________________________");
    }

    private void addTask(String userInput) {
        tasks.add(userInput);
        System.out.println("____________________________________________________________");
        System.out.println("Got it! I've added : \"" + userInput + "\" in my memory!");
        System.out.println("____________________________________________________________");
    }

    private void invalidUserInput() {
        System.out.println("____________________________________________________________");
        System.out.println("Well, seems like you did not input anything! Please try again.");
        System.out.println("____________________________________________________________");
    }





    public static void main(String[] args) {
        Juno junoChatBot = new Juno();
        junoChatBot.run();

    }
}
