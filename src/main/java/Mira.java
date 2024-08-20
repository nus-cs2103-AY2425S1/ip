/**
 * Mira is a simple chatbot that echoes user commands and exits when the user types "bye".
 * It interacts with the user via the UI class, which handles input and output operations.
 */
public class Mira {
    private final UI ui; // handle user interface
    private final TaskList tasks; // Manages tasks
    private boolean isRunning; // default is true

    public Mira() {
        this.ui = new UI();
        this.tasks = new TaskList(this.ui);
        this.isRunning = true;
    }

    /**
     * Runs the chatbot, continuously reading user commands and responding until the user types "bye".
     * The chatbot echoes all commands and displays a goodbye message when exiting.
     */
    public void run() {
        // show welcome message with multiline text block
        this.ui.showMessage("""
                Hello! I'm Mira
                What can I do for you?""");
        while (this.isRunning) {
            // read user input until a newline is entered
            String userInput = ui.readCommand();
            String[] commandParts = userInput.split(" ", 2); // cam only split one time
            String command = commandParts[0];
            String argument = commandParts.length > 1 ? commandParts[1] : "";

            switch (command) {
                case "bye" -> {
                    this.ui.showMessage("Bye. Hope to see you again soon!");
                    this.isRunning = false;
                }
                case "list" -> this.tasks.listTasks();
                case "mark" -> {
                    int index = Integer.parseInt(argument);
                    this.tasks.markTask(index);
                }
                case "unmark" -> {
                    int index = Integer.parseInt(argument);
                    this.tasks.unmarkTask(index);
                }
                default -> this.tasks.addTask(userInput);
            }
        }
    }

    /**
     * The main method to start the Mira chatbot.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        Mira mira = new Mira();
        mira.run();
    }
}
