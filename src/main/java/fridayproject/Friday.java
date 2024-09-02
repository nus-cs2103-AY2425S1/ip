package fridayproject;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * Represents the main class of the program.
 */
public class Friday {
    private static final String FILE_PATH = "data/friday.txt";

    /**
     * Saves the tasks to a file.
     * @param tasks
     * @throws IOException
     */
    public static void saveTasksToFile(ArrayList<Tasks> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        for (Tasks task : tasks) {
            fileWriter.write(task.toFileString() + "\n");
        }
        fileWriter.close();
    }

    /**
     * Loads the tasks from a file.
     * @return
     * @throws IOException
     */
    public static ArrayList<Tasks> loadTasksFromFile() throws IOException {
        File file = new File(FILE_PATH);
        ArrayList<Tasks> tasks = new ArrayList<>();
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
            return tasks;
        }

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String taskString = scanner.nextLine();
            String[] parts = taskString.split(" \\| ");
            try {
                switch (parts[0]) {
                case "T":
                    if (parts.length >= 3) {
                        Tasks todo = new Todo(parts[2]);
                        if(parts[1].equals("1")) {
                            todo.markAsDone();
                        }
                        tasks.add(todo);
                    } else {
                        System.out.println("Error loading task: " + taskString);
                    }
                    break;
                case "D":
                    if (parts.length >= 4) {
                        Tasks deadline = new Deadline(parts[2], parts[3]);
                        if (parts[1].equals("1")) {
                            deadline.markAsDone();
                        }
                        tasks.add(deadline);
                    } else {
                        System.out.println("Error loading task: " + taskString);    
                    }
                    break;
                case "E":
                    if (parts.length >= 5) {
                        Tasks event = new Event(parts[2], parts[3], parts[4]);
                        if (parts[1].equals("1")) {
                            event.markAsDone();
                        }
                        tasks.add(event);
                    } else {
                        System.out.println("Error loading task: " + taskString);
                    }
                    break;
                default:
                    System.out.println("Error loading task: " + taskString);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Error loading task: " + taskString);
            }
        }
        scanner.close();
        return tasks;
    }

    /**
     * Main method to run the program.
     * @param args
     * @throws FridayException
     * @throws IOException 
     */
    public static void main(String[] args) throws FridayException, IOException {
        String greeting = "Hello! I'm Friday\nWhat can I do for you?\n";
        String farewell = "Bye. Hope to see you again soon!";
        ArrayList<Tasks> tasks = new ArrayList<>();
        Scanner scanner = new Scanner(System.in); 
        String line = "_________________________________________";
        System.out.println(greeting + line);

        try {
            tasks = loadTasksFromFile();
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
            tasks = new ArrayList<>();
        }
        
        while (true) {
            String inputString = scanner.nextLine().trim();
            System.out.println(line);
            try {
                System.out.println(line);
                if (inputString.equals("bye")) {
                    System.out.println(farewell);
                    System.out.println(line);
                    break;
                } else if (inputString.startsWith("todo ")) {
                    if (inputString.length() < 6) {
                        throw new FridayException("I'm sorry, but I don't know what that means :(((\n Please enter a valid task description.");
                    }
                    Tasks todo = new Todo(inputString.substring(5)); 
                    tasks.add(todo);
                    saveTasksToFile(tasks);
                    System.out.println("Got it. I've added this task:\n  " + todo.getTypeIcon() 
                    + todo.toString() + "\nNow you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                } else if (inputString.startsWith("deadline")) {
                    if (inputString.length() < 10) {
                        throw new FridayException("I'm sorry, but I don't know what that means :(((\n Please enter a valid task description.");
                    }
                    String remainingInput = inputString.substring(inputString.indexOf(" ") + 1);
                    String[] deadlineParts = remainingInput.split(" /by ");
                    Tasks deadline = new Deadline(deadlineParts[0] , deadlineParts[1]);
                    tasks.add(deadline);
                    saveTasksToFile(tasks);
                    System.out.println("Got it. I've added this task:\n  " + deadline.getTypeIcon() 
                    + deadline.toString() + "\nNow you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                } else if (inputString.startsWith("event")) {
                    if (inputString.length() < 7) {
                        throw new FridayException("I'm sorry, but I don't know what that means :(((\n Please enter a valid task description.");
                    }
                    String[] eventParts = inputString.substring(6).split(" /from | /to ");
                    Tasks event = new Event(eventParts[0], eventParts[1], eventParts[2]);
                    tasks.add(event);
                    saveTasksToFile(tasks);
                    System.out.println("Got it. I've added this task:\n  " + event.getTypeIcon() 
                    + event.toString() + "\nNow you have " + tasks.size() + " tasks in the list.");
                    System.out.println(line);
                } else if (inputString.equals("list")) {
                    System.out.println("Here are the tasks in your list: ");
                    for (int i = 0; i < tasks.size(); i++) {
                        if (tasks.get(i) != null) {
                            System.out.println((i + 1) + ". " + tasks.get(i).getTypeIcon() + tasks.get(i));
                        }
                    }
                    System.out.println(line);
                } else if (inputString.startsWith("mark ")) {
                    int taskNum = Integer.parseInt(inputString.substring(5)) - 1;
                    if (taskNum >= 0 && taskNum < tasks.size() && tasks.get(taskNum) != null) {
                        tasks.get(taskNum).markAsDone();
                        saveTasksToFile(tasks);
                        System.out.println("Nice! I've marked this task as done:\n  [X] " + tasks.get(taskNum).description);
                    }
                    System.out.println(line);
                } else if (inputString.startsWith("unmark ")) {
                    int taskNum = Integer.parseInt(inputString.substring(7)) - 1;
                    if (taskNum >= 0 && taskNum < tasks.size() && tasks.get(taskNum) != null) {
                        tasks.get(taskNum).markAsUndone();
                        saveTasksToFile(tasks);
                        System.out.println("OK, I've marked this task as not done yet:\n  [ ] " + tasks.get(taskNum).description);
                    }
                    System.out.println(line);
                } else if (inputString.startsWith("delete")) {
                    int taskNum = Integer.parseInt(inputString.substring(7)) - 1;
                    if (taskNum >= 0 && taskNum < tasks.size() && tasks.get(taskNum) != null) {
                        System.out.println("Noted. I've removed this task:\n  " + tasks.get(taskNum).getTypeIcon() + tasks.get(taskNum));
                        tasks.remove(taskNum);
                        saveTasksToFile(tasks);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    }
                    System.out.println(line);
                } else {
                    throw new FridayException("I'm sorry, but I don't know what that means :(((\n Please enter a valid task description.");
                }
                System.out.println(line);
            } catch (FridayException e) {
                System.out.println(e.getMessage());
                System.out.println(line);
            }
        }
        scanner.close();
    }
}