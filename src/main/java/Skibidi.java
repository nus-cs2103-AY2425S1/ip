import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

public class Skibidi {
    static class InvalidCommandException extends Exception {
        public InvalidCommandException() {
            super("INVALID COMMAND GIVEN");
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

    void markTask(int taskId) {
        Task task = tasks.get(taskId);
        task.mark();
        System.out.printf("\tMARKING TASK\n");
        System.out.printf("\t%s\n", task.toString());
    }

    void unmarkTask(int taskId) {
        Task task = tasks.get(taskId);
        task.unmark();
        System.out.printf("\tUNMARKING TASK\n");
        System.out.printf("\t%s\n", task.toString());
    }

    void parseAndExecuteCommand(String line) {
        String[] args = line.split(" ", 2);
        if (args.length == 0) {
            System.out.println("\tNO COMMANDS GIVEN");
            Skibidi.printSeparator();
            return;
        }
        String cmdArgs[];
        switch (args[0]) {
            case "list":
                printList();
                break;
            case "mark":
                try {
                    markTask(Integer.parseInt(args[1].strip()) - 1);
                } catch (NumberFormatException e) {
                    System.out.println("\tINVALID NUMBER GIVEN");
                }
                break;
            case "unmark":
                try {
                    unmarkTask(Integer.parseInt(args[1].strip()) - 1);
                } catch (NumberFormatException e) {
                    System.out.println("\tINVALID NUMBER GIVEN");
                }
                break;
            case "todo":
                tasks.add(new Todo(args[1].strip()));
                break;
            case "deadline":
                cmdArgs = args[1].split("/by");
                tasks.add(new Deadline(cmdArgs[0].strip(), cmdArgs[1].strip()));
                break;
            case "event":
                // Assume order of arguments is always /from followed by /to
                cmdArgs = args[1].split("/from|/to");
                tasks.add(new Event(cmdArgs[0].strip(), cmdArgs[1].strip(), cmdArgs[2].strip()));
                break;
            default:
                System.out.println("\tINVALID COMMAND");
                break;
        }
        Skibidi.printSeparator();
    }

    public void start() {
        String filePath = "resources/skibidi-ascii.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
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
