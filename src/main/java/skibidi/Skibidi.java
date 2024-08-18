package skibidi;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.InputStream;

public class Skibidi {
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

    List<Task> tasks = new ArrayList<>();

    static void printSeparator() {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    void printList() {
        if (tasks.size() == 0) {
            System.out.println("\tNO ITEMS");
            return;
        }
        System.out.println("\tLISTING ITEMS:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("\t  %d. %s\n", i + 1, tasks.get(i));
        }
    }

    void markTask(int taskId) throws InvalidItemException {
        if (taskId < 0 || taskId > tasks.size()) {
            throw new InvalidItemException();
        }
        Task task = tasks.get(taskId);
        task.mark();
        System.out.printf("\tMARKING TASK\n");
        System.out.printf("\t%s\n", task.toString());
    }

    void unmarkTask(int taskId) throws InvalidItemException {
        if (taskId < 0 || taskId > tasks.size()) {
            throw new InvalidItemException();
        }
        Task task = tasks.get(taskId);
        task.unmark();
        System.out.printf("\tUNMARKING TASK\n");
        System.out.printf("\t%s\n", task.toString());
    }

    void deleteTask(int taskId) throws InvalidItemException {
        if (taskId < 0 || taskId > tasks.size()) {
            throw new InvalidItemException();
        }
        Task task = tasks.get(taskId);
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
            switch (args[0]) {
                case "list":
                    printList();
                    break;
                case "mark":
                    markTask(Integer.parseInt(args[1].strip()) - 1);
                    break;
                case "unmark":
                    unmarkTask(Integer.parseInt(args[1].strip()) - 1);
                    break;
                case "todo":
                    if (args.length == 1) {
                        throw new SkibidiException("COMMAND todo REQUIRES DESCRIPTION ARGUMENT");
                    }
                    Todo todo = new Todo(args[1].strip());
                    tasks.add(todo);
                    System.out.printf("\tADDED TODO: %s\n", todo.toString());
                    System.out.printf("\tNUMBER OF TASKS IN LIST: %d\n", tasks.size());
                    break;
                case "deadline":
                    cmdArgs = args[1].split("/by");
                    if (cmdArgs.length != 2) {
                        throw new SkibidiException("COMMAND deadline REQUIRES ARGUMENT /by");
                    }
                    Deadline deadline = new Deadline(cmdArgs[0].strip(), cmdArgs[1].strip());
                    tasks.add(deadline);
                    System.out.printf("\tADDED DEADLINE: %s\n", deadline.toString());
                    System.out.printf("\tNUMBER OF TASKS IN LIST: %d\n", tasks.size());
                    break;
                case "event":
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
                case "delete":
                    deleteTask(Integer.parseInt(args[1].strip()) - 1);
                    break;
                default:
                    throw new InvalidCommandException();
            }
        } catch (NumberFormatException e) {
            System.out.printf("\tERROR: INVALID NUMBER GIVEN FOR COMMAND: %s\n", args[0]);
        } catch (Exception e) {
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
            err.printStackTrace();
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
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Skibidi bot = new Skibidi();
        bot.start();
    }
}
