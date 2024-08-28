import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.io.FileWriter;

public class XBot {
    private static List<Task> list = new ArrayList<>();
    private static final Path DATA_PATH = Paths.get("data", "XBot.txt");
    public static void main(String[] args) {
        try {
            loadTask();
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Hello! I'm XBot\n" + "What can I do for you?");
        String input = scanner.nextLine().trim();
        while(!input.equalsIgnoreCase("bye")) {
            try {
                processInput(input);
            } catch (XBotException e) {
                System.out.println("Oh No!! " + e.getMessage());
            }
            input = scanner.nextLine().trim();
        }
        System.out.println("Bye. Hope to see you again soon!");
        scanner.close();
    }

    public static void loadTask() throws IOException {
        if (Files.exists(DATA_PATH)) {
            //Add all task in data/XBot.txt to the list
            try (Scanner scanner = new Scanner(DATA_PATH.toFile())) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(" \\| ");
                    if (parts.length >= 3) {
                        String type = parts[0].trim();
                        boolean isDone = parts[1].trim().equals("1");
                        String description = parts[2].trim();
                        switch (type) {
                            case "T":
                                Task todo = new ToDo(description);
                                if (isDone) todo.setIsDone();
                                list.add(todo);
                                break;
                            case "D":
                                String deadline = parts[3].trim();
                                Task deadlineTask = new Deadline(description, deadline);
                                if (isDone) deadlineTask.setIsDone();
                                list.add(deadlineTask);
                                break;
                            case "E":
                                String from = parts[3].trim();
                                String to = parts[4].trim();
                                Task eventTask = new Event(description, from, to);
                                if (isDone) eventTask.setIsDone();
                                list.add(eventTask);
                                break;
                            default:
                                System.out.println("Unknown task type: " + type);
                        }
                    }
                    // Depending on format, create tasks and add to list
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + e.getMessage());
                throw new IOException("File not found", e);
            }
        } else {
            addFile();
        }
    }

    public static void saveTask() {
        //Save all task to XBot.txt
        try (FileWriter writer = new FileWriter(DATA_PATH.toFile())) {
            for (Task task : list) {
                writer.write(taskToFileString(task) + System.lineSeparator());
            }

        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public static String taskToFileString(Task task) {
        StringBuilder sb = new StringBuilder();
        sb.append(task.type + " | ");
        sb.append(task.isDone() ? "1 | " : "0 | ");
        sb.append(task.description);
        if (task instanceof Deadline) {
            sb.append(" | ").append(((Deadline) task).by);
        } else if (task instanceof Event) {
            sb.append(" | ").append(((Event) task).from)
                    .append(" | ").append(((Event) task).to);
        }
        return sb.toString();
    }
    /*
        To add a storage File to store the memory if the file do no exist
    */
    public static void addFile() {
        Path directoryPath = Paths.get("./data");
        Path filePath = directoryPath.resolve("XBot.txt");

        try {
            // Check if the directory exists, and create it if it doesn't
            if (!Files.exists(directoryPath)) {
                Files.createDirectory(directoryPath);
            }

            // Check if the file exists, and create it if it doesn't
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void processInput(String input) throws XBotException {
        String[] words = input.split("\\s+", 2);
        String command = words[0].toLowerCase();
        String rest = words.length > 1 ? words[1] : "";
        switch(command) {
            case "list":
                displayTask();
                break;
            case "mark":
                markDone(rest);
                break;
            case "unmark":
                markUndone(rest);
                break;
            case "todo":
                if (rest.isEmpty()) {
                    throw new XBotException("The description of the todo cannot be empty!");
                }
                addTodo(rest);
                break;
            case "event":
                if (rest.isEmpty()) {
                    throw new XBotException("The description of the event cannot be empty!");
                }
                addEvent(rest);
                break;
            case "deadline":
                if (rest.isEmpty()) {
                    throw new XBotException("The description of the deadline cannot be empty!");
                }
                addDeadline(rest);
                break;
            case "delete":
                if (rest.isEmpty()) {
                    throw new XBotException("The task number to be deleted cannot be empty!");
                }
                deleteTask(rest);
                break;
            default:
                throw new XBotException("I'm sorry, but I don't know what that means :-(");
        }
    }

    public static void displayTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < list.size(); i++) {
            int index = i + 1;
            System.out.println(index + ". " + list.get(i).toString());
        }
    }

    public static void deleteTask(String rest) throws XBotException {
        try {
            int taskNumber = Integer.parseInt(rest.trim());
            if (taskNumber > 0 && taskNumber <= list.size()) {
                System.out.println("Noted. I've removed this task:");
                System.out.print(list.get(taskNumber).toString() + "\n");
                list.remove(taskNumber);
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                saveTask();
            } else {
                throw new XBotException("This task number do not exist.");
            }
        } catch (NumberFormatException e) {
            throw new XBotException("Invalid task number!");
        }
    }

    public static void addTodo(String rest) {
        System.out.println("Got it. I've added this task:");
        Task newTask = new ToDo(rest);
        list.add(newTask);
        System.out.println(newTask.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
        saveTask();
    }
    public static void addDeadline(String rest) throws XBotException {
        String[] parts = rest.split("/by", 2);
        if (parts.length == 2) {
            System.out.println("Got it. I've added this task:");
            String taskDescription = parts[0].trim();
            String deadline = parts[1].trim();
            Task newTask = new Deadline(taskDescription, deadline);
            list.add(newTask);
            System.out.println(newTask.toString());
            System.out.println("Now you have " + list.size() + " tasks in the list.");
            saveTask();
        } else {
            throw new XBotException("Invalid input format. Please use the format: 'deadline <task> /by <date>'");
        }
    }

    public static void addEvent(String rest) throws XBotException{
        String[] parts = rest.split("/from", 2);
        if (parts.length == 2) {
            System.out.println("Got it. I've added this task:");
            String taskDescription = parts[0].trim();
            String time = parts[1].trim();
            String[] timeParts = time.split("/to", 2);
            if (timeParts.length == 2) {
                String from = timeParts[0].trim();
                String to = timeParts[1].trim();
                Task newTask = new Event(taskDescription, from, to);
                list.add(newTask);
                System.out.println(newTask.toString());
                System.out.println("Now you have " + list.size() + " tasks in the list.");
                saveTask();
            }
        } else {
            throw new XBotException("Invalid input format. Please use the format: 'event <task> /from <start time> /to <end time>'");
        }
    }


    public static void markDone(String rest) throws XBotException {
        try {
            int taskNumber = Integer.parseInt(rest.trim());
            if (taskNumber > 0 && taskNumber <= list.size()) {
                list.get(taskNumber - 1).markAsDone();
                saveTask();
            } else {
                throw new XBotException("This task number do not exist.");
            }
        } catch (NumberFormatException e) {
            throw new XBotException("Invalid task number!");
        }
    }

    public static void markUndone(String rest) throws XBotException {
        try {
            int taskNumber = Integer.parseInt(rest.trim());
            if (taskNumber > 0 && taskNumber <= list.size()) {
                list.get(taskNumber - 1).markAsUndone();
                saveTask();
            } else {
                throw new XBotException("This task number do not exist.");
            }
        } catch (NumberFormatException e) {
            throw new XBotException("Invalid task number!");
        }
    }
}
