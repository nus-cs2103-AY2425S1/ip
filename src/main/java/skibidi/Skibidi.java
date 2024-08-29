package skibidi;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import skibidi.task.AbstractTask;
import skibidi.task.Deadline;
import skibidi.task.Event;
import skibidi.task.Todo;

public class Skibidi {
    enum SkibidiCommand {
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE
    }

    static class SkibidiException extends Exception {
        public SkibidiException(String message) {
            super(String.format("SKIBIDI ERROR: %s", message));
        }
    }

    static class InvalidCommandException extends SkibidiException {
        public InvalidCommandException() {
            super("INVALID COMMAND GIVEN");
        }
    }

    static class InvalidItemException extends SkibidiException {
        public InvalidItemException() {
            super("INVALID ITEM NUMBER GIVEN");
        }
    }

    List<AbstractTask> tasks;
    String dataPath;

    static void printSeparator() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public Skibidi(String dataPath) {
        this.dataPath = dataPath;
        this.tasks = new ArrayList<>();
        loadTasksFromDisk();
    }

    void saveTasksToDisk() {
        File file = new File(dataPath);
        try (FileWriter writer = new FileWriter(file, false)) {
            for (AbstractTask task : tasks) {
                writer.write(task.serialize() + '\n');
            }
        } catch (IOException err) {
            System.out.println(err.toString());
        }
    }

    void loadTasksFromDisk() {
        File file = new File(dataPath);
        tasks = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                tasks.add(AbstractTask.deserialize(line));
            }
            scanner.close();
        } catch (FileNotFoundException err) {
            System.out.println("No data file found, task list is empty!");
        } catch (IOException err) {
            System.out.println(err.toString());
        } catch (AbstractTask.TaskDeserializationException err) {
            System.out.println(err.toString());
        }
    }

    void printList() {
        if (tasks.isEmpty()) {
            System.out.println("\tNO ITEMS");
            return;
        }
        System.out.println("\tLISTING ITEMS:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t  %d. %s\n", i + 1, tasks.get(i));
        }
    }

    void markTask(int taskId) throws InvalidItemException {
        if (taskId < 0 || taskId >= tasks.size()) {
            throw new InvalidItemException();
        }
        AbstractTask task = tasks.get(taskId);
        task.mark();
        System.out.printf("\tMARKING TASK\n");
        System.out.printf("\t%s\n", task.toString());
    }

    void unmarkTask(int taskId) throws InvalidItemException {
        if (taskId < 0 || taskId >= tasks.size()) {
            throw new InvalidItemException();
        }
        AbstractTask task = tasks.get(taskId);
        task.unmark();
        System.out.printf("\tUNMARKING TASK\n");
        System.out.printf("\t%s\n", task.toString());
    }

    void deleteTask(int taskId) throws InvalidItemException {
        if (taskId < 0 || taskId >= tasks.size()) {
            throw new InvalidItemException();
        }
        AbstractTask task = tasks.get(taskId);
        tasks.remove(taskId);
        System.out.printf("\tDELETED TASK: %s\n", task.toString());
        System.out.printf("\tNUMBER OF TASKS IN LIST: %d\n", tasks.size());
    }

    void parseAndExecuteCommand(String line) {
        String[] args = line.split(" ", 2);
        if (args.length == 0) {
            System.out.println("\tNO COMMANDS GIVEN");
            Skibidi.printSeparator();
            return;
        }
        String[] cmdArgs;
        try {
            switch (SkibidiCommand.valueOf(args[0].toUpperCase())) {
                case LIST:
                    printList();
                    break;
                case MARK:
                    markTask(Integer.parseInt(args[1].strip()) - 1);
                    break;
                case UNMARK:
                    unmarkTask(Integer.parseInt(args[1].strip()) - 1);
                    break;
                case TODO:
                    if (args.length == 1) {
                        throw new SkibidiException("COMMAND todo REQUIRES DESCRIPTION ARGUMENT");
                    }
                    Todo todo = new Todo(args[1].strip());
                    tasks.add(todo);
                    System.out.printf("\tADDED TODO: %s\n", todo.toString());
                    System.out.printf("\tNUMBER OF TASKS IN LIST: %d\n", tasks.size());
                    break;
                case DEADLINE:
                    cmdArgs = args[1].split("/by");
                    if (cmdArgs.length != 2) {
                        throw new SkibidiException("COMMAND deadline REQUIRES ARGUMENT /by");
                    }
                    Deadline deadline = new Deadline(cmdArgs[0].strip(), cmdArgs[1].strip());
                    tasks.add(deadline);
                    System.out.printf("\tADDED DEADLINE: %s\n", deadline.toString());
                    System.out.printf("\tNUMBER OF TASKS IN LIST: %d\n", tasks.size());
                    break;
                case EVENT:
                    // Assume order of arguments is always /from followed by /to
                    cmdArgs = args[1].split("/from|/to");
                    if (cmdArgs.length != 3) {
                        throw new SkibidiException("COMMAND event REQUIRES ARGUMENTS /from AND /to");
                    }
                    Event event = new Event(cmdArgs[0].strip(), cmdArgs[1].strip(), cmdArgs[2].strip());
                    tasks.add(event);
                    System.out.printf("\tADDED EVENT: %s\n", event.toString());
                    System.out.printf("\tNUMBER OF TASKS IN LIST: %d\n", tasks.size());
                    break;
                case DELETE:
                    deleteTask(Integer.parseInt(args[1].strip()) - 1);
                    break;
                default:
                    throw new InvalidCommandException();
            }
            saveTasksToDisk();
        } catch (NumberFormatException e) {
            System.out.printf("\tERROR: INVALID NUMBER GIVEN FOR COMMAND: %s\n", args[0]);
        } catch (SkibidiException e) {
            System.out.printf("\t%s\n", e.getMessage());
        }
        Skibidi.printSeparator();
    }

    public void start() {
        InputStream inputStream = Skibidi.class.getResourceAsStream("/skibidi-ascii.txt");
        if (inputStream == null) {
            System.out.println("Resource file 'skibidi-ascii.txt' not found.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException err) {
            System.out.println(err.toString());
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String line;
        while (true) {
            System.out.print("> ");
            try {
                line = reader.readLine();
                if (line == null || line.equals("bye")) {
                    System.out.println();
                    Skibidi.printSeparator();
                    System.out.println("EXITING APPLICATION");
                    Skibidi.printSeparator();
                    break;
                }
                parseAndExecuteCommand(line);
            } catch (IOException err) {
                System.out.println(err.toString());
            }
        }
    }

    public static void main(String[] args) {
        Skibidi bot = new Skibidi("/data/tasks.txt");
        bot.start();
    }
}
