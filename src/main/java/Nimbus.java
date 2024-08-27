import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;

public class Nimbus {
    final private static String name = "Nimbus";
    private static final ArrayList<Task> tasks = new ArrayList<Task>();
    private static boolean isRunning = true;
    final private static String DATA_FILE_PATH = "./data/data.txt";

    static Ui ui;
    static Storage storage;

    public enum Command {
        Remove,
        List,
        Mark,
        Unmark,
        Todo,
        Deadline,
        Event,
        Bye
    }

    public static void addTask(Task task, boolean showMsg, boolean writeToFile) {
        if (writeToFile)
            storage.writeTaskToFile(task);

        tasks.add(task);
        if (showMsg)
            ui.showAddedTask(task, tasks.size());

    }

    public static void removeTask(int index) {
        if (storage.removeTaskFromFileByIndex(index, tasks.size()))
            ui.showRemovedTask(tasks.remove(index), tasks.size());
        /*
        else
            ui.showErrorRemovingTask()
         */
    }

    public static void setDone(int index) {
        tasks.get(index).setDone();
        ui.showDoneTask(tasks.get(index));
        storage.writeTasksToFile(tasks);
    }

    public static void setNotDone(int index) {
        tasks.get(index).setNotDone();
        ui.showNotDoneTask(tasks.get(index));
        storage.writeTasksToFile(tasks);
    }

    public static Command getCommandType(String line) throws InvalidCommandException {
        int index = line.indexOf(" ");
        String command = line;
        if (index != -1) {
            command = line.substring(0, index);
        }
        return switch (command) {
            case "list" -> Command.List;
            case "bye" -> Command.Bye;
            case "remove" -> Command.Remove;
            case "mark" -> Command.Mark;
            case "unmark" -> Command.Unmark;
            case "todo" -> Command.Todo;
            case "deadline" -> Command.Deadline;
            case "event" -> Command.Event;
            default -> throw new InvalidCommandException(command);
        };
    }

    public static String getArgument(String command) throws InvalidArgumentException {
        command = command.trim();
        int index = command.indexOf(" ");
        if (index == -1)
            throw new InvalidArgumentException("Error: Empty Argument!");
        return command.substring(index + 1);
    }

    public static String readOption(String argument, String target) throws InvalidArgumentException {
        int startIndex = argument.indexOf("/" + target);
        if (startIndex == -1) {
            throw new InvalidArgumentException("Error: Missing option: " + target);
        }
        String substringAfterOption = argument.substring(startIndex + target.length() + 1);
        int endIndex = substringAfterOption.indexOf("/");
        if (endIndex == -1)
            return substringAfterOption.trim();
        else
            return argument.substring(startIndex + target.length() + 1,
                    endIndex + startIndex + target.length() + 1).trim();
    }

    public static String getDescription(String argument) {
        int index = argument.indexOf("/");
        if (index == -1)
            return argument.trim();
        else
            return argument.substring(0, index).trim();
    }

    public static void processCommand(String line) throws InvalidCommandException, InvalidArgumentException{
        switch (getCommandType(line)) {
        case List:
            Nimbus.ui.showAllTasks(tasks);
            break;
        case Remove:
            removeTask(Integer.parseInt(getArgument(line)) - 1);
            break;
        case Mark:
            setDone(Integer.parseInt(getArgument(line)) - 1);
            break;
        case Unmark:
            setNotDone(Integer.parseInt(getArgument(line)) - 1);
            break;
        case Todo:
            addTask(new Todo(getArgument(line)), true, true);
            break;
        case Deadline:
            addTask(new Deadline(getDescription(getArgument(line)),
                    readOption(getArgument(line), "by")), true, true);
            break;
        case Event:
            addTask(new Event(getDescription(getArgument(line)),
                    readOption(getArgument(line), "from"),
                    readOption(getArgument(line), "to")), true, true);
            break;
        case Bye:
            isRunning = false;
            break;
        default:
            throw new RuntimeException("Error: Missing instruction for " + getCommandType(line));
        }
    }

    public static void main(String[] args) {
        Nimbus.ui = new Ui(name);
        Nimbus.storage = new Storage(DATA_FILE_PATH);

        run();
    }

    public static void run() {
        tasks.addAll(storage.load());
        ui.showWelcomeMessage();

        Scanner scanner = new Scanner(System.in);
        String line;

        while(isRunning) {
            line = scanner.nextLine();
            try {
                processCommand(line);
            } catch (InvalidCommandException | InvalidArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        ui.showGoodbyeMessage();

    }
}
