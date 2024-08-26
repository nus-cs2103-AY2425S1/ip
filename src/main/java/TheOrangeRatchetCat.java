import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

enum CommandType {
    LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE;
}

public class TheOrangeRatchetCat {

    private static final String FILE_PATH = "./data/OrangeCat.txt";

    public static void main(String[] args) {
        // The ChatBot named OrangeRatchetCat
        List<Task> items = new ArrayList<>();
        loadTasks(items); // Load tasks from file at startup
        ratchetCatBot(items); // Pass the list of tasks to the chatbot
        saveTasks(items); // Save tasks to file before exiting
    }

    private static void ratchetCatBot(List<Task> items) {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm TheOrangeRatchetCat");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine(); // Reads a line of text
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                input = TheOrangeRatchetCat.checkList(input, items, scanner);
                continue;
            }
            if (input.startsWith("mark")) {
                String numberPart = input.substring(4).trim();
                input = TheOrangeRatchetCat.markingTask(numberPart, items, scanner);
                continue;
            }
            if (input.startsWith("unmark")) {
                String numberPart = input.substring(6).trim();
                input = TheOrangeRatchetCat.unmarkingTask(numberPart, items, scanner);
                continue;
            }
            if (input.startsWith("todo")) {
                try {
                    String taskDescription = input.substring(4).trim();
                    input = TheOrangeRatchetCat.addingToDo(taskDescription, items, scanner);
                    continue;
                } catch (TheOrangeRatchetCatException e) {
                    System.out.println(e.getMessage());
                    input = scanner.nextLine();
                    continue;
                }
            }
            if (input.startsWith("deadline")) {
                try {
                    input = TheOrangeRatchetCat.addingDeadline(input, items, scanner);
                    continue;
                } catch (TheOrangeRatchetCatException e) {
                    System.out.println(e.getMessage());
                    input = scanner.nextLine();
                    continue;
                }
            }
            if (input.startsWith("event")) {
                try {
                    input = TheOrangeRatchetCat.addingEvent(input, items, scanner);
                    continue;
                } catch (TheOrangeRatchetCatException e) {
                    System.out.println(e.getMessage());
                    input = scanner.nextLine();
                    continue;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Correct input format for adding event: event <Task> /from <input> /to <input>");
                    input = scanner.nextLine();
                    continue;
                }
            }
            if (input.startsWith("delete")) {
                int indexToDelete = Integer.parseInt(input.substring(6).trim());
                input = TheOrangeRatchetCat.deleteTask(indexToDelete, items, scanner);
                continue;
            }
            System.out.print("Inappropriate Command try again with adding either a Deadline/Todo/Event: ");
            input = scanner.nextLine(); // Reads the next line of input text again
        }
        TheOrangeRatchetCat.bidFarewell();
        scanner.close(); // Close the scanner to avoid resource leaks
    }

    private static void saveTasks(List<Task> items) {
        try {
            Path path = Paths.get(FILE_PATH);
            Files.createDirectories(path.getParent()); // Create directories if they don't exist
            // BufferedWriter is simply more efficient than FileWriter - speeds up the writing process
            BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH));
            for (Task task : items) {
                writer.write(task.toDataString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks: " + e.getMessage());
        }
    }

    private static void loadTasks(List<Task> items) {
        try {
            // Similar to BufferedWriter, BufferedReader allows reading of files to be more efficient
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH));
            String line; // To hold each line read from the file
            while ((line = reader.readLine()) != null) {
                // Gets a string array separate into different parts marked by '|'
                // Note: parts can be of different length depending on the length of the input being read
                String[] parts = line.split(" \\| ");
                String taskType = parts[0]; // Either "T"/"D"/"E"
                // Arrangement is as follows
                // <TaskType> <isDone> <TaskDescription> <fromDate> <toDate>
                boolean isDone = parts[1].equals("1");
                String description = parts[2];
                Task task = null;
                switch (taskType) {
                    case "T":
                        task = new ToDo(description);
                        break; // Continues with reading the next task in the next line
                    case "D":
                        String by = parts[3];
                        task = new Deadline(description, LocalDate.parse(by));
                        break;
                    case "E":
                        String from = parts[3];
                        String to = parts[4];
                        task = new Event(description, LocalDate.parse(from), LocalDate.parse(to));
                        break;
                }
                if (task != null) {
                    task.isDone = isDone;
                    items.add(task);
                }
            }
            reader.close();
        } catch (FileNotFoundException e) { // If FILE_PATH does not exist
            System.out.println("Data file not found, starting with an empty task list.");
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("The data file is corrupted, starting with an empty task list.");
            items.clear();
        }
    }

    private static String checkList(String input, List<Task> items, Scanner scanner) {
        int index = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task item : items) {
            System.out.println(index + "." + item);
            index++;
        }
        return scanner.nextLine();
    }

    private static String markingTask(String input, List<Task> items, Scanner scanner) {
        try {
            int taskIndex = Integer.parseInt(input) - 1;
            Task markingTask = items.get(taskIndex);
            markingTask.isDone = true;
            System.out.println("____________________________________________________________");
            System.out.println("Nice! I've marked this task as done:");
            System.out.println("[" + markingTask.getStatusIcon() + "] " + markingTask.description);
            System.out.println("____________________________________________________________");
            return scanner.nextLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Might want to reconsider your action. Please Try Again");
            return scanner.nextLine();
        } catch (NumberFormatException e) {
            System.out.println("Might want to reconsider your action. Please Try Again");
            return scanner.nextLine();
        }
    }

    private static String unmarkingTask(String input, List<Task> items, Scanner scanner) {
        try {
            int taskIndex = Integer.parseInt(input) - 1;
            Task markingTask = items.get(taskIndex);
            markingTask.isDone = false;
            System.out.println("____________________________________________________________");
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println("[" + markingTask.getStatusIcon() + "] " + markingTask.description);
            System.out.println("____________________________________________________________");
            return scanner.nextLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Might want to reconsider your action. Please Try Again");
            return scanner.nextLine();
        } catch (NumberFormatException e) {
            System.out.println("Might want to reconsider your action. Please Try Again");
            return scanner.nextLine();
        }
    }

    private static String deleteTask(int index, List<Task> items, Scanner scanner) {
        try {
            Task taskToDelete = items.get(index - 1);
            items.remove(index - 1);
            Task.taskCount--;
            System.out.println("____________________________________________________________");
            System.out.println("Noted. I've removed this task:");
            System.out.println(taskToDelete);
            System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
            System.out.println("____________________________________________________________");
            return scanner.nextLine();
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Might want to reconsider your action. Please Try Again");
            return scanner.nextLine();
        }
    }

    private static String addingToDo(String input, List<Task> items, Scanner scanner) throws TheOrangeRatchetCatException {
        if (input.isEmpty()) {
            throw new TheOrangeRatchetCatException("You can't do Nothing!");
        }
        Task nextTask = new ToDo(input);
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(nextTask);
        System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
        items.add(nextTask);
        return scanner.nextLine();
    }

    private static String addingDeadline(String input, List<Task> items, Scanner scanner) throws TheOrangeRatchetCatException {
        // Split the input string by "/by"
        String[] parts = input.split("/by");
        // The description is the first part after removing the word "deadline"
        String taskDescription = parts[0].replace("deadline", "").trim();
        if (taskDescription.isEmpty()) {
            throw new TheOrangeRatchetCatException("You can't do Nothing!");
        }
        // The "by" part is the second part, if it exists
        String date = parts.length > 1 ? parts[1].trim() : "";
        LocalDate dateBy;

        try { // Utilises LocalDate static method to parse input
            dateBy = LocalDate.parse(date); // Could throw a DateTimeParseException
            //System.out.println(dateBy);
        } catch (DateTimeParseException e) {
            System.out.println("The deadline follows a specific format - <YYYY>-<MM>-<DD>. Please Try Again!");
            return scanner.nextLine();
        }

        if (date.isEmpty()) {
            throw new TheOrangeRatchetCatException("You need to provide a deadline!");
        }
        // If task added successfully, the program will reach here!
        Task nextTask = new Deadline(taskDescription, dateBy);
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(nextTask);
        System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
        items.add(nextTask);
        return scanner.nextLine();
    }

    private static String addingEvent(String input, List<Task> items, Scanner scanner) throws TheOrangeRatchetCatException {
        // Split the input string by "/from"
        String[] parts = input.split("/from");
        // The taskDescription is the first part after removing the word "event"
        String taskDescription = parts[0].replace("event", "").trim();
        if (taskDescription.isEmpty()) {
            throw new TheOrangeRatchetCatException("You can't do Nothing!");
        }
        // Further split the remaining part by "/to"
        String[] dateParts = parts[1].split("/to");

        // The "fromDate" is the first part
        String fromDate = dateParts[0].trim();
        // The "toDate" is the second part
        String toDate = dateParts.length > 1 ? dateParts[1].trim() : "";
        LocalDate toLocalDate;
        LocalDate fromLocalDate;
        try { // Utilises LocalDate static method to parse input
            toLocalDate = LocalDate.parse(toDate); // Could throw a DateTimeParseException
            fromLocalDate = LocalDate.parse(fromDate);
        } catch (DateTimeParseException e) {
            System.out.println("The event follows a specific format - <YYYY>-<MM>-<DD>. Please Try Again!");
            return scanner.nextLine();
        }

        if (toDate.isEmpty()) {
            throw new TheOrangeRatchetCatException("You need to specify an end time!");
        }
        Task nextTask = new Event(taskDescription, fromLocalDate, toLocalDate);
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(nextTask);
        System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
        items.add(nextTask);
        return scanner.nextLine();
    }

    // Suppose to print out all activities associated with this date. To be implemented
    private static void activitiesOnThisDate(LocalDate date) {

    }

    private static void bidFarewell() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
