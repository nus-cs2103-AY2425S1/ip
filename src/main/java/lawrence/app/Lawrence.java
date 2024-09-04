package lawrence.app;

import lawrence.command.Command;
import lawrence.database.TaskFileManager;
import lawrence.factory.CommandFactory;
import lawrence.task.Task;
import lawrence.task.TaskList;
import lawrence.ui.UserInterface;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Lawrence {
    private static final String NAME = "Lawrence";
    private static final Path SAVE_LOCATION = Paths.get(".", "data", "tasks.txt");
    private TaskList taskList;
    private final TaskFileManager manager;
    private final UserInterface ui;

    public Lawrence() {
        ui = new UserInterface(NAME);
        manager = new TaskFileManager(SAVE_LOCATION);
        try {
            Task[] existingTasks = manager.readTasksFromFile();
            taskList = new TaskList(existingTasks);
        } catch (IOException e) {
            // initialise with no tasks instead
            taskList = new TaskList(new Task[0]);
        }
    }

    public static void main(String[] args) {
        Lawrence primeMinister = new Lawrence();
        primeMinister.run();
    }

    public void run() {
        ui.greetUser();

        Scanner sc = new Scanner(System.in);
        String userInput;
        boolean shouldContinue = true;
        while (shouldContinue) {
            userInput = sc.nextLine();  // Get next user input
            try {
                Command c = CommandFactory.createCommand(userInput);
                c.execute(taskList, manager, ui);
                shouldContinue = c.shouldContinue();
            } catch (IllegalArgumentException e) {
                ui.showMessage(String.format("%s Please try again.", e.getMessage()));
            }
        }
    }
}
