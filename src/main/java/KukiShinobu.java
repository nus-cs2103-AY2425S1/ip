import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;  // Import the Scanner class
import java.io.File;
import java.io.FileWriter;


public class KukiShinobu {
    private final String name = "Kuki Shinobu";
    private ArrayList<Task> tasks = new ArrayList<>();
    // IMPORTANT: Relative Filepath Specified must always be relative to root directory of the entire project
    private static final String FILE_PATH = "./data/database.txt";
    private static final String DELIMITER = " \\| ";
    public static void main(String[] args) {
//        final String filepath = "../../../data/database.txt";
        KukiShinobu kukiShinobu = new KukiShinobu();
        kukiShinobu.listen();
    }

    public static void printHorizontalLine() {
        System.out.println("____________________________________________________________");
    }

//    public KukiShinobu(String filepath) {
//        this.filepath = filepath;
//    }

    public static boolean readBoolean(int i) {
        return i != 0;
    }
    public List<Task> loadDatabaseFile() {
        String cwd = System.getProperty("user.dir");
        System.out.println("Current working directory: " + cwd);

        ArrayList<String> input = readFile(FILE_PATH);
        ArrayList<Task> existingTasks = parseTasks(input);
        System.out.println(existingTasks);
        this.tasks = existingTasks;
        System.out.println(this.tasks);
        return parseTasks(input);
    }

    public void writeToDatabaseFile() {
        // TODO: Takes this.tasks and write it to the database file
        StringBuilder stringToWrite = new StringBuilder();
        // Step 1: Parse the file into a single string
        for (Task task: this.tasks) {
            stringToWrite.append(task.getDatabaseString() + System.lineSeparator());
        }

        // Step 2: Insert the entire text string into the file
        FileWriter fw = null;
        try {
            fw = new FileWriter(KukiShinobu.FILE_PATH);
            fw.write(stringToWrite.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private ArrayList<String> readFile(String filePath) {
        File file = new File(filePath);
        // Ensure parent directory exists
        file.getParentFile().mkdirs();
        ArrayList<String> input = new ArrayList<>();
        try {
            System.out.println(file.exists());
            // Check if the file exists
            if (!file.exists()) {
                // Create an empty file
                file.createNewFile();
            }
            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    input.add(scanner.nextLine());
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Error creating or accessing the file: " + filePath);
            e.printStackTrace();
        }
        System.out.println(input);
        return input;
    }

    private ArrayList<Task> parseTasks(ArrayList<String> input) {
        ArrayList<Task> existingTasks = new ArrayList<>();
        for (String taskString : input) {
            String[] taskConstituents = taskString.split(DELIMITER);
            if (taskConstituents.length < 2) {
                System.err.println("Invalid task format: " + taskString);
                continue; // Skip to the next line
            }

            String taskType = taskConstituents[0];
            boolean isCompleted = taskConstituents[1].equals("1");

            switch (taskType) {
                case "T":
                    if (taskConstituents.length >= 3) {
                        existingTasks.add(new Todo(taskConstituents[2], isCompleted));
                    } else {
                        System.err.println("Invalid Todo task format: " + taskString);
                    }
                    break;
                case "D":
                    if (taskConstituents.length >= 4) {
                        existingTasks.add(new Deadline(taskConstituents[2], taskConstituents[3], isCompleted));
                    } else {
                        System.err.println("Invalid Deadline task format: " + taskString);
                    }
                    break;
                case "E":
                    if (taskConstituents.length >= 5) {
                        existingTasks.add(new Event(taskConstituents[2], taskConstituents[3], taskConstituents[4], isCompleted));
                    } else {
                        System.err.println("Invalid Event task format: " + taskString);
                    }
                    break;
                default:
                    System.err.println("Unknown task type: " + taskType);
                    break;
            }
        }
        return existingTasks;
    }

//    public ArrayList<Task> loadDatabaseFile() {
//        // TODO: Loads the file specified by the filepath and updates task
//
//        ArrayList<String> input = new ArrayList<>();
//        ArrayList<Task> existingTasks = new ArrayList<>();
//
//        // Attempt to open file and file path and read it
//        try (Scanner scanner = new Scanner(new File(this.filepath))) {
//            while (scanner.hasNextLine()) {
//                input.add(scanner.nextLine());
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//            File file = new File(this.filepath);
//            if (!file.exists()) {
//                // Create an empty file
//                file.createNewFile();
//                file.getParentFile().mkdirs();
//            }
//            return existingTasks;
//        }
//
//
//        // Loop through each entry in the input line and then break into it's constitutents
//        for (String taskString : input) {
//            String[] taskConstituents = taskString.split(" \\| ");
//            String taskType = taskConstituents[0];
//            switch (taskType) {
//                case "T":
//                    existingTasks.add(new Todo(taskConstituents[2], taskConstituents[1].equals("1")));
//                    break;
//                case "D":
//                    existingTasks.add(new Deadline(taskConstituents[2], taskConstituents[3], taskConstituents[1].equals("1")));
//                    break;
//                case "E":
//                    existingTasks.add(new Event(taskConstituents[2], taskConstituents[3], taskConstituents[4], taskConstituents[1].equals("1")));
//                    break;
//            }
//        }
//        return existingTasks;
//    }

    public void listen() {
        this.loadDatabaseFile();
        this.greet();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();  // Read user input
            KukiShinobu.printHorizontalLine();

            // split user input into commands and argument (if applicable)
            String[] parts = input.split(" ", 2);
            String command = parts[0];
            //prevents index out of bounds error if no arguments provided after command
            String arguments = parts.length > 1 ? parts[1] : "";

            // break out of while loop if user issues "bye" command
            if (command.equals("bye")) {
                break;
            }

            try {
                // otherwise, handle all other commands as appropriate
                switch (command) {
                    case "list":
                        this.listTasks();
                        break;
                    case "mark":
                        // argument is task index
                        this.markAsDone(arguments);
                        break;
                    case "unmark":
                        // argument is task index
                        this.unmarkAsDone(arguments);
                        break;
                    case "delete":
                        // argument is task index
                        this.deleteTask(arguments);
                        break;
                    case "todo":
                        // argument is desc, pass desc in
                        this.addTodo(arguments);
                        break;
                    case "deadline":
                        // Break arguments into desc + by
                        this.addDeadline(arguments);
                        break;
                    case "event":
                        // break arguments into desc, start and end
                        this.addEvent(arguments);
                        break;
                    default:
                        this.handleUnknownCommand();
                }
                writeToDatabaseFile();
            } catch (KukiShinobuException e) {
                System.out.println(e.getMessage());
            }

            KukiShinobu.printHorizontalLine();
        }
        this.goodbye();
    }

    private void handleUnknownCommand() throws KukiShinobuException {
        throw new KukiShinobuException("Hmm... I don't quite understand what you mean!");
    }

    private void listTasks() {
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.println((i + 1) + "." + this.tasks.get(i));
        }
    }

    private void markAsDone(String indexString) {
        int i = Integer.parseInt((indexString)) - 1;
        this.tasks.get(i).markAsDone();
    }

    private void unmarkAsDone(String indexString) {
        int i = Integer.parseInt((indexString)) - 1;
        this.tasks.get(i).unmarkAsDone();
    }

    private void deleteTask(String indexString) {
        int i = Integer.parseInt((indexString)) - 1;
        Task deletedTask = this.tasks.remove(i);
        printDeletedTaskSummary(deletedTask);
    }

    private void printAddedTaskSummary(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
    }

    private void printDeletedTaskSummary(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.tasks.size() + " tasks in the list.");
    }

