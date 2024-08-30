import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Easton {

    public static final String CHATBOT_NAME = "Easton";
    private ArrayList<Task> tasks = new ArrayList<>();;
    private Storage storage;

    public Easton(String fileName) {
        try {
            storage = new Storage(fileName);
        } catch (IOException e) {
            System.out.println("Cannot connect to the storage.");
        }

        tasks = retrieveTasks();
    }

    public void run() {
        printWelcome();

        Scanner scanner = new Scanner(System.in);
        Action action;
        boolean isFinished = false;
        String userInput;

        while (!isFinished) {
            userInput = scanner.nextLine();;
            printDivider();

            try {
                action = getActionFromInput(userInput);
            } catch (IllegalActionException e) {
                System.out.println(e.getMessage());
                action = Action.INVALID;
            }

            switch (action) {
            case BYE:
                System.out.println("Bye. Hope to see you again soon!");
                isFinished = true;
                break;
            case LIST:
                System.out.println("Here are the tasks in your list:");
                printList();
                break;
            case MARK:
                changeTaskStatus(userInput, true, "Nice! I've marked this task as done:");
                saveTasks();
                break;
            case UNMARK:
                changeTaskStatus(userInput, false, "OK, I've marked this task as not done yet:");
                saveTasks();
                break;
            case TODO:
                try {
                    addTask(createToDo(userInput));
                    saveTasks();
                } catch (EmptyDescriptionException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case DEADLINE:
                try {
                    addTask(createDeadline(userInput));
                    saveTasks();
                } catch (EmptyDescriptionException | InvalidFormatException | DateTimeFormatException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case EVENT:
                try {
                    addTask(createEvent(userInput));
                    saveTasks();
                } catch (EmptyDescriptionException | InvalidFormatException | DateTimeFormatException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case DELETE:
                deleteTask(userInput);
                saveTasks();
                break;
            }

            printDivider();
        }

    }

    private static void printDivider() {
        System.out.println("____________________________________________________________");
    }

    private static void printWelcome() {
        String logo = " _______  _______  _______  _______  _______  __    _\n"
                + "|       ||   _   ||       ||       ||       ||  |  | |\n"
                + "|    ___||  |_|  ||  _____||_     _||   _   ||   |_| |\n"
                + "|   |___ |       || |_____   |   |  |  | |  ||       |\n"
                + "|    ___||       ||_____  |  |   |  |  |_|  ||  _    |\n"
                + "|   |___ |   _   | _____| |  |   |  |       || | |   |\n"
                + "|_______||__| |__||_______|  |___|  |_______||_|  |__|\n";
        System.out.println("Hello from\n" + logo);
        printDivider();
        System.out.println("Hello! I'm " + CHATBOT_NAME);
        System.out.println("What can I do for you?");
        printDivider();
    }

    public static void main(String[] args) {
        new Easton("task.csv").run();
    }

    private void printList() {
        for (int i = 0; i < tasks.size(); i ++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    private void changeTaskStatus(String input, boolean isDone, String message) {
        try {
            int index = getIndexFromInput(input);
            Task task = tasks.get(index - 1);
            task.setDone(isDone);
            System.out.println(message);
            System.out.println(task);

        } catch (InvalidIndexException | EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteTask(String input) {
        try {
            int index = getIndexFromInput(input);
            Task task = tasks.remove(index - 1);
            System.out.println("Noted. I've removed this task:");
            System.out.println(task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");

        } catch (InvalidIndexException | EmptyDescriptionException e) {
            System.out.println(e.getMessage());
        }
    }

    private int getIndexFromInput(String input) throws InvalidIndexException, EmptyDescriptionException {
        int index;
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length != 2) {
            throw new EmptyDescriptionException();
        }

        try {
            index = Integer.parseInt(splitInput[1]);
        } catch (NumberFormatException e) {
            throw new InvalidIndexException(splitInput[1]);
        }

        if (0 < index && index <= tasks.size()) {
            return index;
        } else {
            throw new InvalidIndexException(splitInput[1]);
        }
    }

    private static ToDo createToDo(String input) throws EmptyDescriptionException {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length == 2) {
            return new ToDo(splitInput[1]);
        } else {
            throw new EmptyDescriptionException();
        }
    }

    private static Deadline createDeadline(String input) throws EmptyDescriptionException,
            InvalidFormatException,
            DateTimeFormatException {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length != 2) {
            throw new EmptyDescriptionException();
        }

        if (!splitInput[1].contains(" /by ")) {
            throw new InvalidFormatException();
        }

        String[] content = splitInput[1].split(" /by ", 2);
        return new Deadline(content[0], content[1]);
    }

    private static Event createEvent(String input) throws EmptyDescriptionException,
            InvalidFormatException,
            DateTimeFormatException {
        String[] splitInput = input.split(" ", 2);
        if (splitInput.length != 2) {
            throw new EmptyDescriptionException();
        }

        if (!(splitInput[1].contains(" /from ") && splitInput[1].contains(" /to "))) {
            throw new InvalidFormatException();
        }

        String[] content = splitInput[1].split(" /from | /to ", 3);
        return new Event(content[0], content[1], content[2]);
    }

    private static Action getActionFromInput(String input) throws IllegalActionException {
        String action = input.split(" ", 2)[0];
        try {
            return Action.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalActionException();
        }
    }

    private void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }



    private Path getFilePath(String fileName) throws IOException{
        String currentDirectory = System.getProperty("user.dir");
        Path folder = Paths.get(currentDirectory, "data");
        Path filePath = Paths.get(folder.toString(), fileName);

        if (Files.notExists(folder)) {
            Files.createDirectory(folder);
        }

        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }

        return filePath;
    }

    private ArrayList<Task> retrieveTasks() {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        ArrayList<String> records = new ArrayList<>();
        Task task;
        try {
            records = storage.retrieve();
        } catch (IOException e) {
            System.out.println("Could not retrieve tasks from storage.");
        }

        for (String record : records) {
            String[] data = record.split(",");
            switch (data[0]) {
            case "T":
                task = new ToDo(data[2]);
                break;
            case "D":
                try {
                    task = new Deadline(data[2], data[3]);
                } catch (DateTimeFormatException e) {
                    System.out.println("Deadline (" +
                            data[2] +
                            ") is using the wrong DateTime Format, the record is voided.");
                    continue;
                }
                break;
            case "E":
                try {
                    task = new Event(data[2], data[3], data[4]);
                } catch (DateTimeFormatException e) {
                    System.out.println("Event ("+
                            data[2] +
                            ") is using the wrong DateTime Format, the record is voided.");
                    continue;
                }
                break;
            default:
                continue;
            }
            task.setDone(data[1].equals("1"));
            taskArrayList.add(task);
        }

        return taskArrayList;
    }

    private void saveTasks() {
        ArrayList<String> records = new ArrayList<>();

        for (Task task : tasks) {
            records.add(task.getCsvFormat() + "\n");
        }

        storage.save(records);
    }
}
