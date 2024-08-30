import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.Todo;
import Exception.MissingDateException;
import Exception.EmptyDescriptionException;
import enums.TaskType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Chatgpt {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) throws EmptyDescriptionException {

        File file = new File("./data/duke.txt");
        try {
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] split = line.split(" \\| ");
                switch (split[0]) {
                    case "TODO":
                        if (split[1] == "1") {
                            Todo todo = new Todo(split[2]);
                            todo.markDone();
                            tasks.add(todo);
                        } else {
                            Todo todo = new Todo(split[2]);
                            todo.markDone();
                        };
                        break;

                    case "DEADLINE":
                        if (split[1] == "1") {
                            Deadline deadline = new Deadline(split[2], split[3]);
                            deadline.markDone();
                            tasks.add(deadline);
                        } else {
                            Deadline deadline = new Deadline(split[2], split[3]);
                            tasks.add(deadline);
                        }
                        break;
                    case "EVENT":
                        if (split[1] == "1") {
                            Event event = new Event(split[2], split[3], split[4]);
                            event.markDone();
                            tasks.add(event);
                        } else {
                            Event event = new Event(split[2], split[3], split[4]);
                            tasks.add(event);
                        }
                        break;
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            new File("./data").mkdirs();
            try {
                file.createNewFile();
            } catch (Exception ex) {
                System.out.println("Unable to create data file");
            }
        }

        System.out.println("Hello! I'm chatbot lisWhat can I do for you?");

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    saveTasksToFile();
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (input.startsWith("delete")) {
                    deleteTask(Integer.parseInt(input.substring(7)) - 1);
                } else if (input.startsWith("todo")) {
                    addTodoTask(input);
                } else if (input.startsWith("deadline")) {
                    addDeadlineTask(input);
                } else if (input.startsWith("event")) {
                    addEventTask(input);
                } else if (input.equals("list")) {
                    listTasks();
                } else if (input.startsWith("mark")) {
                    markTask(Integer.parseInt(input.substring(5)) - 1);
                } else if (input.startsWith("unmark")) {
                    unmarkTask(Integer.parseInt(input.substring(7)) - 1);
                } else if (input.equalsIgnoreCase("exit")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else {
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }

            } catch (EmptyDescriptionException | MissingDateException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }

    private static void deleteTask(int id) {
        if (id < 0 || id >= tasks.size()) {
            System.out.println("Task ID is out of range!");
            return;
        }

        Task removedTask = tasks.remove(id); // Removes the task and captures it for confirmation message
        System.out.println("Noted. I've removed this task:\n  " + removedTask);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");

        saveTasksToFile(); // Update the file after deleting the task
    }

    private static void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            int index = i+1;
            System.out.println(index + " , " + tasks.get(i).toString());
        }
    }

    private static void markTask(int id) {
        tasks.get(id).markDone();
        System.out.println("Nice! I've marked this task as done:");
    }
    private static void unmarkTask(int id) {
        tasks.get(id).unmarkDone();
        System.out.println("OK, I've marked this task as not done yet:");
    }

    private static void addTodoTask(String input) throws EmptyDescriptionException {
        if (input.trim().length() <= 5) {
            throw new EmptyDescriptionException("OPS!!! The description of a todo cannot be empty.");
        }
        String description = input.substring(5).trim();
        tasks.add(new Todo(description));
        saveTasksToFile();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + new Todo(description));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addMarkedTodoTask(String input) throws EmptyDescriptionException {
        if (input.trim().length() <= 5) {
            throw new EmptyDescriptionException("OPS!!! The description of a todo cannot be empty.");
        }
        String description = input.substring(5).trim();
        Todo todo = new Todo(description);
        todo.markDone();
        tasks.add(todo);

        System.out.println("Got it. I've added this task:");
        System.out.println("  " + new Todo(description));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void addDeadlineTask(String input) throws EmptyDescriptionException, MissingDateException {
        String[] parts = input.split(" by ");
        if (parts.length < 2) {
            throw new MissingDateException("OPS!!! The date of a deadline cannot be empty.");
        }
        String description = parts[0].substring(9).trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("OPS!!! The description of a deadline cannot be empty");
        }
        try {
            String by = parts[1].trim();
            tasks.add(new Deadline(description, by));
            saveTasksToFile();
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks.get(tasks.size() - 1));
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Please use yyyy-MM-dd HHmm.");
        }
    }

    private static void addEventTask(String input) throws EmptyDescriptionException, MissingDateException {
        String[] parts = input.split(" from | to ");
        if (parts.length < 3) {
            throw new MissingDateException("OPS!!! The start or end time of an event cannot be missing.");
        }
        String description = parts[0].substring(6).trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("OPS!!! The description of an event cannot be empty.");
        }
        String start = parts[1].trim();
        String end = parts[2].trim();
        tasks.add(new Event(description, start, end));
        saveTasksToFile();
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + new Event(description, start, end));
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static void saveTasksToFile() {
        File file = new File("./data/duke.txt");  // Create a File object to handle file operations
        try {
            // Print the absolute path of the file
            System.out.println("Saving tasks to file: " + file.getAbsolutePath());

            PrintWriter writer = new PrintWriter(file);
            for (Task task : tasks) {
                writer.println(task.toFileFormat());
            }
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error writing to file.");
        }
    }
}
