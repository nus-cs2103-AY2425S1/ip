import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Krona {
    private static final String FILE_PATH = "./data/krona.txt";
    private static final String DIR_PATH = "./data";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = loadTasks();

        System.out.println("Hello! I'm Krona\n" +
                "What can I do for you?");

        while (true) {
            String input = sc.nextLine();
            String[] words = input.split(" ", 2);

            try {
            if (words[0].equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (words[0].equals("list")) {
                if (tasks.isEmpty()) {
                    System.out.println("Your list is currently empty.");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                }
            } else if (words[0].equals("mark")) {
                int taskIndex = Integer.parseInt(words[1]) - 1;
                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                    tasks.get(taskIndex).markDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(taskIndex));
                    saveTasks(tasks);
                } else {
                    System.out.println("There is no task with that number.");
                }
            } else if (words[0].equals("unmark")) {
                int taskIndex = Integer.parseInt(words[1]) - 1;
                if (taskIndex >= 0 && taskIndex < tasks.size()) {
                    tasks.get(taskIndex).markNotDone();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(tasks.get(taskIndex));
                    saveTasks(tasks);
                } else {
                    System.out.println("There is no task with that number.");
                }
            } else if (words[0].equals("delete")) {
                int taskIndex = Integer.parseInt(words[1]) - 1;
                if ( taskIndex >= 0 && taskIndex < tasks.size()) {
                    Task removedTask = tasks.remove(taskIndex);
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(removedTask);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    saveTasks(tasks);
                } else {
                    System.out.println("There is no task with that number.");
                }
            } else if (words[0].startsWith("todo")) {
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    System.out.println("The description of the todo is empty. Please add a description.");
                } else {
                    tasks.add(new ToDo(words[1]));
                    System.out.println("Got it. I've added this task:");
                    System.out.println(tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    saveTasks(tasks);
                }
            } else if (words[0].startsWith("deadline")) {
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    System.out.println("The description of the deadline is empty. Please add a description.");
                } else {
                    String[] parts = words[1].split("/by", 2);
                    if (parts.length < 2) {
                        System.out.println("The deadline must include a /by date.");
                    } else {
                        tasks.add(new Deadline(parts[0], parts[1]));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        saveTasks(tasks);
                    }
                }
            } else if (words[0].startsWith("event")) {
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    System.out.println("OOPS!!! The description of an event cannot be empty.");
                } else {
                    String[] parts = words[1].split("/from ", 2);
                    if (parts.length < 2 || !parts[1].contains("/to ")) {
                        System.out.println("OOPS!!! The event must include a /from and /to time.");
                    } else {
                        String[] time = parts[1].split("/to ", 2);
                        tasks.add(new Event(parts[0], time[0], time[1]));
                        System.out.println("Got it. I've added this task:");
                        System.out.println(tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        saveTasks(tasks);
                    }
                }
            } else {
                System.out.println("Unknown command. Please try again.");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("There is no task with that number.");
            }
        }
    }

    private static void saveTasks(ArrayList<Task> tasks) {
        try {
            File dir = new File(DIR_PATH);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                writer.write(taskToString(task) + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving the tasks.");
            e.printStackTrace();
        }
    }

    private static String taskToString(Task task) {
        String type = task instanceof ToDo ? "T" :
                task instanceof Deadline ? "D" : "E";
        String status = task.isDone ? "1" : "0";
        String extra = "";

        if (task instanceof Deadline) {
            extra = " | " + ((Deadline) task).dateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        } else if (task instanceof Event) {
            extra = " | " + ((Event) task).startDateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"))
                    + " to " + ((Event) task).endDateTime.format(DateTimeFormatter.ofPattern("d/M/yyyy HHmm"));
        }

        return type + " | " + status + " | " + task.description + extra;
    }

    private static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Task task = stringToTask(line);
                    if (task != null) {
                        tasks.add(task);
                    }
                }
                scanner.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading the tasks.");
            e.printStackTrace();
        }
        return tasks;
    }

    private static Task stringToTask(String line) {
        String[] parts = line.split(" \\| ");
        Task task = null;

        switch (parts[0]) {
            case "T":
                task = new ToDo(parts[2]);
                break;
            case "D":
                task = new Deadline(parts[2], parts[3]);
                break;
            case "E":
                String[] times = parts[3].split(" to ");
                task = new Event(parts[2], times[0], times[1]);
                break;
        }

        if (task != null && parts[1].equals("1")) {
            task.markDone();
        }

        return task;
    }
}
