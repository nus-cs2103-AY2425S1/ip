import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CancelGPT {
    private final String CHATBOT_NAME;
    private final TasksList TASKS_LIST;
    private TasksStorage tasksStorage;
    private CommandParser commandParser;

    public CancelGPT() {
        this.CHATBOT_NAME = "CancelGPT";
        this.TASKS_LIST = new TasksList();
        try {
            this.tasksStorage = new TasksStorage(this);
        } catch (IOException e) {
            UI.printMessageToConsole("Unable to use TASKS STORAGE. Exiting program");
            System.exit(1);
        }
        this.commandParser = new CommandParser(this);
    }

    public String getName() {
        return this.CHATBOT_NAME;
    }

    public static void main(String[] args) {
        CancelGPT cancelGPT = new CancelGPT();
        cancelGPT.run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);

        UI.printHorizontalLineToConsole();
        greet();
        UI.printHorizontalLineToConsole();

        String command = sc.nextLine();
        while (!command.equals(Command.BYE.toString())) {
            UI.printHorizontalLineToConsole();
            handleCommand(command);
            UI.printHorizontalLineToConsole();

            try {
                saveTasks();
            } catch (IOException e) {
                UI.printMessageToConsole("Unable to save tasks to TASKS STORAGE. Exiting program");
                System.exit(1);
            }

            command = sc.nextLine();
        }

        sc.close();
        UI.printHorizontalLineToConsole();
        exit();
        UI.printHorizontalLineToConsole();
    }

    public void greet() {
        UI.printMessageToConsole("Hello! I am " + CHATBOT_NAME);
        UI.printMessageToConsole("What can I do for you?");
    }

    public void exit() {
        UI.printMessageToConsole("Good bye. Hope to see you again soon!");
    }

    public void handleCommand(String command) {
        this.commandParser.parseAndHandle(command);
    }

    public void deleteTask(int taskNumber) throws TaskDoesNotExist {
        this.TASKS_LIST.deleteTask(taskNumber);
    }

    public void handleAddingTask(Task task) {
        UI.printMessageToConsole("Got it. I've added this task:");
        UI.printMessageToConsole(" " + this.addToTaskList(task));
        UI.printMessageToConsole("Now you have " + this.TASKS_LIST.getSize() + " tasks in the list.");
    }

    public void markTask(int taskNumber) throws TaskDoesNotExist {
        this.TASKS_LIST.markTask(taskNumber);
    }

    public void unmarkTask(int taskNumber) throws TaskDoesNotExist {
        this.TASKS_LIST.unmarkTask(taskNumber);
    }

    public String addToTaskList(Task task) {
        return this.TASKS_LIST.addToTaskList(task);
    }

    public void displayTasksList() {
        this.TASKS_LIST.displayTasksList();
    }

    public void saveTasks() throws IOException {
        this.tasksStorage.saveTasks();
    }

    public List<Task> getTasks() {
        return this.TASKS_LIST.getTasksList();
    }
}
