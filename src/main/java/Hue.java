import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
public class Hue {

    private static Storage storage;
    private static ArrayList<Task> tasks;

    public Hue(String filePath) {
        storage = new Storage(filePath);
        tasks = storage.loadTasks();
    }

    private static void saveTasks() {
        try {
            storage.saveTasks(tasks);
        } catch (IOException e) {
            System.out.println("Error saving tasks to file:" + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Hue heu = new Hue("./data/Hue.txt");
        String name = "Hue";


        System.out.println("____________________________________________________________" );
        System.out.println("Hello! I'm [" + name + "]");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________" );

        Scanner scanner = new Scanner(System.in);
        String input;

        do {
            input = scanner.nextLine();

            System.out.println("____________________________________________________________");
            if (input.equals("bye")) {
                break;
            }
            try {

                if (input.equalsIgnoreCase("list")) {
                    listTasks(tasks, tasks.size());
                } else if (input.startsWith("mark")) {
                   markTaskAsDone(input, tasks);
                } else if (input.startsWith("unmark")) {
                  unmarkTask(input, tasks);
                } else if (input.startsWith("todo")) {
                   addTodoTask(input, tasks);
                } else if (input.startsWith("deadline")) {
                   addDeadlineTask(input, tasks);
                } else if (input.startsWith("event")) {
                    addEventTask(input, tasks);
                } else if (input.startsWith("delete")) {
                    deleteTask(input, tasks);
                }else if (!input.equalsIgnoreCase("bye")) {
                   throw new HueException("I'm sorry, but I don't know what that means. Womp Womp :(");
                }
            } catch (HueException e) {
                System.out.print(e.getMessage());
            }
            System.out.println("____________________________________________________________");
        } while (!input.equalsIgnoreCase("bye"));

            System.out.println("Bye. Hope to see you again soon!");
            System.out.println("____________________________________________________________");
            scanner.close();


    }

    private static void listTasks(ArrayList<Task> tasks, int taskCount) {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }

    private static void markTaskAsDone(String input, ArrayList<Task> tasks) throws HueException {
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                tasks.get(taskIndex).markDone();
                saveTasks();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(" " + tasks.get(taskIndex));
            } else {
                throw new HueException("Task Number is out of range.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new HueException("Please give a valid task number to mark.");
        }
    }

    private static void unmarkTask(String input, ArrayList<Task> tasks) throws HueException {
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                tasks.get(taskIndex).unmarkDone();
                saveTasks();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(" " + tasks.get(taskIndex));
            } else {
                throw new HueException("Task Number is out of range.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new HueException("Please provide a valid task number to unmark.");
        }
    }

    private static void addTodoTask(String input, ArrayList<Task> tasks) throws HueException {
        try {
            String description = input.substring(5).trim();
            if (description.isEmpty()) {
                throw new HueException("The description of a todo cannot be empty.");
            }
            tasks.add(new Todo(description));
            saveTasks();
            System.out.println("Got it. I've added this task");
            System.out.println(" " + tasks.get(tasks.size() - 1));
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            throw new HueException("The description of a todo cannot be empty." );
        }
    }

    private static void addDeadlineTask(String input, ArrayList<Task> tasks) throws HueException {
        try {
            String[] parts = input.split("/by");
            String description = parts[0].substring(9).trim();
            String by = parts[1].trim();
            if (description.isEmpty() || by.isEmpty()) {
                throw new HueException("The description and deadline of a task cannot be empty.");
            }
            tasks.add(new Deadline(description, by));
            saveTasks();
            System.out.println("Got it. I've added this task");
            System.out.println(" " + tasks.get(tasks.size() - 1));
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            throw new HueException("Please provide both a description and a deadline for the task");
        }
    }

    private static void addEventTask(String input, ArrayList<Task> tasks) throws HueException {
        try {
            String[] parts = input.split ("/from|/to");
            String description = parts[0].substring(6).trim();
            String from = parts[1].trim();
            String to = parts[2].trim();
            if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
                throw new HueException("The description, start time and end time of an event cannot be empty.");
            }
            tasks.add(new Event(description, from, to));
            saveTasks();
            System.out.println("Got it. I've added this task");
            System.out.println(" " + tasks.get(tasks.size() - 1));
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }  catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
            throw new HueException("Pleae provide a description, start time and end time for the event.");
        }
    }

    private static void deleteTask(String input, ArrayList<Task> tasks) throws HueException {
        try {
            int taskIndex = Integer.parseInt(input.split(" ")[1]) - 1;
            if (taskIndex >= 0 && taskIndex < tasks.size()) {
                Task removedTask = tasks.remove(taskIndex);
                saveTasks();
                System.out.println("Noted. I've removed this task:");
                System.out.println(removedTask);
                System.out.println("Now you have " + tasks.size() + " tasks in the list");
            } else {
                throw new HueException("Task number is out of range.");
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new HueException("Please provide a valid task number to delete");
        }
    }

}
