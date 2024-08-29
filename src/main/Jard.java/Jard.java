import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Jard {

    public static class JardException extends RuntimeException {
        public JardException(String message) {
            super(message);
        }
    }

    public static class Task {
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

        public String toFileString() {
            return String.format("T | %d | %s", isDone ? 1 : 0, description);
        }

        @Override
        public String toString() {
            return "[" + getStatusIcon() + "] " + description;
        }
    }

    public static class Todo extends Task {
        public Todo(String description) {
            super(description);
        }

        @Override
        public String toFileString() {
            return String.format("T | %d | %s", isDone ? 1 : 0, description);
        }

        @Override
        public String toString() {
            return "[T]" + super.toString();
        }
    }

    public static class Deadline extends Task {
        protected LocalDateTime by;

        public Deadline(String description, String by) {
            super(description);
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        }

        @Override
        public String toFileString() {
            return String.format("D | %d | %s | %s", isDone ? 1 : 0, description, by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
        }

        @Override
        public String toString() {
            return "[D]" + super.toString() + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + ")";
        }
    }

    public static class Event extends Task {
        protected LocalDateTime from;
        protected LocalDateTime to;

        public Event(String description, String from, String to) {
            super(description);
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        }

        public String toFileString() {
            return String.format("E | %d | %s | %s | %s", isDone ? 1 : 0, description,
                    from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")),
                    to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")));
        }

        @Override
        public String toString() {
            return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma"))
                    + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mma")) + ")";
        }
    }

    public static class Storage {
        private String filePath;

        public Storage(String filePath) {
            this.filePath = filePath;
        }

        public List<Task> loadTasks() {
            List<Task> tasks = new ArrayList<>();
            try {
                File file = new File(filePath);
                if (file.exists()) {
                    Scanner scanner = new Scanner(file);
                    while (scanner.hasNextLine()) {
                        String taskData = scanner.nextLine();
                        tasks.add(parseTask(taskData));
                    }
                    scanner.close();
                } else {
                    createFile();
                }
            } catch (FileNotFoundException e) {
                System.out.println("Jartaloon! " + e.getMessage());
            }
            return tasks;
        }

        public void saveTasks(List<Task> tasks) {
            try {
                FileWriter writer = new FileWriter(filePath);
                for (Task task : tasks) {
                    writer.write(task.toFileString() + System.lineSeparator());
                }
                writer.close();
            } catch (IOException e) {
                System.out.println("Jartaloon! " + e.getMessage());
            }
        }

        private Task parseTask(String taskData) {
            String[] parts = taskData.split(" \\| ");
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String description = parts[2];

            Task task;
            switch (type) {
                case "T":
                    task = new Todo(description);
                    break;
                case "D":
                    String by = parts[3];
                    task = new Deadline(description, by);
                    break;
                case "E":
                    String from = parts[3];
                    String to = parts[4];
                    task = new Event(description, from, to);
                    break;
                default:
                    throw new JardException("Jartaloon! " + taskData);
            }
            if (isDone) {
                task.markAsDone();
            }
            return task;
        }

        private void createFile() {
            try {
                File file = new File(filePath);
                file.getParentFile().mkdirs(); 
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Jartaloon! " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Jard.");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        String filePath = "./data/jard.txt";
        Storage storage = new Storage(filePath);
        List<Task> tasks = storage.loadTasks();

        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            input = scanner.nextLine().trim();
            String[] inputParts = input.split(" ", 2);
            String command = inputParts[0];
            try{
                if (command.equals("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                } else if (command.equals("list")) {
                    if (tasks.isEmpty()) {
                        throw new JardException("Nothing in the list!");
                    }
                    System.out.println("____________________________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks.get(i));
                    }
                    System.out.println("____________________________________________________________");
                } else if (command.equals("mark") || command.equals("unmark")) {
                    if (inputParts.length < 2) {
                        throw new JardException("Invalid command! Please specify the task number.");
                    }
                    int taskIndex;
                    try {
                        taskIndex = Integer.parseInt(inputParts[1]) - 1;
                    } catch (NumberFormatException e) {
                        throw new JardException("That's not number!");
                    }
                    if (taskIndex < 0 || taskIndex >= tasks.size()) {
                        throw new JardException("No task number!");
                    }
                    Task task = tasks.get(taskIndex);
                    if (command.equals("mark")) {
                        task.markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println("Nice! I've marked this task as done:");
                    } else {
                        task.markAsNotDone();
                        System.out.println("____________________________________________________________");
                        System.out.println("OK, I've marked this task as not done yet:");
                    }
                    System.out.println("   " + task);
                    System.out.println("____________________________________________________________");
                } else if (command.equals("delete")) {
                    if (inputParts.length < 2) {
                        throw new JardException("Invalid command! No task number.");
                    } else {
                        try {
                            int taskIndex = Integer.parseInt(inputParts[1]) - 1;
                            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                                Task removedTask = tasks.remove(taskIndex);
                                System.out.println("____________________________________________________________");
                                System.out.println(" Noted. I've removed this task:");
                                System.out.println("   " + removedTask);
                                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                                System.out.println("____________________________________________________________");
                            } else {
                                throw new JardException("Task number does not exist!");
                            }
                        } catch (NumberFormatException e) {
                            throw new JardException("That's not number!");
                        }
                    }
                } else if (command.equals("todo") && inputParts.length > 1) {
                    tasks.add(new Todo(inputParts[1]));
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (command.equals("deadline") && inputParts.length > 1) {
                    String[] details = inputParts[1].split("/by", 2);
                    if (details.length < 2) {
                        throw new JardException("Invalid format! Deadline should be /by.");
                    }
                    tasks.add(new Deadline(details[0].trim(), details[1].trim()));
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (command.equals("event") && inputParts.length > 1) {
                    String[] details = inputParts[1].split("/from|/to");
                    if (details.length != 3) {
                        throw new JardException("Invalid format! Events should be /from and /to.");
                    }

                    tasks.add(new Event(details[0].trim(), details[1].trim(), details[2].trim()));
                    System.out.println("____________________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("   " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else {
                    throw new JardException("Invalid command!");
                }
                storage.saveTasks(tasks);
            } catch (JardException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" Error: Jard! " + e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }
}
