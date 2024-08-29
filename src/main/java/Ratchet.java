import exception.InvalidCommandArgumentException;
import exception.InvalidCommandException;
import exception.RatchetException;
import task.DeadlineTask;
import task.EventTask;
import task.Task;
import task.TodoTask;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Ratchet {
    private static final String INDENT = "    ";
    private static final ArrayList<Task> tasks = new ArrayList<Task>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            load();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return;
        }
        greet();
        String input;
        String command = "";
        while (!command.equals("bye")) {
            input = scanner.nextLine();
            command = input.split(" ")[0].toLowerCase();
            try {
                switch (command) {
                case "list":
                    displayList();
                    break;
                case "mark":
                    mark(input);
                    break;
                case "unmark":
                    unmark(input);
                    break;
                case "todo", "deadline", "event":
                    addTask(input);
                    break;
                case "delete":
                    delete(input);
                    break;
                case "bye":
                    exit();
                    save();
                    break;
                default:
                    throw new InvalidCommandException(
                            "Ratchet is unable to " + "execute" + " " + command + "!");
                }
            } catch (RatchetException e) {
                lineBreak();
                System.out.println(INDENT + e.getMessage());
                lineBreak();
            }
        }
    }

    private static void lineBreak() {
        System.out.println("   " + "________________________________________________________");
    }

    private static String taskCount() {
        String count = tasks.size() <= 1 ? tasks.size() + " task" : tasks.size() + " tasks";
        return "Now you have " + count + " in the list.";
    }

    private static void load() throws IOException {
        System.out.println(INDENT + "Attempting to load data...");
        File file = new File("data/ratchet.txt");
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] info = scanner.nextLine().split("\\|");
                String type = info[0];
                switch (type) {
                case "T":
                    tasks.add(new TodoTask(info[1], Boolean.parseBoolean(info[2])));
                    break;
                case "D":
                    tasks.add(new DeadlineTask(info[1], Boolean.parseBoolean(info[2]),
                            LocalDate.parse(info[3])));
                    break;
                case "E":
                    tasks.add(new EventTask(info[1], Boolean.parseBoolean(info[2]), LocalDate.parse(info[3]),
                            LocalDate.parse(info[4])));
                    break;
                }
            }
            System.out.println(INDENT + "Data loaded!");
        } catch (FileNotFoundException e) {
            System.out.println(INDENT + "Data file not found, creating now...");
            createFile();
            System.out.println(INDENT + "Data file created!");
        }
    }

    private static void createFile() throws IOException {
        new File("data").mkdir();
        File f = new File("data/ratchet.txt");
        f.createNewFile();
        System.out.println(INDENT + "Save file is created!");
    }

    private static void save() {
        System.out.println(INDENT + "Attempting to save data!");
        try (FileWriter fw = new FileWriter("data/ratchet.txt")) {
            for (Task task : tasks) {
                fw.write(task.toSave());
                fw.write(System.lineSeparator());
            }
            System.out.println(INDENT + "Data saved!");
        } catch (IOException e) {
            System.out.println(INDENT + "Unable to save data!");
        }
    }

    private static void greet() {
        lineBreak();
        System.out.println(
                INDENT + "Hello! I'm " + "Ratchet\n" + INDENT + "What can I do " + "for" + " you" + "?");
        lineBreak();
    }

    private static void exit() {
        lineBreak();
        System.out.println(INDENT + "Bye. Hope to see " + "you" + " again soon!");
        lineBreak();
    }

    private static void addTask(String text) throws InvalidCommandArgumentException {
        Task task = null;
        if (text.startsWith("todo")) {
            try {
                String description = text.split("todo ")[1];
                task = new TodoTask(description);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new InvalidCommandArgumentException("The description of a todo cannot be empty!");
            }
        } else if (text.startsWith("deadline")) {
            String[] split = text.split(" /by ");
            LocalDate deadline = LocalDate.parse(split[1]);
            String description = split[0].split("deadline" + " ")[1];
            task = new DeadlineTask(description, deadline);
        } else {
            String[] split1 = text.split(" /to ");
            LocalDate to = LocalDate.parse(split1[1]);
            String[] split2 = split1[0].split(" /from ");
            LocalDate from = LocalDate.parse(split2[1]);
            String description = split2[0].split("event ")[1];
            task = new EventTask(description, from, to);
        }
        tasks.add(task);
        lineBreak();
        System.out.println(
                INDENT + "Got it. I've added " + "this task:\n" + INDENT + "  " + task + "\n" + INDENT
                        + taskCount());
        lineBreak();
    }

    private static void displayList() {
        lineBreak();
        if (tasks.isEmpty()) {
            System.out.println(INDENT + "You have no " + "tasks currently!");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(INDENT + (i + 1) + "." + tasks.get(i));
            }
        }
        lineBreak();
    }

    private static void mark(String input) {
        int num = Integer.parseInt(input.split(" ")[1]) - 1;
        tasks.get(num).markAsDone();
        lineBreak();
        System.out.println(
                INDENT + "Nice! I've marked " + "this task as done:\n" + INDENT + "  " + tasks.get(num));
        lineBreak();
    }

    private static void unmark(String input) {
        int num = Integer.parseInt(input.split(" ")[1]) - 1;
        tasks.get(num).markAsNotDone();
        lineBreak();
        System.out.println(INDENT + "OK, I've marked " + "this" + " task as not done yet:\n" + INDENT + "  "
                + tasks.get(num));
        lineBreak();
    }

    private static void delete(String input) {
        int num = Integer.parseInt(input.split(" ")[1]) - 1;
        Task task = tasks.remove(num);
        lineBreak();
        System.out.println(
                INDENT + "Noted. I've removed " + "this task:\n" + INDENT + "  " + task + "\n" + INDENT
                        + taskCount());
        lineBreak();
    }
}