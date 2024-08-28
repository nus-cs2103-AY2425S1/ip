import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Sage {
    private static final String FILE_PATH = "./data/sage.txt";
    public static void main(String[] args) {
        Storage storage = new Storage(FILE_PATH);

        //Create a scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String input;

        try {
            tasks = new ArrayList<>(storage.loadTasks());
        } catch (IOException e) {
            System.out.println("Unable to load tasks. Starting with an empty list");
            tasks = new ArrayList<>();
        }

        //Greet the user
        System.out.println("______________________________________________________");
        System.out.println("Hello! I'm Sage");
        System.out.println("What can I do for you?");
        System.out.println("______________________________________________________");

        //Read user input until they say bye
        do {
            input = scanner.nextLine().trim(); //trim the white space

            try {
                if (input.equalsIgnoreCase("list")) {
                    System.out.println("______________________________________________________");
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + ". " + tasks.get(i));
                    }
                    System.out.println("______________________________________________________");
                } else if (input.startsWith("on")) {
                    String dateStr = input.substring(2).trim();
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    try {
                        LocalDate date = LocalDate.parse(dateStr, dateFormatter);
                        System.out.println("______________________________________________________");
                        System.out.println("Tasks on " + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ":");
                        for (Task task : tasks) {
                            if (task instanceof Deadline) {
                                if (((Deadline) task).getDate().toLocalDate().equals(date)) {
                                    System.out.println(task);
                                }
                            } else if (task instanceof Event) {
                                if (((Event) task).getFrom().toLocalDate().equals(date) ||
                                        ((Event) task).getTo().toLocalDate().equals(date)) {
                                    System.out.println(task);
                                }
                            }
                        }
                        System.out.println("______________________________________________________");
                    } catch (DateTimeParseException e) {
                        System.out.println("Wrong date format!! Please use yyyy-mm-dd format");
                    }
                } else if (input.startsWith("mark")) {
                    int taskNumber = Integer.parseInt(input.substring(4).trim()) - 1;
                    if (taskNumber >= 0 && taskNumber < tasks.size()) {
                        tasks.get(taskNumber).markAsDone();
                        storage.saveTasks(tasks);
                        System.out.println("______________________________________________________");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println(" " + tasks.get(taskNumber));
                        System.out.println("______________________________________________________");
                    } else {
                        System.out.println("Invalid task number :(");
                    }
                } else if (input.startsWith("unmark")) {
                    int taskNumber = Integer.parseInt(input.substring(6).trim()) - 1;
                    if (taskNumber >= 0 && taskNumber < tasks.size()) {
                        tasks.get(taskNumber).markAsNotDone();
                        storage.saveTasks(tasks);
                        System.out.println("______________________________________________________");
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println(" " + tasks.get(taskNumber));
                        System.out.println("______________________________________________________");
                    } else {
                        System.out.println("Invalid task number :(");
                    }
                } else if (input.startsWith("delete")) {
                    int taskNumber = Integer.parseInt(input.substring(6).trim()) - 1;
                    if (taskNumber >= 0 && taskNumber < tasks.size()) {
                        Task removedTask = tasks.remove(taskNumber);
                        storage.saveTasks(tasks);
                        System.out.println("______________________________________________________");
                        System.out.println("OK! I will remove this task:");
                        System.out.println(" " + removedTask);
                        System.out.println("Now you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list");
                        System.out.println("______________________________________________________");
                    }
                } else if (input.startsWith("todo")) {
                    String description = input.substring(4).trim();
                    if (description.isEmpty()) {
                        throw new SageException("Oh No! Please tell me what task to do :(");
                    }
                    tasks.add(new ToDo(description));
                    storage.saveTasks(tasks);
                    System.out.println("______________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println(" " + tasks.get(tasks.size() - 1));
                    System.out.println("Now you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.");
                    System.out.println("______________________________________________________");
                } else if (input.startsWith("deadline")) {
                    String[] parts = input.substring(8).split(" /by ");
                    if (parts.length == 2) {
                        String description = parts[0].trim();
                        String by = parts[1].trim();
                        tasks.add(new Deadline(description, by));
                        storage.saveTasks(tasks);
                        System.out.println("______________________________________________________");System.out.println("Got it. I've added this task:");
                        System.out.println(" " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.");
                        System.out.println("______________________________________________________");
                    } else {
                        throw new SageException("You need to add a task and a deadline!! -_-");
                    }
                } else if (input.startsWith("event")) {
                    String[] parts = input.substring(5).split(" /from | /to ");
                    if (parts.length == 3) {
                        String description = parts[0].trim();
                        String from = parts[1].trim();
                        String to = parts[2].trim();
                        tasks.add(new Event(description, from, to));
                        storage.saveTasks(tasks);
                        System.out.println("______________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println(" " + tasks.get(tasks.size() - 1));
                        System.out.println("Now you have " + tasks.size() + (tasks.size() > 1 ? " tasks" : " task") + " in the list.");
                        System.out.println("______________________________________________________");
                    } else {
                        throw new SageException("What time is your event?? :o");
                    }
                } else if (!input.equalsIgnoreCase("bye")) {
                    throw new SageException("Sorry what do you mean? :p");
                }
            } catch (SageException e) {
                System.out.println("______________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("______________________________________________________");
            }
        } while (!input.equalsIgnoreCase("bye"));

        //Exit
        System.out.println("______________________________________________________");
        System.out.println(" " + "Bye. Hope to see you again soon!");
        System.out.println("______________________________________________________");

        scanner.close();
    }
}