    private void addTodo(String arguments) throws KukiShinobuException {
        // TODO: Check for missing description
        if (arguments.isEmpty()) {
            throw new KukiShinobuException("Todo is missing description!");
        }
        // argument is taskDescription
        Task newTodo = new Todo(arguments);
        this.tasks.add(newTodo);
        this.printAddedTaskSummary(newTodo);

    }

    private void addDeadline(String arguments) throws KukiShinobuException {
        // TODO: Check for missing description or /by
        String[] parts = arguments.split(" /by ", 2);

        // Checks for missing arguments
        if (parts.length != 2) {
            if (!arguments.contains("/by")) {
                throw new KukiShinobuException("You're missing the /by flag and argument!");
            } else {
                throw new KukiShinobuException("Deadline is missing the description!");
            }
        }

        String taskDescription = parts[0];
        String by = parts[1];
        Task newDeadline = new Deadline(taskDescription, by);
        this.tasks.add(newDeadline);
        this.printAddedTaskSummary(newDeadline);
    }

    private void addEvent(String arguments) throws KukiShinobuException {
        //TODO: Check for missing desc, /from or /to
        //TODO: Modify the logic to split based on "/" instead to accommodate flipped order of flags
//        String[] parts = arguments.split("/", 3);


        String[] parts = arguments.split("\\s+/from\\s+|\\s+/to\\s+", 3);
        if (parts.length != 3) {
            throw new KukiShinobuException("Event is missing description, from or to.");
        }

        String taskDescription = parts[0];
        String start = parts[1];
        String end = parts[2];
        Task newEvent = new Event(taskDescription, start, end);
        this.tasks.add(newEvent);
        this.printAddedTaskSummary(newEvent);
    }

//    private void addTask(String taskDescription) {
//        Task newTask = new Task(taskDescription);
//        this.tasks.add(newTask);
//        System.out.println("added: " + taskDescription);
//    }

    public void greet() {
        KukiShinobu.printHorizontalLine();
        System.out.println("Hey Traveller! I'm " + this.name + ", deputy leader of the Arataki Gang.");
        System.out.println("Just let me know if you ever find yourself in a pinch. I can help you out.");
        KukiShinobu.printHorizontalLine();
    }

    public void goodbye() {
        System.out.println("Bye. Hope to see you again soon!");
        KukiShinobu.printHorizontalLine();
    }

    public void fancyGreet() {
        String logo = " ____  __.      __   .__       _________.__    .__             ___.          \n"
                + "|    |/ _|__ __|  | _|__|     /   _____/|  |__ |__| ____   ____\\_ |__  __ __ \n"
                + "|      < |  |  \\  |/ /  |     \\_____  \\ |  |  \\|  |/    \\ /  _ \\| __ \\|  |  \\\n"
                + "|    |  \\|  |  /    <|  |     /        \\|   Y  \\  |   |  (  <_> ) \\_\\ \\  |  /\n"
                + "|____|__ \\____/|__|_ \\__|    /_______  /|___|  /__|___|  /\\____/|___  /____/ \n"
                + "        \\/          \\/               \\/      \\/        \\/           \\/      \n";
        System.out.println("Hello from\n" + logo);
    }


}
