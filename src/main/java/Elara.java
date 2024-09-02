import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Elara {

    private static final String DATA_DIRECTORY = "./data";
    private static final String DATA_FILE = DATA_DIRECTORY + "/Elara.txt";
    private ArrayList<Task> tasks;

    public Elara() {
        tasks = new ArrayList<>();
    }

    public static void main(String[] args) {
        Elara elara = new Elara();
        elara.setupFile();
        elara.tasks = elara.readFile();
        elara.runChatbot();
    }

    private void setupFile() {
        try {
            File file = new File(DATA_FILE);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Error creating file");
            e.printStackTrace();
        }
    }

    private ArrayList<Task> readFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(DATA_FILE));
            for (String line : lines) {
                String[] taskDetails = line.split(" \\| ");
                if (taskDetails.length > 0) {
                    Task task = parseTask(taskDetails);
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        }
        return tasks;
    }

    private Task parseTask(String[] taskDetails) {
        String taskType = taskDetails[0];
        boolean isDone = taskDetails[1].trim().equals("1");
        String description = taskDetails[2].trim();

        switch (taskType) {
            case "T":
                return new ToDoTask(description, isDone);
            case "D":
                if (taskDetails.length == 4) {
                    String deadline = taskDetails[3];
                    return new DeadlineTask(description, deadline, isDone);
                }
                throw new IllegalArgumentException("Invalid deadline task format");
            case "E":
                if (taskDetails.length == 5) {
                    String startTime = taskDetails[3];
                    String endTime = taskDetails[4];
                    return new EventTask(description, startTime, endTime, isDone);
                }
                throw new IllegalArgumentException("Invalid event task format");
            default:
                throw new IllegalArgumentException("Invalid task type");
        }
    }

    public void saveTasksToFile() {
        try (FileWriter writer = new FileWriter(DATA_FILE)) {
            for (Task task : tasks) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks to file");
            e.printStackTrace();
        }
    }

    private void runChatbot() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Elara");
        System.out.println("What can I do for you?");

        while (true) {
            String text = scanner.nextLine().trim();
            String[] split = text.split(" ", 2);
            String command = split[0];
            String arg = split.length > 1 ? split[1] : "";

            try {
                switch (command) {
                    case "bye":
                        System.out.println("Bye. Hope to see you again!");
                        break;
                    case "list":
                        listTasks();
                        break;
                    case "mark":
                        int i = Integer.parseInt(arg) - 1;
                        if (i >= 0 && i < tasks.size()) {
                            mark(i);
                            break;
                        }
                        throw new InvalidInputException("Index out of range!");
                    case "unmark":
                        int j = Integer.parseInt(arg) - 1;
                        if (j >= 0 && j < tasks.size()) {
                            unmark(j);
                            break;
                        }
                        throw new InvalidInputException("Index out of range!");
                    case "delete":
                        int k = Integer.parseInt(arg) - 1;
                        if (k >= 0 && k < tasks.size()) {
                            delete(k);
                            break;
                        }
                        throw new InvalidInputException("Index out of range!");
                    case "todo":
                    case "deadline":
                    case "event":
                        addTask(command, arg);
                        break;
                    default:
                        throw new InvalidInputException("Errrrrrrr... I don't know what you are trying to say...\n" +
                                "Try one of our commands: list mark unmark bye deadline todo event");
                }
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i) == null) {
                break;
            }
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private void mark(int index) {
        tasks.get(index).mark();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(index));
        saveTasksToFile();
    }

    private void unmark(int index) {
        tasks.get(index).unmark();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(index));
        saveTasksToFile();
    }

    private void delete(int index) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(tasks.get(index));
        tasks.remove(index);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        saveTasksToFile();
    }

    private void addTask(String command, String arg) {
        Task newTask = null;
        try {
            switch (command) {
                case "todo":
                    if (arg.isEmpty()) {
                        throw new ToDoException();
                    }
                    newTask = new ToDoTask(arg);
                    break;
                case "deadline":
                    String[] deadlineArgs = arg.split("/by ");
                    if (deadlineArgs.length == 2) {
                        newTask = new DeadlineTask(deadlineArgs[0], deadlineArgs[1]);
                        break;
                    }
                    throw new DeadlineException();
                case "event":
                    String[] eventArgs = arg.split("/from |/to ");
                    if (eventArgs.length == 3) {
                        newTask = new EventTask(eventArgs[0], eventArgs[1], eventArgs[2]);
                        break;
                    }
                    throw new EventException();
            }

        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
        }

        if (newTask != null) {
            tasks.add(newTask);
            saveTasksToFile();
            System.out.println("Got it. I've added this task:");
            System.out.println(newTask);
            System.out.printf("Now you have %d tasks in the list%n", tasks.size());
        }
    }
}