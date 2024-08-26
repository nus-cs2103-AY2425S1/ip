import java.util.ArrayList;
import java.util.Scanner;

public class Bopes {
    public static final String FILE_PATH = "data/tasks.txt";
    public static void main(String[] args) {
        String intro = "Bopes is a personal assistant that helps you manage your tasks.";
        System.out.println(intro);

        Scanner scanner = new Scanner(System.in);
        String input;

        TaskStorage storage = new TaskStorage(FILE_PATH);
        ArrayList<Task> tasks = storage.loadTasks();

         // Display tasks loaded from storage
        if (tasks.size() > 0) {
            System.out.println("Here are your tasks loaded from storage:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        } else {
            System.out.println("No tasks found in storage.");
        }

        while (true) {
            System.out.println("\nWhat can I do for you?");
            input = scanner.nextLine();

            // Exit 
            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Goodbye!");
                break;
            }

            try {
                // Input special commands
                if (input.equalsIgnoreCase("list")) {
                    System.out.println("Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i).toString());
                    }
                } else if (input.split(" ")[0].equalsIgnoreCase("mark")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markAsDone();
                        System.out.println("Marked task " + (index + 1) + " as done.");
                        storage.saveTasks(tasks);
                    } else {
                        throw BopesException.invalidIndex(tasks.size());
                    }
                } else if (input.split(" ")[0].equalsIgnoreCase("unmark")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markAsUndone();
                        System.out.println("Unmarked task " + (index + 1) + " as undone.");
                        storage.saveTasks(tasks);
                    } else {
                        throw BopesException.invalidIndex(tasks.size());
                    }
                } else if (input.split(" ")[0].equalsIgnoreCase("delete")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        System.out.println("Noted. I've removed this task:");
                        System.out.println(tasks.get(index).toString());
                        tasks.remove(index);
                        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                        storage.saveTasks(tasks);
                    } else {
                        throw BopesException.invalidIndex(tasks.size());
                    }
                }else {
                    // Adding of tasks
                    Task newTask = null;
                    if (input.startsWith("todo ")) {
                        newTask = new ToDo(input.substring(5));
                    } else if (input.startsWith("deadline ")) {
                        String[] temp = input.substring(9).split(" /by ");
                        if (temp.length == 2) {
                            newTask = new Deadline(temp[0], temp[1]);
                        } else {
                            throw BopesException.invalidDeadlineFormat();
                        }
                    } else if (input.startsWith("event ")) {
                        String[] temp = input.substring(6).split(" /from | /to ");
                        if (temp.length == 3) {
                            newTask = new Event(temp[0], temp[1], temp[2]);
                        } else {
                            throw BopesException.invalidEventFormat();
                        }
                    } else {
                        throw BopesException.unknownCommand();
                    }
                    tasks.add(newTask);
                    System.out.println("Got it. I've added this task:");
                    System.out.println(newTask.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    storage.saveTasks(tasks);
                }
            } catch (BopesException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Error: Something went wrong. Please try again.");
            }
        }

        scanner.close();
    }
}
