import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hien {
    private ArrayList<Task> tasks;
    private static final String DATA_FILE_PATH = "data/tasks.txt";
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter OUTPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy, hh:mm a");

    // Constructor
    public Hien() {
        this.tasks = new ArrayList<>();
        loadTasks();
    }

    public static void main(String[] args) {
        Hien hien = new Hien();
        hien.runHien();
    }

    public void runHien() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Hien");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = scanner.nextLine().trim();

            System.out.println("____________________________________________________________");

            try {
                if (input.equalsIgnoreCase("bye")) {
                    System.out.println(" Bye. Hope to see you again soon!");
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    listTasks();
                } else if (input.startsWith("mark")) {
                    markTask(input, true);
                } else if (input.startsWith("unmark")) {
                    markTask(input, false);
                } else if (input.startsWith("todo")) {
                    addTodo(input);
                } else if (input.startsWith("deadline")) {
                    addDeadline(input);
                } else if (input.startsWith("event")) {
                    addEvent(input);
                } else if (input.startsWith("delete")) {
                    deleteTask(input);
                } else {
                    throw new HienException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (HienException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("____________________________________________________________");
        }

        scanner.close();
    }

    private void addTodo(String input) throws HienException {
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            throw new HienException("☹ OOPS!!! The description of todo cannot be empty");
        }
        System.out.printf("description: %s", description);
        Todo todo = new Todo(description);
        tasks.add(todo);
        saveTasks();
        System.out.println(" Got it. I've added this task:");
        System.out.println("   " + todo);
        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");

    }

    private void addDeadline(String input) throws HienException {
        String[] parts = input.substring(8).split(" /by ");
        if (parts.length == 2) {
            try {
                LocalDateTime dateTime = LocalDateTime.parse(parts[1].trim(), INPUT_DATE_FORMAT);
                Deadline deadline = new Deadline(parts[0].trim(), dateTime);
                tasks.add(deadline);
                saveTasks();
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + deadline);
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            } catch (DateTimeParseException e) {
                throw new HienException("☹ OOPS!!! The date format is incorrect. Please use: yyyy-MM-dd HHmm");
            }
        } else {
            throw new HienException(" ☹ OOPS!!! The deadline format is incorrect. Please use: deadline <description> /by <yyyy-MM-dd HHmm>");
        }
    }

    private void addEvent(String input) throws HienException {
        String[] parts = input.substring(5).split(" /from | /to ");
        if (parts.length == 3) {
            try {
                LocalDateTime startTime = LocalDateTime.parse(parts[1].trim(), INPUT_DATE_FORMAT);
                LocalDateTime endTime = LocalDateTime.parse(parts[2].trim(), INPUT_DATE_FORMAT);
                Event event = new Event(parts[0].trim(), startTime, endTime);
                tasks.add(event);
                saveTasks();
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + event);
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            } catch (DateTimeParseException e) {
                throw new HienException("☹ OOPS!!! The date format is incorrect. Please use: yyyy-MM-dd HHmm");
            }
        } else {
            throw new HienException("☹ OOPS!!! The event format is incorrect. Please use: event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>");
        }
    }

    private void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println(" There are no tasks in your list.");
        } else {
            System.out.println(" Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(" " + (i + 1) + "." + tasks.get(i));
            }
        }
    }
    private boolean isValidIndex(String index) throws HienException {
        if (index.isEmpty() || !index.matches("-?(0|[1-9]\\d*)")) {
            throw new HienException("☹ OOPS!!! The index of the task is either empty or not integer. Please try again!");
        }
        int i = Integer.parseInt(index);
        if (i < 1) {
            throw new HienException("☹ OOPS!!! Task index cannot be less than 1");
        } else if (i > tasks.size()) {
            throw new HienException("☹ OOPS!!! Task index cannot be greater than current number of tasks. You currently only have " + tasks.size() + " tasks.");
        } else {
            return true;
        }
    }
    private void deleteTask(String input) throws HienException {
        String index = input.substring(6).trim();
        if (isValidIndex(index)) {
            int i = Integer.parseInt(index);
            Task removedTask = tasks.get(i - 1);
            tasks.remove(i - 1);
            saveTasks();
            System.out.println(" Got it. I've deleted this task:");
            System.out.println("   " + removedTask);
            System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
        }
    }


    private void markTask(String input, boolean isDone) throws HienException {
        String index = isDone ? input.substring(4).trim() : input.substring(6).trim();
        boolean isValidIndex = isValidIndex(index);
        if (isValidIndex) {
            int i = Integer.parseInt(index);
            Task task = tasks.get(i - 1);
            if (isDone) {
                task.markAsDone();
                System.out.println(" Nice! I've marked this task as done:");
            } else {
                task.markAsUndone();
                System.out.println(" OK, I've marked this task as not done yet:");
            }
            saveTasks();
            System.out.println("   " + task);
        }
    }
    private void loadTasks() {
        try {
            Files.createDirectories(Paths.get("data"));
            File file = new File(DATA_FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
                return;
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" \\| ");
                if (parts.length >= 3) {
                    Task task;
                    switch (parts[0]) {
                        case "T":
                            task = new Todo(parts[2]);
                            break;
                        case "D":
                            LocalDateTime by = LocalDateTime.parse(parts[3].trim(), INPUT_DATE_FORMAT);
                            task = new Deadline(parts[2], by);
                            break;
                        case "E":
                            LocalDateTime from = LocalDateTime.parse(parts[3].trim(), INPUT_DATE_FORMAT);
                            LocalDateTime to = LocalDateTime.parse(parts[4].trim(), INPUT_DATE_FORMAT);
                            task = new Event(parts[2], from, to);
                            break;
                        default:
                            continue;
                    }
                    if (parts[1].equals("1")) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks: " + e.getMessage());
        }
    }

    private void saveTasks() {
        try {
            Files.createDirectories(Paths.get("data"));
            BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE_PATH));
            for (Task task : tasks) {
                String line;
                if (task instanceof Todo) {
                    line = "T | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription();
                } else if (task instanceof Deadline) {
                    Deadline deadline = (Deadline) task;
                    line = "D | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() + " | " + deadline.getBy().format(INPUT_DATE_FORMAT);
                } else if (task instanceof Event) {
                    Event event = (Event) task;
                    line = "E | " + (task.isDone() ? "1" : "0") + " | " + task.getDescription() + " | " + event.getFrom().format(INPUT_DATE_FORMAT) + " | " + event.getTo().format(INPUT_DATE_FORMAT);
                } else {
                    continue;
                }
                writer.write(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }
}



