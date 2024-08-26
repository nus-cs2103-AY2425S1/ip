import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
public class ChatterBox {
    private static final String FILE_PATH = "./data/chatterbox.txt";

    public static void createFileIfNotExists(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                if (file.createNewFile()) {
                    System.out.println("File created: " + filePath);
                } else {
                    System.out.println("File already exists: " + filePath);
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + filePath);
                e.printStackTrace();
            }
        }
    }
    public static void saveTasks(ArrayList<Task> taskList, String filePath) {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for(Task task : taskList) {
                writer.write(task.toSaveFormat() + System.lineSeparator());
            }
        } catch(IOException e) {
            System.out.println("\t\t" + "_".repeat(50));
            System.out.println("\t\t" + "Error saving tasks to file.");
            System.out.println("\t\t" + "_".repeat(50));
        }
    }

    public static ArrayList<Task> readTasks(String filePath) {
        createFileIfNotExists(FILE_PATH);
        ArrayList<Task> taskList = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while((line = reader.readLine()) != null) {
                String[] component = line.split("\\|");
                String taskType = component[0].trim();
                boolean isDone = component[1].trim().equals("Done");
                String description = component[2];
                Task task = null;

                if(taskType.equalsIgnoreCase("ToDo")) {
                    task = new ToDo(description);
                } else if(taskType.equalsIgnoreCase("Event")) {
                    String startDate = component[3].trim();
                    String endDate = component[4].trim();
                    task = new Event(description, startDate, endDate);
                } else if(taskType.equalsIgnoreCase("Deadline")) {
                    String by = component[3].trim();
                    task = new Deadline(description, by);
                } else {
                        System.out.println("Unknown task type");
                }
                if (task != null && isDone) {
                    task.markAsDone();
                }
                taskList.add(task);
            }
        } catch(IOException e) {
            System.out.println("\t\t" + "_".repeat(50));
            System.out.println("\t\t" + "Error reading tasks from file.");
            System.out.println("\t\t" + "_".repeat(50));
        }
        return taskList;
    }

    public static void deleteTask(ArrayList<Task> taskList, int taskIndex) {
        taskList.remove(taskIndex);
    }
    public static void main(String[] args) {
        ArrayList<Task> taskList = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        //Greeting the user
        System.out.println("\t\t" + "_".repeat(50));
        System.out.println("\t\t" + "Hey there! I'm ChatterBox");
        System.out.println("\t\t" + "What's on your plate today?");
        System.out.println("\t\t" + "_".repeat(50));

        while (true) {
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("\t\t" + "_".repeat(50));
                System.out.println("\t\t" + "Take care! Looking forward to helping you again soon!");
                System.out.println("\t\t" + "_".repeat(50));
                break;
            } else if (input.equalsIgnoreCase("list")) {
                taskList = readTasks(FILE_PATH);
                System.out.println("\t\t" + "_".repeat(50));
                System.out.println("\t\t Here's what you've got on your to-do list so far:");
                for(int i = 0; i < taskList.size(); i++) {
                    System.out.println("\t\t\t- " + taskList.get(i));
                }
                System.out.println("\t\t" + "_".repeat(50));
            } else if (input.startsWith("mark")) {
                if(input.length() < 6 || !Character.isDigit(input.charAt(5))) {
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\t Command must be followed by a specific task number");
                    System.out.println("\t\t" + "_".repeat(50));
                }
                int indexSpace = input.indexOf(" ");
                int taskIndex = Integer.parseInt(input.substring(indexSpace + 1)) - 1;
                if (taskIndex >= 0 && taskIndex < taskList.size()) {
                    taskList.get(taskIndex).markAsDone();
                    saveTasks(taskList, FILE_PATH);
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\t Super! Task marked as done:");
                    System.out.println("\t\t\t" + taskList.get(taskIndex));
                    System.out.println("\t\t" + "_".repeat(50));
                } else {
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\t Invalid task number.");
                    System.out.println("\t\t" + "_".repeat(50));
                }
            } else if (input.startsWith("unmark")) {
                if(input.length() < 8 || !Character.isDigit(input.charAt(7))) {
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\t Command must be followed by a specific task number");
                    System.out.println("\t\t" + "_".repeat(50));
                }
                int indexSpace = input.indexOf(" ");
                int taskIndex = Integer.parseInt(input.substring(indexSpace + 1)) - 1;
                if (taskIndex >= 0 && taskIndex < taskList.size()) {
                    taskList.get(taskIndex).markAsNotDone();
                    saveTasks(taskList, FILE_PATH);
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\t Sure, task has been marked as not done:");
                    System.out.println("\t\t\t" + taskList.get(taskIndex));
                    System.out.println("\t\t" + "_".repeat(50));
                } else {
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\t Invalid task number.");
                    System.out.println("\t\t" + "_".repeat(50));
                }
            } else if(input.startsWith("todo")) {
                if (input.length() == 4) {
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\tOOPS!!! The description of a todo cannot be empty");
                    System.out.println("\t\t" + "_".repeat(50));
                } else {
                    String description = input.substring(5);
                    taskList.add(new ToDo(description));
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\t" + description + " is added to your list");
                    System.out.println("\t\t" + taskList.getLast());
                    System.out.println("\t\t" + "Now you have " + taskList.size() + " tasks in your list.");
                    System.out.println("\t\t" + "_".repeat(50));
                    saveTasks(taskList, FILE_PATH);
                }
            } else if(input.startsWith("deadline")) {
                if (!input.contains("/by")) {
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\t Deadline format should be: deadline DESCRIPTION /by DATE");
                    System.out.println("\t\t" + "_".repeat(50));
                    continue;
                }
                if (input.length() == 8) {
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\tOOPS!!! The description of a deadline cannot be empty");
                    System.out.println("\t\t" + "_".repeat(50));
                } else {
                    int index = input.indexOf("/");
                    String temp = input.substring(index + 1);
                    int tempIndex = input.indexOf("y");
                    String deadline = input.substring(tempIndex + 2);
                    String description = input.substring(9, index);
                    taskList.add(new Deadline(description, deadline));
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\t" + description + " is added to your list");
                    System.out.println("\t\t" + taskList.getLast());
                    System.out.println("\t\t" + "Now you have " + taskList.size() + " tasks in your list.");
                    System.out.println("\t\t" + "_".repeat(50));
                    saveTasks(taskList, FILE_PATH);
                }
            }  else if(input.startsWith("event")) {
                if (!input.contains("/from") || !input.contains("/to")) {
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\t event format should be: event DESCRIPTION /from DATE/to DATE");
                    System.out.println("\t\t" + "_".repeat(50));
                    continue;
                }
                if (input.length() == 5) {
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\tOOPS!!! The description of an event cannot be empty");
                    System.out.println("\t\t" + "_".repeat(50));
                } else {
                    int index = input.indexOf("/");
                    String description = input.substring(6, index);
                    String temp = input.substring(index + 1);
                    int index_2 = temp.indexOf("/");
                    int index_m = temp.indexOf("m");
                    String dateStart = temp.substring(index_m + 1, index_2);
                    String dateEnd = temp.substring(index_2 + 4);
                    taskList.add(new Event(description, dateStart, dateEnd));
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\t" + description + " is added to your list");
                    System.out.println("\t\t" +taskList.getLast());
                    System.out.println("\t\t" +"Now you have " + taskList.size() + " tasks in your list.");
                    System.out.println("\t\t" + "_".repeat(50));
                    saveTasks(taskList, FILE_PATH);
                }
            } else if (input.startsWith("delete")) {
                if (input.length() < 8 || !Character.isDigit(input.charAt(7))) {
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\t Command must be followed by a specific task number");
                    System.out.println("\t\t" + "_".repeat(50));
                }
                int indexSpace = input.indexOf(" ");
                int taskIndex = Integer.parseInt(input.substring(indexSpace + 1)) - 1;
                if (taskIndex >= 0 && taskIndex < taskList.size()) {
                    taskList.get(taskIndex).markAsNotDone();
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\t Noted. I've removed this task:");
                    System.out.println("\t\t\t" + taskList.get(taskIndex));
                    taskList.remove(taskIndex);
                    saveTasks(taskList, FILE_PATH);
                    System.out.println("\t\t Now you have " + taskList.size() + " tasks in the list");
                    System.out.println("\t\t" + "_".repeat(50));
                } else {
                    System.out.println("\t\t" + "_".repeat(50));
                    System.out.println("\t\t Invalid task number.");
                    System.out.println("\t\t" + "_".repeat(50));
                }
            } else {
                System.out.println("\t\t" + "_".repeat(50));
                System.out.println("\t\tOOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("\t\t" + "_".repeat(50));
            }
        }
    }
}