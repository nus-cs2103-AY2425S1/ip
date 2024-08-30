import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.IOException;

public class Gale {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private ArrayList<Task> taskList;
    private Storage storage;

    public Gale() {
        this.storage = new Storage("src/main/java/data/galeTasks.txt");
        loadTasks();

    }
    public static void main(String[] args) {
        Gale gale = new Gale();
        gale.run();
    }

    protected void loadTasks() {
        try {
            taskList = storage.loadTasks();
        } catch (IOException e) {
            System.out.println("Oops! The wind blew away your task files. Starting over.");
            this.taskList = new ArrayList<>();
        }
    }

    protected void saveTasks() {
        try {
            storage.saveTasks(taskList);
        } catch (IOException e) {
            System.out.println("Oops! The wind interfered with saving your tasks. Please try again.");
        }
    }

    public void run() {
        greet();
        Scanner scanner = new Scanner(System.in);
        String input;

        while (true) {
            try {
                input = scanner.nextLine().trim();
                if (input.equalsIgnoreCase("bye")) {
                    exit();
                    break;
                } else if (input.equalsIgnoreCase("list")) {
                    listTasks();
                } else if (input.startsWith("mark")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    markTask(taskIndex, true);
                } else if (input.startsWith("unmark")) {
                    int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
                    markTask(taskIndex, false);
                } else if (input.startsWith("todo")) {
                    addToDo(input);
                } else if (input.startsWith("deadline")) {
                    addDeadline(input);
                } else if (input.startsWith("event")) {
                    addEvent(input);
                } else if (input.startsWith("delete")) {
                    deleteTask(input);
                } else {
                    throw new GaleException("Oops! That command got lost in the windy realm. Please try again!");
                }
                saveTasks();
            } catch (GaleException e) {
                System.out.println(e.getMessage());
            }
        }
        scanner.close();
    }
    public void greet() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Hello! I'm Gale, your friendly windy assistant.");
        System.out.println("I'll keep your deadlines, to-do's and events in my memory. What do you have to do?");
        System.out.println(HORIZONTAL_LINE);
    }

    public void printAddedTask(Task task) {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Whoosh! Task \"" + task.toString() + "\" added to my windy memory.");
        if (taskList.size() == 1) {
            System.out.println("Now you have 1 task in the list.");
        } else {
            System.out.println("Now you have " + taskList.size() + " tasks in the air.");
        }
        System.out.println("Anything else?");
        System.out.println(HORIZONTAL_LINE);
    }

    public void addToDo(String input) throws GaleException {
        if (input.length() <= 5) {
            throw new GaleException("Oops! The wind blew away your to-do description. Please use: 'todo [description]'");
        }
        String description = input.substring(5).trim();
        if (description.isEmpty()) {
            throw new GaleException("Oops! The wind blew away your to-do description. Please furnish it again.");
        }
        Task task = new ToDo(description);
        taskList.add(task);
        printAddedTask(task);
    }

    public void addDeadline(String input) throws GaleException {
        if (input.length() <= 9) {
            throw new GaleException("Your deadline got tossed by the wind! Please use 'deadline [description] /by [date]'");
        }
        String[] strA  = input.substring(9).split("/by");
        if (strA.length != 2 || strA[0].trim().isEmpty() || strA[1].trim().isEmpty()) {
            throw new GaleException("Your deadline got tossed by the wind! Please use 'deadline [description] /by [date]'");
        }
        String description = strA[0].trim();
        String by = strA[1].trim();
        try {
            Task task = new Deadline(description, by);
            taskList.add(task);
            printAddedTask(task);
        } catch (DateTimeParseException e) {
            throw new GaleException("Oops! The wind blew away your date. Please use 'yyyy-MM-dd HH:mm' or 'd/M/yyyy HH:mm'");
        }
    }

    public void addEvent(String input) throws GaleException {
        if (input.length() <= 6) {
            throw new GaleException("Your event is lost in the wind! Please use 'event [description] /from [start] /to [end]'");
        }
        String[] strA = input.substring(6).split("/from|/to");
        if (strA.length != 3 || strA[0].trim().isEmpty() || strA[1].trim().isEmpty()
            || strA[2].trim().isEmpty()) {
            throw new GaleException("Your event is lost in the wind! Please use 'event [description] /from [start] /to [end]'");
        }
        String description = strA[0].trim();
        String from = strA[1].trim();
        String to = strA[2].trim();
        try {
            Task task = new Event(description, from, to);
            taskList.add(task);
            printAddedTask(task);
        } catch (DateTimeParseException e) {
            throw new GaleException("Oops! The wind blew away your date. Please use 'yyyy-MM-dd HH:mm' or 'd/M/yyyy HH:mm'");
        }
    }

    public void deleteTask(String input) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        Task task = taskList.get(index);
        taskList.remove(index);
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Poof! The wind has blown away this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + taskList.size() + " tasks in your windy list.");
        System.out.println(HORIZONTAL_LINE);
    }
    public void listTasks() {
        System.out.println(HORIZONTAL_LINE);
        if (taskList.isEmpty()) {
            System.out.println("No tasks breezing around now!");
        } else {
            System.out.println("You are breezy with these tasks:");
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println(" " + (i + 1) + ". " + taskList.get(i));
            }
        }
        System.out.println(HORIZONTAL_LINE);
    }
    public void markTask(int index, boolean isDone) throws GaleException {
        if (index >= 0 && index < taskList.size()) {
            Task task = taskList.get(index);
            if (task.status() == isDone) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("You've already marked/unmarked this windy task!");
            } else {
                if (isDone) {
                    task.markAsDone();
                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("Good work! You breezed through this task!");
                } else {
                    task.markAsNotDone();
                    System.out.println(HORIZONTAL_LINE);
                    System.out.println("Tough, this task is back in the windy realm.");
                }
                System.out.println("  " + task.toString());
            }
        } else {
//          System.out.println(HORIZONTAL_LINE);
            throw new GaleException("Oops! That task number is lost in the wind. Try again?");
        }
        System.out.println(HORIZONTAL_LINE);
    }

    public void exit() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("Aw, it's time for you to go huh?");
        System.out.println("Catch you on the next gust!");
        System.out.println(HORIZONTAL_LINE);
    }
}
