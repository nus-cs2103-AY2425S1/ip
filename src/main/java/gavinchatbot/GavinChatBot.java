package gavinchatbot;

import gavinchatbot.command.Command;
import gavinchatbot.task.TaskList;
import gavinchatbot.util.Parser;
import gavinchatbot.util.Ui;
import gavinchatbot.util.Storage;
import gavinchatbot.util.GavinException;
import java.io.IOException;

/*
public class gavinchatbot.GavinChatBot{
    // array to hold gavinchatbot.task.Task objects
    // static gavinchatbot.task.Task[] tasks = new gavinchatbot.task.Task[100];
    static ArrayList<gavinchatbot.task.Task> tasks = new ArrayList<>();
    static gavinchatbot.util.Storage storage;
    public static void main(String[] args) {
        String filePath = "./data/duke.txt";
        storage = new gavinchatbot.util.Storage(filePath);

        // Load tasks from file
        try {
            tasks = storage.load();
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
            tasks = new ArrayList<>();
        }

        String horizontalLine = "___________________________________________________________________________________\n";

        // print welcome message
        System.out.println(horizontalLine);
        System.out.println("Hello! I'm Gavin's Chat Bot!\n");
        System.out.println("What can I do for you?\n");
        System.out.println(horizontalLine);

        // create scanner to read user input
        Scanner scanner = new Scanner(System.in);
        String input;

        // echo input by user until user types bye
        while (true) {
            try {
                input = scanner.nextLine();
                // skip adding tasks for "list" or "bye"
                if (input.equalsIgnoreCase("list") || input.equalsIgnoreCase("bye") || input.startsWith("mark") || input.startsWith("unmark") || input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event") || input.startsWith("delete")) {
                    handleInput(input);
                } else {
                    // throw an error for invalid input
                    throw new gavinchatbot.util.GavinException("Invalid input! Please start with 'todo', 'deadline', or 'event'.");
                }
            } catch (gavinchatbot.util.GavinException e) {
                System.out.println(horizontalLine);
                System.out.println("!!!ERROR!!! " + e.getMessage());
                System.out.println(horizontalLine);
            }
        }
    }

    public static void handleInput(String input) throws gavinchatbot.util.GavinException{
        String horizontalLine = "___________________________________________________________________________________\n";
        if (input.equalsIgnoreCase("bye")) {
            // print exit message
            System.out.println(horizontalLine);
            System.out.println("Bye. Hope to see you again soon!");
            System.out.println(horizontalLine);
            System.exit(0);
        } else if (input.equalsIgnoreCase("list")) {
            // print out list of items
            System.out.println(horizontalLine);
            System.out.println("Here are the tasks in your list: ");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + 1 + ". " + tasks.get(i));
            }
            System.out.println(horizontalLine);
        } else if (input.startsWith("mark")) {
            markTask(input);
        } else if (input.startsWith("unmark")) {
            unmarkTask(input);
        } else if (input.startsWith("todo")) {
            toDoTask(input);
        } else if (input.startsWith("deadline")) {
            deadlineTask(input);
        } else if (input.startsWith("event")) {
            eventTask(input);
        } else if (input.startsWith("delete")) {
            deleteTask(input);
        }
    }

    public static void markTask(String input) throws gavinchatbot.util.GavinException {
        String horizontalLine = "___________________________________________________________________________________\n";
        // mark task as done
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;

        // throw an error if taskNumber is negative, or greater than the current task count
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new gavinchatbot.util.GavinException("gavinchatbot.task.Task number is invalid!!!");
        }

        tasks.get(taskNumber).markAsDone();
        System.out.println(horizontalLine);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(" " + tasks.get(taskNumber));
        System.out.println(horizontalLine);

        try {
            storage.save(tasks);
        } catch (IOException e){
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public static void unmarkTask(String input) throws gavinchatbot.util.GavinException {
        String horizontalLine = "___________________________________________________________________________________\n";
        // mark task as undone
        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;

        // throw an error if taskNumber is negative, or greater than the current task count
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new gavinchatbot.util.GavinException("gavinchatbot.task.Task number is invalid!!!");
        }
        tasks.get(taskNumber).markAsNotDone();
        System.out.println(horizontalLine);
        System.out.println("OK, I've marked this task as not done yet!");
        System.out.println(" " + tasks.get(taskNumber));
        System.out.println(horizontalLine);

        try {
            storage.save(tasks);
        } catch (IOException e){
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public static void toDoTask(String input) throws gavinchatbot.util.GavinException {
        String[] inputParts = input.split(" ", 2);

        // throw an error if description is empty
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            throw new gavinchatbot.util.GavinException("A ToDo must have a description!!!");
        }

        String taskDescription = inputParts[1];
        tasks.add(new gavinchatbot.task.ToDos(taskDescription));
        printAddTaskMessage(tasks.get(tasks.size() - 1));

        try {
            storage.save(tasks);
        } catch (IOException e){
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public static void deadlineTask(String input) throws gavinchatbot.util.GavinException {
        // throw an error if there is no description
        if (input.length() <= 9) {
            throw new gavinchatbot.util.GavinException("A deadline must have a description and a /by date!!!");
        }

        String[] inputParts = input.split("/by", 2); // ["deadline return book" , "Sunday"]
        String taskDescription = inputParts[0].substring(9).trim(); // skip first 9 chars, which is "deadline "
        String deadlineDay = inputParts[1].trim();

        // throw an error if there is no /by date
        if (inputParts.length < 2 || inputParts[1].trim().isEmpty()) {
            throw new gavinchatbot.util.GavinException("A deadline must have a /by date!!!");
        }
        tasks.add(new gavinchatbot.task.Deadline(taskDescription, deadlineDay));
        printAddTaskMessage(tasks.get(tasks.size() - 1));

        try {
            storage.save(tasks);
        } catch (IOException e){
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public static void eventTask(String input) throws gavinchatbot.util.GavinException {
        // throw an error if there is no taskDescription
        if (input.length() <= 6) {
            throw new gavinchatbot.util.GavinException("An event must have a task description!!!");
        }

        String[] inputParts = input.split("/from", 2); //["event project meeting" , "Mon 2pm /to 4pm"]
        String taskDescription = inputParts[0].substring(6);
        String[] timeParts = inputParts[1].split("/to", 2); //["Mon 2pm" , "4pm"]
        String fromTime = timeParts[0];
        String toTime = timeParts[1];

        // throw an error if there is either no 'from' or 'to' time
        if (timeParts.length < 2) {
            throw new gavinchatbot.util.GavinException("An event must have a /from and /to time!!!");
        }
        tasks.add(new gavinchatbot.task.Event(taskDescription, fromTime, toTime));
        printAddTaskMessage(tasks.get(tasks.size() - 1));

        try {
            storage.save(tasks);
        } catch (IOException e){
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public static void deleteTask(String input) throws gavinchatbot.util.GavinException {
        String horizontalLine = "___________________________________________________________________________________\n";

        int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;

        // throw an error if taskNumber is negative, or greater than the current task count
        if (taskNumber < 0 || taskNumber >= tasks.size()) {
            throw new gavinchatbot.util.GavinException("gavinchatbot.task.Task number is invalid!!!");
        }

        gavinchatbot.task.Task removedTask = tasks.remove(taskNumber);

        System.out.println(horizontalLine);
        System.out.println("Noted. I've removed this task:");
        System.out.println(" " + removedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(horizontalLine);

        try {
            storage.save(tasks);
        } catch (IOException e){
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    private static void printAddTaskMessage(gavinchatbot.task.Task task) {
        String horizontalLine = "___________________________________________________________________________________\n";
        System.out.println(horizontalLine);
        System.out.println("Got it. I've added this task:");
        System.out.println(" " + task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        System.out.println(horizontalLine);

    }
}
*/
/**
 * The main class for Gavin's Chat Bot application.
 * This class handles the initialization of the application and the main logic for running the chatbot.
 */
public class GavinChatBot {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a GavinChatBot object and initializes the necessary components.
     *
     * @param filePath The file path where the tasks will be stored and loaded from.
     */
    public GavinChatBot(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();

        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the chatbot, showing a welcome message and processing user commands until the exit command is issued.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (GavinException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The main method that starts the GavinChatBot application.
     *
     * @param args Command line arguments, not used in this application.
     */
    public static void main(String[] args) {
        new GavinChatBot("data/duke.txt").run();
    }
}

