import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Moody {
    private static final String SPACER = "____________________________________________________________\n";
    private static final String INDENT = "    ";
    private static final String FILE_PATH = "./data/moody.txt";
    private static final ArrayList<Task> userTasks = new ArrayList<>();

    public static void main(String[] args) {
        greetUser();
        loadTasksFromFile();

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            try {
                userInput = scanner.nextLine().trim();
                if (userInput.equals("bye")) {
                    sayGoodbye();
                    break;
                } else if (userInput.equals("list")) {
                    listTasks();
                } else if (userInput.startsWith("mark")) {
                    markTask(userInput);
                } else if (userInput.startsWith("unmark")) {
                    unmarkTask(userInput);
                } else if (userInput.startsWith("todo")) {
                    addTodoTask(userInput);
                } else if (userInput.startsWith("deadline")) {
                    addDeadlineTask(userInput);
                } else if (userInput.startsWith("event")) {
                    addEventTask(userInput);
                } else if (userInput.startsWith("delete")) {
                    deleteTask(userInput);
                } else {
                    invalidCommand();
                }
            } catch (TaskInputException | InvalidCommandException | TaskOutOfBoundsException e) {
                System.out.println(SPACER + e.getMessage() + SPACER);
            }
        }
        scanner.close();
    }

    private static void greetUser() {
        System.out.println(SPACER + "Hello! I'm Moody!\nWhat can I do for you?\n" + SPACER);
    }

    private static void sayGoodbye() {
        System.out.println(SPACER + "Bye. Hope to see you again soon!\n" + SPACER);
    }

    private static void listTasks() {
        System.out.print(SPACER);
        for (int i = 0; i < userTasks.size(); i++) {
            System.out.println((i + 1) + ". " + userTasks.get(i));
        }
        System.out.println(SPACER);
    }

    private static void markTask(String userInput) throws TaskOutOfBoundsException, TaskInputException {
        // to handle empty task number
        if (userInput.equals("mark") || userInput.equals("mark ")) {
            throw new TaskInputException("""
                    Error: Missing task number
                    
                    Please use the following format: mark <task number>
                    """);
        }

        int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;

        // to handle array out-of-bounds cases when accessing userTasks arraylist
        if (taskNumber >= userTasks.size() || taskNumber < 0) {
            throw new TaskOutOfBoundsException("""
                    Error: Cannot mark a task that does not exist
                    
                    Please check that you are marking the correct task number.
                    """);
        }

        userTasks.get(taskNumber).markAsDone();
        saveTasksToFile();
        System.out.println(SPACER + "Nice! I've marked this task as done:");
        System.out.println(INDENT + userTasks.get(taskNumber).toString());
        System.out.println(SPACER);
    }

    private static void unmarkTask(String userInput) throws TaskOutOfBoundsException, TaskInputException {
        // to handle empty task number
        if (userInput.equals("unmark") || userInput.equals("unmark ")) {
            throw new TaskInputException("""
                    Error: Missing task number
                    
                    Please use the following format: unmark <task number>
                    """);
        }

        int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;

        // to handle array out-of-bounds cases when accessing userTasks arraylist
        if (taskNumber >= userTasks.size() || taskNumber < 0) {
            throw new TaskOutOfBoundsException("""
                    Error: Cannot unmark a task that does not exist
                    
                    Please check that you are marking the correct task number.
                    """);
        }

        userTasks.get(taskNumber).markAsNotDone();
        saveTasksToFile();
        System.out.println(SPACER + "OK, I've marked this task as not done yet:");
        System.out.println(INDENT + userTasks.get(taskNumber).toString());
        System.out.println(SPACER);
    }

    private static void addTodoTask(String userInput) throws TaskInputException, InvalidCommandException {
        // to handle empty description case
        if (userInput.equals("todo")) {
            throw new TaskInputException("""
                    Error: Missing task description
                    
                    Please use the following format: todo <description>
                    """);
        }

        // to handle cases where userInput has same prefix but different command
        if (!userInput.startsWith("todo ")) {
            invalidCommand();
        }

        String description = userInput.substring(5).trim();
        userTasks.add(new Todo(description));
        saveTasksToFile();
        System.out.println(SPACER + "Got it. I've added this task:\n"
                + INDENT + userTasks.get(userTasks.size() - 1) + "\n"
                + "Now you have " + userTasks.size() + " tasks in the list.\n" + SPACER);
    }

    private static void addDeadlineTask(String userInput) throws TaskInputException, InvalidCommandException {
        // to handle empty description and date case
        if (userInput.equals("deadline")) {
            throw new TaskInputException("""
                    Error: Missing deadline description and date
                    
                    Please use the following format: deadline <description> /by <date>
                    """);
        }

        // to handle cases where userInput has same prefix but different command
        if (!userInput.startsWith("deadline ")) {
            invalidCommand();
        }

        String[] substrings = userInput.substring(9).split(" /by ");

        // to handle empty date case (i.e. wrong format)
        if (substrings.length != 2) {
            throw new TaskInputException("""
                    Error: Wrong input format
                    
                    Please use the following format: deadline <name> /by <date>
                    """);
        }

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        try {
            userTasks.add(new Deadline(substrings[0].trim(), LocalDateTime.parse(substrings[1].trim(), inputFormatter)));
        } catch (DateTimeParseException e) {
            throw new TaskInputException(("Error: Invalid date format\n" +
                    "Please use yyyy-MM-dd HHmm for the date\n"));
        }

        saveTasksToFile();
        System.out.println(SPACER + "Got it. I've added this task:\n"
                + INDENT + userTasks.get(userTasks.size() - 1) + "\n"
                + "Now you have " + userTasks.size() + " tasks in the list.\n" + SPACER);
    }

    private static void addEventTask(String userInput) throws TaskInputException, InvalidCommandException {
        // to handle empty description and date case
        if (userInput.equals("event")) {
            throw new TaskInputException("""
                    Error: Missing event description and date
                    
                    Please use the following format: event <name> /from <date> <time> /to <date> <time>
                    """);
        }

        // to handle cases where userInput has same prefix but different command
        if (!userInput.startsWith("event ")) {
            invalidCommand();
        }

        String[] substrings = userInput.substring(5).split(" /from | /to ");

        // to handle empty date(s) case
        if (substrings.length != 3) {
            throw new TaskInputException("""
                    Error: Wrong input format
                    
                    Please use the following format: event <name> /from <date> <time> /to <date> <time>
                    """);

        }

        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        try {
            userTasks.add(new Event(substrings[0].trim(), LocalDateTime.parse(substrings[1].trim(), inputFormatter),
                    LocalDateTime.parse(substrings[2].trim(), inputFormatter)));
        } catch (DateTimeParseException e) {
            throw new TaskInputException(("Error: Invalid date format\n" +
                    "Please use yyyy-MM-dd HHmm format for the dates\n"));
        }

        saveTasksToFile();
        System.out.println(SPACER + "Got it. I've added this task:\n"
                + INDENT + userTasks.get(userTasks.size() - 1) + "\n"
                + "Now you have " + userTasks.size() + " tasks in the list.\n" + SPACER);
    }

    private static void deleteTask(String userInput) throws TaskOutOfBoundsException, TaskInputException {
        // to handle empty task number
        if (userInput.equals("delete") || userInput.equals("delete ")) {
            throw new TaskInputException("""
                    Error: Missing task number
                    
                    Please use the following format: delete <task number>
                    """);
        }

        int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;

        // to handle array out-of-bounds cases when accessing userTasks arraylist
        if (taskNumber >= userTasks.size() || taskNumber < 0) {
            throw new TaskOutOfBoundsException("""
                    Error: Cannot mark a task that does not exist
                    
                    Please check that you are marking the correct task number.
                    """);
        }

        Task removedTask = userTasks.remove(taskNumber);
        saveTasksToFile();
        System.out.println(SPACER + "Noted. I've removed this task:\n" + INDENT + removedTask + "\n"
                + "Now you have " + userTasks.size() + " tasks in the list.\n" + SPACER);

    }

    private static void invalidCommand() throws InvalidCommandException {
        throw new InvalidCommandException("""
                Error: Command not found
                
                Please input a valid command
                """);
    }

    private static void loadTasksFromFile() {
        try {
            File file = new File(FILE_PATH);
            // Check if file exists
            if (!file.exists()) {
                return;
            }

            Scanner fs = new Scanner(file);
            while (fs.hasNextLine()) {
                String line = fs.nextLine();
                String[] parts = line.split(" \\| ");
                String taskType = parts[0];
                boolean isTaskDone = parts[1].equals("1");
                Task task;

                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

                switch (taskType) {
                case "T":
                    task = new Todo(parts[2].trim());
                    break;
                case "D":
                    task = new Deadline(parts[2].trim(), LocalDateTime.parse(parts[3].trim(), inputFormatter));
                    break;
                case "E":
                    task = new Event(parts[2].trim(), LocalDateTime.parse(parts[3].trim(), inputFormatter),
                            LocalDateTime.parse(parts[4].trim(), inputFormatter));
                    break;
                default:
                    continue;
                }

                if (isTaskDone) {
                    task.markAsDone();
                }

                userTasks.add(task);
            }

            fs.close();
        } catch (FileNotFoundException e) {
            System.out.println(SPACER + "Error: Unable to load tasks from file" + SPACER);
        }
    }

    private static void saveTasksToFile() {
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            FileWriter fileWriter = new FileWriter("./data/moody.txt");
            for (Task task : userTasks) {
                fileWriter.write(task.toFileFormat() + System.lineSeparator());
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(SPACER + "Error: Unable to save tasks to file" + SPACER);
        }
    }
}
