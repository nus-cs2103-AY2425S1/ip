package Bob;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

class Task {

    private String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

class Deadline extends Task {

    private LocalDateTime by;

    private static String dateTimeToString(LocalDateTime dateTime) {
        // Correct format: "MMM dd yyyy" e.g., (Oct 15 2019)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH);
        return dateTime.format(formatter);
    }

    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dateTimeToString(by) + ")";
    }
}

class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    private static String dateTimeToString(LocalDateTime dateTime) {
        // Correct format: "MMM dd yyyy" e.g., (Oct 15 2019)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm", Locale.ENGLISH);
        return dateTime.format(formatter);
    }

    public Event(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + dateTimeToString(from) + " to: " + dateTimeToString(to) + ")";
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

class BobException extends Exception {

    public BobException(String message) {
        super(message);
    }
}

class Ui {
    public static void dialogue(String input) {
        System.out.println("___________________________________\n");
        System.out.println(input);
        System.out.println("___________________________________\n");
    }

    public static void runDialogue(Scanner scanner, List<Task> tasks) {
        Ui.dialogue("Hello! I'm Bob\nWhat can I do for you?");

        while (true) {
            try {
                String userInput = scanner.nextLine().trim();

                if (userInput.equalsIgnoreCase("bye")) {
                    Ui.dialogue("Bye. Hope to see you again soon!");
                    break;
                } else if (userInput.equalsIgnoreCase("list")) {
                    Parser.processListCommand(tasks);
                } else if (userInput.startsWith("mark") || userInput.startsWith("unmark") || userInput.startsWith("delete")) {
                    Parser.processTaskModificationCommands(userInput, tasks);
                } else if (userInput.startsWith("todo") || userInput.startsWith("deadline") || userInput.startsWith("event")) {
                    Parser.processTaskCreationCommands(userInput, tasks);
                } else {
                    throw new BobException("I'm sorry, but I don't know what that means :(");
                }
            } catch (BobException | IOException e) {
                Ui.dialogue(e.getMessage());
            }
        }
        scanner.close();
    }
}

class Parser {
    public static LocalDateTime parseDateTime(String input) throws BobException {
        try {
            // Correct format: "dd/MM/yyyy HHmm" (e.g., "02/12/2019 1800")
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm", Locale.ENGLISH);
            return LocalDateTime.parse(input, formatter);
        } catch (DateTimeParseException e) {
            throw new BobException("The date and time format is incorrect: " + e.getMessage());
        }
    }

    
    public static void processListCommand(List<Task> tasks) {
        String out = "Here are the tasks in your list: \n";
        for (int i = 0; i < tasks.size(); i++) {
            out += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        Ui.dialogue(out);
    }

    public static void processTaskModificationCommands(String userInput, List<Task> tasks) throws BobException, IOException {
        String[] words = userInput.split(" ");
        int index = Integer.parseInt(words[1]) - 1;

        if (index < 0 || index >= tasks.size()) {
            throw new BobException("The task index you provided is out of range.");
        }

        if (userInput.startsWith("mark")) {
            tasks.get(index).markAsDone();
            Ui.dialogue("Nice! I've marked this task as done: \n" + tasks.get(index));
        } else if (userInput.startsWith("unmark")) {
            tasks.get(index).unmarkAsDone();
            Ui.dialogue("OK, I've marked this task as not done yet: \n" + tasks.get(index));
        } else if (userInput.startsWith("delete")) {
            Task removed = tasks.remove(index);
            Ui.dialogue("Noted. I've removed this task: \n" + removed + "\nNow you have " + tasks.size() + " tasks in the list.");
        }
        Storage.saveTasks(tasks);
    }

    public static void processTaskCreationCommands(String userInput, List<Task> tasks) throws BobException, IOException {
        Task newTask = null;
        if (userInput.startsWith("todo")) {
            String description = userInput.substring(5).trim();
            if (description.isEmpty()) {
                throw new BobException("The description of a todo cannot be empty.");
            }
            newTask = new Todo(description);
        } else if (userInput.startsWith("deadline")) {
            String[] parts = userInput.substring(9).split(" /by ");
            if (parts[0].trim().isEmpty()) {
                throw new BobException("The description of a deadline cannot be empty.");
            }
            LocalDateTime by = Parser.parseDateTime(parts[1].trim());
            newTask = new Deadline(parts[0].trim(), by);
        } else if (userInput.startsWith("event")) {
            String[] parts = userInput.substring(6).split(" /from ");
            String[] times = parts[1].split(" /to ");
            if (parts[0].trim().isEmpty()) {
                throw new BobException("The description of an event cannot be empty.");
            }
            LocalDateTime from = Parser.parseDateTime(times[0].trim());
            LocalDateTime to = Parser.parseDateTime(times[1].trim());
            newTask = new Event(parts[0].trim(), from, to);
        }

        tasks.add(newTask);
        Ui.dialogue("Got it. I've added this task: \n" + newTask + "\nNow you have " + tasks.size() + " tasks in the list.");
        Storage.saveTasks(tasks);
    }
}

class Storage {
    public static void saveTasks(List<Task> tasks) throws IOException {
        File directory = new File("./data");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(directory, "bob.txt");
        try (FileWriter writer = new FileWriter(file)) {
            for (Task task : tasks) {
                writer.write(task.toString() + "\n");
            }
        }
    }
}

public class Bob {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        
        Ui.runDialogue(scanner, tasks);
    }

}
