import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
public class Skywalker {
    private static final String FILE_PATH = "./data/skywalker.txt";



    public static void main(String[] args) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Skywalker");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        ArrayList<Task> tasks = loadTasksFromFile(); //helper function to load the file
        Scanner scanner = new Scanner(System.in);


        while (true) {
            String printable = scanner.nextLine();
            System.out.println("____________________________________________________________");
            try {

                if (Objects.equals(printable, "bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    saveTasksToFile(tasks); //helper function to save the tasks before exiting the programme
                    break;
                } else if (Objects.equals(printable, "list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(i + 1 + ". " + tasks.get(i).toString());
                    }
                    System.out.println("____________________________________________________________");
                } else if (printable.startsWith("mark ")) {
                    int index = Integer.parseInt(printable.split(" ")[1]) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new IndexOutOfBoundsException("Task number is out of range!");
                    }
                    tasks.get(index).markDone();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(tasks.get(index).toString());
                    System.out.println("____________________________________________________________");

                } else if (printable.startsWith("unmark ")) {
                    int index = Integer.parseInt(printable.split(" ")[1]) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new IndexOutOfBoundsException("Task number is out of range!");
                    }
                    tasks.get(index).unmarkDone();
                    System.out.println(tasks.get(index).toString());
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("____________________________________________________________");
                } else if (printable.startsWith("todo ")) {
                    //exceptions handling to check if the command is correct
                    if (printable.substring(5).isEmpty()) {
                        throw new EmptyDescriptionException("the todo task description cannot be empty!!!!");
                    }
                    //System.out.println("____________________________________________________________");
                    Task task = new Todo(printable.substring(5));
                    tasks.add(task);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(task.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");

                } else if (printable.startsWith("deadline ")) {
                    // System.out.println("____________________________________________________________");

                    String[] information = printable.substring(9).split("/by ");
                    String description = information[0];
                    String by = information[1];
                    // Parse the date and time string to a LocalDateTime object
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    LocalDate parsedDate = LocalDate.parse(by, formatter);
                    //empty exception catching
                    if (information.length < 2 || description.isEmpty() || by.isEmpty()) {
                        throw new EmptyDescriptionException("the deadline task description/date cannot be empty!!!!");
                    }
                    System.out.println("Got it. I've added this task:");
                    Deadline task = new Deadline(description, parsedDate.toString());
                    tasks.add(task);
                    System.out.println(task.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (printable.startsWith("event ")) {
                    //  System.out.println("____________________________________________________________");
                    String[] information = printable.substring(6).split("/from |/to ");
                    String description = information[0];
                    String from = information[1];
                    String to = information[2];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
                    LocalDateTime startTime = LocalDateTime.parse(from, formatter);
                    LocalDateTime endTime = LocalDateTime.parse(to, formatter);
                    if (information.length < 3 || from.isEmpty() || to.isEmpty()) {
                        throw new EmptyDescriptionException("the deadline task description/ from date/ to date cannot be empty!!!!");
                    }
                    System.out.println("Got it. I've added this task:");
                    Event task = new Event(description, startTime.toString(), endTime.toString());
                    tasks.add(task);
                    System.out.println(task.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else if (printable.startsWith("delete ")) {

                    int index = Integer.parseInt(printable.substring(7)) - 1;
                    if (index < 0 || index >= tasks.size()) {
                        throw new IndexOutOfBoundsException("Task number is out of range!");
                    }
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(tasks.get(index).toString());
                    tasks.remove(index);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else {
                    throw new UnknownCommandException("the command is not correct :( check again!");
                }
            } catch (EmptyDescriptionException | IndexOutOfBoundsException | UnknownCommandException e) {
                System.out.println("OOPS!" + e.getMessage());
            } catch (Exception e) { // safety net catch error
                System.out.println("OOPS! Something is wrong: " + e.getMessage());
            }
        }
    }

    private static void saveTasksToFile(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter("./data/skywalker.txt");
            for (Task task : tasks) {
                writer.write(taskToFileString(task) + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("OOPS! Unable to save tasks to file: " + e.getMessage());
        }


    }

    // Method to convert a task to a file string
    private static String taskToFileString(Task task) {
        String status = task.isDone ? "1" : "0";
        if (task instanceof Todo) {
            return "T | " + status + " | " + task.description;
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return "D | " + status + " | " + deadline.description + " | " + (deadline.by);
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return "E | " + status + " | " + event.description + " | " + event.from + " | " + event.to;
        }
        return"";
    }


    // Method to load tasks from the file
    private static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(FILE_PATH);
            if (file.exists()) {
                Scanner scanner = new Scanner(file);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    Task task = parseTaskFromFileString(line);
                    tasks.add(task);
                }
                scanner.close();
            }
        } catch (Exception e) {
            System.out.println("OOPS! Unable to load task from file" + e.getMessage());
        }
        return tasks;
    }
    // Method to parse a task from a file string
    private static Task parseTaskFromFileString(String fileString) {
        String[] parts = fileString.split(" \\| ");
        String taskTypeCode = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskTypeCode) {
            case "T":
                Todo todo = new Todo(description);
                if (isDone) todo.markDone();
                return todo;
            case "D":
                String by = parts[3];
                Deadline deadline = new Deadline(description, by);
                if (isDone) deadline.markDone();
                return deadline;
            case "E":
                String from = parts[3];
                String to = parts[4];
                Event event = new Event(description, from, to);
                if (isDone) event.markDone();
                return event;
            default:
                throw new IllegalArgumentException("Invalid task type found in file.");
        }
    }
}

