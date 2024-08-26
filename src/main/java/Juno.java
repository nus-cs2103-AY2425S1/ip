import java.util.ArrayList;
import java.util.Scanner;

public class Juno {
    private TaskManager taskManager;

    private FileManager fileManager;
    private JunoUi junoUi;

    private CommandParser commandParser;

    public Juno() {
    }

    public void startBot() {

        // start the UI
        this.junoUi = new JunoUi();

        // file manager to handle all file related method calls
        this.fileManager = new FileManager();

        // check if the tasks.json file exist
        this.fileManager.ensureFileExist();

        this.commandParser = new CommandParser();

        // read the data from the file
        ArrayList<Task> storedTasks = this.fileManager.readTasksFromFile();

        // task manager to handle all the task related method calls
        this.taskManager = new TaskManager(storedTasks);

        // display welcome message for users
        this.junoUi.displayWelcomeMessage();

        // detect what user inputs with a scanner
        this.detectUserInput();
    }

    public void detectUserInput() {
        Scanner scanner = new Scanner(System.in);
        boolean inWhileLoop = true;


        while (inWhileLoop) {
            String userInput = scanner.nextLine().trim();
            try {
                Command command = this.commandParser.parse(userInput, this.junoUi, this.fileManager, this.taskManager);
                command.runCommand();
                inWhileLoop = command.isInWhileLoop();
            } catch (TaskManagerException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }



    public static void main(String[] args) {
        Juno junoChatBot = new Juno();
        junoChatBot.startBot();

    }
}
