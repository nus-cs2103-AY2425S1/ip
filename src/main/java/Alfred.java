import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Alfred {
    private static List<Task> lis;
    private static Path filePath;

    public static void main(String[] args) {
        // Greet user
        Ui.greet();

        String filePath = "./data/Alfred.txt";

        // Create Storage
        Storage storage = new Storage(filePath);

        // List to store tasks
        lis = new ArrayList<>(storage.loadTasks());

        // Create a Scanner Object
        Scanner in = new Scanner(System.in);

        String input = in.nextLine();
        while (!input.equals("bye")) {
            String command = getCommand(input);

            if (input.equals("list")) {
                Ui.showList(lis);
            } else if (command.equals("mark") || command.equals("unmark")) {
                processMarkCommand(input, command.equals("mark"));
            } else if (command.equals("delete")) {
                deleteTask(input);
            } else if (Task.isCreateTaskCommand(input)){
                try {
                    Task task = Task.initialise(input);
                    lis.add(task);
                    Ui.showAddedTaskMessage(task, lis.size());
                } catch (AlfredException e) {
                    Ui.showAlfredError(e);
                } catch (Exception e) {
                    Ui.showUnexpectedError(e);
                }
            } else {
                Ui.showInvalidCommand();
            }

            input = in.nextLine();
        }

        // save tasks
        storage.saveTasks(lis);

        Ui.farewell();
    }

    public static int getTaskNumberFromInput(String input) {
        String[] parts = input.split(" ");
        return Integer.parseInt(parts[1]);
    }

    public static String getCommand(String input) {
        return input.split(" ")[0];
    }

    public static void processMarkCommand(String input, boolean mark) {
        String action = mark ? "mark" : "unmark";

        if (!isValidCommand(input, action)) {
            return;
        }

        int taskNumber = getTaskNumberFromInput(input);
        Task task = lis.get(taskNumber - 1);

        if (mark) {
            task.markAsDone();
            Ui.showTaskMarked(task);
        } else {
            task.unmark();
            Ui.showTaskUnmarked(task);
        }
    }

    public static void deleteTask(String input) {
        if (!isValidCommand(input, "delete")) {
            return;
        }

        int taskNumber = getTaskNumberFromInput(input);
        Task task = lis.remove(taskNumber - 1);
        Ui.showTaskDeleted(task, lis.size());
    }

    private static boolean isValidCommand(String input, String action) {
        String regex = "^" + action + " \\d+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) {
            Ui.showInvalidCommandFormat();
            return false;
        }

        int taskNumber = getTaskNumberFromInput(input);
        if (taskNumber <= 0 || taskNumber > lis.size()) {
            Ui.showInvalidTaskNumber(lis.size());
            return false;
        }
        return true;
    }
}
