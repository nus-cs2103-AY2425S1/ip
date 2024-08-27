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
        greet();

        // Path
        filePath = Paths.get("data", "Alfred.txt");

        // List to store tasks
        lis = new ArrayList<>();

        // load tasks
        loadTasks();

        // Create a Scanner Object
        Scanner in = new Scanner(System.in);

        String input = in.nextLine();
        while (!input.equals("bye")) {
            String command = getCommand(input);

            if (input.equals("list")) {
                printList();
            } else if (command.equals("mark") || command.equals("unmark")) {
                processMarkCommand(input, command.equals("mark"));
            } else if (command.equals("delete")) {
                deleteTask(input);
            } else if (Task.isCreateTaskCommand(input)){
                try {
                    Task task = Task.initialise(input);
                    lis.add(task);
                    printAddedTaskMessage(task);
                } catch (AlfredException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(e.getMessage());
                    System.out.println("____________________________________________________________");
                } catch (Exception e) {
                    System.out.println("Unexpected error: " + e.getMessage());
                }
            } else {
                invalidCommand();
            }

            input = in.nextLine();
        }

        // save tasks
        saveTasks();

        farewell();
    }

    public static void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Good day Sir. I am Alfred, your English butler.");
        System.out.println("How can I help you today?");
        System.out.println("____________________________________________________________");
    }

    public static void farewell() {
        System.out.println("____________________________________________________________");
        System.out.println("Farewell Sir. Should you need anything, you know where to find me.");
        System.out.println("____________________________________________________________");
    }

    public static void invalidCommand() {
        System.out.println("____________________________________________________________");
        System.out.println("Terribly sorry Sir, I have no idea what you are saying.");
        System.out.println("____________________________________________________________");
    }

    public static void printList() {
        System.out.println("____________________________________________________________");
        int counter = 1;
        for (Task task : lis) {
            System.out.println(counter + ". " + task);
            counter++;
        }
        System.out.println("____________________________________________________________");
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
            System.out.println("____________________________________________________________");
            System.out.println("Indeed, Sir, the task has been duly completed:");
            System.out.println("    " + task);
            System.out.println("____________________________________________________________");
        } else {
            task.unmark();
            System.out.println("____________________________________________________________");
            System.out.println("Very well Sir, the task remains outstanding:");
            System.out.println("    " + task);
            System.out.println("A reminder that even small tasks deserve attention.");
            System.out.println("____________________________________________________________");
        }
    }

    public static void printAddedTaskMessage(Task task) {
        System.out.println("____________________________________________________________");
        System.out.println("Got it Sir. The following task has been added to your list:");
        System.out.println("    " + task);
        System.out.println("You now have " + lis.size() + " tasks awaiting your attention.");
        System.out.println("____________________________________________________________");
    }

    public static void deleteTask(String input) {
        if (!isValidCommand(input, "delete")) {
            return;
        }

        int taskNumber = getTaskNumberFromInput(input);
        Task task = lis.remove(taskNumber - 1);

        System.out.println("____________________________________________________________");
        System.out.println("Of course Sir, the task has been successfully removed.");
        System.out.println("    " + task);
        System.out.println("Your list now contains " + lis.size() + " tasks. Efficiency is its own rewards.");
        System.out.println("____________________________________________________________");
    }

    private static boolean isValidCommand(String input, String action) {
        String regex = "^" + action + " \\d+$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) {
            System.out.println("____________________________________________________________");
            System.out.println("I regret to inform you, Sir, that the command you entered is not recognized.");
            System.out.println("Please check the instructions, and I shall be ready to assist you further.");
            System.out.println("____________________________________________________________");
            return false;
        }

        int taskNumber = getTaskNumberFromInput(input);
        if (taskNumber <= 0 || taskNumber > lis.size()) {
            System.out.println("____________________________________________________________");
            System.out.println("Apologies, Sir, but that task number is not valid.");
            System.out.println("You currently have only " + lis.size() + " items in the list.");
            System.out.println("Might I suggest reviewing the list before proceeding?");
            System.out.println("____________________________________________________________");
            return false;
        }
        return true;
    }

    private static void loadTasks() {
        if (!Files.exists(filePath)) {
            return; // Start with an empty list if file does not exist
        }
        try {
            BufferedReader reader = Files.newBufferedReader(filePath);
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromFileFormat(line);
                lis.add(task);
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        } catch (AlfredException e) {
            handleAlfredCorruptedSave(e.getMessage());
        }
    }

    private static void saveTasks() {
        try {
            Files.createDirectories(filePath.getParent()); // Ensure the folder exists
            BufferedWriter writer = Files.newBufferedWriter(filePath);
            for (Task task : lis) {
                writer.write(task.toFileFormat());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    private static void handleAlfredCorruptedSave(String errorMessage) {
        System.out.println("Terribly sorry sir, I have misplaced your list of tasks.");
        System.out.println("To be more exact, the situation is as follows - ");
        System.out.println(errorMessage);
        System.out.println("Please create your list again.");
        System.out.println("____________________________________________________________");

        try {
            Files.delete(filePath);
        } catch (IOException e) {
            System.out.println("Error deleting tasks: " + e.getMessage());
        }
    }
}
