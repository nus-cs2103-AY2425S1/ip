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

    private boolean isRunning;
    
    public String spacer = "-".repeat(40);
    
    // Functions

    Quack() {
        this.isRunning = true;
    }

    private void printLogo() {
        System.out.println(logo + "\n" + spacer);
    }

    private void greeting() {
        System.out.println("Hello! I'm " + botName + "\nWhat can I do for you?\n" + spacer);
    }

    private void farewell() {
        System.out.println("Bye. Hope to see you again soon!\n" + spacer);
    }

    private void echo (String text) {
        System.out.println(text + "\n" + spacer);
    }

    private void run() {

        Scanner sc = new Scanner(System.in);  // Create a Scanner object

        // Chatbot is running for the first time, display the logo and greet the user
        this.printLogo();
        this.greeting();
        
        // Keep taking inputs from the user
        while (isRunning) {
            String command = sc.nextLine();
            System.out.println(spacer);
            
            // As long as the user types bye in any format it will terminate
            if (command.toLowerCase().equals("bye")) {
                this.isRunning = false;
            } else {
                echo(command);
            }
        }

        // Chatbot has exited, display the farewell message
        this.farewell();
    }

    public static void main(String[] args) {    
        Quack bot = new Quack();
        bot.run();
    }
}
