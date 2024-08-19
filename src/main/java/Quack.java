import java.util.Scanner;

public class Quack {

    // Variables
    private String botName = "Quack";
    private String logo =
        "________                       __    \n" +
        "\\_____  \\  __ _______    ____ |  | __\n" +
        " /  / \\  \\|  |  \\__  \\ _/ ___\\|  |/ /\n" +
        "/   \\_/.  \\  |  // __ \\\\  \\___|    < \n" +
        "\\_____\\ \\_/____/(____  /\\___  >__|_ \\ \n" +
        "       \\__>          \\/     \\/     \\/\n";
    
    public String spacer = "-".repeat(40);

    private ToDoList toDoList;
    private boolean isRunning;
    
    // Functions

    Quack() {
        this.isRunning = true;
        this.toDoList = new ToDoList();
    }

    private void printLogo() {
        System.out.println(logo + "\n" + spacer);
    }

    private void greeting() {
        System.out.println("Hello! I'm " + botName + "\nWhat can I do for you?\n" + spacer);
    }

    private void farewell() {
        this.isRunning = false;
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void act (String input) {
        String[] inputs = input.split(" ");
        String command = inputs[0].toLowerCase();

        switch (command) {
            case "list":
                System.out.println(this.toDoList.toString());
                break;
            case "bye":
                farewell();
                break;
            default:
                System.out.println("There is no such function, please try again.");
                break;
        }
        System.out.println(spacer);
    }

    private void run() {

        Scanner sc = new Scanner(System.in);  // Create a Scanner object

        // Chatbot is running for the first time, display the logo and greet the user
        this.printLogo();
        this.greeting();
        
        // Keep taking inputs from the user
        while (isRunning) {
            String input = sc.nextLine();
            System.out.println(spacer);

            act(input);
        }

        // Close the scanner since it exited the forloop means the bot has terminated
        sc.close();
    }

    public static void main(String[] args) {    
        Quack bot = new Quack();
        bot.run();
    }
}
