import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Kobe {

    public static void main(String[] args) {

        String logo = " K   K   OOOOO   BBBBB   EEEEE \n"
                + " K  K   O     O  B    B  E     \n"
                + " KKK    O     O  BBBBB   EEEE  \n"
                + " K  K   O     O  B    B  E     \n"
                + " K   K   OOOOO   BBBBB   EEEEE \n";

        System.out.println("____________________________________________________________");
        System.out.println("Greetings! I am Kobe Bryant. \n" + logo);
        System.out.println("How can I help you, my man?");
        System.out.println("____________________________________________________________");

        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> tasks = new ArrayList<>(FileHandler.loadTasks());
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        while (true) {
            String userInput = scanner.nextLine();
            System.out.println("____________________________________________________________");

            if (userInput.equals("bye")) {
                System.out.println("Goodbye! My man.");
                System.out.println("____________________________________________________________");
                break;
            } else if (userInput.equals("list")) {
                if (tasks.isEmpty()) {
                    System.out.println("Your task list is currently empty.");
                } else {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + "." + tasks.get(i));
                    }
                }
            } else if (userInput.startsWith("mark ")) {
                try {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (taskNumber >= 0 && taskNumber < tasks.size()) {
                        tasks.get(taskNumber).markAsDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + tasks.get(taskNumber));
                        FileHandler.saveTasks(tasks);
                    } else {
                        System.out.println("OOPS!!! The task number you provided is not valid.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("OOPS!!! Please provide a valid task number.");
                }
            } else if (userInput.startsWith("unmark ")) {
                try {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (taskNumber >= 0 && taskNumber < tasks.size()) {
                        tasks.get(taskNumber).markAsNotDone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + tasks.get(taskNumber));
                        FileHandler.saveTasks(tasks);
                    } else {
                        System.out.println("OOPS!!! The task number you provided is not valid.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("OOPS!!! Please provide a valid task number.");
                }
            } else if (userInput.startsWith("delete ")) {
                try {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                    if (taskNumber >= 0 && taskNumber < tasks.size()) {
                        Task removedTask = tasks.remove(taskNumber);
                        System.out.println("Noted. I've removed this task:");
                        System.out.println("  " + removedTask);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        FileHandler.saveTasks(tasks);
                    } else {
                        System.out.println("OOPS!!! The task number you provided is not valid.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("OOPS!!! Please provide a valid task number.");
                }
            } else if (userInput.startsWith("todo ")) {
                String name = userInput.substring(5).trim();
                if (name.isEmpty()) {
                    System.out.println("OOPS!!! The description of a todo cannot be empty.");
                } else {
                    Todo todo = new Todo(name);
                    tasks.add(todo);
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + todo);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    FileHandler.saveTasks(tasks);
                }
            } else if (userInput.startsWith("deadline ")) {
                String[] parts = userInput.substring(9).split("/by ");
                if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                    System.out.println("OOPS!!! The description or deadline of a task cannot be empty.");
                } else {
                    String name = parts[0].trim();
                    try {
                        LocalDateTime by = LocalDateTime.parse(parts[1].trim(), inputFormatter);
                        Deadline deadline = new Deadline(name, by);
                        tasks.add(deadline);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + deadline);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        FileHandler.saveTasks(tasks);
                    } catch (DateTimeParseException e) {
                        System.out.println("OOPS!!! Please enter the date in the format yyyy-MM-dd HHmm.");
                    }
                }
            } else if (userInput.startsWith("event ")) {
                String[] parts = userInput.substring(6).split("/from |/to ");
                if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
                    System.out.println("OOPS!!! The event description, start time, or end time cannot be empty.");
                } else {
                    String name = parts[0].trim();
                    try {
                        LocalDateTime from = LocalDateTime.parse(parts[1].trim(), inputFormatter);
                        LocalDateTime to = LocalDateTime.parse(parts[2].trim(), inputFormatter);
                        Event event = new Event(name, from, to);
                        tasks.add(event);
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + event);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        FileHandler.saveTasks(tasks);
                    } catch (DateTimeParseException e) {
                        System.out.println("OOPS!!! Please enter the date and time in the format yyyy-MM-dd HHmm.");
                    }
                }
            } else {
                System.out.println("Please enter a valid command, Sir.");
            }
            System.out.println("____________________________________________________________");
        }
        scanner.close();
    }
}
