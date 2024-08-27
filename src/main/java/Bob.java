import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bob {
    private static final String DATA_DIR = "data";
    private static final String FILE_PATH = DATA_DIR + File.separator + "Bob.txt";
    private static final List<Task> taskList = new ArrayList<>();
    private static int numTasks = 0;

    public enum Command {
        BYE, LIST, RELEVANT, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, UNKNOWN
    }

    public static void main(String[] args) throws BobException, IOException {
        System.out.println(loadTasks());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Hello! I'm Bob the bot!\nHow can I help you?");

        while (true) {
            String userInput = br.readLine().trim();

            try {
                Command command = getCommand(userInput);
                String taskDetails = getTaskDetails(userInput);

                switch (command) {
                    case BYE:
                        System.out.println(commandBye());
                        return;

                    case LIST:
                        System.out.println(commandList());
                        break;

                    case RELEVANT:
                        System.out.println(commandRelevant(taskDetails));
                        break;

                    case MARK:
                        System.out.println(commandMark(taskDetails));
                        break;

                    case UNMARK:
                        System.out.println(commandUnmark(taskDetails));
                        break;

                    case TODO:
                        System.out.println(commandTodo(taskDetails));
                        break;

                    case DEADLINE:
                        System.out.println(commandDeadline(taskDetails));
                        break;

                    case EVENT:
                        System.out.println(commandEvent(taskDetails));
                        break;

                    case DELETE:
                        System.out.println(commandDelete(taskDetails));
                        break;

                    case UNKNOWN:
                    default:
                        throw new BobException("Sorry, I do not understand. Please try something else.");
                }
            } catch (BobException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
        }
    }

    static String loadTasks() throws BobException {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                return "No saved tasks found.";
            }
            Scanner s = new Scanner(file);
            while (s.hasNext()) {
                String taskLine = s.nextLine();
                Task task = parseTask(taskLine);
                addTask(task);
            }
            return "Saved tasks has been successfully loaded.";
        } catch (FileNotFoundException e) {
            throw new BobException("File not found! Please check again.");
        }
    }

    static Task parseTask(String line) throws BobException {
        String[] details = line.split(",");
        String type = details[0];
        boolean isDone = details[1].equals("1");
        String description = details[2];

        switch (type) {
            case "T":
                return new ToDo(description, isDone);
            case "D":
                String byStr = details[3];
                LocalDateTime by = parseDateTime(byStr);
                return new Deadline(description, isDone, by);
            case "E":
                String fromStr = details[3];
                String toStr = details[4];
                LocalDateTime from = parseDateTime(fromStr);
                LocalDateTime to = parseDateTime(toStr);
                return new Event(description, isDone, from, to);
            default:
                System.out.println("Unknown task type: " + type);
                return new ToDo(description, isDone);
        }
    }

    static LocalDateTime parseDateTime(String dateTimeStr) throws BobException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (DateTimeParseException e) {
            throw new BobException("Please provide the correct date and 24-hour time format: yyyy-mm-dd HHmm"
                    + "\nEg. 2024-08-27 1530 for Aug 27 2024 03.30pm");
        }
    }

    static Command getCommand(String userInput) throws BobException {
        if (userInput.isEmpty() || userInput.equals(" ")) {
            throw new BobException("You did not key in anything...");
        }
        String commandStr = userInput.split(" ", 2)[0].toUpperCase();
        try {
            return Command.valueOf(commandStr);
        } catch (IllegalArgumentException e) {
            return Command.UNKNOWN;
        }
    }

    static String getTaskDetails(String userInput) throws BobException {
        String[] args = userInput.split(" ", 2);
        if (args.length < 2) {
            return "";
        }
        return args[1].trim();
    }

    static Task getTask(int taskNum) throws BobException {
        try {
            return taskList.get(taskNum - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new BobException("No such task number found. Please provide a valid task number.");
        }
    }

    static void addTask(Task task) {
        taskList.add(task);
        numTasks++;
    }

    static void delTask(int taskNum) {
        taskList.remove(taskNum - 1);
        numTasks--;
    }

    static String getTaskList() {
        StringBuilder tasks = new StringBuilder();
        for (int i = 1; i <= numTasks; i++) {
            Task currTask = taskList.get(i - 1);
            if (i == numTasks) {
                tasks.append(i).append(". ").append(currTask);
                continue;
            }
            tasks.append(i).append(". ").append(currTask).append("\n");
        }
        return tasks.toString();
    }

    static void saveTasks() throws BobException {
        try {
            ensureDataDirectoryExists();
            FileWriter fw = new FileWriter(FILE_PATH);
            for (Task task : taskList) {
                fw.write(task.getTaskLine() + System.lineSeparator());
            }
            fw.close();
        } catch (IOException e) {
            throw new BobException("Oh no! An error occurred while saving tasks: " + e.getMessage());
        }
    }

    static String getRelevantTasks(String dateStr) throws BobException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.parse(dateStr, formatter);

            StringBuilder tasks = new StringBuilder();
            int numRelevantTasks = 0;
            for (Task currTask : taskList) {
                if (currTask.isRelevant(date)) {
                    numRelevantTasks++;
                    tasks.append(numRelevantTasks).append(". ").append(currTask).append("\n");
                }
            }
            DateTimeFormatter formatterWords = DateTimeFormatter.ofPattern("MMM dd yyyy");
            tasks.append("Total number of relevant tasks for ").append(date.format(formatterWords))
                    .append(": ").append(numRelevantTasks);
            return tasks.toString();
        } catch (DateTimeParseException e) {
            throw new BobException("Invalid date format. Required format: relevant yyyy-MM-dd");
        }
    }

    static String commandBye() {
        return "Bye! Hope to see you again :)";
    }

    static String commandList() {
        return "Your list of tasks:\n" + getTaskList();
    }

    static String commandRelevant(String dateStr) throws BobException {
        return getRelevantTasks(dateStr);
    }

    static String commandMark(String taskDetails) throws BobException {
        if (taskDetails.isEmpty()) {
            throw new BobException("Please provide a task number.");
        }
        try {
            int taskNum = Integer.parseInt(taskDetails);
            Task currTask = getTask(taskNum);
            currTask.markAsDone();
            saveTasks();
            return "Good Job! Marking this task as done:\n " + currTask;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new BobException("The task number provided is invalid.");
        }
    }

    static String commandUnmark(String taskDetails) throws BobException {
        if (taskDetails.isEmpty()) {
            throw new BobException("Please provide a task number.");
        }
        try {
            int taskNum = Integer.parseInt(taskDetails);
            Task currTask = getTask(taskNum);
            currTask.markAsUndone();
            saveTasks();
            return "Okay, marking this task as not done yet:\n " + currTask;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new BobException("The task number provided is invalid.");
        }
    }

    static String commandTodo(String taskDetails) throws BobException {
        String format = "Add ToDo task in the following format:\ntodo <description>";
        if (taskDetails.isEmpty()) {
            throw new BobException("Missing details!\n" + format);
        }
        ToDo task = new ToDo(taskDetails);
        addTask(task);
        saveTasks();
        return "Adding ToDo task:\n " + task
                + "\nTotal number of tasks in your list: " + numTasks;
    }

    static String commandDeadline(String taskDetails) throws BobException {
        String format = "Add Deadline task in the following format:\n"
                + "deadline <description> /by <due date>";
        if (taskDetails.isEmpty()) {
            throw new BobException("Missing details!\n" + format);
        }

        try {
            String[] params = taskDetails.split(" /");
            String description = params[0];
            String byStr = params[1].split(" ", 2)[1];

            LocalDateTime by = parseDateTime(byStr);
            if (by.isBefore(LocalDateTime.now())) {
                throw new BobException("Oops! The date you provided is in the past. "
                        + "Kindly provide a future date.");
            }
            Deadline task = new Deadline(description, by);
            addTask(task);
            saveTasks();
            return "Adding Deadline task:\n " + task
                    + "\nTotal number of tasks in your list: " + numTasks;
        } catch (IndexOutOfBoundsException e) {
            throw new BobException("You may have missing details or wrong format!\n" + format);
        }
    }

    static String commandEvent(String taskDetails) throws BobException {
        String format = "Add Event task in the following format:\n"
                + "deadline <description> /from <start date> /to <due date>";
        if (taskDetails.isEmpty()) {
            throw new BobException("Missing details!\n" + format);
        }

        try {
            String[] params = taskDetails.split(" /");
            String description = params[0];
            String fromStr = params[1].split(" ", 2)[1];
            String toStr = params[2].split(" ", 2)[1];

            LocalDateTime from = parseDateTime(fromStr);
            LocalDateTime to = parseDateTime(toStr);
            if (from.isBefore(LocalDateTime.now()) || to.isBefore(LocalDateTime.now())) {
                throw new BobException("Oops! The date you provided is in the past. "
                        + "Kindly provide a future date.");
            }
            if (to.isBefore(from)) {
                throw new BobException("The end date cannot be before the start date. " +
                        "Please try again.");
            }
            Event task = new Event(description, from, to);
            addTask(task);
            saveTasks();
            return "Adding Event task:\n " + task
                    + "\nTotal number of tasks in your list: " + numTasks;
        } catch (IndexOutOfBoundsException e) {
            throw new BobException("You may have missing details or wrong format!\n" + format);
        }
    }

    static String commandDelete(String taskDetails) throws BobException {
        if (taskDetails.isEmpty()) {
            throw new BobException("Please provide a task number.");
        }
        try {
            int taskNum = Integer.parseInt(taskDetails);
            Task currTask = getTask(taskNum);
            delTask(taskNum);
            saveTasks();
            return "Noted, removing this task:\n " + currTask
                    + "\nTotal number of tasks in your list: " + numTasks ;
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new BobException("The task number provided is invalid.");
        }
    }

    // Method to ensure the data directory exists
    private static void ensureDataDirectoryExists() throws BobException {
        File dataDir = new File(DATA_DIR);
        if (!dataDir.exists()) {
            if (!dataDir.mkdir()) {
                throw new BobException("Failed to create directory: " + DATA_DIR);
            }
        }
    }
}