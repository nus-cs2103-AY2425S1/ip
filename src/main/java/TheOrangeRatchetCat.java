import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.io.*;
import java.nio.file.*;

enum CommandType {
    LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE;
}

public class TheOrangeRatchetCat {

    private static final String FILE_PATH = "./data/OrangeCat.txt";
    public static void main(String[] args) {
        // The ChatBot named OrangeRatchetCat
        //ratchetCatBot();

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

    // Method to load tasks from file
    private static void loadTasks(List<Task> items) {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("Data file does not exist. A new file will be created.");
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length >= 3) {
                    String type = parts[0].trim();
                    boolean isDone = parts[1].trim().equals("1");
                    String description = parts[2].trim();

                    Task task;
                    if (type.equals("T")) {
                        task = new ToDo(description);
                    } else if (type.equals("D")) {
                        task = new Deadline(description, parts.length > 3 ? parts[3].trim() : "");
                    } else if (type.equals("E")) {
                        task = new Event(description, parts.length > 3 ? parts[3].trim() : "", parts.length > 4 ? parts[4].trim() : "");
                    } else {
                        continue; // Unknown task type
                    }

                    task.isDone = isDone;
                    items.add(task);
                    //Task.taskCount++; // How did this introduce the bug, please find out!
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading the data file: " + e.getMessage());
        }
    }

    // Method to save tasks to file
    private static void saveTasks(List<Task> items) {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs(); // Ensure that the parent directory exists

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (Task item : items) {
                String taskType = "";
                if (item instanceof ToDo) {
                    taskType = "T";
                } else if (item instanceof Deadline) {
                    taskType = "D";
                } else if (item instanceof Event) {
                    taskType = "E";
                }

                String isDone = item.isDone ? "1" : "0";
                String description = item.description;
                String extraInfo = "";

                if (item instanceof Deadline) {
                    extraInfo = ((Deadline) item).by;
                } else if (item instanceof Event) {
                    Event event = (Event) item;
                    extraInfo = event.fromDuration + " | " + event.toDuration;
                }

                bw.write(String.format("%s | %s | %s %s%n", taskType, isDone, description, extraInfo));
            }
        } catch (IOException e) {
            System.out.println("Error writing to the data file: " + e.getMessage());
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
        if (date.isEmpty()) {
            throw new TheOrangeRatchetCatException("You need to provide a deadline!");
        }
        Task nextTask = new Deadline(taskDescription, date);
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
        if (toDate.isEmpty()) {
            throw new TheOrangeRatchetCatException("You need to specify an end time!");
        }
        Task nextTask = new Event(taskDescription, fromDate, toDate);
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(nextTask);
        System.out.println("Now you have " + Task.taskCount + " tasks in the list.");
        System.out.println("____________________________________________________________");
        items.add(nextTask);
        return scanner.nextLine();
    }

    private static void bidFarewell() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }
}
