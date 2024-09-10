import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents Elsa, a chatbot.
 * @author Aaron
 */
public class Elsa {
    private static final List<Task> THINGS_TO_DO = new ArrayList<>();

    private static final String BASE_DIR = System.getProperty("user.dir");
    private static final Path DATA_DIR = Paths.get(BASE_DIR, "data");
    private static final Path DATA_FILE = DATA_DIR.resolve("Elsa.txt");

    public static void main(String[] args) {
        ensureDataFileExists();
        populateListOfTasks(THINGS_TO_DO);
        greetUser();
        processUserInput(THINGS_TO_DO);
        saveTasksToDataFile(THINGS_TO_DO);
    }

    /**
     * This method ensures that the directory for the Elsa.txt data file which is ..\ip\data exists.
     * If it does not exist, the method creates the ..\ip\data folder.
     * The method then checks if an Elsa.txt file exists within the ..\ip\data folder.
     * If it does not exist, a new Elsa.txt file is created.
     */
    private static void ensureDataFileExists() {
        try {
            if (!Files.exists(DATA_DIR)) {
                Files.createDirectories(DATA_DIR);
            }
            if (!Files.exists(DATA_FILE)) {
                Files.createFile(DATA_FILE);
            }
        } catch (IOException e) {
            System.out.println("Oops, it seems like an error has occurred while creating directories or files.");
        }
    }

