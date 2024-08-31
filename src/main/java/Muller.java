import java.io.*;
import java.util.Scanner;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.time.format.DateTimeFormatter;


public class Muller {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Muller(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (MullerException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Parser p = new Parser();
                Command c = p.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (MullerException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }
    public static void main(String[] args) {
        new Muller("data/muller.txt").run();
    }

    public class Ui {
        private Scanner sc;

        public Ui() {
            sc = new Scanner(System.in);
        }

        public void showWelcome() {
            String logo = "____________________________________________________________";
            System.out.println(logo + "\nHello! I'm Muller\nWhat can I do for you?\n" + logo);
        }

        public String readCommand() {
            return sc.nextLine();
        }

        public void showLine() {
            System.out.println("____________________________________________________________");
        }

        public void showLoadingError() {
            System.out.println("Error loading tasks from file.");
        }

        public void showError(String message) {
            System.out.println(message);
        }

        public void showTaskList(TaskList tasks) {
            try { if (tasks.isEmpty()) {
                System.out.println("No tasks found.");
            } else {
                System.out.println("Here are the tasks in your list:");
                for (int i = 1; i <= tasks.size(); i++) {
                    System.out.println(i + ": " + tasks.get(i - 1));
                }
            } } catch (MullerException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public class Storage {
        private String filePath;

        public Storage(String filePath) {
            this.filePath = filePath;
        }

        public ArrayList<Task> load() throws MullerException {
            ArrayList<Task> list = new ArrayList<>();
            try {
                File file = new File(filePath);
                if (!file.exists()) {
                    return list;  // No tasks to load, return empty list
                }
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                int lineNumber = 1;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(" \\| ");
                    if (parts.length < 3) {
                        throw new MullerException("File is corrupted at line " + lineNumber + ".");
                    }
                    Task task = parseTask(parts);
                    list.add(task);
                    lineNumber++;
                }
                reader.close();
            } catch (IOException e) {
                throw new MullerException("Error loading tasks: " + e.getMessage());
            }
            return list;
        }

        public void save(TaskList tasks) throws MullerException {
            try {
                File file = new File(filePath);
                File directory = new File(file.getParent());
                if (!directory.exists()) {
                    directory.mkdirs();
                }
                BufferedWriter writer = new BufferedWriter(new FileWriter(file));
                for (Task task : tasks.getTasks()) {
                    writer.write(task.toFileString());
                    writer.newLine();
                }
                writer.close();
            } catch (IOException e) {
                throw new MullerException("Error saving tasks: " + e.getMessage());
            }
        }

        private Task parseTask(String[] parts) throws MullerException {
            String type = parts[0];
            boolean isDone = parts[1].equals("1");
            String name = parts[2];
            Task task = new Task(name);
            task.setType(type);
            task.markAsDone(isDone);

            if (type.equals("[D]") && parts.length >= 4) {
                LocalDate date = LocalDate.parse(parts[3], Task.INPUT_DATE_FORMATTER);
                task.setDate(date);
            } else if (type.equals("[E]") && parts.length >= 5) {
                LocalDate startDate = LocalDate.parse(parts[3], Task.INPUT_DATE_FORMATTER);
                LocalDate endDate = LocalDate.parse(parts[4], Task.INPUT_DATE_FORMATTER);
                task.setDateRange(startDate, endDate);
            }
            return task;
        }
    }
    public class Parser {
        public Parser() {}
        public Command parse(String fullCommand) throws MullerException {
            String[] inputs = fullCommand.split(" ", 2);
            String commandWord = inputs[0].toLowerCase();

            switch (commandWord) {
                case "bye":
                    return new ExitCommand();
                case "list":
                    return new ListCommand();
                case "mark":
                    return new MarkCommand(inputs);
                case "unmark":
                    return new UnmarkCommand(inputs);
                case "todo":
                    return new AddCommand(inputs, "T");
                case "deadline":
                    return new AddCommand(inputs, "D");
                case "event":
                    return new AddCommand(inputs, "E");
                case "delete":
                    return new DeleteCommand(inputs);
                case "on":
                    return new OnCommand(inputs);
                default:
                    throw new MullerException("I'm sorry, but I don't know what that means :-(");
            }
        }
    }

    public class TaskList {
        private ArrayList<Task> tasks;

        public TaskList() {
            tasks = new ArrayList<>();
        }

        public TaskList(ArrayList<Task> tasks) {
            this.tasks = tasks;
        }

        public void addTask(Task task) {
            tasks.add(task);
        }

        public void deleteTask(int index) throws MullerException {
            if (index < 0 || index >= tasks.size()) {
                throw new MullerException("Invalid task number!");
            }
            tasks.remove(index);
        }

        public Task get(int index) throws MullerException {
            if (index < 0 || index >= tasks.size()) {
                throw new MullerException("Invalid task number!");
            }
            return tasks.get(index);
        }

        public int size() {
            return tasks.size();
        }

        public boolean isEmpty() {
            return tasks.isEmpty();
        }

        public ArrayList<Task> getTasks() {
            return tasks;
        }
    }

    public abstract class Command {
        public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws MullerException;

        public boolean isExit() {
            return false;
        }
    }

    public class ExitCommand extends Command {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            ui.showLine();
            System.out.println("Bye. Hope to see you again soon!");
        }

        @Override
        public boolean isExit() {
            return true;
        }
    }

    public class ListCommand extends Command {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            ui.showTaskList(tasks);
        }
    }

    public class AddCommand extends Command {
        private String[] inputs;
        private String type;

        public AddCommand(String[] inputs, String type) {
            this.inputs = inputs;
            this.type = type;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws MullerException {
            if (inputs.length < 2) {
                throw new MullerException(type.equals("T") ? "Todo what?" : type.equals("D") ? "Deadline for what?" : "Event for what?");
            }
            String details = inputs[1].trim();
            Task task;

            switch (type) {
                case "T":
                    task = new Task(details);
                    task.setType("T");
                    break;
                case "D":
                    task = parseDeadline(details);
                    break;
                case "E":
                    task = parseEvent(details);
                    break;
                default:
                    throw new MullerException("Unknown task type!");
            }

            tasks.addTask(task);
            ui.showLine();
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + task);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            ui.showLine();

            // Save the updated task list to the storage
            storage.save(tasks);
        }

        private Task parseDeadline(String details) throws MullerException {
            String[] detailParts = details.split("/by", 2);
            if (detailParts.length < 2) {
                throw new MullerException("Oops, you didn't specify the deadline!");
            }
            Task task = new Task(detailParts[0].trim());
            task.setType("D");
            LocalDate date = parseDate(detailParts[1].trim());
            task.setDate(date);
            return task;
        }

        private Task parseEvent(String details) throws MullerException {
            String[] detailParts = details.split("/from", 2);
            if (detailParts.length < 2) {
                throw new MullerException("Oops, you didn't specify the start date!");
            }
            String[] dateParts = detailParts[1].split("/to", 2);
            if (dateParts.length < 2) {
                throw new MullerException("You missed out either the start or end date!");
            }
            Task task = new Task(detailParts[0].trim());
            task.setType("E");
            LocalDate startDate = parseDate(dateParts[0].trim());
            LocalDate endDate = parseDate(dateParts[1].trim());
            task.setDateRange(startDate, endDate);
            return task;
        }

        private LocalDate parseDate(String dateStr) throws MullerException {
            try {
                return LocalDate.parse(dateStr, Task.INPUT_DATE_FORMATTER);
            } catch (Exception e) {
                throw new MullerException("Invalid date format! Use yyyy-MM-dd (e.g., 2019-10-15).");
            }
        }
    }

    public class MarkCommand extends Command {
        private int index;

        public MarkCommand(String[] inputs) throws MullerException {
            if (inputs.length < 2 || !isNumeric(inputs[1])) {
                throw new MullerException("Pick a valid task number to mark!");
            }
            this.index = Integer.parseInt(inputs[1]) - 1;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws MullerException {
            tasks.get(index).markAsDone();
            ui.showLine();
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("  " + tasks.get(index));
            ui.showLine();

            storage.save(tasks);
        }

        private boolean isNumeric(String str) {
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    public class UnmarkCommand extends Command {
        private int index;

        public UnmarkCommand(String[] inputs) throws MullerException {
            if (inputs.length < 2 || !isNumeric(inputs[1])) {
                throw new MullerException("Pick a valid task number to unmark!");
            }
            this.index = Integer.parseInt(inputs[1]) - 1;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws MullerException {
            tasks.get(index).markAsNotDone();
            ui.showLine();
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("  " + tasks.get(index));
            ui.showLine();

            storage.save(tasks);
        }

        private boolean isNumeric(String str) {
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    public class DeleteCommand extends Command {
        private int index;

        public DeleteCommand(String[] inputs) throws MullerException {
            if (inputs.length < 2 || !isNumeric(inputs[1])) {
                throw new MullerException("Pick a valid task number to delete!");
            }
            this.index = Integer.parseInt(inputs[1]) - 1;
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) throws MullerException {
            if (index < 0 || index >= tasks.size()) {
                throw new MullerException("Invalid task number!");
            }

            ui.showLine();
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + tasks.get(index));
            tasks.deleteTask(index);
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            ui.showLine();

            storage.save(tasks);
        }

        private boolean isNumeric(String str) {
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
    }

    public class OnCommand extends Command {
        private LocalDate date;

        public OnCommand(String[] inputs) throws MullerException {
            if (inputs.length < 2) {
                throw new MullerException("Specify a date (e.g., 'on 2019-10-15')!");
            }
            try {
                this.date = LocalDate.parse(inputs[1].trim(), Task.INPUT_DATE_FORMATTER);
            } catch (Exception e) {
                throw new MullerException("Invalid date format! Use yyyy-MM-dd (e.g., 2019-10-15).");
            }
        }

        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            ui.showLine();
            System.out.println("Tasks on " + date.format(Task.OUTPUT_DATE_FORMATTER) + ":");
            boolean found = false;
            for (Task task : tasks.getTasks()) {
                if (task.isOnDate(date)) {
                    System.out.println("  " + task);
                    found = true;
                }
            }
            if (!found) {
                System.out.println("  No tasks found.");
            }
            ui.showLine();
        }
    }


    // Exception class
    public static class MullerException extends Exception {
        public MullerException(String message) {
            super(message);
        }
    }

    public class Task {
        public static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        public static final DateTimeFormatter OUTPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

        private String name;
        private boolean isDone;
        private String type = "[ ]";  // [T], [D], [E]
        private LocalDate date; // For Deadline or Event start date
        private LocalDate endDate;   // For Event end date

        public Task(String name) {
            this.name = name;
            this.isDone = false;
        }

        public void setType(String type) {
            this.type = "[" + type + "]";
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public void setDateRange(LocalDate startDate, LocalDate endDate) {
            this.date = startDate;
            this.endDate = endDate;
        }

        public void markAsDone() {
            this.isDone = true;
        }

        public void markAsDone(boolean isDone) {
            this.isDone = isDone;
        }

        public void markAsNotDone() {
            this.isDone = false;
        }

        public boolean isOnDate(LocalDate date) {
            if (type.equals("[T]")) {
                return false; // Todo tasks don't have dates
            } else if (type.equals("[D]")) {
                return this.date != null && this.date.equals(date);
            } else if (type.equals("[E]")) {
                return this.date != null && this.endDate != null &&
                        (date.equals(this.date) || date.equals(this.endDate) ||
                                (date.isAfter(this.date) && date.isBefore(this.endDate)));
            }
            return false;
        }

        public String DoneIcon() {
            return (isDone ? "[X]" : "[ ]");
        }

        @Override
        public String toString() {
            String dateStr = "";
            if (type.equals("[D]") && date != null) {
                dateStr = " (by: " + date.format(OUTPUT_DATE_FORMATTER) + ")";
            } else if (type.equals("[E]") && date != null && endDate != null) {
                dateStr = " (from: " + date.format(OUTPUT_DATE_FORMATTER) + " to: " + endDate.format(OUTPUT_DATE_FORMATTER) + ")";
            }
            return this.type + DoneIcon() + " " + name + dateStr;
        }

        public String toFileString() {
            StringBuilder sb = new StringBuilder();
            sb.append(type.charAt(1)).append(" | ").append(isDone ? "1" : "0").append(" | ").append(name);
            if (type.equals("[D]")) {
                sb.append(" | ").append(date.format(INPUT_DATE_FORMATTER));
            } else if (type.equals("[E]")) {
                sb.append(" | ").append(date.format(INPUT_DATE_FORMATTER))
                        .append(" | ").append(endDate.format(INPUT_DATE_FORMATTER));
            }
            return sb.toString();
        }
    }
}





