import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class YourHelperBuddy {
    /**
     * Get user's home directory
     */
    private static String home = System.getProperty("user.home");

    /**
     * Define the directory path in user's computer
     */
    private static String directoryPath = home + "/Documents/";

    /**
     * Define the file path in user's computer
     */
    private static String filePath = directoryPath + "TaskInfo.txt";

    public static void main(String[] args) {
        System.out.println("Hello! I'm YourHelperBuddy.");
        System.out.println("How may I assist you today?");
        System.out.println("________________________________________________");
        Scanner myObj = new Scanner(System.in);
        ArrayList<Task> taskList = new ArrayList<>();

        /**
         * Ensure the directory exists
         * If it does not exist, create a new directory
         */
        File directory = new File(directoryPath);
        if (!directory.exists()){
            if (directory.mkdirs()) {
                System.out.println("Directory created: " + directoryPath);
            }
            else {
                System.out.println("Failed to create directory: " + directoryPath);
                return;
            }
        }

        /**
         * Ensure the task info file exists
         * If it does not exist, create a new file
         */
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + filePath);
                }
                else {
                    System.out.println("Failed to create file: " + filePath);
                    return;
                }
            }
            catch (IOException e) {
                System.out.println("________________________________________________");
                System.out.println("An error occurred while creating the file.");
                System.out.println("________________________________________________");
                e.printStackTrace();
                return;
            }
        }
        loadTasksFromFile(taskList);
        while (true) {
            System.out.println("Enter your task: ");
            if (!myObj.hasNextLine()) {
                break;
            }
            String taskDescription = myObj.nextLine();
            if (taskDescription.equals("bye")) {
                break;
            }
            else if (taskDescription.equals("list")) {
                System.out.println("________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    Task currentTask = taskList.get(i);
                    System.out.println(" " + (i + 1) + ". " + currentTask);
                }
                System.out.println("________________________________________________");
            }
            else if (taskDescription.startsWith("delete")) {
                try {
                    int taskIndex = Integer.parseInt(taskDescription.split(" ")[1]) - 1;
                    if (taskIndex < 0 || taskIndex >= taskList.size()) {
                        throw new IndexOutOfBoundsException();
                    }
                    Task removedTask = taskList.remove(taskIndex);
                    System.out.println("________________________________________________");
                    System.out.println("Noted. I've removed this task:");
                    System.out.println("  " + removedTask);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println("________________________________________________");

                }
                catch (IndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("________________________________________________");
                    System.out.println("Sorry! There seems to be an issue with the task number you provided.");
                    System.out.println("________________________________________________");
                }
            }
            else if (taskDescription.startsWith("mark")) {
                try {
                    int taskLabel = Integer.parseInt(taskDescription.split(" ")[1]) - 1;
                    Task currentTask = taskList.get(taskLabel);
                    System.out.println("________________________________________________");
                    System.out.println("Nice! I've marked this task as done:");
                    currentTask.markDone();
                    System.out.println("  " + currentTask);
                    System.out.println("________________________________________________");
                }
                catch(Exception e) {
                    System.out.println("________________________________________________");
                    System.out.println("Oops! There seems to be an issue with the task number you provided.");
                    System.out.println("________________________________________________");
                }
            }
            else if (taskDescription.startsWith("unmark")) {
                try {
                    int taskLabel = Integer.parseInt(taskDescription.split(" ")[1]) - 1;
                    Task currentTask = taskList.get(taskLabel);
                    System.out.println("________________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:");
                    currentTask.markUndone();
                    System.out.println("  [" + currentTask);
                    System.out.println("________________________________________________");
                }
                catch (Exception e) {
                    System.out.println("________________________________________________");
                    System.out.println("Oops! There seems to be an issue with the task number you provided.");
                    System.out.println("________________________________________________");
                }
            }
            else if (taskDescription.startsWith("todo")) {
                String todoDescription = taskDescription.substring(5).trim();
                if (todoDescription.isEmpty()) {
                    System.out.println("________________________________________________");
                    System.out.println("Sorry! The description cannot be empty.");
                    System.out.println("________________________________________________");
                }
                else {
                    Task newTask = new ToDo(todoDescription);
                    taskList.add(newTask);
                    System.out.println("________________________________________________");
                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + newTask);
                    System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                    System.out.println("________________________________________________");
                }
            }
            else if (taskDescription.startsWith("deadline")) {
                try {
                    String[] parts = taskDescription.split(" /by ");
                    String deadlineDescription = parts[0].substring(9).trim();
                    String deadlineBy = parts[1].trim();
                    LocalDateTime deadlineDateTime = parseDateTime(deadlineBy);
                    if (deadlineDescription.isEmpty() || deadlineDateTime == null) {
                        System.out.println("________________________________________________");
                        System.out.println("Sorry! The description or the deadline cannot be empty.");
                        System.out.println("________________________________________________");
                    }
                    else {
                        Task newTask = new Deadline(deadlineDescription, deadlineDateTime);
                        taskList.add(newTask);
                        System.out.println("________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + newTask);
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                        System.out.println("________________________________________________");
                    }
                }
                catch (Exception e) {
                    System.out.println("________________________________________________");
                    System.out.println("Oops! The deadline command is not formatted correctly.");
                    System.out.println("Please use 'deadline <description> /by <date/time>'.");
                    System.out.println("________________________________________________");
                }
            }
            else if (taskDescription.startsWith("event")) {
                try {
                    String[] parts = taskDescription.split(" /from ");
                    String[] subParts = parts[1].split(" /to ");
                    String eventDescription = parts[0].substring(6).trim();
                    String eventFrom = subParts[0].trim();
                    String eventTo = subParts[1].trim();
                    LocalDateTime eventFromDateTime = parseDateTime(eventFrom);
                    LocalDateTime eventToDateTime = parseDateTime(eventTo);
                    if (eventDescription.isEmpty() || eventFromDateTime == null || eventToDateTime == null) {
                        System.out.println("________________________________________________");
                        System.out.println("Sorry! The description or the timing cannot be empty.");
                        System.out.println("________________________________________________");
                    }
                    else {
                        Task newTask = new Event(eventDescription, eventFromDateTime, eventToDateTime);
                        taskList.add(newTask);
                        System.out.println("________________________________________________");
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + newTask);
                        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                        System.out.println("________________________________________________");
                    }
                }
                catch (Exception e) {
                    System.out.println("________________________________________________");
                    System.out.println("Sorry! The event command is not formatted correctly.");
                    System.out.println("Please use 'event <description> /from <start> /to <end>'.");
                    System.out.println("________________________________________________");
                }
            }
            else {
                System.out.println("________________________________________________");
                System.out.println("Invalid command. Please use 'todo', 'deadline', 'event', 'delete'," +
                        " 'mark', 'unmark', 'list' or 'bye'. Thank you for understanding!");
                System.out.println("________________________________________________");
            }
        }
        saveTasksToFile(taskList);
        myObj.close();
        System.out.println("________________________________________________");
        System.out.println("Goodbye. Take care and see you again!");
        System.out.println("________________________________________________");
    }

    private static void loadTasksFromFile(ArrayList<Task> taskList) {
        File file = new File(filePath);
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("T")) {
                    ToDo task = ToDo.parseTask(line);
                    taskList.add(task);
                }
                else if (line.startsWith("D")) {
                    Deadline task = Deadline.parseTask(line);
                    taskList.add(task);
                }
                else if (line.startsWith("E")) {
                    Event task = Event.parseTask(line);
                    taskList.add(task);
                }
            }
        }
        catch (IOException e) {
            System.out.println("________________________________________________");
            System.out.println("An error occurred while loading tasks from file.");
            System.out.println("________________________________________________");
            e.printStackTrace();
        }
    }

    private static void saveTasksToFile(ArrayList<Task> taskList) {
        try {
            FileWriter writer = new FileWriter(filePath);
            for (Task task : taskList) {
                writer.write(task.toFileFormat() + System.lineSeparator());
            }
            writer.close();
        }
        catch (IOException e) {
            System.out.println("________________________________________________");
            System.out.println("An error occurred while saving tasks to file.");
            System.out.println("________________________________________________");
            e.printStackTrace();
        }
    }

    private static LocalDateTime parseDateTime(String dateTimeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
        try {
            return LocalDateTime.parse(dateTimeStr, formatter);
        }
        catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use dd/MM/yyyy HHmm.");
            return null;
        }
    }
}
