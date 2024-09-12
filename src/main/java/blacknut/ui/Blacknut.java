

//public class Blacknut {
//    private static final String FILE_PATH = "data" + File.separator + "blacknut.txt";
//
//    public static void main(String[] args) {
//        String logo = " ____  _            _                _   \n"
//                + "|  _ \\| |          | |              | |  \n"
//                + "| |_) | | __ _  ___| | ___ __  _   _| |_ \n"
//                + "|  _ <| |/ _` |/ __| |/ / '_ \\| | | | __|\n"
//                + "| |_) | | (_| | (__|   <| | | | |_| | |_ \n"
//                + "|____/|_|\\__,_|\\___|_|\\_\\_| |_|\\__,_|\\__|\n";
//
//        System.out.println("Hello from\n" + logo);
//
//        System.out.println("____________________________________________________________");
//        System.out.println(" Hello! I'm Blacknut");
//        System.out.println(" What can I do for you?");
//        System.out.println("____________________________________________________________");
//
//        ArrayList<Task> tasks = new ArrayList<>();
//        loadTasksFromFile(tasks);
//        Scanner scanner = new Scanner(System.in);
//        //ArrayList<Task> tasks = new ArrayList<>();
//
//        while (true) {
//            String input = scanner.nextLine().trim();
//
//            System.out.println("____________________________________________________________");
//
//            try {
//                if (input.equalsIgnoreCase("bye")) {
//                    System.out.println(" Bye. Hope to see you again soon!");
//                    break;
//                } else if (input.equalsIgnoreCase("list")) {
//                    listTasks(tasks);
//                } else if (input.startsWith("mark ")) {
//                    markTask(tasks, input, true);
//                } else if (input.startsWith("unmark ")) {
//                    markTask(tasks, input, false);
//                } else if (input.startsWith("todo ")) {
//                    addTodo(tasks, input);
//                } else if (input.startsWith("deadline ")) {
//                    addDeadline(tasks, input);
//                } else if (input.startsWith("event ")) {
//                    addEvent(tasks, input);
//                } else if (input.startsWith("delete ")) {
//                    deleteTask(tasks, input);
//                } else if (input.startsWith("find ")) {
//                    String keyword = input.substring(5).trim();
//                    findTask(tasks, keyword);
//                }
//                else {
//                    throw new InvalidCommandException("I don't know what that means. Please enter a valid command.");
//                }
//            } catch (InvalidCommandException | EmptyDescriptionException | InvalidTaskNumberException | IncorrectFormatException e) {
//                System.out.println(" ☹ OOPS!!! " + e.getMessage());
//            }
//
//            System.out.println("____________________________________________________________");
//        }
//
//        scanner.close();
//    }
//
//    private static void findTask(ArrayList<Task> tasks, String keyword) {
//        ArrayList<Task> matchingTasks = new ArrayList<>();
//        for (int i = 0; i < tasks.size(); i++) {
//            if (tasks.get(i).description.contains(keyword)) {
//                matchingTasks.add(tasks.get(i));
//            }
//        }
//
//        if (matchingTasks.isEmpty()) {
//            System.out.println(" No tasks match your search.");
//        } else {
//            System.out.println(" Here are the matching tasks in your list:");
//            for (int i = 0; i < matchingTasks.size(); i++) {
//                System.out.println(" " + (i + 1) + "." + matchingTasks.get(i));
//            }
//        }
//    }
//
//    private static void saveTasksToFile(ArrayList<Task> tasks) {
//        try {
//            // Ensure the directory exists
//            File directory = new File("data");
//            if (!directory.exists()) {
//                directory.mkdir();
//            }
//
//            // Proceed to write tasks to the file
//            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
//            for (Task task : tasks) {
//                writer.write(task.toFileFormat());
//                writer.newLine();
//            }
//            writer.close();
//        } catch (IOException e) {
//            System.out.println("Error saving tasks to file: " + e.getMessage());
//        }
//    }
//
//
//    private static void loadTasksFromFile(ArrayList<Task> tasks) {
//        File file = new File(FILE_PATH);
//        if (!file.exists()) {
//            return; // If the file doesn't exist, there's nothing to load
//        }
//
//        try {
//            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
//            String line;
//            while ((line = reader.readLine()) != null) {
//                Task task = Task.fromFileFormat(line);
//                tasks.add(task);
//            }
//            reader.close();
//        } catch (IOException | IncorrectFormatException e) {
//            System.out.println("Error loading tasks from file: " + e.getMessage());
//        }
//    }
//
//    private static void listTasks(ArrayList<Task> tasks) {
//        if (tasks.isEmpty()) {
//            System.out.println(" The list is empty.");
//        } else {
//            System.out.println(" Here are the tasks in your list:");
//            for (int i = 0; i < tasks.size(); i++) {
//                System.out.println(" " + (i + 1) + "." + tasks.get(i));
//            }
//        }
//    }
//
//    private static void markTask(ArrayList<Task> tasks, String input, boolean markAsDone) throws InvalidTaskNumberException {
//        try {
//            int index = Integer.parseInt(input.split(" ")[1]) - 1;
//            if (index >= 0 && index < tasks.size()) {
//                Task task = tasks.get(index);
//                if (markAsDone) {
//                    task.markAsDone();
//                    System.out.println(" Nice! I've marked this task as done:");
//                } else {
//                    task.markAsNotDone();
//                    System.out.println(" OK, I've marked this task as not done yet:");
//                }
//                System.out.println("   " + task);
//            } else {
//                throw new InvalidTaskNumberException("Invalid task number. Please provide a valid number from the list.");
//            }
//        } catch (NumberFormatException e) {
//            throw new InvalidTaskNumberException("Invalid command format. Please enter a valid number after 'mark' or 'unmark'.");
//        } catch (IndexOutOfBoundsException e) {
//            throw new InvalidTaskNumberException("Task number out of range. Please provide a valid number from the list.");
//        }
//    }
//
//    private static void addTodo(ArrayList<Task> tasks, String input) throws EmptyDescriptionException {
//        String description = input.substring(5).trim();
//        if (description.isEmpty()) {
//            throw new EmptyDescriptionException("The description of a todo cannot be empty.");
//        }
//        Task newTask = new Todo(description);
//        tasks.add(newTask);
//        saveTasksToFile(tasks);
//        System.out.println(" Got it. I've added this task:");
//        System.out.println("   " + newTask);
//        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
//    }
//
//    private static void addDeadline(ArrayList<Task> tasks, String input) throws IncorrectFormatException {
//        String[] parts = input.substring(9).split(" /by ");
//        if (parts.length != 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
//            throw new IncorrectFormatException("The format for a deadline should be: deadline <description> /by <d/M/yyyy HHmm>");
//        }
//        Task newTask = new Deadline(parts[0].trim(), parts[1].trim());
//        tasks.add(newTask);
//        saveTasksToFile(tasks);
//        System.out.println(" Got it. I've added this task:");
//        System.out.println("   " + newTask);
//        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
//    }
//
//
//    private static void addEvent(ArrayList<Task> tasks, String input) throws IncorrectFormatException {
//        String[] parts = input.substring(6).split(" /from | /to ");
//        if (parts.length != 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
//            throw new IncorrectFormatException("The format for an event should be: event <description> /from <yyyy-mm-dd> /to <yyyy-mm-dd>");
//        }
//        Task newTask = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
//        tasks.add(newTask);
//        saveTasksToFile(tasks);
//        System.out.println(" Got it. I've added this task:");
//        System.out.println("   " + newTask);
//        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
//    }
//
//    // Method to delete a task
//    private static void deleteTask(ArrayList<Task> tasks, String input) throws InvalidTaskNumberException {
//        try {
//            int index = Integer.parseInt(input.split(" ")[1]) - 1;
//            if (index >= 0 && index < tasks.size()) {
//                Task removedTask = tasks.remove(index);
//                saveTasksToFile(tasks);
//                System.out.println(" Noted. I've removed this task:");
//                System.out.println("   " + removedTask);
//                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
//            } else {
//                throw new InvalidTaskNumberException("Invalid task number. Please provide a valid number from the list.");
//            }
//        } catch (NumberFormatException e) {
//            throw new InvalidTaskNumberException("Invalid command format. Please enter a valid number after 'delete'.");
//        } catch (IndexOutOfBoundsException e) {
//            throw new InvalidTaskNumberException("Task number out of range. Please provide a valid number from the list.");
//        }
//    }
//}

