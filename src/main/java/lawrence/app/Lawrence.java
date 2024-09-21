package lawrence.app;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import lawrence.command.Command;
import lawrence.command.CommandType;
import lawrence.database.TaskFileManager;
import lawrence.parser.CommandParser;
import lawrence.task.Task;
import lawrence.task.TaskList;
import lawrence.ui.UserInterface;

/**
 * The Lawrence chatbot that is able to keep track of tasks and their
 * respective completion statuses.
 * <p>
 * The tasks are saved automatically after every action.
 * This ensures that all changes are preserved and will be available
 * the next time the program is run.
 * </p>
 * @author Kok Bo Chang
 */
public class Lawrence {
    private static final String NAME = "Lawrence";
    private static final Path SAVE_LOCATION = Paths.get(".", "data", "tasks.txt");

    private final TaskFileManager manager;
    private TaskList tasks;
    private final UserInterface ui;

    /**
     * Default constructor.
     */
    public Lawrence() {
        ui = new UserInterface(NAME);
        manager = new TaskFileManager(SAVE_LOCATION);
        try {
            Task[] existingTasks = manager.readTasksFromFile();
            tasks = new TaskList(existingTasks);
        } catch (IOException e) {
            // initialise with no tasks instead
            tasks = new TaskList();
        }

        assert tasks != null; // the task object will always be initialised
    }

    /**
     * Initialises and runs the Lawrence chatbot.
     *
     * @param args optional startup arguments
     */
    public static void main(String[] args) {
        Lawrence lawrence = new Lawrence();
        lawrence.run();
    }

    /**
     * Runs the chatbot to start listening for user commands
     * in the console.
     */
    public void run() {
        ui.greetUser();

        Scanner sc = new Scanner(System.in);
        String userInput;
        boolean shouldContinue = true;
        while (shouldContinue) {
            userInput = sc.nextLine();
            try {
                Command c = CommandParser.createCommand(userInput);
                c.execute(tasks, manager, ui);
                shouldContinue = c.shouldContinue();
            } catch (IllegalArgumentException | IllegalStateException e) {
                ui.showMessage(String.format("%s Please try again.", e.getMessage()));
            }
        }

        assert shouldContinue == false; // program should only exit once shouldContinue is false
    }

    /**
     * Returns the text response of the bot after parsing the input string and executing the relevant command.
     *
     * @param input the input string containing instructions on what command to run
     * @return a {@link Response} object containing details on the actions the bot took
     */
    public Response getResponse(String input) {
        try {
            Command c = CommandParser.createCommand(input);
            String message = c.execute(tasks, manager, ui);
            return new Response(c.getType(), message, c.shouldContinue());
        } catch (IllegalArgumentException | IllegalStateException e) {
            String message = String.format("%s Please try again.", e.getMessage());
            return new Response(CommandType.INVALID, message, true);
        }
    }

    public String getWelcomeMessage() {
        return String.format("Hello! I'm %s and I'm here to establish another GST hike.%n"
                + "What can I do for you?", NAME);
    }
}
