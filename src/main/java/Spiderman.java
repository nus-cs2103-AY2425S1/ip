import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;  // Import the Scanner class
import java.util.ArrayList; // import the ArrayList class

public class Spiderman {
    public static void main(String[] args) {
        // Initialise scanner
        Scanner scan = new Scanner(System.in);  // Create a Scanner object

        // Initialise arrays for tasks
        //ArrayList<Task> taskList = new ArrayList<>();

        ArrayList<Task> taskList = loadTasksFromFile();

        // Greeting users
        System.out.println("Hello! This is your friendly neighbourhood Spiderman.");
        System.out.println("What can I do for you?");

        while (true) {
            // Get users input
            String input = scan.nextLine();
            String[] splitInput = input.split("\\s+");
            String[] splitInputByCommand = input.split("/");

            // User types bye; program terminates
            if (input.equals("bye")) {
                break;
            }

            // User types list; display list
            if (input.equals("list")) {
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println((i+1) + ". " + taskList.get(i).toString());
                }
                continue;
            }

            // User types delete; delete task
            if (splitInput[0].equals("delete")) {
                int number = Integer.parseInt(splitInput[1]) - 1;
                System.out.println("Alright! I will delete this task for you!");
                System.out.println(taskList.get(number).toString());

                taskList.remove(number);
                continue;
            }

            // User types mark; mark as done
            if (splitInput[0].equals("mark")) {
                int number = Integer.parseInt(splitInput[1]) - 1;
                taskList.get(number).markAsDone();
                System.out.println("Great! I've marked this task as done:");
                System.out.println(taskList.get(number).toString());
                continue;
            }

            // User types unmark; mark as not done
            if (splitInput[0].equals("unmark")) {
                int number = Integer.parseInt(splitInput[1]) - 1;
                taskList.get(number).markAsNotDone();
                System.out.println("OK, this task will be marked as not done yet:");
                System.out.println(taskList.get(number).toString());
                continue;
            }

            // Store users input into tasks
            // Check what kind of tasks to add
            if (splitInput[0].equals("deadline")) {
                String description = splitInputByCommand[0].replaceFirst("deadline", "").trim();
                if (description.isEmpty()) {
                    System.out.println("The description of a deadline cannot be empty.");
                    continue;
                }

                try {
                    String by = splitInputByCommand[1].replaceFirst("by", "").trim();
                    taskList.add(new Deadline(description, by));
                }
                catch (Exception e) {
                    System.out.println("The stated deadline should have a date");
                    continue;
                }
                System.out.println("Cool! I'll add this to your task list!");
                System.out.println("You now have " + taskList.size() + " tasks in your task list.");
            }
            else if (splitInput[0].equals("event")) {
                String description = splitInputByCommand[0].replaceFirst("event", "").trim();

                if (description.isEmpty()) {
                    System.out.println("The description of an event cannot be empty.");
                    continue;
                }

                try {
                    String from = splitInputByCommand[1].replaceFirst("from", "").trim();
                    String to = splitInputByCommand[2].replaceFirst("to", "").trim();
                    taskList.add(new Event(description, from, to));
                }
                catch (Exception e) {
                    System.out.println("The from and/or to cannot be empty");
                    continue;
                }
                System.out.println("Cool! I'll add this to your task list!");
                System.out.println("You now have " + taskList.size() + " tasks in your task list.");
            }
            else if (splitInput[0].equals("todo")) {
                String description = splitInputByCommand[0].replaceFirst("todo", "").trim();
                if (description.isEmpty()) {
                    System.out.println("The description of a todo cannot be empty.");
                    continue;
                }

                taskList.add(new Todo(description));
                System.out.println("Cool! I'll add this to your task list!");
                System.out.println("You now have " + taskList.size() + " tasks in your task list.");
            }
            else {
                System.out.println("Sorry, I do not understand what you mean. Check the README file for the list of known actions!");
            }
        }

        saveTasksToFile(taskList);

        // Exit program message
        System.out.println("Bye. Hope to see you again soon!");
        scan.close();
    }

    // Method to save tasks to file
    private static void saveTasksToFile(ArrayList<Task> taskList) {
        try (FileWriter writer = new FileWriter("tasks.txt")) {
            for (Task task : taskList) {
                writer.write(task.toTextFormat());
                writer.write("\n");
            }
            System.out.println("Tasks have been saved to tasks.txt.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks to file.");
        }
    }

    private static ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> taskList = new ArrayList<>();
        try {
            File myObj = new File("tasks.txt");
            Scanner scan = new Scanner(myObj);
            while (scan.hasNextLine()) {
                String data = scan.nextLine();
                String[] savedTasks = data.split("\\|");
                // If task is a todo
                if (savedTasks[0].equals("T")) {
                    Todo task = new Todo(savedTasks[2]);
                    if (savedTasks[1].equals("T")) {
                        task.markAsDone();
                    } else {
                        task.markAsNotDone();
                    }
                    taskList.add(task);
                }

                // If task is a deadline
                if (savedTasks[0].equals("D")) {
                    Deadline task = new Deadline(savedTasks[2], savedTasks[3]);
                    if (savedTasks[1].equals("T")) {
                        task.markAsDone();
                    } else {
                        task.markAsNotDone();
                    }
                    taskList.add(task);
                }

                // If task is an event
                if (savedTasks[0].equals("E")) {
                    Event task = new Event(savedTasks[2], savedTasks[3], savedTasks[4]);
                    if (savedTasks[1].equals("T")) {
                        task.markAsDone();
                    } else {
                        task.markAsNotDone();
                    }
                    taskList.add(task);
                }
            }
            scan.close();
        } catch (FileNotFoundException e) {
            System.out.println("There is no existing tasks.txt. Hence, an empty task list will be created!");
        }
        return taskList;
    }
}
