import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * PandaBot is a simple task management bot that allows users to add, list, mark, unmark,
 * and delete tasks. It supports different types of tasks including ToDos, Deadlines, and Events.
 */
public class PandaBot {

    private static final String FILE_PATH = "./data/PandaBot.txt";

    /**
     * Prints a line separator for visual clarity in the console output.
     */
    private static void printLine() {
        String line = "_________________________________________";
        System.out.println(line);
    }

    /**
     * The main method to run the PandaBot application.
     * This method continuously reads user input, processes commands, and manages the task list
     * until the user inputs "bye" to exit the program.
     *
     * @param args command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        loadTaskList(taskList);

        // Greet the user
        printLine();
        System.out.println("Hello! I'm PandaBot.");
        System.out.println("What can I do for you?");
        printLine();

        // Main Loop
        while (true) {
            String input = scanner.nextLine();

            // Exit command
            if (input.equalsIgnoreCase("bye")) {
                printLine();
                System.out.println("Bye. Hope to see you again soon!");
                printLine();
                break;
            }
            // List tasks
            else if (input.equalsIgnoreCase("list")){
                if (taskList.isEmpty()) {
                    System.out.println("There are currently no items in the list.");
                    continue;
                }
                printLine();
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i + 1) + ". " + taskList.get(i));
                }
                printLine();
            }
            // Mark a task as done
            else if (input.startsWith("mark")) {
                if (input.equalsIgnoreCase("mark")) {
                    System.out.println("Please specify which Task number to mark.");
                    continue;
                }
                int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskNum >= 0 && taskNum < taskList.size()) {
                    taskList.get(taskNum).markAsDone();
                    saveTaskList(taskList);
                    printLine();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(taskList.get(taskNum));
                    printLine();
                } else {
                    System.out.println("The specified task does not exist.");
                }
            }
            // Unmark a task
            else if (input.startsWith("unmark")) {
                if (input.equalsIgnoreCase("unmark")) {
                    System.out.println("Please specify which Task number to unmark.");
                    continue;
                }
                int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskNum >= 0 && taskNum < taskList.size()) {
                    taskList.get(taskNum).unmark();
                    saveTaskList(taskList);
                    printLine();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(taskList.get(taskNum));
                    printLine();
                } else {
                    System.out.println("The specified task does not exist.");
                }
            }
            // Delete a task
            else if (input.startsWith("delete")) {
                if (input.equalsIgnoreCase("delete")) {
                    System.out.println("Please specify which Task number to delete.");
                    continue;
                }
                int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
                if (taskNum >= 0 && taskNum < taskList.size()) {
                    printLine();
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(taskList.remove(taskNum));
                    saveTaskList(taskList);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    printLine();
                } else {
                    System.out.println("The specified task does not exist.");
                }
            }
            // Display help
            else if (input.equalsIgnoreCase("help")) {
                printLine();
                System.out.println("PandaBot Commands:");
                System.out.println("1. todo <description> : Adds a new Todo task.");
                System.out.println("2. deadline <description> /by <date/time> : Adds a new Deadline task.");
                System.out.println("3. event <description> /from <start time> /to <end time> : Adds a new Event task.");
                System.out.println("4. list : Lists all tasks.");
                System.out.println("5. mark <task number> : Marks the specified task as done.");
                System.out.println("6. unmark <task number> : Unmarks the specified task.");
                System.out.println("7. delete <task number> : Deletes the specified task.");
                System.out.println("8. help : Displays this help message.");
                System.out.println("9. bye : Exits the PandaBot.");
                printLine();
            }
            // Add a new task (ToDo. Deadline, or Event)
            else {
                Task task = null;
                try {
                    if (input.startsWith("todo")) {
                        task = new ToDo("").createTask(input);
                    } else if (input.startsWith("deadline")) {
                        task = new Deadline("", "").createTask(input);
                    } else if (input.startsWith("event")) {
                        task = new Event("", "", "").createTask(input);
                    } else {
                        System.out.println("Invalid command. Type 'help' for assistance.");
                        continue;
                    }
                    taskList.add(task);
                    saveTaskList(taskList);
                    printLine();
                    System.out.println("Got it. I've added this task:");
                    System.out.println(taskList.get(taskList.size() - 1));
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    printLine();
                } catch (InputException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        scanner.close();
    }

    /**
     * Loads the list of tasks from a file
     *
     * @param taskList the list to populate with tasks.
     */
    private static void loadTaskList(ArrayList<Task> taskList) {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = parseTask(line);
                if (task != null) {
                    taskList.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred while loading tasks: " + e.getMessage());
        }
    }

    /**
     * Parses a line from the file to create that task.
     *
     * @param line the selected line from the file.
     * @return the created task.
     */
    private static Task parseTask(String line) {
        String[] details = line.split(" \\| ");
        if (details.length < 3) {
            return null;
        }

        String type = details[0];
        boolean isDone = details[1].equals("1");
        String description = details[2];

        Task task = null;

        switch (type) {
        case "T":
            task = new ToDo(description);
            break;
        case "D":
            if (details.length < 4) {
                return null;
            }
            String by = details[3];
            task = new Deadline(description, by);
            break;
        case "E":
            if (details.length < 5) {
                return null;
            }
            String from = details[3];
            String to = details[4];
            task = new Event(description, from, to);
            break;
        }

        if (task != null && isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Saves the current task list to a file.
     *
     * @param taskList the list of tasks to save.
     */
    private static void saveTaskList(ArrayList<Task> taskList) {
        try {
            // Ensure the directory exists
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (Task task : taskList) {
                    String type = task instanceof ToDo ? "T" :
                            task instanceof Deadline ? "D" : "E";
                    String taskString = type + " | " + (task.isDone ? "1" : "0") + " | " + task.description;

                    if (task instanceof Deadline) {
                        taskString += " | " + ((Deadline) task).by;
                    } else if (task instanceof Event) {
                        taskString += " | " + ((Event) task).from + " | " + ((Event) task).to;
                    }

                    writer.write(taskString + System.lineSeparator());
                }
            }
        } catch (IOException e) {
            System.out.println("Error occurred while saving tasks: " + e.getMessage());
        }
    }
}
