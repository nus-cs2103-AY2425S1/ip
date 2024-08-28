package main.java.chatbot;

import main.java.chatbot.exceptions.ChatsyException;
import main.java.chatbot.exceptions.EmptyDescriptionException;
import main.java.chatbot.exceptions.InvalidCommandException;
import main.java.chatbot.exceptions.InvalidTaskIndexException;
import main.java.chatbot.exceptions.LocalFileException;
import main.java.chatbot.tasks.DeadlineTask;
import main.java.chatbot.tasks.EventTask;
import main.java.chatbot.tasks.Task;
import main.java.chatbot.tasks.TodoTask;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Chatsy {
    static final String name = "Chatsy";
    static final String LOCAL_DIRECTORY_PATH = "./data";
    static final String LOCAL_FILE_PATH = LOCAL_DIRECTORY_PATH + "/chatsy.txt";
    static final String line = "\t____________________________________________________________";
    static final Scanner scanner = new Scanner(System.in);
    static ArrayList<Task> tasks = new ArrayList<>();
    static boolean isRunning = true;
    static Storage storage;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        storage = new Storage(LOCAL_DIRECTORY_PATH, LOCAL_FILE_PATH);
        try {
            tasks = storage.loadTasks();
        } catch (LocalFileException e) {
            output("\tFailed to load tasks: " + e.getMessage());
        }
        greet();
        nextCommand();
    }

    public static void greet() {
        System.out.println(line);
        System.out.println("\tHello! I'm " + name + "\n\tWhat can I do for you?");
        System.out.println(line);
    }

    public static void output(String output) {
        System.out.println(line);
        System.out.println(output);
        System.out.println(line);
    }

    public static void exit() {
        try {
            storage.saveTasks(tasks);
        } catch (LocalFileException e) {
            output("\tFailed to save tasks: " + e.getMessage());
        }
        output("\tBye. Hope to see you again soon!");
        scanner.close();
        isRunning = false;
    }

    public static void addTask(Task task) {
        tasks.add(task);
        output("\tGot it. I've added this task:\n\t  " + task + "\n\tNow you have " + tasks.size() + " tasks in the list.");
        saveTasks();
    }

    public static void listTasks() {
        if (tasks.isEmpty()) {
            output("\tNo tasks to list.");
        } else {
            StringBuilder listOutput = new StringBuilder("\tHere are the tasks in your list:\n");
            for (int i = 0; i < tasks.size(); i++) {
                listOutput.append("\t").append(i + 1).append(". ").append(tasks.get(i)).append("\n");
            }
            output(listOutput.toString());
        }
    }

    public static void markTask(int index) throws InvalidTaskIndexException {
        if (index >= 1 && index <= tasks.size()) {
            tasks.get(index - 1).markAsDone();
            output("\tNice! I've marked this task as done:\n\t  " + tasks.get(index - 1));
            saveTasks();
        } else {
            throw new InvalidTaskIndexException();
        }
    }

    public static void unmarkTask(int index) throws InvalidTaskIndexException {
        if (index >= 1 && index <= tasks.size()) {
            tasks.get(index - 1).markAsNotDone();
            output("\tOK, I've marked this task as not done yet:\n\t  " + tasks.get(index - 1));
            saveTasks();
        } else {
            throw new InvalidTaskIndexException();
        }
    }

    public static void deleteTask(int index) throws InvalidTaskIndexException {
        if (index >= 1 && index <= tasks.size()) {
            Task removedTask = tasks.remove(index - 1);
            output("\tNoted. I've removed this task:\n\t  " + removedTask + "\n\tNow you have " + tasks.size() + " tasks in the list.");
            saveTasks();
        } else {
            throw new InvalidTaskIndexException();
        }
    }

    public static void nextCommand() {
        try {
            String command = scanner.nextLine();
            String[] parts = command.split(" ", 2);
            String action = parts[0];

            switch (action) {
                case "bye":
                    exit();
                    break;
                case "list":
                    listTasks();
                    break;
                case "mark":
                    if (parts.length > 1) {
                        int taskNumber = Integer.parseInt(parts[1]);
                        markTask(taskNumber);
                    } else {
                        output("\tPlease specify the task number to mark.");
                    }
                    break;
                case "unmark":
                    if (parts.length > 1) {
                        int taskNumber = Integer.parseInt(parts[1]);
                        unmarkTask(taskNumber);
                    } else {
                        output("\tPlease specify the task number to unmark.");
                    }
                    break;
                case "delete":
                    if (parts.length > 1) {
                        int taskNumber = Integer.parseInt(parts[1]);
                        deleteTask(taskNumber);
                    } else {
                        output("\tPlease specify the task number to delete.");
                    }
                    break;
                case "todo":
                    if (parts.length > 1) {
                        addTask(new TodoTask(parts[1]));
                    } else {
                        throw new EmptyDescriptionException();
                    }
                    break;
                case "deadline":
                    if (parts.length > 1) {
                        String[] deadlineParts = parts[1].split(" /by ", 2);
                        if (deadlineParts.length > 1) {
                            try {
                                LocalDate by = LocalDate.parse(deadlineParts[1], DATE_FORMATTER);
                                addTask(new DeadlineTask(deadlineParts[0], by));
                            } catch (DateTimeParseException e) {
                                output("\tPlease enter the deadline in the correct format (yyyy-MM-dd).");
                            }
                        } else {
                            output("\tPlease specify the deadline in the format: description /by yyyy-MM-dd");
                        }
                    } else {
                        throw new EmptyDescriptionException();
                    }
                    break;
                case "event":
                    if (parts.length > 1) {
                        String[] eventParts = parts[1].split(" /from | /to ", 3);
                        if (eventParts.length == 3) {
                            try {
                                LocalDateTime from = LocalDateTime.parse(eventParts[1], DATE_TIME_FORMATTER);
                                LocalDateTime to = LocalDateTime.parse(eventParts[2], DATE_TIME_FORMATTER);
                                addTask(new EventTask(eventParts[0], from, to));
                            } catch (DateTimeParseException e) {
                                output("\tPlease enter the event times in the correct format (yyyy-MM-dd HH:mm).");
                            }
                        } else {
                            output("\tPlease specify the event in the format: description /from yyyy-MM-dd HH:mm /to yyyy-MM-dd HH:mm");
                        }
                    } else {
                        throw new EmptyDescriptionException();
                    }
                    break;
                default:
                    throw new InvalidCommandException();
            }
        } catch (ChatsyException e) {
            output("\tOOPS!!! " + e.getMessage());
        } catch (NumberFormatException e) {
            output("\tPlease enter a valid number for task selection.");
        } catch (Exception e) {
            output("\tAn unexpected error occurred: " + e.getMessage());
        } finally {
            if (isRunning) {
                nextCommand();
            }
        }
    }

    private static void saveTasks() {
        try {
            storage.saveTasks(tasks);
        } catch (LocalFileException e) {
            output("\tFailed to save tasks: " + e.getMessage());
        }
    }
}
