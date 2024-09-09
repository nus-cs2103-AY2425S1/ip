package meep;

import java.util.Scanner;

import meep.parser.Parser;
import meep.task.Storage;
import meep.task.TaskList;
import meep.ui.Ui;


/**
 * The {@code Meep} class represents the main application that runs the task management system.
 * It is responsible for initializing the necessary components and handling the main program flow.
 */
public class Meep {
    private static final String DEFAULT_FILE_PATH = "data.txt";
    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;
    private final Parser parser;

    /**
     * Initializes a new instance of the {@code Meep} application with the specified file path.
     * This constructor loads the tasks from the file and prepares the UI, storage, and parser components.
     *
     * @param filePath The path to the file where tasks are stored.
     */
    public Meep(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (MeepException e) {
            System.out.println(ui.errorLoadingTask());
            taskList = new TaskList();
        }
    }

    public Meep() {
        this(DEFAULT_FILE_PATH);
    }

    public String getResponse(String input) {
        try {
            String output = this.parser.checkCommand(input, taskList);
            storage.saveTasks(taskList);
            return output;
        } catch (MeepException e) {
            return ui.error();
        }
    }

    public String getGreeting() {
        return ui.greeting();
    }

    /**
     * Starts the main loop of the {@code Meep} application.
     * This method continuously waits for user input, processes commands, and updates the task list.
     * It saves the tasks after each command is executed and handles exceptions by displaying error messages.
     */
    public void run() {
        System.out.println(ui.greeting());
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            try {
                System.out.println(this.parser.checkCommand(input, taskList));
                storage.saveTasks(taskList);
                if (input.equalsIgnoreCase("bye")) {
                    return;
                }
            } catch (MeepException e) {
                System.out.println(ui.error());
            }
        }
    }

    public static void main(String[] args) {
        new Meep("data.txt").run();
    }
}
