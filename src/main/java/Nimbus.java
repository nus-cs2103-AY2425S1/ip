import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;

public class Nimbus {
    final private static String name = "Nimbus";
    private static final ArrayList<Task> tasks = new ArrayList<Task>();
    private static boolean isRunning = true;
    final private static String DATA_FILE_PATH = "./data/data.txt";

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

    private static void printDash() {
        System.out.println("____________________________________________________________");
    }

    public static void printWelcomeMessage() {
        printDash();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        printDash();
    }

    public static void printGoodbyeMessage() {
        printDash();
        System.out.println("Bye. Hope to see you again soon!");
        printDash();
    }

    public static void addTask(Task task, boolean showMsg, boolean writeToFile) {
        tasks.add(task);
        if (showMsg) {
            System.out.println("Got it. I've added this task:\n" + task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }

        if (!writeToFile)
            return;

        try (FileWriter file = new FileWriter(DATA_FILE_PATH, true)){
            file.write(task.toFileFormat());
            file.write(System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Unable to write to file: " + e.getMessage());
        }
    }

    public static void removeTask(int index) {
        try {
            File file = new File(DATA_FILE_PATH);
            Scanner sc = new Scanner(file);
            ArrayList<String> arr = new ArrayList<String>();
            for (int i = 0; i < tasks.size() && sc.hasNext(); ++i) {
                String nxt = sc.nextLine();
                if (i == index)
                    continue;
                arr.add(nxt);
            }
            sc.close();
            FileWriter fw = new FileWriter(DATA_FILE_PATH);
            for (String s : arr) {
                fw.write(s);
                fw.write(System.lineSeparator());
            }
            fw.close();

        } catch (IOException e) {
            System.out.println("Unable to write to file: " + e.getMessage());
        }

        System.out.println("Noted. I've removed this task: " + tasks.remove(index));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public static void setDone(int index) {
        System.out.println("Nice! I've marked this task as done:");
        tasks.get(index).setDone();
        System.out.println(tasks.get(index));

        try {
            File file = new File(DATA_FILE_PATH);
            Scanner sc = new Scanner(file);
            ArrayList<String> arr = new ArrayList<String>();
            for (int i = 0; i < tasks.size() && sc.hasNext(); ++i) {
                arr.add(sc.nextLine());
            }
            sc.close();

            FileWriter fw = new FileWriter(DATA_FILE_PATH);
            for (int i = 0; i < tasks.size(); ++i) {
                if (i != index)
                    fw.write(arr.get(i));
                else
                    fw.write(tasks.get(index).toFileFormat());
                fw.write(System.lineSeparator());
            }
            fw.close();

        } catch (IOException e) {
            System.out.println("Unable to write to file: " + e.getMessage());
        }
    }

    public static void setNotDone(int index) {
        System.out.println("OK, I've marked this task as not done yet:");
        tasks.get(index).setNotDone();
        System.out.println(tasks.get(index));

        try {
            File file = new File(DATA_FILE_PATH);
            Scanner sc = new Scanner(file);
            ArrayList<String> arr = new ArrayList<String>();
            for (int i = 0; i < tasks.size() && sc.hasNext(); ++i) {
                arr.add(sc.nextLine());
            }
            sc.close();

            FileWriter fw = new FileWriter(DATA_FILE_PATH);
            for (int i = 0; i < tasks.size(); ++i) {
                if (i != index)
                    fw.write(arr.get(i));
                else
                    fw.write(tasks.get(index).toFileFormat());
                fw.write(System.lineSeparator());
            }
            fw.close();

        } catch (IOException e) {
            System.out.println("Unable to write to file: " + e.getMessage());
        }
    }

    public static void printAllTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); ++i) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public static void invalidCommand() {
        System.out.println("Invalid Command");
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
            return argument;
        else
            return argument.substring(0, index);
    }

    public static void processCommand(String line) throws InvalidCommandException, InvalidArgumentException{
        switch (getCommandType(line)) {
        case List:
            printAllTask();
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

    // create all the necessary directory and file if the file doesn't exists
    public static void checkSavedFile(String filePath) {
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error occur when creating file.");
            }
        }
    }

    public static void readSavedFile(String filePath) {
        checkSavedFile(filePath);
        File file = new File(filePath);
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNext()) {
                Task task = getTaskFromSavedCommand(sc.nextLine());
                if (task != null) {
                    addTask(task, false, false);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Can't find saved data file");
        }
    }

    public static Task getTaskFromSavedCommand(String command) {
        String[] arr = command.split("\\|");
        boolean isDone = arr[1].equals("1");
        return switch (arr[0]) {
            case "T" -> new Todo(arr[2], isDone);
            case "D" -> new Deadline(arr[2], isDone, arr[3]);
            case "E" -> new Event(arr[2], isDone, arr[3], arr[4]);
            default -> null;
        };
    }

    public static void main(String[] args) {
        printWelcomeMessage();

        readSavedFile(DATA_FILE_PATH);

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

        printGoodbyeMessage();
    }
}
