import java.util.Scanner;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class Tissue {
    private static ArrayList<Task> taskArray = new ArrayList<>();
    private static final String LINE =
            "--------------------------------------------------------------";
    private static final String INDENT = "       ";

    private static Scanner scanner;

    public static void main(String[] args) {
        chatFunction();
    }

    private static void chatFunction() {
        System.out.println(LINE);
        scanner = new Scanner(System.in);
        System.out.println("Hello! I'm Tissue");
        System.out.println("What can I do for you?");
        System.out.println(LINE);

        String in = scanner.next();

        while (!in.equals("bye")) {
            System.out.println(LINE);

            if (in.equals("list")) {
                System.out.println(INDENT + "Here are the tasks in your list:");
                System.out.println(listTask());

            } else if (in.equals("mark")) {
                Task task = taskArray.get(scanner.nextInt() - 1).markTask();
                System.out.println(INDENT + "Nice! I've marked this task as done:");
                System.out.println(INDENT + "  " + task);
            } else if (in.equals("unmark")) {

                Task task = taskArray.get(scanner.nextInt() - 1).unmarkTask();
                System.out.println(INDENT + "OK, I've marked this task as not done yet:");
                System.out.println(INDENT + "  " + task);

            } else if (in.equals("delete")) {
                deleteTask(scanner.nextInt());
            } else {
                storeTask(in);
            }

            System.out.println(LINE);
            in = scanner.next();
        }

        scanner.close();

        System.out.println(LINE);
        System.out.print(INDENT);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(LINE);

    }


    private static void deleteTask(int number) {
        Task task = taskArray.remove(number - 1);
        System.out.println(INDENT + "Noted. I've removed this task:");
        System.out.println(INDENT + "  " + task);
        System.out.println(INDENT + "Now you have " + taskArray.size() + " tasks in the list.");
    }

    private static void saveTaskToDisk(Task task) {
        String pathStr = "./data/";

        String parsedTask = parseTask(task);
        try {
            Files.createDirectories(Paths.get(pathStr));
            Files.write(Paths.get(pathStr + "tissue.txt"),
                    parsedTask.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e);
        }

    }


    private static String parseTask(Task task) {
        String temp = "";
        if (task instanceof ToDo) {
            temp = String.format("T | %s | %s\n", task.getDone() ? 1 : 0, task.getTask());

        } else if (task instanceof Event) {
            Event event = (Event) task;
            temp = String.format("E | %s | %s | %s | %s\n", event.getDone() ? 1 : 0,
                    event.getTask(), event.getFrom(), event.getTo());
        } else if (task instanceof Deadline) {
            Deadline dl = (Deadline) task;
            temp = String.format("D | %s | %s | %s\n", dl.getDone() ? 1 : 0, dl.getTask(),
                    dl.getBy());
        }
        return temp;
    }

    private static void storeTask(String in) {
        if (in.equals("todo")) {

            String item = scanner.next();
            if (item.equals("")) {
                System.out.println("Decription of TODO cannot be empty.");
            } else {
                Task task = new ToDo(false, item);
                saveTaskToDisk(task);
                taskArray.add(task);
                System.out.println(INDENT + "Got it. I've added this task:");
                System.out.println(INDENT + "  " + task);
                System.out.println(
                        INDENT + "Now you have " + taskArray.size() + " tasks in the list.");
            }



        } else if (in.equals("deadline")) {
            String item = scanUntil("/by");
            String by = scanner.nextLine().strip();
            Task task = new Deadline(false, item, by);
            saveTaskToDisk(task);
            taskArray.add(task);
            System.out.println(INDENT + "Got it. I've added this task:");
            System.out.println(INDENT + "  " + task);
            System.out.println(INDENT + "Now you have " + taskArray.size() + " tasks in the list.");

        } else if (in.equals("event")) {
            String item = scanUntil("/from");
            String from = scanUntil("/to");
            String to = scanner.nextLine().strip();
            Task task = new Event(false, item, from, to);
            saveTaskToDisk(task);
            taskArray.add(task);
            System.out.println(INDENT + "Got it. I've added this task:");
            System.out.println(INDENT + "  " + task);
            System.out.println(INDENT + "Now you have " + taskArray.size() + " tasks in the list.");
        } else {

            System.out.println(
                    "Invalid input. Possible inputs are deadline, todo, event, list, mark, and unmark.");

        }
    }

    private static String scanUntil(String pattern) {
        String item = "";
        String temp = scanner.next();
        while (!temp.equals(pattern)) {
            item += temp + " ";
            temp = scanner.next();
        }
        return item;
    }

    private static String listTask() {
        String parsedText = "";
        for (int i = 0; i < taskArray.size(); i++) {
            Task task = taskArray.get(i);
            parsedText += INDENT + String.valueOf(i + 1) + "." + " " + task + "\n";
        }
        return parsedText;
    }
}
