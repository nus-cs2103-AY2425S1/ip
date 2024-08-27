import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ShoAI {
    // Updated file path for data storage
    private static final String FILE_PATH = "src/main/data/ShoAI.txt";
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        // Load tasks from the file
        loadTasks();

        Scanner scanner = new Scanner(System.in);
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm ShoAI");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = scanner.nextLine();
            try {
                if (handleInput(input)) {
                    break;  // Exit the loop if the user says "bye"
                }
            } catch (ShoAIException e) {
                System.out.println("____________________________________________________________");
                System.out.println(e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }

        scanner.close();
    }

    private static boolean handleInput(String input) throws ShoAIException {
        String[] words = input.split(" ", 2);
        String command = words[0];

        switch (command) {
            case "bye":
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                return true; // Return true to exit the loop
            case "list":
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i));
                }
                System.out.println("____________________________________________________________");
                break;
            case "mark":
                if (words.length < 2) {
                    throw new ShoAIException("Please specify the task number to mark.");
                }
                int markIndex = Integer.parseInt(words[1]) - 1;
                if (markIndex < 0 || markIndex >= tasks.size()) {
                    throw new ShoAIException("Task number " + (markIndex + 1) + " does not exist.");
                }
                tasks.get(markIndex).markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(tasks.get(markIndex));
                System.out.println("____________________________________________________________");
                saveTasks(); // Save the updated tasks
                break;
            case "unmark":
                if (words.length < 2) {
                    throw new ShoAIException("Please specify the task number to unmark.");
                }
                int unmarkIndex = Integer.parseInt(words[1]) - 1;
                if (unmarkIndex < 0 || unmarkIndex >= tasks.size()) {
                    throw new ShoAIException("Task number " + (unmarkIndex + 1) + " does not exist.");
                }
                tasks.get(unmarkIndex).markAsNotDone();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(tasks.get(unmarkIndex));
                System.out.println("____________________________________________________________");
                saveTasks(); // Save the updated tasks
                break;
            case "todo":
                if (words.length < 2 || words[1].trim().isEmpty()) {
                    throw new ShoAIException("The description of a todo cannot be empty.");
                }
                tasks.add(new Todo(words[1]));
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(tasks.size() - 1));
                System.out.println("Now you have " + tasks.size() + " task" + (tasks.size() > 1 ? "s" : "") + " in the list.");
                System.out.println("____________________________________________________________");
                saveTasks(); // Save the updated tasks
                break;
            case "deadline":
                if (words.length < 2) {
                    throw new ShoAIException("The description of a deadline cannot be empty.");
                }
                String[] deadlineParts = words[1].split(" /by ");
                if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
                    throw new ShoAIException("The deadline description or date/time cannot be empty.");
                }
                tasks.add(new Deadline(deadlineParts[0], deadlineParts[1]));
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(tasks.size() - 1));
                System.out.println("Now you have " + tasks.size() + " task" + (tasks.size() > 1 ? "s" : "") + " in the list.");
                System.out.println("____________________________________________________________");
                saveTasks(); // Save the updated tasks
                break;
            case "event":
                if (words.length < 2) {
                    throw new ShoAIException("The description of an event cannot be empty.");
                }
                String[] eventParts = words[1].split(" /from ");
                if (eventParts.length < 2) {
                    throw new ShoAIException("The event description or start time cannot be empty.");
                }
                String[] timeParts = eventParts[1].split(" /to ");
                if (timeParts.length < 2 || eventParts[0].trim().isEmpty() || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
                    throw new ShoAIException("The event description, start time, or end time cannot be empty.");
                }
                tasks.add(new Event(eventParts[0], timeParts[0], timeParts[1]));
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:");
                System.out.println(tasks.get(tasks.size() - 1));
                System.out.println("Now you have " + tasks.size() + " task" + (tasks.size() > 1 ? "s" : "") + " in the list.");
                System.out.println("____________________________________________________________");
                saveTasks(); // Save the updated tasks
                break;
            case "delete":
                if (words.length < 2) {
                    throw new ShoAIException("Please specify the task number to delete.");
                }
                int deleteIndex = Integer.parseInt(words[1]) - 1;
                if (deleteIndex < 0 || deleteIndex >= tasks.size()) {
                    throw new ShoAIException("Task number " + (deleteIndex + 1) + " does not exist.");
                }
                Task removedTask = tasks.remove(deleteIndex);
                System.out.println("____________________________________________________________");
                System.out.println("Noted. I've removed this task:");
                System.out.println("  " + removedTask);
                System.out.println("Now you have " + tasks.size() + " task" + (tasks.size() > 1 ? "s" : "") + " in the list.");
                System.out.println("____________________________________________________________");
                saveTasks(); // Save the updated tasks
                break;
            default:
                throw new ShoAIException("Sorry, I don't understand that command.");
        }

        return false; // Continue the loop for other commands
    }

    private static void saveTasks() {
        File file = new File(FILE_PATH);
        file.getParentFile().mkdirs(); // Create the directory if it doesn't exist

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))) {
            for (Task task : tasks) {
                writer.write(taskToFileString(task));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    private static void loadTasks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return; // No file to load
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(fileStringToTask(line));
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        } catch (ShoAIException e) {
            System.out.println("Error in file format: " + e.getMessage());
        }
    }

    private static String taskToFileString(Task task) {
        StringBuilder sb = new StringBuilder();
        if (task instanceof Todo) {
            sb.append("T | ");
            sb.append(task.isDone ? "1" : "0");
            sb.append(" | ");
            sb.append(task.description);
        } else if (task instanceof Deadline) {
            sb.append("D | ");
            Deadline deadline = (Deadline) task;
            sb.append(deadline.isDone ? "1" : "0");
            sb.append(" | ");
            sb.append(deadline.description).append(" | ").append(deadline.by);
        } else if (task instanceof Event) {
            sb.append("E | ");
            Event event = (Event) task;
            sb.append(event.isDone ? "1" : "0");
            sb.append(" | ");
            sb.append(event.description).append(" /from ").append(event.from).append(" /to ").append(event.to);
        }
        return sb.toString();
    }

    private static Task fileStringToTask(String line) throws ShoAIException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new ShoAIException("Invalid task format in file.");
        }
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
            case "T":
                Todo todo = new Todo(description);
                todo.isDone = isDone;
                return todo;
            case "D":
                if (parts.length < 4) {
                    throw new ShoAIException("Invalid deadline format in file.");
                }
                Deadline deadline = new Deadline(description, parts[3]);
                deadline.isDone = isDone;
                return deadline;
            case "E":
                String[] eventParts = description.split(" /from ");
                if (eventParts.length < 2) {
                    throw new ShoAIException("Invalid event format in file.");
                }
                String[] timeParts = eventParts[1].split(" /to ");
                if (timeParts.length < 2) {
                    throw new ShoAIException("Invalid event time format in file.");
                }
                Event event = new Event(eventParts[0], timeParts[0], timeParts[1]);
                event.isDone = isDone;
                return event;
            default:
                throw new ShoAIException("Unknown task type in file.");
        }
    }
}
