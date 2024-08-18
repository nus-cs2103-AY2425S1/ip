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
        System.out.println("Bye. Hope to see you again soon!\n" + spacer);
    }

    private void act (String item) {

        if (item.equals("list")) {
            System.out.println(this.toDoList.toString());
        } else {
           this.toDoList.addItem(item);
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
            String command = sc.nextLine();
            System.out.println(spacer);
            
            // As long as the user types bye in any format it will terminate
            if (command.toLowerCase().equals("bye")) {
                this.isRunning = false;
            } else {
                act(command);
            }
        }

        sc.close();
        // Chatbot has exited, display the farewell message
        this.farewell();
    }

    public static void main(String[] args) {    
        Quack bot = new Quack();
        bot.run();
    }
}