    /**
     * This method updates the THINGS_TO_DO arrayList to match the data in the Elsa.txt file.
     */
    private static void populateListOfTasks(List<Task> tasks) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Oops, it appears that an error has occurred while reading the file: " + e.getMessage());
        }

        for (int i = 0; i < lines.size(); i++) {
            tasks.add(convertStringToTask(lines.get(i)));
        }
    }

    /**
     * This method converts a line in the Elsa.txt data file to its corresponding Task object.
     */
    private static Task convertStringToTask(String taskInfo) {
        String[] parts = taskInfo.split(" \\| ");

        String taskType = parts[0];
        String description = parts[2];
        boolean isDone = parts[1].equals("1");

        switch (taskType) {
            case "T":
                // Create a Todo task
                return new Todo(description, isDone);

            case "D":
                // Create a Deadline task
                String by = parts[3];
                return new Deadline(description, isDone, by);

            case "E":
                // Create an Event task
                String[] eventTimes = parts[3].split(" - ");
                String from = eventTimes[0].trim();
                String to = eventTimes[1].trim();
                return new Event(description, isDone, from, to);

            default:
                throw new IllegalArgumentException("Invalid task type: " + taskType);
        }
    }

    /**
     * This method saves the data of the tasks in the THINGS_TO_DO arrayList in the Elsa.txt file.
     */
    private static void saveTasksToDataFile(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE.toFile(), false))) {
            for (Task task : tasks) {
                String taskString = taskToString(task);
                writer.write(taskString);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Oops, it appears that an error has occurred while writing to the file: " +
                               e.getMessage());
        }
    }

    /**
     * This method converts a task object in the tasks arrayList to a corresponding line for the Elsa.txt data file.
     */
    private static String taskToString(Task task) {
        if (task instanceof Todo) {
            return "T | " + (task.isDone ? "1" : "0") + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + (task.isDone ? "1" : "0") + " | " + task.getDescription() + " | " + deadline.getBy();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + (task.isDone ? "1" : "0") + " | " + task.getDescription() + " | " +
                    event.getFrom() + " - " + event.getTo();
        } else {
            throw new IllegalArgumentException("Unknown task type: " + task.getClass().getSimpleName());
        }
    }

    /**
     * This method adds a horizontal separator line consisting of underscores.
     */
    private static void addLine() {
        System.out.println("______________________________________");
    }

    /**
     * This method greets the user and begins the conversation.
     */
    private static void greetUser() {
        System.out.println("Hi! I'm Elsa");
        System.out.println("It's nice to meet you.\nHow can I help you?");
        addLine();
    }

    /**
     * This method ends the conversation and says bye to the user.
     */
    private static void goodbye() {
        addLine();
        System.out.println("Bye! It was so nice chatting with you.\nSee you again soon!");
        addLine();
    }

    /**
     * This method adds a Todo_task to the list.
     */
    private static void addTodo(List<Task> tasks, String description) {
        Todo newTodo = new Todo(description, false);
        tasks.add(newTodo);
        addLine();
        System.out.println("Alright, I've added this task:\n  " + tasks.get(tasks.size() - 1) + "\nWe have " +
                           tasks.size() + " tasks in our list now.");
        addLine();
    }

    /**
     * This method adds a Deadline_task to the list.
     */
    private static void addDeadline(List<Task> tasks, String description, String by) {
        Deadline newDeadline = new Deadline(description, false, by);
        tasks.add(newDeadline);
        addLine();
        System.out.println("Alright, I've added this task:\n  " + tasks.get(tasks.size() - 1) + "\nWe have " +
                           tasks.size() + " tasks in our list now.");
        addLine();
    }

    /**
     * This method adds an Event_task to the list.
     */
    private static void addEvent(List<Task> tasks, String description, String from, String to) {
        Event newEvent = new Event(description, false, from, to);
        tasks.add(newEvent);
        addLine();
        System.out.println("Alright, I've added this task:\n  " + tasks.get(tasks.size() - 1) + "\nWe have " +
                           tasks.size() + " tasks in our list now.");
        addLine();
    }

    /**
     * This method deletes a task from the list.
     * @param tasks the list of tasks
     * @param userInput the input provided by the user
     */
    private static void deleteTask(List<Task> tasks, String userInput) {
        // Finds the index of the task that is to be deleted
        int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            // Informs the user that the task has been deleted
            addLine();
            System.out.println("Okay, I've removed this task:\n  " + tasks.get(taskIndex).toString() + "\nWe have " +
                               (tasks.size() - 1) + " tasks in our list now.");
            addLine();
            // Deletes the task from the list
            tasks.remove(taskIndex);
        }
    }

    /**
     * This method lists all the tasks in the task list.
     */
    private static void listTasks(List<Task> tasks) {
        addLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        addLine();
    }

    /**
     * This method marks any task as done with an 'X'.
     */
    private static void markTask(List<Task> tasks, String userInput) {
        // Finds and marks the task the user is referring to as done
        int taskIndex = Integer.parseInt(userInput.substring(5)) - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).done();
            // Informs the user that the task has been marked as done
            addLine();
            System.out.println("Great! I've marked it as done:\n  " + tasks.get(taskIndex).toString());
            addLine();
        }
    }

    /**
     * This method unmarks any task by removing any 'X'.
     */
    private static void unmarkTask(List<Task> tasks, String userInput) {
        // Finds and marks the task the user is referring to as not done
        int taskIndex = Integer.parseInt(userInput.substring(7)) - 1;
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).notDone();
            // Informs the user that the task has been marked as not done
            addLine();
            System.out.println("Alright, I've unchecked this task:\n  " + tasks.get(taskIndex).toString());
            addLine();
        }
    }

    /**
     * This method creates a new scanner object to process user input.
     */
    private static void processUserInput(List<Task> tasks) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userInput = scanner.nextLine();

            if (userInput.contains("bye")) {
                goodbye();
                break;
            } else if (userInput.contains("list")) {
                listTasks(tasks);
            } else if (userInput.startsWith("mark")) {
                markTask(tasks, userInput);
            } else if (userInput.startsWith("unmark")) {
                unmarkTask(tasks, userInput);
            } else if (userInput.startsWith("todo")) {
                if (userInput.trim().substring(4).isEmpty()) {
                    addLine();
                    System.out.println("Oh, it appears that the description of your ToDo item is empty...");
                    addLine();
                    continue;
                }
                addTodo(tasks, userInput.substring(5));
            } else if (userInput.startsWith("deadline")) {
                String[] parts = userInput.split(" /by ", 2);
                String description = parts[0].replace("deadline ", "").trim();
                String by = parts[1].trim();
                // Handle the case where the 'by' variable is not in the correct format of yyyy-mm-dd hhmm (time)
                if (by.length() != 16 || !by.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}")) {
                    addLine();
                    System.out.println("Please follow the yyyy-mm-dd hh:mm format for this task with a deadline.");
                    addLine();
                    continue;
                }
                addDeadline(tasks, description, by);
            } else if (userInput.startsWith("event")) {
                String[] parts = userInput.split(" /from | /to ");
                String description = parts[0].replace("event ", "").trim();
                String from = parts[1].trim();
                String to = parts[2].trim();
                addEvent(tasks, description, from, to);
            } else if (userInput.startsWith("delete")) {
                deleteTask(tasks, userInput);
            } else {
                // Elsa will ask for clarification upon encountering any unrecognised input
                addLine();
                System.out.println("Sorry, I'm unable to perform this action: " + userInput);
                addLine();
            }
        }
        scanner.close();
    }
}
