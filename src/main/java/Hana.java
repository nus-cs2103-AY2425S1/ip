import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Scanner;
import java.util.ArrayList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Hana {
    private static final int MAX_TASKS = 100;
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static final String LINE = "___________________________________________";
	private static final String FILE_PATH = "./data/hana.txt";
    private static final Path FILE = Paths.get(FILE_PATH);
    private static final Path DIR = Paths.get("./data");


    public static void main(String[] args) throws HanaException {
        load();

        Scanner scanner = new Scanner(System.in);
        String input;

        //greet
        System.out.println(LINE);
		String name = "Hana";
		System.out.println(" Hello! I'm " + name);
        System.out.println(" What can I do for you?");
        System.out.println(LINE);

        while (true) {
            input = scanner.nextLine();
            try {
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println(LINE);
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println(LINE);
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    listTasks();
                } else if (input.startsWith("mark")) {
                    handleMark(input);
                } else if (input.startsWith("unmark")) {
                    handleUnmark(input);
                } else if (input.startsWith("todo")) {
                    handleTodo(input);
                } else if (input.startsWith("deadline")) {
                    handleDeadline(input);
                } else if (input.startsWith("event")) {
                    handleEvent(input);
                } else if (input.startsWith("delete")) {
                    handleDelete(input);
                } else {
                    throw new HanaException("""
                            I'm sorry, I don't recognize that command. Here are some examples of what you can do:
                            1. List all tasks: list
                            2. Mark a task as done: mark [task number]
                            3. Unmark a task: unmark [task number]
                            4. Add a todo: todo [description]
                            5. Add a deadline: deadline [description] /by [d/M/yyyy HHmm]
                            6. Add an event: event [description] /from [d/M/yyyy HHmm] /to [d/M/yyyy HHmm]
                            7. Delete a task: delete [task number]""");
                }
            } catch (HanaException e) {
                System.out.println(LINE);
                System.out.println(e.getMessage());
                System.out.println(LINE);
            } catch (Exception e) {
                System.out.println(LINE);
                System.out.println("Oops! Something went wrong. Please try again.");
                System.out.println(LINE);
            }
        }
        scanner.close();
    }

    private static void handleMark(String input) throws HanaException {
        String[] parts = input.split(" ", 2);
        if (tasks.isEmpty()) {
            throw new HanaException("No tasks added yet. Add a task first!");
        }
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new HanaException("Task number cannot be empty.");
        }
        int taskNumber = Integer.parseInt(parts[1].trim());
        markTask(taskNumber, true);
    }

    private static void handleUnmark(String input) throws HanaException {
        String[] parts = input.split(" ", 2);
        if (tasks.isEmpty()) {
            throw new HanaException("No tasks added yet. Add a task first!");
        }
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new HanaException("Task number cannot be empty.");
        }
        int taskNumber = Integer.parseInt(parts[1].trim());
        markTask(taskNumber, false);
    }

    private static void handleTodo(String input) throws HanaException {
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            throw new HanaException("ToDo task must have a description.");
        }
        addTask(new ToDo(description));
    }

    private static void handleDeadline(String input) throws HanaException {
        try {
            String[] parts = input.substring(8).split(" /by ");
            if (parts.length < 2) {
                throw new HanaException("Deadline task must have a description and a due date.");
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime deadline = LocalDateTime.parse(parts[1].trim(), formatter);
            addTask(new Deadline(parts[0].trim(), deadline));
        } catch (DateTimeParseException e) {
            throw new HanaException("Please provide a valid deadline command in the format: " +
                    "deadline [description] /by [d/M/yyyy HHmm]");
        }
    }

    private static void handleEvent(String input) throws HanaException {
        try {
            String[] parts = input.substring(5).split(" /from | /to ");
            if (parts.length < 3) {
                throw new HanaException("Event task must have a description, start time, and end time.");
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            LocalDateTime from = LocalDateTime.parse(parts[1].trim(), formatter);
            LocalDateTime to = LocalDateTime.parse(parts[2].trim(), formatter);
            addTask(new Event(parts[0].trim(), from, to));
        } catch (DateTimeParseException e) {
            throw new HanaException("Please provide a valid deadline command in the format: " +
                    "event [description] /from [d/M/yyyy HHmm] /to [d/M/yyyy HHmm]");
        }
    }

    private static void handleDelete(String input) throws HanaException {
        String[] parts = input.split(" ", 2);
        if (tasks.isEmpty()) {
            throw new HanaException("No tasks added yet. Add a task first!");
        }
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new HanaException("Task number cannot be empty.");
        }
        int taskNumber = Integer.parseInt(parts[1].trim());
        deleteTask(taskNumber);
    }

    private static void addTask(Task task) throws HanaException {
        if (tasks.size() < MAX_TASKS) {
            tasks.add(task);
            System.out.println(LINE);
            System.out.println("Got it. I've added this task:");
            System.out.println("    " + task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(LINE);
            saveTask();
        } else {
            throw new HanaException("Task list is full!");
        }
    }

    private static void listTasks() throws HanaException {
        if (tasks.isEmpty()) {
            throw new HanaException("No tasks added yet.");
        } else {
            System.out.println(LINE);
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
            System.out.println(LINE);
        }
    }

    private static void markTask(int taskNumber, boolean isDone) throws HanaException {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task task = tasks.get(taskNumber - 1);
            task.setDone(isDone);
            System.out.println(LINE);
            if (isDone) {
                System.out.println("Nice! I've marked this task as done:");
            } else {
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println("  " + task);
            System.out.println(LINE);
            saveTask();
        } else {
            throw new HanaException("Invalid task number! Task number must be between 1 and " + tasks.size() + ".");
        }
    }

    private static void deleteTask(int taskNumber) throws HanaException {
        if (taskNumber > 0 && taskNumber <= tasks.size()) {
            Task removedTask = tasks.remove(taskNumber - 1);
            System.out.println(LINE);
            System.out.println("Noted. I've removed this task:");
            System.out.println("    " + removedTask);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            System.out.println(LINE);
            saveTask();
        } else {
            throw new HanaException("Invalid task number! Task number must be between 1 and " + tasks.size() + ".");
        }
    }

    private static void load() throws HanaException {
        if(!Files.exists(DIR)) {
            try {
                Files.createDirectories(DIR);
            } catch (IOException e) {
                throw new HanaException("Failed to create the directory for saving task");
            }
        }

        if(!Files.exists(FILE)) {
            try {
                Files.createFile(FILE);
            } catch (IOException e) {
                throw new HanaException("Failed to create the directory for saving task");
            }
        }

        try (BufferedReader br = Files.newBufferedReader(FILE)) {
            String line = br.readLine();
            while (line != null) {
                String[] parts = line.split(" \\| ");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                Task task;
                switch (parts[0]) {
                case "T":
                    task = new ToDo(parts[2]);
                    break;
                case "D":
                    task = new Deadline(parts[2], LocalDateTime.parse(parts[3], formatter));
                    break;
                case "E":
                    task = new Event(parts[2], LocalDateTime.parse(parts[3], formatter), LocalDateTime.parse(parts[3], formatter));
                    break;
                default:
                    System.out.println("Failed to read saved task. File may be corrupted. Skipping line");
                    continue;
                    // Fallthrough
                }
                task.setDone(parts[1].equals("1"));
                tasks.add(task);
                line = br.readLine();
            }
        } catch (IOException | DateTimeParseException e) {
            System.out.println("Failed to read saved tasks. File may be corrupted.");
        }
    }

    private static void saveTask() throws HanaException {
        try (BufferedWriter bw = Files.newBufferedWriter(FILE)) {
            for (Task task : tasks) {
                bw.write(task.fileString());
                bw.newLine();
            }
        } catch (IOException e) {
            throw new HanaException("Failed to save tasks.");
        }
    }
}