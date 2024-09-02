import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class Sammy {
    private static final String FILE_PATH = "./data/Sammy.txt";

    public static void main(String[] args) {
        String line = "____________________________________________________________";
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = loadTasksFromFile();

        System.out.println(line);
        System.out.println(" Hello! I'm Sammy.");
        System.out.println(" What can I do for you?");
        System.out.println(line);

        while (true) {
            try {
                String input = scanner.nextLine();

                if (input.equals("bye")) {
                    System.out.println(line);
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println(line);
                    break;
                } else if (input.equals("list")) {
                    System.out.println(line);
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + "." + tasks.get(i));
                    }
                    System.out.println(line);
                } else if (input.startsWith("mark ")) {
                    int index = Integer.parseInt(input.substring(5).trim()) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markAsDone();
                        System.out.println(line);
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println(" " + taskToString(tasks.get(index)));
                        System.out.println(line);
                        saveTasksToFile(tasks);
                    } else {
                        throw new InvalidTaskNumberException();
                    }
                } else if (input.startsWith("unmark ")) {
                    int index = Integer.parseInt(input.substring(7).trim()) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markAsNotDone();
                        System.out.println(line);
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println(" " + taskToString(tasks.get(index)));
                        System.out.println(line);
                        saveTasksToFile(tasks);
                    } else {
                        throw new InvalidTaskNumberException();
                    }
                } else if (input.startsWith("delete ")) {
                    int index = Integer.parseInt(input.substring(7).trim()) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        Task removedTask = tasks.remove(index);
                        System.out.println(line);
                        System.out.println(" Noted. I've removed this task:");
                        System.out.println(" " + taskToString(removedTask));
                        System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                        System.out.println(line);
                        saveTasksToFile(tasks);
                    } else {
                        throw new InvalidTaskNumberException();
                    }
                } else if (input.startsWith("todo ")) {
                    String description = input.substring(5).trim();
                    if (description.isEmpty()) {
                        throw new InvalidDescriptionException();
                    }
                    tasks.add(new Todo(description));
                    System.out.println(line);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println(" " + taskToString(tasks.get(tasks.size() - 1)));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                    saveTasksToFile(tasks);
                } else if (input.startsWith("deadline ")) {
                    int byIndex = input.indexOf("/by ");
                    if (byIndex == -1) {
                        throw new InvalidCommandException();
                    }
                    String description = input.substring(9, byIndex).trim();
                    String by = input.substring(byIndex + 4).trim();
                    if (description.isEmpty() || by.isEmpty()) {
                        throw new InvalidDescriptionException();
                    }
                    tasks.add(new Deadline(description, by));
                    System.out.println(line);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println(" " + taskToString(tasks.get(tasks.size() - 1)));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                    saveTasksToFile(tasks);
                } else if (input.startsWith("event ")) {
                    int fromIndex = input.indexOf("/from ");
                    int toIndex = input.indexOf("/to ");
                    if (fromIndex == -1 || toIndex == -1) {
                        throw new InvalidCommandException();
                    }
                    String description = input.substring(6, fromIndex).trim();
                    String startTime = input.substring(fromIndex + 6, toIndex).trim();
                    String endTime = input.substring(toIndex + 4).trim();
                    if (description.isEmpty() || startTime.isEmpty() || endTime.isEmpty()) {
                        throw new InvalidDescriptionException();
                    }
                    tasks.add(new Event(description, startTime, endTime));
                    System.out.println(line);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println(" " + taskToString(tasks.get(tasks.size() - 1)));
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                    saveTasksToFile(tasks);
                } else {
                    throw new InvalidCommandException();
                }
            } catch (SammyException e) {
                System.out.println(line);
                System.out.println(" OOPS!!! " + e.getMessage());
                System.out.println(line);
            }
        }
    }

    private static void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir();
            }

            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
            for (Task task : tasks) {
                writer.write(taskToString(task));
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    private static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
                String line;
                while ((line = reader.readLine()) != null) {
                    Task task = stringToTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    private static String taskToString(Task task) {
        String taskType = "";
        String taskDetails = "";

        if (task instanceof Todo) {
            taskType = "T";
            taskDetails = task.description;
        } else if (task instanceof Deadline) {
            taskType = "D";
            taskDetails = task.description + " | " +
                    ((Deadline) task).deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } else if (task instanceof Event) {
            taskType = "E";
            taskDetails = task.description + " | "
                    + ((Event) task).startTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")) + " | "
                    + ((Event) task).endTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        }

        return taskType + " | " + (task.isDone ? "1" : "0") + " | " + taskDetails;
    }

    private static Task stringToTask(String line) {

        String[] parts = line.split(" \\| ");

            String taskType = parts[0];
            boolean isDone = parts[1].trim().equals("1");
            String description = parts[2].trim();

            Task task = null;

            switch (taskType) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    task = new Deadline(description, parts[3].trim());
                    break;
                case "E":
                    task = new Event(description, parts[3].trim(), parts[4].trim());
                    break;
                default:
                    System.out.println("Unknown task type: " + taskType);
                    return null;
            }

            if (task != null && isDone) {
                task.markAsDone();
            }
            return task;
    }
}

class SammyException extends Exception {
    public SammyException(String message) {
        super(message);
    }
}

class InvalidDescriptionException extends SammyException {
    public InvalidDescriptionException() {
        super("The description is invalid. It cannot be empty.");
    }
}

class InvalidCommandException extends SammyException {
    public InvalidCommandException() {
        super("I'm sorry, your reply is not in my dictionary.");
    }
}

class InvalidTaskNumberException extends SammyException {
    public InvalidTaskNumberException() {
        super("Wrong task number.");
    }
}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    public LocalDateTime deadline;

//    public Deadline(String description, String deadline) {
//        super(description);
//        //DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
//        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
//        this.deadline = LocalDateTime.parse(deadline, inputFormat);
//    }
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = parseDate(deadline);
    }
    private LocalDateTime parseDate(String date) {
        DateTimeFormatter[] formatters = {
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"),        // 2019-12-02 1800
                DateTimeFormatter.ofPattern("d/M/yyyy HHmm"),          // 2/12/2019 1800
                DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"),        // 02-12-2019 1800
                DateTimeFormatter.ofPattern("MMM d yyyy HHmm"),        // Dec 2 2019 1800
                DateTimeFormatter.ofPattern("d MMM yyyy HHmm"),        // 2 Dec 2019 1800
                DateTimeFormatter.ofPattern("MMMM d, yyyy HHmm"),      // December 2, 2019 1800
                DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm"),        // 2019/12/02 1800
                DateTimeFormatter.ofPattern("M/d/yyyy HHmm"),          // 12/2/2019 1800
                DateTimeFormatter.ofPattern("MM-dd-yyyy HHmm"),        // 12-02-2019 1800
        };

        for (DateTimeFormatter formatter : formatters) {
            try {
                return LocalDateTime.parse(date, formatter);
            } catch (DateTimeParseException e) {
              // let the loop continue
            }
        }

        throw new DateTimeParseException("Unrecognized date format: " + date, date, 0);
    }
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) + ")";
    }
}

class Event extends Task {
    public LocalDateTime startTime;
    public LocalDateTime endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        this.startTime = LocalDateTime.parse(startTime, inputFormat);
        this.endTime = LocalDateTime.parse(endTime, inputFormat);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a"))
                + " to: " + endTime.format(DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a")) + ")";
    }
}


