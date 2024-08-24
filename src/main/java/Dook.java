import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

import java.io.FileWriter;
import java.io.File;
import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Dook {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Task> tasks = new ArrayList<>(100);
    private static final String separator = "____________________________________________________________";
    private static final String greeting = "Hello! I'm Dook\nWhat can I do for you?\n" + separator;
    private static final String exit = "Bye. Hope to see you again soon!\n" + separator;

    private static File savedTasks = new File("data/Tasks.txt");

    public static void start() throws IOException {
        File directory = new File("data");
        if (!directory.exists()) {
            directory.mkdirs();
        }
        if (!savedTasks.exists()) {
            savedTasks.createNewFile();
        } else {
            loadFromFile();
        }
        System.out.println(separator);
        System.out.println(greeting);
    }

    public static void end() {
        System.out.println(separator);
        System.out.println(exit);
    }

    public static void list() throws FileNotFoundException {
        System.out.println(separator);
        if (tasks.size() == 0) {
            System.out.println("No tasks");
        }
        for (int i = 1; i <= tasks.size(); i++) {
            System.out.println(i + ". " + tasks.get(i - 1));
        }
        System.out.println(separator);
    }

    public static void markDone(int taskNumber) throws DookException, IOException {
        if (taskNumber >= tasks.size()) {
            throw new DookException("You don't have that many tasks");
        }
        tasks.get(taskNumber).markAsDone();
        saveToFile();
        System.out.println(separator);
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(tasks.get(taskNumber));
        System.out.println(separator);
    }

    public static void unmark(int taskNumber) throws DookException, IOException {
        if (taskNumber >= tasks.size()) {
            throw new DookException("You don't have that many tasks");
        }
        tasks.get(taskNumber).unmark();
        saveToFile();
        System.out.println(separator);
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(tasks.get(taskNumber));
        System.out.println(separator);
    }

    public static void createTodo(String description) throws DookException, IOException {
        if (description.isEmpty()) {
            throw new DookException("Need a description for your todo");
        }
        Task todo = new Todo(description);
        tasks.add(todo);
        saveToFile();
        System.out.println(separator);
        System.out.println("Got it. I've added this task:");
        System.out.println(todo);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        System.out.println(separator);
    }

    public static void createDeadline(String description, String by) throws DookException, IOException, DateTimeParseException {
        if (description.isEmpty()) {
            throw new DookException("Need a description for your deadline");
        } else if (by.isEmpty()) {
            throw new DookException("Need a due date for your deadline");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Task deadline = new Deadline(description, LocalDateTime.parse(by, formatter));
        tasks.add(deadline);
        saveToFile();
        System.out.println(separator);
        System.out.println("Got it. I've added this task:");
        System.out.println(deadline);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        System.out.println(separator);
    }

    public static void createEvent(String description, String start, String end) throws DookException, IOException, DateTimeParseException {
        if (description.isEmpty()) {
            throw new DookException("Need a description for your event");
        } else if (start.isEmpty() && end.isEmpty()) {
            throw new DookException("Need a timeline for your event");
        } else if (start.isEmpty()) {
            throw new DookException("Need a start time for your event");
        } else if (end.isEmpty()) {
            throw new DookException("Need an end time for your event");
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        Task event = new Event(description, LocalDateTime.parse(start, formatter), LocalDateTime.parse(end, formatter));
        tasks.add(event);
        saveToFile();
        System.out.println(separator);
        System.out.println("Got it. I've added this task:");
        System.out.println(event);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        System.out.println(separator);
    }

    public static void delete(int taskNumber) throws DookException, IOException {
        if (taskNumber > tasks.size()) {
            throw new DookException("You don't have that many tasks");
        }

        Task removed = tasks.remove(taskNumber - 1);
        saveToFile();
        System.out.println(separator);
        System.out.println("Noted. I've removed this task:");
        System.out.println(removed);
        System.out.println("Now you have " + tasks.size() + " tasks in the list");
        System.out.println(separator);
    }

    public static void saveToFile() throws IOException {
        FileWriter writer = new FileWriter(savedTasks);
        for (Task task : tasks) {
            writer.write(task.fileFormatted() + System.lineSeparator());
        }
        writer.close();
    }

    public static void loadFromFile() throws FileNotFoundException {
        Scanner reader = new Scanner(savedTasks);
        while (reader.hasNextLine()) {
            String line = reader.nextLine();
            String[] components = line.split(" \\| ");
            String taskType = components[0];
            boolean isDone = components[1].equals("1");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            Task task;
            switch (taskType) {
            case "T":
                task = new Todo(components[2]);
                break;
            case "D" :
                task = new Deadline(components[2], LocalDateTime.parse(components[3], formatter));
                break;
            case "E" :
                task = new Event(components[2], LocalDateTime.parse(components[3], formatter), LocalDateTime.parse(components[4], formatter));
                break;
            default:
                continue;
            }
            if (isDone) {
                task.markAsDone();
            }

            tasks.add(task);
        }
        reader.close();
    }

    public static void main(String[] args) {

        try {
            start();
        } catch (FileNotFoundException e) {
            System.out.println("File Not Found");
        } catch (IOException e) {
            System.out.println("Invalid I/O");
        }


        String response;

        while (true) {
            response = scanner.nextLine();
            try {
                if (response.equals("bye")) {
                    end();
                    break;
                } else if (response.equals("list")) {
                    list();
                } else if (response.matches("^mark \\d+$")) {
                    int number = Integer.parseInt(response.trim().split(" ")[1]) - 1;
                    markDone(number);
                } else if (response.matches("^unmark \\d+$")) {
                    int number = Integer.parseInt(response.trim().split(" ")[1]) - 1;
                    unmark(number);
                } else if (response.startsWith("todo")) {
                    String description = response.substring(4).trim();
                    createTodo(description);

                } else if (response.startsWith("deadline")) {
                    String[] deadlineInput = response.substring(8).split(" /by ", 2);
                    if (deadlineInput.length == 2) {
                        String description = deadlineInput[0].trim();
                        String by = deadlineInput[1].trim();
                        createDeadline(description, by);
                        continue;
                    }
                    throw new DookException("Invalid command, check your formatting");

                } else if (response.startsWith("event")) {
                    String[] eventInput = response.substring(5).split(" /from ", 2);
                    if (eventInput.length == 2) {
                        String description = eventInput[0].trim();
                        String[] timings = eventInput[1].split(" /to ", 2);
                        if (timings.length == 2) {
                            String start = timings[0].trim();
                            String end = timings[1].trim();
                            createEvent(description, start, end);
                            continue;
                        }
                    }
                    throw new DookException("Invalid command, check your formatting");

                } else if (response.matches("^delete \\d+$")) {
                    int number = Integer.parseInt(response.trim().split(" ")[1]);
                    delete(number);
                } else {
                    throw new DookException("Invalid command");
                }

            } catch (FileNotFoundException e) {
                System.out.println(separator);
                System.out.println(e.getMessage());
                System.out.println(separator);
            } catch (IOException e) {
                System.out.println(separator);
                System.out.println(e.getMessage());
                System.out.println(separator);
            } catch (DookException e) {
                System.out.println(separator);
                System.out.println(e.getMessage());
                System.out.println(separator);
            } catch (DateTimeParseException e) {
                System.out.println(separator);
                System.out.println(e.getMessage());
                System.out.println("Enter your date in yyyy-MM-dd HH:mm format");
                System.out.println(separator);
            }

        }
    }
}