package blacknut.ui;

import java.util.ArrayList;
import java.util.Scanner;
import blacknut.ui.BlacknutExceptions.InvalidCommandException;
import blacknut.ui.BlacknutExceptions.EmptyDescriptionException;
import blacknut.ui.BlacknutExceptions.InvalidTaskNumberException;
import blacknut.ui.BlacknutExceptions.IncorrectFormatException;
import blacknut.ui.BlacknutExceptions.BlacknutException;

import java.io.File;

/**
 * Represents the main Blacknut application that handles user input, processes commands,
 * and manages tasks.
 */
public class Blacknut {
    private static final String FILE_PATH = "data" + File.separator + "blacknut.txt";
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a new Blacknut application.
     */
    public Blacknut() {
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        tasks = new TaskList(storage.loadTasksFromFile());
        assert tasks != null : "TaskList should not be null after loading from file";
        parser = new Parser();
    }

    /**
     * Runs the Blacknut application, handling user input and executing commands.
     */
    public void run() {
        ui.showWelcome();

        while (true) {
            String input = ui.readInput();
            ui.showLine();

            try {
                String command = parser.parseCommand(input);
                switch (command) {
                    case "bye":
                        ui.showGoodbye();
                        return;
                    case "list":
                        ui.showTasks(tasks.getTasks());
                        break;
                    case "mark":
                    case "unmark":
                        int markIndex = parser.parseIndex(input);
                        assert markIndex >= 0 && markIndex < tasks.size() : "Mark index out of bounds";
                        boolean markAsDone = command.equals("mark");
                        tasks.markTask(markIndex, markAsDone);
                        ui.showMarkedTask(tasks.getTask(markIndex), markAsDone);
                        break;
                    case "todo":
                        String todoDescription = parser.parseDescription(input, "todo");
                        assert !todoDescription.isEmpty() : "Todo description should not be empty";
                        Task newTodo = new Todo(todoDescription);
                        tasks.addTask(newTodo);
                        ui.showAddedTask(newTodo, tasks.size());
                        break;
                    case "deadline":
                        String[] deadlineParts = parser.parseDeadline(input);
                        Task newDeadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                        tasks.addTask(newDeadline);
                        ui.showAddedTask(newDeadline, tasks.size());
                        break;
                    case "event":
                        String[] eventParts = parser.parseEvent(input);
                        Task newEvent = new Event(eventParts[0], eventParts[1], eventParts[2]);
                        tasks.addTask(newEvent);
                        ui.showAddedTask(newEvent, tasks.size());
                        break;
                    case "delete":
                        int deleteIndex = parser.parseIndex(input);
                        Task deletedTask = tasks.deleteTask(deleteIndex);
                        ui.showDeletedTask(deletedTask, tasks.size());
                        break;
                    case "find":
                        String keyword = parser.parseKeyword(input);
                        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
                        ui.showMatchingTasks(matchingTasks);
                        break;
                    default:
                        throw new InvalidCommandException("I don't know what that means. Please enter a valid command.");
                }
            } catch (InvalidCommandException e) {
                ui.showError(e.getMessage());
            } catch (EmptyDescriptionException e) {
                ui.showError(e.getMessage());
            } catch (InvalidTaskNumberException e) {
                ui.showError(e.getMessage());
            } catch (IncorrectFormatException e) {
                ui.showError(e.getMessage());
            }

            ui.showLine();
            storage.saveTasksToFile(tasks.getTasks());
        }
    }

    /**
     * The main method to run the Blacknut application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        assert new File(FILE_PATH).exists() || new File(FILE_PATH).getParentFile().exists() : "File path must be valid";
        new Blacknut().run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        return "Blacknut heard: " + input;
    }
}

