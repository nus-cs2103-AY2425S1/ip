import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Easton {

    public static final String CHATBOT_NAME = "Easton";
    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
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

        Scanner scanner = new Scanner(System.in);
        boolean isFinished = false;
        String input;
        Action action;

        try {
            retrieveData();
        } catch (IOException e) {
            System.out.println("There was an error in retrieving my storage. ABORTING!!!");
            isFinished = true;
        }

        while (!isFinished) {
            input = prompt(scanner);
            printDivider();

            try {
                action = getActionFromInput(input);
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
                changeTaskStatus(input, true, "Nice! I've marked this task as done:");
                saveData();
                break;
            case UNMARK:
                changeTaskStatus(input, false, "OK, I've marked this task as not done yet:");
                saveData();
                break;
            case TODO:
                try {
                    addTask(createToDo(input));
                    saveData();
                } catch (EmptyDescriptionException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case DEADLINE:
                try {
                    addTask(createDeadline(input));
                    saveData();
                } catch (EmptyDescriptionException | InvalidFormatException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case EVENT:
                try {
                    addTask(createEvent(input));
                    saveData();
                } catch (EmptyDescriptionException | InvalidFormatException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case DELETE:
                deleteTask(input);
                saveData();
                break;
            }

            printDivider();
        }
    }

    private static void printList() {
        for (int i = 0; i < tasks.size(); i ++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    private static void changeTaskStatus(String input, boolean isDone, String message) {
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

    private static void deleteTask(String input) {
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

    private static int getIndexFromInput(String input) throws InvalidIndexException, EmptyDescriptionException {
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

    private static Deadline createDeadline(String input) throws EmptyDescriptionException, InvalidFormatException {
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

    private static Event createEvent(String input) throws EmptyDescriptionException, InvalidFormatException {
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

    private static void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void printDivider() {
        System.out.println("____________________________________________________________");
    }

    private static String prompt(Scanner scanner) {
        return scanner.nextLine();
    }

    private static Path getFilePath() throws IOException{
        String currentDirectory = System.getProperty("user.dir");
        Path folder = Paths.get(currentDirectory, "data");
        Path filePath = Paths.get(folder.toString(), "tasks.csv");

        if (Files.notExists(folder)) {
            Files.createDirectory(folder);
        }

        if (Files.notExists(filePath)) {
            Files.createFile(filePath);
        }

        return filePath;
    }

    private static void retrieveData() throws IOException {
        Path filePath = getFilePath();
        String line;
        Task task;

        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(
                        filePath.toFile()
                )
        );

        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(",");
            switch (data[0]) {
            case "T":
                task = new ToDo(data[2]);
                break;
            case "D":
                task = new Deadline(data[2], data[3]);
                break;
            case "E":
                task = new Event(data[2], data[3], data[4]);
                break;
            default:
                continue;
            }

            task.setDone(data[1].equals("1"));
            tasks.add(task);
        }
    }

    private static void saveData() {
        try {
            Path filePath = getFilePath();
            FileWriter fileWriter = new FileWriter(filePath.toFile());
            for (Task task : tasks) {
                fileWriter.write(task.getCsvFormat() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Updated list was not saved properly.");
        }


    }
}
