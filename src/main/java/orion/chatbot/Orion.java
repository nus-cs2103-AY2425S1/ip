package orion.chatbot;

import orion.tasks.Task;
import orion.tasks.Todo;
import orion.tasks.Deadline;
import orion.tasks.Event;
import orion.exceptions.OrionTaskDataException;
import orion.exceptions.OrionInputException;

import java.util.ArrayList;
import java.util.Arrays;

import java.io.File;
import java.util.Scanner;
import java.nio.file.Files;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Orion {

    public static final String LOGO = "             .__               \n"
                                    + "  ___________|__| ____   ____  \n"
                                    + " /  _ \\_  __ \\  |/  _ \\ /    \\ \n"
                                    + "(  <_> )  | \\/  (  <_> )   |  \\\n"
                                    + " \\____/|__|  |__|\\____/|___|  /\n"
                                    + "                            \\/ \n";

    public static final String BAR = "______________________________________________\n";

    public static final String INDENT = "    ";

    public static final String DATA_PATHNAME = "./data/tasks.txt";

    private static boolean isOnline;
    private static ArrayList<Task> tasks;
    private static int noTasks;

    private static void loadTasks() {
        File taskList = new File(DATA_PATHNAME);
        Path path = Paths.get(DATA_PATHNAME);
        try {
            Scanner s = new Scanner(taskList);

            while (s.hasNext()) {
                String task = s.nextLine();
                String[] parsed = task.split(",");
                switch (parsed[0]) {
                    case "todo":
                        if (parsed.length != 3) {
                            throw new OrionTaskDataException("Unrecognised todo task format");
                        } else {
                            boolean done = parsed[1].equals("T");
                            Task todo = new Todo(parsed[2], done);
                            Orion.tasks.add(todo);
                            Orion.noTasks++;
                            break;
                        }
                    case "deadline":
                        if (parsed.length != 4) {
                            throw new OrionTaskDataException("Unrecognised deadline task format");
                        } else {
                            boolean done = parsed[1].equals("T");
                            LocalDate time = LocalDate.parse(parsed[3]);
                            Task deadline = new Deadline(parsed[2], done, time);
                            Orion.tasks.add(deadline);
                            Orion.noTasks++;
                            break;
                        }
                    case "event":
                        if (parsed.length != 5) {
                            throw new OrionTaskDataException("Unrecognised event task format");
                        } else {
                            boolean done = parsed[1].equals("T");
                            LocalDate start = LocalDate.parse(parsed[3]);
                            LocalDate end = LocalDate.parse(parsed[4]);
                            Task event = new Event(parsed[2], done, start, end);
                            Orion.tasks.add(event);
                            Orion.noTasks++;
                            break;
                        }
                    default:
                        // File corrupted
                        throw new OrionTaskDataException("Unrecognised task type");
                }
            }

            Orion.printIndent(String.format("Welcome back! You have %d tasks in your task list.", Orion.noTasks));
            Orion.printBar();
        } catch (FileNotFoundException e) {
            try {
                Orion.printIndent("Your existing task list is somewhere amongst the stars...");
                Orion.printIndent("We can't seem to find it!");
                File dataDirectory = new File("./data");
                Path dir = Paths.get("./data");
                if (dataDirectory.exists() && dataDirectory.isDirectory()) {
                    Files.createFile(path);
                } else if (dataDirectory.exists()) {
                    Files.delete(dir);
                    Files.createDirectory(dir);
                    Files.createFile(path);
                } else {
                    Files.createDirectory(dir);
                    Files.createFile(path);
                }
                Orion.printIndent("We've created a new task list for you.");
                Orion.printBar();
            } catch (IOException err) {
                Orion.printIndent("Unfortunately... an error has occurred when creating a new task list...");
                Orion.printBar();
            }
        } catch (OrionTaskDataException | DateTimeException e) {
            try {
                Orion.printIndent("Looks like your existing task list has been corrupted...");
                Files.delete(path);
                Files.createFile(path);
                Orion.printIndent("We've created a new task list for you.");
                Orion.printBar();
            } catch (IOException err) {
                Orion.printIndent("Unfortunately... an error has occurred when creating a new task list...");
                Orion.printBar();
            }
        }
    }

    private static void saveTasks() {
        try {
            FileWriter fw = new FileWriter(DATA_PATHNAME);
            for (Task task : Orion.tasks) {
                fw.write(task.saveString() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            Orion.printIndent("We had a problem saving your task list... Sorry about that!");
            Orion.printBar();
        }
    }

    private static void printBar() {
        System.out.println(Orion.BAR);
    }

    private static void printIndent(String message) {
        System.out.println(Orion.INDENT + message);
    }

    private static String removeFirstWordFromString(String str) {
        return str.split(" ", 2)[1];
    }

    private static void greet() {
        Orion.printBar();
        System.out.println(Orion.LOGO);
        Orion.printBar();

        Orion.printIndent("Hello from Orion!");
        Orion.printIndent("We're fetching your task list from the cosmos...");
        Orion.printBar();
    }

    private static void sayGoodbye() {
        Orion.printIndent("Bye! Hope to see you again soon!");
    }

    private static void list() {
        Orion.printIndent("Here are the tasks in your list:");
        for (int i = 0; i < Orion.noTasks; i++) {
            String task = String.format("%d. %s", i + 1, Orion.tasks.get(i));
            Orion.printIndent(task);
        }
    }

    private static void addTask(Task task) {
        Orion.tasks.add(task);
        Orion.noTasks++;
        Orion.printIndent("Sure! I've added the following task to your list:");
        Orion.printIndent(task.toString());
        Orion.printIndent("Now you have " + Orion.noTasks + " tasks in your list.");
    }

    private static void addTodo(String input) {
        Task task = new Todo(input);
        Orion.addTask(task);
    }

    private static void addDeadline(String name, String deadline) throws OrionInputException {
        try {
            LocalDate time = LocalDate.parse(deadline);
            Task task = new Deadline(name, time);
            Orion.addTask(task);
        } catch (DateTimeException e) {
            throw new OrionInputException("Correct syntax: deadline <task> /by <yyyy-mm-dd>." +
                    "Please input a valid date in the correct format!");
        }
    }

    private static void addEvent(String name, String from, String to) throws OrionInputException {
        try {
            LocalDate start = LocalDate.parse(from);
            LocalDate end = LocalDate.parse(to);
            if (start.isAfter(end)) {
                throw new OrionInputException("Your start date must be later than your end date!");
            }
            Task task = new Event(name, start, end);
            Orion.addTask(task);
        } catch (DateTimeException e) {
            throw new OrionInputException("Correct syntax: event <task> /from <yyyy-mm-dd> /to <yyyy-mm-dd>. " +
                    "Please input valid dates in the correct format!");
        }
    }

    // taskNo is 0 indexed
    private static void markTask(int taskNo) {
        Task task = Orion.tasks.get(taskNo);
        task.setDone();
        Orion.printIndent("Sure! I've marked the following task as done:");
        Orion.printIndent(task.toString());
    }

    // taskNo is 0 indexed
    private static void unmarkTask(int taskNo) {
        Task task = Orion.tasks.get(taskNo);
        task.setUndone();
        Orion.printIndent("Sure! I've marked the following task as undone:");
        Orion.printIndent(task.toString());
    }

    private static void deleteTask(int taskNo) {
        Task task = Orion.tasks.get(taskNo);
        Orion.tasks.remove(task);
        Orion.noTasks--;
        Orion.printIndent("Sure! I've deleted the following task:");
        Orion.printIndent(task.toString());
        Orion.printIndent("Now you have " + Orion.noTasks + " tasks in your list.");
    }

    private static void obey(String input) {
        try {
            String[] inputArray = input.split(" ");
            String command = inputArray[0].toLowerCase();
            // For use by deadline and event commands
            String[] parsed = input.split("/");
            switch (command) {
                case "bye":
                    Orion.isOnline = false;
                    Orion.sayGoodbye();
                    break;
                case "list":
                    Orion.list();
                    break;
                case "mark":
                    if (inputArray.length != 2) {
                        throw new OrionInputException("Correct syntax: mark <task number>");
                    } else {
                        try {
                            int taskNo = Integer.parseInt(inputArray[1]);
                            if (taskNo < 1) {
                                throw new OrionInputException("Please provide a positive task number!");
                            } else if (taskNo > Orion.noTasks) {
                                String errorMsg = String.format("Number of tasks: %d. Unable to mark task %d as done.", Orion.noTasks, taskNo);
                                throw new OrionInputException(errorMsg);
                            } else {
                                Orion.markTask(taskNo - 1);
                                break;
                            }
                        } catch (NumberFormatException e) {
                            throw new OrionInputException("Correct syntax: mark <task number>");
                        }
                    }
                case "unmark":
                    if (inputArray.length != 2) {
                        throw new OrionInputException("Correct syntax: unmark <task number>");
                    } else {
                        try {
                            int taskNo = Integer.parseInt(inputArray[1]);
                            if (taskNo < 1) {
                                throw new OrionInputException("Please provide a positive task number!");
                            } else if (taskNo > Orion.noTasks) {
                                String errorMsg = String.format("Number of tasks: %d. Unable to mark task %d as undone.", Orion.noTasks, taskNo);
                                throw new OrionInputException(errorMsg);
                            } else {
                                Orion.unmarkTask(taskNo - 1);
                                break;
                            }
                        } catch (NumberFormatException e) {
                            throw new OrionInputException("Correct syntax: unmark <task number>");
                        }
                    }
                case "todo":
                    if (inputArray.length < 2) {
                        throw new OrionInputException("Correct syntax: todo <task>");
                    } else {
                        Orion.addTodo(Orion.removeFirstWordFromString(input).trim());
                        break;
                    }
                case "deadline":
                    if (parsed.length != 2 || !parsed[1].matches("^by.*$")) {
                        throw new OrionInputException("Correct syntax: deadline <task> /by <yyyy-mm-dd>");
                    } else {
                        // Removes "deadline" and "by" keywords from input
                        String[] mapped = Arrays.stream(parsed)
                                .map(Orion::removeFirstWordFromString)
                                .toArray(String[]::new);
                        Orion.addDeadline(mapped[0].trim(), mapped[1].trim());
                        break;
                    }
                case "event":
                    if (parsed.length != 3 || !parsed[1].matches("^from.*$") || !parsed[2].matches("^to.*$")) {
                        throw new OrionInputException("Correct syntax: event <task> /from <yyyy-mm-dd> /to <yyyy-mm-dd>");
                    } else {
                        // Removes "event", "from" and "to" keywords from input
                        String[] mapped = Arrays.stream(parsed)
                                .map(Orion::removeFirstWordFromString)
                                .toArray(String[]::new);
                        Orion.addEvent(mapped[0].trim(), mapped[1].trim(), mapped[2].trim());
                        break;
                    }
                case "delete":
                    if (inputArray.length != 2) {
                        throw new OrionInputException("Correct syntax: delete <task number>");
                    } else {
                        try {
                            int taskNo = Integer.parseInt(inputArray[1]);
                            if (taskNo < 1) {
                                throw new OrionInputException("Please provide a positive task number!");
                            } else if (taskNo > Orion.noTasks) {
                                String errorMsg = String.format("Number of tasks: %d. Unable to delete task %d.", Orion.noTasks, taskNo);
                                throw new OrionInputException(errorMsg);
                            } else {
                                Orion.deleteTask(taskNo - 1);
                                break;
                            }
                        } catch (NumberFormatException e) {
                            throw new OrionInputException("Correct syntax: delete <task number>");
                        }
                    }
                default:
                    throw new OrionInputException("Please provide a supported command!");
            }
        } catch (OrionInputException e) {
            Orion.printIndent(e.getMessage());
        } catch (Exception e) {
            Orion.printIndent("Unexpected error!" + e.getMessage());
        }
        Orion.printBar();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Orion.isOnline = true;
        Orion.tasks = new ArrayList<>();
        Orion.noTasks = 0;
        Orion.greet();
        Orion.loadTasks();

        while (Orion.isOnline) {
            String command = sc.nextLine();
            Orion.printBar();
            Orion.obey(command);
        }

        Orion.saveTasks();
        sc.close();
    }
}