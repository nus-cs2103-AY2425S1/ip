import java.util.Scanner;

public class LunaBot {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public LunaBot(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (LunaBotException e) {
            ui.printError(e.getMessage());
            this.taskList = new TaskList();
        }
    }

    public void run() {
        ui.printWelcome();
        Scanner scanner = new Scanner(System.in);

        while (true) {  // Infinite loop, break on "bye" command
            try {
                String input = ui.readCommand();
                Command command = Parser.parse(input);  // Parse the input into a command
                command.execute(taskList, ui, storage); // Execute the command on the taskList

                // Check if the command is an ExitCommand, break the loop if so
                if (command instanceof ExitCommand) {
                    break;  // Exit the loop and end the program
                }
            } catch (LunaBotException e) {
                ui.printError(e.getMessage());
            }
        }
        ui.printGoodbye();  // Show goodbye message after loop breaks
    }

    public static void main(String[] args) {
        String filePath = "data/tasks.txt"; // Set the path for saving/loading tasks
        new LunaBot(filePath).run();  // Run the LunaBot application
    }
}
