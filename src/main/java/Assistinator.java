import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

enum Command {
    BYE, LIST, MARK, UNMARK, TODO, EVENT, DEADLINE, DELETE, UNKNOWN;

    static Command fromString(String input) {
        String commandString = input.toUpperCase();
        try {
            return valueOf(commandString);
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
public class Assistinator {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private Parser parser;
    public Assistinator(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (AssitinatorExceptions e){
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isRunning = true;
        while (isRunning) {
            try {
                String fullCommand = ui.readCommand();
                Command command = parser.parseCommand(fullCommand);
                String response = executeCommand(command, fullCommand);
                ui.showResponse(response);
                if (command == Command.BYE) {
                    isRunning = false;
                }
            } catch (AssitinatorExceptions e) {
                ui.showError(e.getMessage());
            }
        }
    }

    private String executeCommand(Command command, String fullCommand) throws AssitinatorExceptions {
        switch (command) {
            case BYE:
                return "Bye. Hope to see you again soon!";
            case LIST:
                return tasks.listTasks();
            case MARK:
            case UNMARK:
                int index = parser.parseIndex(fullCommand);
                tasks.markTask(index, command == Command.MARK);
                storage.saveTasks(tasks.getTasks());
                return tasks.listTasks();
            case TODO:
            case DEADLINE:
            case EVENT:
                Task newTask = parser.parseTask(command, fullCommand);
                tasks.addTask(newTask);
                storage.saveTasks(tasks.getTasks());
                return "Task added successfully\nNumber of Tasks: " + tasks.size();
            case DELETE:
                int deleteIndex = parser.parseIndex(fullCommand);
                tasks.deleteTask(deleteIndex);
                storage.saveTasks(tasks.getTasks());
                return "Task " + (deleteIndex + 1) + " deleted successfully. Number of Tasks: " + tasks.size();
            default:
                throw new AssitinatorExceptions("Command does not exist");
        }
    }
    public static void main(String[] args) {
        new Assistinator("./data/assistinator.txt").run();
    }
}
