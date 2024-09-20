package diomon;

import diomon.command.Command;
import diomon.parser.Parser;
import diomon.task.TaskList;

import java.util.Scanner;

/**
 * The {@code Main} class is the entry point for the Diomon application, which manages a task list.
 * It handles user input, interacts with the storage to save and load tasks, and executes commands.
 */
public class Diomon {
    private Storage storage;
    private Command command;
    private TaskList taskList;

    public Diomon(String filepath){
        storage = new Storage(filepath);
        taskList = new TaskList(storage.load());
    }

    /**
     * The main loop of the application.
     * It loads tasks from storage, listens for user input, executes commands, and saves tasks before exiting.
     */
    private void run() {
        // Initialise instance
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            command = Parser.parse(input);
            command.execute(taskList, storage);
            System.out.println(command.getResponse());
        }
    }

    public String getResponse(String input) {
        try {
            command = Parser.parse(input);
            command.execute(taskList, storage);
            return command.getResponse();
        } catch (RuntimeException e){
            return e.getMessage();
        }
    }

    /**
     * The main method to start the Diomon application.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Diomon("data/data.txt").run();
    }
}
