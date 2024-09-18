package donk;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import donk.task.*;


/**
 * The main class that runs
 */
public class Donk {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Donk class.
     * Initializes the user interface (UI) and storage components
     * Attempts to load tasks from the specified file.
     * If loading fails, it displays a loading error
     * and initializes an empty task list.
     * @param filePath The path to the file where tasks are stored.
     */
    public Donk(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DonkException e) {

            tasks = new TaskList();
        }
    }

    /**
     * Runs the main loop of the Donk application.
     *
     * Greets the user and continually reads input from the user. Processes each line
     * of input using the Parser class. If any exceptions are thrown during processing,
     * an error message is displayed to the user.
     */
    public void run() {
        //ui.greet();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            try {
                String userInput = scanner.nextLine();
                String command = Parser.parse(userInput);
                execute(command, userInput);
            } catch (TodoException e) {
                System.out.println("    " + e.getMessage());
            } catch (Exception e) {
                System.out.println("    " + e.getMessage());

            }
        }
    }

    /**
     * The main method that starts the Donk application.
     * Creates a new instance of Donk with the specified file path and runs the application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Donk("./save.txt").run();
    }

    /**
     * Generates a response for the user's chat message.
     */
    public String getResponse(String input) {
        try {
            String command = Parser.parse(input);
            return execute(command, input);
        } catch (TodoException e) {
            System.out.println("    " + e.getMessage());
            return e.getMessage();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Execute command word given
     * Supported commands include:
     * - "bye": Saves the current tasks to a file and exits the program.
     * - "list": Displays all tasks.
     * - "mark [index]": Marks the task at the given index as done.
     * - "unmark [index]": Unmarks the task at the given index as not done.
     * - "delete [index]": Deletes the task at the given index.
     * - "todo [description]": Adds a new todo task with the given description.
     * - "deadline [description] /by [date]": Adds a new deadline task with the given description and due date.
     * - "event [description] /start [start] /end [end date]":
     *          Adds a new event task with the given description, start time, and end time.
     *
     * @param command
     * @param input
     * @return String to display to user
     * @throws Exception
     */
    private String execute(String command, String input) throws Exception {
        String[] inputArray = input.split("\\s+");
        switch (command) {
        case "bye":
            String filePath = "./save.txt";
            File file = new File(filePath);
            if (!file.exists()) {
                try {
                    // Attempt to create the file
                    if (file.createNewFile()) {
                        System.out.println("Save file created successfully.");
                    } else {
                        System.out.println("Failed to create the file.");
                    }
                } catch (IOException e) {
                    System.out.println("An error occurred while creating the file: " + e.getMessage());
                    e.printStackTrace();
                    throw new Exception("An error occurred while creating the file: \" + e.getMessage()");
                }
            }
            storage.writeToFile("./save.txt", tasks);
            break;
        case "todo":
            if (inputArray.length < 2 || input.length() < 5) {
                return ui.invalidFormat(TaskType.TODO);
            }
            Task t = new ToDo(input.substring(5));
            tasks.add(t);
            return ui.notifyAddedTask(t, tasks);
        case "deadline":
            String[] split = input.split("/by");
            if (split.length < 2) {
                return ui.invalidFormat(TaskType.DEADLINE);
            }
            String bef = split[0].substring(9);
            String aft = split[1];
            t = new Deadline(bef, aft.strip());
            tasks.add(t);
            return ui.notifyAddedTask(t, tasks);
        case "event":
            String[] split1 = input.split("/start");
            if (split1.length < 2) {
                return ui.invalidFormat(TaskType.EVENT);
            }
            String[] split2 = split1[1].split("/end");
            if (split1.length < 2 || split2.length < 2) {
                return ui.invalidFormat(TaskType.EVENT);
            }
            String start = split2[0];
            String end = split2[1];
            String description = split1[0].substring(6);
            t = new Event(description, start.strip(), end.strip());
            tasks.add(t);
            return ui.notifyAddedTask(t, tasks);
        case "find":
            String searchTerm = input.substring(5);
            TaskList results = tasks.find(searchTerm);
            return ui.listTasks(results);
        case "delete":
            return delete(Parser.parseIndex(inputArray));
        case "unmark":
            return unmark(Parser.parseIndex(inputArray));
        case "mark":
            return mark(Parser.parseIndex(inputArray));
        case "list":
            return ui.listTasks(tasks);
        default:
            throw new Exception("Ehhh not sure what this is man");
        }

        return "error with executing command";

    }

    /**
     * mark task as done
     * @param index
     * @return String message to user
     */
    private String mark(int index) {
        Task temp = tasks.getTask(index);
        temp.markDone();
        return "Yo I've marked this thingy as done\n" + temp.toString();

    }

    /**
     * Mark task as undone
     * @param index
     * @return String message to user
     */
    private String unmark(int index) {
        Task temp = tasks.getTask(index);
        temp.unmarkDone();
        return "Aights now it's unmarked again\n" + temp.toString();

    }


    private String delete(int index) {
        Task tp = tasks.getTask(index);
        tasks.remove(index);
        return "Alright bro I deleted that for you\ndeleted: " + tp.toString()
                + "\nYou now have " + tasks.size() + " tasks";

    }


}

